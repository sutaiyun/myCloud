package myCloud.work;

import myCloud.common.msgque.MsgQue;

/**
 * Created by sutaiyun on 2017/1/10.
 */
public class WorkMsgQue extends MsgQue {
    public static final WorkMsgQue instance = new WorkMsgQue("WorkMsgQue");

    private WorkMsgQue(String name) {
        super.MsgQue(name);
    }
}
