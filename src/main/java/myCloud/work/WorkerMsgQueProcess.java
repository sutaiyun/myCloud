package myCloud.work;

import myCloud.common.msg.MyMsg;
import myCloud.common.msg.MyRequestMsg;
import myCloud.common.msg.MyResponseMsg;
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
        if (myMsg.getMsgType() == MyMsg.REQUEST_TYPE) {
            return processReq((MyRequestMsg)myMsg);
        } else if (myMsg.getMsgType() == MyMsg.RESPONSE_TYPE) {
            return processRsp((MyResponseMsg)myMsg);
        }  else {
            log.error("myMsg are unknown! {}", myMsg);
            return false;
        }
    }

    public boolean processReq(MyRequestMsg requestMsg) {
        log.info("requestMsg: {}", requestMsg);
        return true;
    }

    public boolean processRsp(MyResponseMsg responseMsg) {
        log.info("responseMsg: {}", responseMsg);
        return true;
    }
}
