package myCloud.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import myCloud.common.msg.MyMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by sutaiyun on 2017/1/10.
 */
public class HttpClient {
    private static final Logger log = LogManager.getLogger(HttpClient.class);

    public void connect(String host, int port, String msg) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientInboundHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            String uriString = "http://" + host + ":" + port;
            //URI uri = new URI("http://127.0.0.1:8844");
            URI uri = new URI(uriString);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

            // 构建http请求
            request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
            // 发送http请求
            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();  //block ??????
        } catch (Exception e) {
            log.error("HttpClient connect ERROR: {}", e);
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    private class HttpClientInboundHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpResponse)
            {
                HttpResponse response = (HttpResponse) msg;
                log.info("HttpClientInboundHandler: CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
            }
            if(msg instanceof HttpContent)
            {
                HttpContent content = (HttpContent)msg;
                ByteBuf buf = content.content();
                log.info("HttpClientInboundHandler contend: " + buf.toString(io.netty.util.CharsetUtil.UTF_8));
                buf.release();
            }
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("messageReceived: XXXXXXXX XXXXXKXXX XXXXXXXX XXXXXXXX");
            if (msg instanceof FullHttpResponse) {
                log.info("req.headers:{}", ((FullHttpRequest) msg).headers());
                log.info("req.contends:{}", ((FullHttpRequest) msg).content());

            } else  {
                log.error("HttpFileServerHandler.messageReceived are error: ctx:{} msg:{}", ctx, msg);
            }
        }
    }
}
