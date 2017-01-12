package myCloud.work;

import myCloud.common.msg.MyMsg;
import myCloud.common.msgque.MsgQueProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/12.
 */
public class WorkerMsgQueProcess extends MsgQueProcess {
    private final static Logger log = LogManager.getLogger(WorkerMsgQueProcess.class);
    public WorkerMsgQueProcess() {
        super(WorkMsgQue.instance);
    }
    @Override
    protected boolean process(MyMsg myMsg) {
        log.info("WorkerMsgQueProcess: {}", myMsg);
        return true;
    }
}
