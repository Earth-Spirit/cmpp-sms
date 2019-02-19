package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CMPPTerminalResp extends CMPPHeader implements CMPPIntf{


    public CMPPTerminalResp() {
    }

    public CMPPTerminalResp(byte[] data) throws IOException {
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

    public static CMPPTerminalResp build(int sequenceId){
        CMPPTerminalResp msg = new CMPPTerminalResp();

        msg.setCommandId(CMPPCommandIdEnum.CMPP_TERMINATE_RESP.getCode());
        msg.setSequenceId(sequenceId);
        msg.setTotalLength(12);
        return msg;
    }
}
