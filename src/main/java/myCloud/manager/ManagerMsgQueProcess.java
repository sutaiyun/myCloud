package myCloud.manager;

import myCloud.common.msg.MyMsg;
import myCloud.common.msgque.MsgQueProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/12.
 */
public class ManagerMsgQueProcess extends MsgQueProcess {
    private final static Logger log = LogManager.getLogger(ManagerMsgQueProcess.class);
    public ManagerMsgQueProcess() {
        super(ManagerMsgQue.instance);
    }
    @Override
    protected boolean process(MyMsg myMsg) {
        log.info("ManagerMsgQueProcess:{}", myMsg);
        return true;
    }
}
