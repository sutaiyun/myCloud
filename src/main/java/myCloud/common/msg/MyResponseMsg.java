package myCloud.common.msg;

import myCloud.common.Util;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by sutaiyun on 2017/1/8.
 */
public class MyResponseMsg extends MyMsg {
    private Integer result;
    private String resultString;

    public MyResponseMsg(Integer myMsgID, String msgVersion, Integer result, String resultString, String payload) {
        super(Util.getSerialNo(), MyMsg.RESPONSE_TYPE, myMsgID, msgVersion, payload);

        this.setResult(result);
        this.setResultString(resultString);
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getResultString() {
        return this.resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String encode() {
        JSONObject jObject = super.encodeJson();
        if (null != result) jObject.put("RESULT", this.result);
        if (null != resultString) jObject.put("RET_DEC", this.resultString);
        return jObject.toString();
    }

    public void decode(String raw) {
        try {
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(raw);

            this.setSerialNo((Integer) jsonObject.get("SNO"));
            this.setMsgType((Integer) jsonObject.get("MSG_TYPE"));
            this.setMsgID((Integer) jsonObject.get("MSG_ID"));
            this.setMsgLen((Integer) jsonObject.get("MSG_LEN"));
            this.setPayload((String) jsonObject.get("PAYLOAD"));

            this.setResult((Integer) jsonObject.get("RESULT"));
            this.setResultString((String) jsonObject.get("RET_DEC"));
        } catch (ParseException e) {
            log.error("MyMsg.decode Error: {}", e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
