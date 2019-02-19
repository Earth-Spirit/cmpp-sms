package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import java.io.*;

public class CMPPActiveTestResp extends CMPPHeader implements CMPPIntf {

    //一个1字节的空回复即可,该字节为空,无需解析
    private byte reserved;


    public CMPPActiveTestResp(){

    }


    public byte getReserved() {
        return reserved;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }

    public CMPPActiveTestResp(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        CMPPUtil.setHeader(dins, this);
        this.reserved = dins.readByte();

        dins.close();
        bins.close();
    }


    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        dataOutputStream.writeByte(this.getReserved());

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPActiveTestResp build(int sequenceId){
        CMPPActiveTestResp msg = new CMPPActiveTestResp();

        msg.setTotalLength(12 + 1);
        msg.setCommandId(CMPPCommandIdEnum.CMPP_ACTIVE_TEST_RESP.getCode());
        msg.setSequenceId(sequenceId);
        msg.setReserved((byte) 0x1);
        return msg;
    }

}
