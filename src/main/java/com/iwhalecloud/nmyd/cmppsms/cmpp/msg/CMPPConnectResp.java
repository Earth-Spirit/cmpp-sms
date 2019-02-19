package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPConnectErrorEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.MD5Util;

import java.io.*;

/**
 * 1.1.1	SP请求连接到ISMG（CMPP_CONNECT）操作
 * CMPP_CONNECT操作的目的是SP向ISMG注册作为一个合法SP身份，若注册成功后即建立了应用层的连接，此后SP可以通过此ISMG接收和发送短信。
 * ISMG以CMPP_CONNECT_RESP消息响应SP的请求。
 */
public class CMPPConnectResp extends CMPPHeader implements CMPPIntf {

    /**
     * 状态0：正确1：消息结构错  2：非法源地址  3：认证错  4：版本太高    5~ ：其他错误
     */
    private int status;

    /**
     * ISMG认证码，用于鉴别ISMG。其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorISMG =MD5（
     * Status
     * +AuthenticatorSource
     * +shared secret），
     * Shared secret 由中国移动与源地址实体事先商定，
     * AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。认证出错时，此项为空
     */
    private byte[] authenticatorISMG;

    /**
     * 服务器支持的最高版本号
     */
    private byte version;


    public CMPPConnectResp() {
    }

    public CMPPConnectResp(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        //CMPPUtil.setHeader(dins, this);
        this.setTotalLength(dins.readInt());
        this.setCommandId(dins.readInt());
        this.setSequenceId(dins.readInt());
        this.setStatus(dins.readByte());
        this.authenticatorISMG = CMPPUtil.readByte(dins,16);
        this.version = dins.readByte();

        dins.close();
        bins.close();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    public void setAuthenticatorISMG(byte[] authenticatorISMG) {
        this.authenticatorISMG = authenticatorISMG;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }


    @Override
    public byte[] toByteArray() throws IOException {


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        dataOutputStream.writeInt(this.getStatus());
        dataOutputStream.write(this.getAuthenticatorISMG());
        dataOutputStream.writeByte(this.version);

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPConnectResp bulid (Integer sequenceId, String shareSecret){

        CMPPConnectResp msg = new CMPPConnectResp();

        msg.setCommandId(CMPPCommandIdEnum.CMPP_CONNECT_RESP.getCode());
        msg.setSequenceId(sequenceId);
        msg.setTotalLength(12+ 4 +16 +1);
        msg.setStatus(CMPPConnectErrorEnum.SUCCESS.getCode());
        msg.setAuthenticatorISMG(MD5Util.MD5("spId" + "\0\0\0\0\0\0\0\0\0" + shareSecret));
        msg.setVersion((byte)0x20);
        return msg;
    }
}
