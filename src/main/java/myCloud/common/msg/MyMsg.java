package myCloud.common.msg;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sutaiyun on 2017/1/8.
 */
public class MyMsg {
    public static final Logger log = LogManager.getLogger(MyMsg.class);
    public static int REQUEST_TYPE = 0;
    public static int RESPONSE_TYPE = 1;
    public static int UNKNOWN_TYPE = 2;

    private String serialNo;
    private String msgType;
    private String msgID;
    private String msgLen;
    private String payload;

    String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgID() {
        return this.msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getMsgLen() {
        return this.msgLen;
    }

    public void setMsgLen(String msgLen) {
        this.msgLen = msgLen;
    }

    public String getPayload() {
        return this.payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String encode() {
        JSONObject jObject = new JSONObject();
        if (null != serialNo) jObject.put("SNO", this.serialNo);
        if (null != msgType) jObject.put("MSG_TYPE", this.msgType);
        if (null != msgID) jObject.put("MSG_ID", this.msgID);
        if (null != msgLen) jObject.put("MSG_LEN", this.msgLen);
        if (null != payload) jObject.put("PAYLOAD", this.payload);

        return jObject.toString();
    }

    public void decode(String raw) {
        try {
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(raw);

            this.serialNo = (String) jsonObject.get("SNO");
            this.msgType = (String) jsonObject.get("MSG_TYPE");
            this.msgID = (String) jsonObject.get("MSG_ID");
            this.msgLen = (String) jsonObject.get("MSG_LEN");
            this.payload = (String) jsonObject.get("PAYLOAD");
        } catch (ParseException e) {
            log.error("MyMsg.decode Error: {}", e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String args[]) {
        MyMsg msg = new MyMsg();
        msg.setSerialNo("1");
        msg.setMsgType("REQ");
        msg.setMsgID("ffffffff");
        msg.setMsgLen("0x12345678");
        msg.setPayload("{}");

        String jsonString = msg.encode();
        log.info("jsongString:{}", jsonString);

        MyMsg msg1 = new MyMsg();
        msg1.decode(jsonString);
        String jsonString1 = msg1.encode();
        log.info("jsongString1:{}", jsonString1);
    }
}
