package myCloud.work.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import myCloud.common.msg.MyMsg;
import myCloud.common.msg.MyRequestMsg;
import myCloud.common.msg.MyResponseMsg;
import myCloud.work.WorkMsgQue;
import myCloud.work.WorkSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by sutaiyun on 2017/1/10.
 */
public class WorkHttpService {
    private int port = 8080;
    private int MAX_ACCEPT_THREAD = 2;
    private int MAX_WORK_THREAD = 4;
    private static final Logger log = LogManager.getLogger(WorkHttpService.class);

    public void start() throws Exception {
        EventLoopGroup acceptGroup = new NioEventLoopGroup(MAX_ACCEPT_THREAD);
        EventLoopGroup workGroup = new NioEventLoopGroup(MAX_WORK_THREAD);

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(acceptGroup, workGroup)
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new WorkHttpHandler());

            Channel serverChannel = server.bind(port).sync().channel();
            log.info("WorkHttpService start ........ port {}", port);

            serverChannel.closeFuture().sync();
            log.info("WorkHttpService start1 ........ port {}", port);
        } finally {
            acceptGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.info("WorkHttpService goodby !!!!!!!!!!!!  port {}", port);
        }
    }

    public class WorkHttpHandler extends ChannelInitializer<SocketChannel> {
        @Override
        public void initChannel(SocketChannel socketChannel) {
            ChannelPipeline pipeline = socketChannel.pipeline();

            pipeline.addLast("http-decoder", new HttpRequestDecoder());

            //把多个消息转化成一个消息(FullHttpRequest或者FullHttpResponse),原因是HTTP解码器在每个HTTP消息中会生成多个消息对象。
            pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));

            pipeline.addLast("http-encoder", new HttpResponseEncoder());

            //支持处理异步发送大数据文件，但不占用过多的内存，防止发生内存泄漏
            pipeline.addLast("http-chunked", new ChunkedWriteHandler());

            //这个是我们自定义的，处理文件服务器逻辑。主要功能还是在这个文件中
            //pipeline.addLast("http-fileServerHandler", new HttpFileServerHandler("/fortest"));
            pipeline.addLast("SimpleHttpServer", new SimpleHttpServerHandler());

            log.info("WorkHttpHandler.initChannel are OK!!!!!!");
        }
    }

    private class SimpleHttpServerHandler extends SimpleChannelInboundHandler<Object> {
        HttpRequest request;

        @Override
        public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("messageReceived: XXXXXXXX XXXXXKXXX XXXXXXXX XXXXXXXX");
            if (msg instanceof FullHttpRequest) {
                log.info("req.headers:{}", ((FullHttpRequest) msg).headers());
                log.info("req.contends:{}", ((FullHttpRequest) msg).content());
                ByteBuf buf = ((FullHttpRequest)msg).content();
                log.info("req.contents1: {}", buf.toString(io.netty.util.CharsetUtil.UTF_8));
                //buf.release(); //can't release buf, it's content
                String resMsg = "Msg Process Error!";
                int msgType = buf.readByte() - '0';
                if (msgType == MyMsg.REQUEST_TYPE) {
                    MyRequestMsg myRequestMsg = new MyRequestMsg();
                    myRequestMsg.decode(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                    if (WorkMsgQue.instance.add(myRequestMsg) == true)
                    {
                        resMsg = "RequestMsg Process OK!";
                    }
                } else if (msgType == MyMsg.RESPONSE_TYPE) {
                    MyResponseMsg myResponseMsg = new MyResponseMsg();
                    myResponseMsg.decode(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                    if (WorkMsgQue.instance.add(myResponseMsg) == true)
                    {
                        resMsg = "ResponseMsg Process OK!";
                    }
                }


                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                        OK, Unpooled.wrappedBuffer(resMsg.getBytes("UTF-8")));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH,
                        response.content().readableBytes());
                if (HttpHeaders.isKeepAlive((HttpRequest)msg)) {
                    response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                }
                ctx.write(response);
                ctx.flush();
            } else  {
                log.error("HttpFileServerHandler.messageReceived are error: ctx:{} msg:{}", ctx, msg);
            }
            /*
            if (msg instanceof HttpRequest) {
                request = (HttpRequest) msg;

                String uri = request.getUri();
                log.info("channelRead: Uri:" + uri);
            }
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = content.content();
                log.info(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                buf.release();

                String res = "This is messageReceived!!!!";
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                        OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH,
                        response.content().readableBytes());
                if (HttpHeaders.isKeepAlive(request)) {
                    response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                }
                ctx.write(response);
                ctx.flush();
            }
            */
        }
/*
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            log.info("channelRead: ******** ******** ******** ********");
            if (msg instanceof HttpRequest) {
                request = (HttpRequest) msg;

                String uri = request.getUri();
                log.info("channelRead: Uri:" + uri);
            }
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = content.content();
                log.info(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                buf.release();

                String res = "This is ChannelRead!!!!";
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                        OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH,
                        response.content().readableBytes());
                if (HttpHeaders.isKeepAlive(request)) {
                    response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                }
                ctx.write(response);
                ctx.flush();
            }
        }
        */
    }

    private class HttpFileServerHandler extends SimpleChannelInboundHandler<Object> {
        public HttpFileServerHandler(String url) {
            log.info("HttpFileServerHandler are:{}", url);
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof FullHttpRequest) {
                log.info("req.headers:{}", ((FullHttpRequest) msg).headers());
                log.info("req.contends:{}", ((FullHttpRequest) msg).content());

                String resMsg = "This is test!!!!!!";
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                        OK, Unpooled.wrappedBuffer(resMsg.getBytes("UTF-8")));
                response.headers().set(CONTENT_TYPE, "text/plain");
                response.headers().set(CONTENT_LENGTH,
                        response.content().readableBytes());
                if (HttpHeaders.isKeepAlive((HttpRequest)msg)) {
                    response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                }
                ctx.write(response);
                ctx.flush();
            } else  {
                log.error("HttpFileServerHandler.messageReceived are error: ctx:{} msg:{}", ctx, msg);
            }
        }
    }
}
