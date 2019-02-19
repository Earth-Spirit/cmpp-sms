package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

/**
 * CMPP_CANCEL操作的目的是SP通过此操作可以将已经提交给ISMG的短信删除，ISMG将以CMPP_CANCEL_RESP回应删除操作的结果
 * CMPP_CANCEL_RESP消息定义（ISMG -> SP）
 */
public class CMPPCancelResp extends CMPPHeader implements CMPPIntf{


    //4-Unsigned Integer-成功标识。0：成功；1：失败。 3.0
    //1-Unsigned Integer-成功标识。0：成功；1：失败。 2.0

    private int SuccessId;

    public CMPPCancelResp() {
    }

    public CMPPCancelResp(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        CMPPUtil.setHeader(dins, this);
        this.SuccessId = dins.readInt();
        dins.close();
        bins.close();
    }

    public int getSuccessId() {
        return SuccessId;
    }

    public void setSuccessId(int successId) {
        SuccessId = successId;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        dataOutputStream.write(this.getSuccessId());
        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPCancelResp build(){
        CMPPCancelResp msg = new CMPPCancelResp();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_CANCEL_RESP.getCode());
        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setSuccessId(0);
        msg.setTotalLength(12 + 4);
        return msg;

    }
}
