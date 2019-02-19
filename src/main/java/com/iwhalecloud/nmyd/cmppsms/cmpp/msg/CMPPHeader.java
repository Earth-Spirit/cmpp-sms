package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class CMPPHeader {
    //message header

    //消息总长度(含消息头及消息体)-unsignedInteger-4
    protected Integer totalLength;

    //命令或响应类型-UnsignedInteger-4
    protected Integer commandId;

    //消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同-UnsignedInteger-4
    protected Integer sequenceId;


    public CMPPHeader() {
    }

    public CMPPHeader(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        this.setTotalLength(dins.readInt());
        this.setCommandId(dins.readInt());
        this.setSequenceId(dins.readInt());

        dins.close();
        bins.close();
    }

    public Integer getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

}
