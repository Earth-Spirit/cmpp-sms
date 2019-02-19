package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

/**
 * CMPP_CANCEL操作的目的是SP通过此操作可以将已经提交给ISMG的短信删除，ISMG将以CMPP_CANCEL_RESP回应删除操作的结果。
 * CMPP_CANCEL消息定义（SP -> ISMG）
 */
public class CMPPCancel extends CMPPHeader implements CMPPIntf {

    //8-Unsigned Integer-信息标识（SP想要删除的信息标识）。
    private int MsgId;

    public CMPPCancel() {
    }

    public CMPPCancel(byte[] data) throws IOException {
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        this.setTotalLength(dins.readInt());
        this.setCommandId(dins.readInt());
        this.setSequenceId(dins.readInt());
        this.setMsgId(dins.readUnsignedByte());

        dins.close();
        bins.close();
    }

    public int getMsgId() {
        return MsgId;
    }

    public void setMsgId(int msgId) {
        MsgId = msgId;
    }


    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        dataOutputStream.writeLong(this.getMsgId());

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPCancel build(int msgId){
        CMPPCancel msg = new CMPPCancel();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_CANCEL.getCode());
        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setMsgId(msgId);
        msg.setTotalLength(12 + 8);
        return msg;
    }

}
