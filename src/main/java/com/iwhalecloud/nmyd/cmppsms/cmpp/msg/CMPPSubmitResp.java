package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPSubmitErrorEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

public class CMPPSubmitResp extends CMPPHeader implements CMPPIntf {

    /**
     * 信息标识，生成算法如下：
     * 采用64位（8字节）的整数：
     * （1） 时间（格式为MMDDHHMMSS，即月日时分秒）：bit64~bit39，
     * 其中bit64~bit61：月份的二进制表示；
     * bit60~bit56：日的二进制表示；bit55~bit51：
     * 小时的二进制表示；bit50~bit45：分的二进制表示；
     * bit44~bit39：秒的二进制表示；
     * （2） 短信网关代码：bit38~bit17，把短信网关的代码转换为整数填写到该字段中。
     * （3） 序列号：bit16~bit1，顺序增加，步长为1，循环使用。各部分如不能填满，左补零，右对齐。
     * （SP根据请求和应答消息的Sequence_Id一致性就可得到CMPP_Submit消息的Msg_Id）
     */
    private Long msgId;

    /**
     * 结果0：正确1：消息结构错  2：命令字错  3：消息序号重复4：消息长度错5：资费代码错6：超过最大信息长7：业务代码错8：流量控制错9~ ：其他错误-4字节
     */
    private int result;

    public CMPPSubmitResp() {
    }

    public CMPPSubmitResp(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        CMPPUtil.setHeader(dins, this);
        this.msgId = dins.readLong();
        this.result = dins.readByte();

        dins.close();
        bins.close();

    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        dataOutputStream.writeLong(this.getMsgId());
        dataOutputStream.writeInt(this.getResult());
        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPSubmitResp build(int sequenceId, long msgId) {

        CMPPSubmitResp msg = new CMPPSubmitResp();

        msg.setCommandId(CMPPCommandIdEnum.CMPP_SUBMIT_RESP.getCode());
        msg.setSequenceId(sequenceId);
        msg.setMsgId(msgId);
        msg.setResult(CMPPSubmitErrorEnum.SUCCESS.getCode());
        return msg;

    }
}
