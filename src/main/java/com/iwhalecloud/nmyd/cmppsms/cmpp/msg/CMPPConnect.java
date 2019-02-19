package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.MD5Util;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 1.1.1	SP请求连接到ISMG（CMPP_CONNECT）操作
 * CMPP_CONNECT操作的目的是SP向ISMG注册作为一个合法SP身份，若注册成功后即建立了应用层的连接，此后SP可以通过此ISMG接收和发送短信。
 * ISMG以CMPP_CONNECT_RESP消息响应SP的请求。
 */
public class CMPPConnect extends CMPPHeader implements CMPPIntf {


    /**
     * 源地址，此处为SP_Id，即SP的企业代码,6位
     */
    private String sourceAddr;

    /**
     * 用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorSource =  MD5（
     * Source_Addr
     * +9 字节的0
     * +shared secret
     * +timestamp）
     * Shared  secret  由中国移动与源地址实;
     * 体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。
     * <p>
     * 16位
     */
    private byte[] authenticatorSource;

    /**
     * 双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号)
     */
    private byte version;

    /**
     * 时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐。
     */
    private Integer timestamp;


    public CMPPConnect() {
    }

    public CMPPConnect(byte[] bytes) {

    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        CMPPUtil.writeString(dataOutputStream, this.sourceAddr, 6);
        dataOutputStream.write(this.authenticatorSource);
        dataOutputStream.writeByte(this.version);
        dataOutputStream.writeInt(this.timestamp);

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }


    @Override
    public String toString() {
        return "CMPPConnect{" +
                "sourceAddr='" + sourceAddr + '\'' +
                ", authenticatorSource=" + Arrays.toString(authenticatorSource) +
                ", version=" + version +
                ", timestamp=" + timestamp +
                '}';
    }

    public static CMPPConnect bulid(String spId, String shareSecret){
        CMPPConnect msg = new CMPPConnect();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_CONNECT.getCode());

        String timestamp = CMPPUtil.generateTimestamp();

        System.out.println("timestamp=" + timestamp);


        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setTimestamp(Integer.parseInt(timestamp));
        //msg.setAuthenticatorSource(MD5Util.MD5(spId + "\0\0\0\0\0\0\0\0\0" + shareSecret + timestamp));
        msg.setAuthenticatorSource(MD5Util.md5(spId, shareSecret, timestamp));
        //System.out.println("msg.authenticatorSource.length=" + msg.authenticatorSource.length);
        msg.setSourceAddr(spId);
        msg.setVersion((byte)0x20);
        msg.setTotalLength(12 + 6 + 16 + 1 + 4);// 消息总长度，级总字节数:4+4+4(消息头)+6+16+1+4(消息主体)

        //System.out.println(msg.toString());
        return msg;
    }
}
