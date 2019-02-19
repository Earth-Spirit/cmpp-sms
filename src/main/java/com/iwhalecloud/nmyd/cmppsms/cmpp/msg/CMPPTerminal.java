package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

public class CMPPTerminal extends CMPPHeader implements CMPPIntf {

    public CMPPTerminal() {
    }

    public CMPPTerminal(byte[] data) throws IOException {
        super(data);
    }


    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }


    public static CMPPTerminal build(){
        CMPPTerminal msg = new CMPPTerminal();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_TERMINATE.getCode());
        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setTotalLength(12);
        return msg;
    }
}
