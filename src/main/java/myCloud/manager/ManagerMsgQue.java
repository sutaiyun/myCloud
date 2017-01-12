package myCloud.manager;

import myCloud.common.msgque.MsgQue;

/**
 * Created by sutaiyun on 2017/1/11.
 */
public class ManagerMsgQue extends  MsgQue {
    public static final myCloud.manager.ManagerMsgQue instance = new myCloud.manager.ManagerMsgQue("WorkMsgQue");

    private ManagerMsgQue(String name) {
        super.MsgQue(name);
    }
}
