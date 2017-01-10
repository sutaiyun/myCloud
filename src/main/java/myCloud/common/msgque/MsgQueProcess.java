package myCloud.common.msgque;

import myCloud.common.msg.MyMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sutaiyun on 2017/1/9.
 */

public class MsgQueProcess {
    public static final Logger log = LogManager.getLogger(MsgQueProcess.class);
    protected MsgQue msgQue = null;

    /*
    public MsgQueProcess(MsgQue msgQue) {
        this.msgQue = msgQue;
    }
    */

    public int start() throws Exception {
        int threadNum = Runtime.getRuntime().availableProcessors() * 2;

        log.info("msgQue.name are {}", msgQue.getMsgQueName());

        for(int i = 0; i < threadNum; i++){
            Runnable runner = new MsgQueProcessRunnable();
            Thread thread = new Thread(runner);
            thread.setName(msgQue.getMsgQueName() + "Runner-" + i);
            thread.start();
        }
        return threadNum;
    }

    private class MsgQueProcessRunnable implements Runnable {
        private int MAX_PROCESS_MSQ_NUM_PER = 100;

        public void run() {
            log.info("thread() are run ......", Thread.currentThread().getName());
            while (true) {
                try {
                    List<MyMsg> msgList = new ArrayList<MyMsg>();
                    msgList.add(msgQue.take());
                    msgQue.drainTo(msgList, MAX_PROCESS_MSQ_NUM_PER);

                    if (msgQue.isBusy()) {
                        log.error("MsgQue {} are busy:", msgQue.getMsgQueName());
                        continue;
                    }
                    Iterator<MyMsg> iter = msgList.iterator();
                    while (iter.hasNext()) {
                        final MyMsg msg = (MyMsg) iter.next();
                        process(msg);
                    }
                } catch (Throwable thr) {
                    log.error(thr);
                }
            }
        }
    }

    private void process(MyMsg msg) {
        log.info("msg:{}", msg);
    }
}
