package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

public class CMPPDeliverResp extends CMPPHeader {

    //信息标识（CMPP_DELIVER中的Msg_Id字段）
    private Long msgId;
    /**
     * 结果,详见枚举
     */
    private byte result;


    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
