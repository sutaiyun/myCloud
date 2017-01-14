package myCloud.common.msg;

import myCloud.common.Util;

/**
 * Created by sutaiyun on 2017/1/8.
 */
public class MyRequestMsg extends MyMsg {
    public MyRequestMsg(Integer myMsgID, String msgVersion, String payload) {
        super(Util.getSerialNo(), MyMsg.REQUEST_TYPE, myMsgID, msgVersion, payload);
    }

    public String encode() {
        return super.encode();
    }

    public void decode(String raw) {
        super.decode(raw);
    }
}
