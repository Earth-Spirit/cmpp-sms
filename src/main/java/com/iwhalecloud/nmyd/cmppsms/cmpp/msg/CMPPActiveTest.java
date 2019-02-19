package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;



import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CMPPActiveTest extends CMPPHeader implements CMPPIntf {

    public CMPPActiveTest() {
    }

    public CMPPActiveTest(byte[] data) throws IOException {
        super(data);
    }

    public static CMPPActiveTest build(){
        CMPPActiveTest msg = new CMPPActiveTest();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_ACTIVE_TEST.getCode());
        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setTotalLength(12);

        return msg;
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
}
