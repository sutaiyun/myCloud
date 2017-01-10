package myCloud.work;

import myCloud.common.msgque.MsgQue;
import myCloud.common.msgque.MsgQueProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/10.
 */
public class WorkMsgQueProcess extends MsgQueProcess {
    public static final Logger logger = LogManager.getLogger(WorkMsgQueProcess.class);

    public WorkMsgQueProcess() {
        log.info("WorkMsgQueProcess start ......");
        this.msgQue = WorkMsgQue.instance;
    }
}
