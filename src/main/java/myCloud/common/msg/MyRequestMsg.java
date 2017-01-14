package myCloud.common.msg;

import myCloud.common.Util;

/**
 * Created by sutaiyun on 2017/1/8.
 */
public class MyRequestMsg extends MyMsg {
    public MyRequestMsg() {
        super();
        this.setSerialNo(Util.getSerialNo());
        this.setMsgType((Integer)MyMsg.REQUEST_TYPE);
        this.setMsgID((Integer)MyMsgID.MY_REQUEST);
    }
}
