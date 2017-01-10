package myCloud.common.msgque;

import myCloud.common.msg.MyMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by sutaiyun on 2017/1/8.
 */
public class MsgQue {
    public static final Logger log = LogManager.getLogger(MsgQue.class);
    private LinkedTransferQueue<MyMsg> linkedTransferQueue = new LinkedTransferQueue<MyMsg>();
    private int MAX_MSGQUE_SIZE = 10000;
    private String msgQueName = "MsgQue";

    public void MsgQue(String msgQueName) {
       this.msgQueName = msgQueName;
    }

    public String getMsgQueName() {
        return this.msgQueName;
    }

    public void setMsgQueName(String msgQueName) {
        this.msgQueName = msgQueName;
    }

    public int size() {
        return this.linkedTransferQueue.size();
    }

    public boolean add(MyMsg msg) {
        if (linkedTransferQueue.size() > MAX_MSGQUE_SIZE) {
            log.error("MsgQue Error: {} > MAX_MSGQUE_SIZE {}", linkedTransferQueue.size(), MAX_MSGQUE_SIZE);
            return false;
        }

        return linkedTransferQueue.add(msg);
    }

    /* take是阻塞操作 */
    public MyMsg take() throws InterruptedException {
        return linkedTransferQueue.take();
    }

    /* drainTo是非阻塞，批量获取操作 */
    public int drainTo(Collection<MyMsg> c, int maxMsgNum) {
        return linkedTransferQueue.drainTo(c, maxMsgNum);
    }

    public boolean isBusy() {
        return linkedTransferQueue.size() >= MAX_MSGQUE_SIZE;
    }

    /*
    现在还没有使用 transfer等高级特性
     */
}
