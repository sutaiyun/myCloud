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

    private Integer serialNo;
    private Integer msgType;
    private Integer msgID;
    private Integer msgLen;    //don't use now!
    private String msgVersion;
    private String payload;
    //private String msgRaw;  //include serialNo + mygType + msgID + msgLen + payload

    public MyMsg() {
    }

    public MyMsg(Integer serialNo, Integer msgType, Integer msgID, String msgVersion, String payload) {
        this.serialNo = serialNo;
        this.msgType = msgType;
        this.msgID = msgID;
        this.msgVersion = msgVersion;
        this.payload = payload;
    }

    Integer getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getMsgType() {
        return this.msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getMsgID() {
        return this.msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getMsgLen() {
        return this.msgLen;
    }

    public void setMsgLen(Integer msgLen) {
        this.msgLen = msgLen;
    }

    public String getMsgVersion() {
        return this.msgVersion;
    }

    public void setMsgVersion(String msgVersion) {
        this.msgVersion = msgVersion;
    }

    public String getPayload() {
       return this.payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    /*
    public String getMsgRaw() {
        return this.msgRaw;
    }

    public void setMsgRaw(String rawMsg) {
        this.msgRaw = rawMsg;
    }
    */

    public String encode() {
        JSONObject jObject = new JSONObject();
        if (null != serialNo) jObject.put("SNO", this.serialNo);
        if (null != msgType) jObject.put("MSG_TYPE", this.msgType);
        if (null != msgID) jObject.put("MSG_ID", this.msgID);
        if (null != msgLen) jObject.put("MSG_LEN", this.msgLen);
        if (null != payload) jObject.put("PAYLOAD", this.payload);
        if (null != msgVersion) jObject.put("VERSION", this.msgVersion);

        return jObject.toString();
    }

    protected JSONObject encodeJson() {
        JSONObject jObject = new JSONObject();
        if (null != serialNo) jObject.put("SNO", this.serialNo);
        if (null != msgType) jObject.put("MSG_TYPE", this.msgType);
        if (null != msgID) jObject.put("MSG_ID", this.msgID);
        if (null != msgLen) jObject.put("MSG_LEN", this.msgLen);
        if (null != payload) jObject.put("PAYLOAD", this.payload);
        if (null != msgVersion) jObject.put("VERSION", this.msgVersion);

        return jObject;
    }

    public void decode(String raw) {
        try {
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(raw);

            this.serialNo = (Integer) jsonObject.get("SNO");
            this.msgType = (Integer) jsonObject.get("MSG_TYPE");
            this.msgID = (Integer) jsonObject.get("MSG_ID");
            this.msgLen = (Integer) jsonObject.get("MSG_LEN");
            this.payload = (String) jsonObject.get("PAYLOAD");
            this.msgVersion = (String) jsonObject.get("VERSION");
        } catch (ParseException e) {
            log.error("MyMsg.decode Error: {}", e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String args[]) {
        MyMsg msg = new MyMsg(1, 1, 0xffffffff, "1.1.1", "{}");

        String jsonString = msg.encode();
        log.info("jsongString:{}", jsonString);

        MyMsg msg1 = new MyMsg();
        msg1.decode(jsonString);
        String jsonString1 = msg1.encode();
        log.info("jsongString1:{}", jsonString1);
    }
}
