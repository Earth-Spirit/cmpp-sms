package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

public class CMPPQueryResp extends CMPPHeader implements CMPPIntf{


    //8 -Octet String-时间(精确至日)。
    private String time;

    //1-Unsigned Integer-查询类别：0：总数查询；1：按业务类型查询。
    private int queryType;

    //10-Octet String-查询码。
    private String queryCode;

    //4-Unsigned Integer-从SP接收信息总数。
    private int MTTLMsg;

    //4-Unsigned Integer-从SP接收用户总数。
    private int MTTlusr;

    //4-Unsigned Integer-成功转发数量。
    private int MTScs;

    //4-Unsigned Integer-待转发数量。
    private int MTWT;

    //4-Unsigned Integer-转发失败数量。
    private int MTFL;

    //4-Unsigned Integer-向SP成功送达数量。
    private int MOScs;

    //4-Unsigned Integer-向SP待送达数量。
    private int MOWT;

    //4-Unsigned Integer-向SP送达失败数量。
    private int MOFL;


    public CMPPQueryResp() {
    }

    public CMPPQueryResp(byte[] data) throws IOException {
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        CMPPUtil.setHeader(dins, this);
        this.time = CMPPUtil.readString(dins, 8);
        this.queryType = dins.readByte();
        this.queryCode = CMPPUtil.readString(dins, 10);
        this.MTTLMsg = dins.readInt();
        this.MTTlusr = dins.readInt();
        this.MTScs = dins.readInt();
        this.MTWT = dins.readInt();
        this.MTFL = dins.readInt();
        this.MOScs = dins.readInt();
        this.MOWT = dins.readInt();
        this.MOFL = dins.readInt();

        dins.close();
        bins.close();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    public int getMTTLMsg() {
        return MTTLMsg;
    }

    public void setMTTLMsg(int MTTLMsg) {
        this.MTTLMsg = MTTLMsg;
    }

    public int getMTTlusr() {
        return MTTlusr;
    }

    public void setMTTlusr(int MTTlusr) {
        this.MTTlusr = MTTlusr;
    }

    public int getMTScs() {
        return MTScs;
    }

    public void setMTScs(int MTScs) {
        this.MTScs = MTScs;
    }

    public int getMTWT() {
        return MTWT;
    }

    public void setMTWT(int MTWT) {
        this.MTWT = MTWT;
    }

    public int getMTFL() {
        return MTFL;
    }

    public void setMTFL(int MTFL) {
        this.MTFL = MTFL;
    }

    public int getMOScs() {
        return MOScs;
    }

    public void setMOScs(int MOScs) {
        this.MOScs = MOScs;
    }

    public int getMOWT() {
        return MOWT;
    }

    public void setMOWT(int MOWT) {
        this.MOWT = MOWT;
    }

    public int getMOFL() {
        return MOFL;
    }

    public void setMOFL(int MOFL) {
        this.MOFL = MOFL;
    }

    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        dataOutputStream.writeInt(this.getTotalLength());
        dataOutputStream.writeInt(this.getCommandId());
        dataOutputStream.writeInt(this.getSequenceId());
        CMPPUtil.writeString(dataOutputStream, this.getTime(), 8);
        dataOutputStream.writeByte(this.getQueryType());
        CMPPUtil.writeString(dataOutputStream, this.getQueryCode(), 10);
        dataOutputStream.writeInt(this.getMTTLMsg());
        dataOutputStream.writeInt(this.getMTTlusr());
        dataOutputStream.writeInt(this.getMTScs());
        dataOutputStream.writeInt(this.getMTWT());
        dataOutputStream.writeInt(this.getMTFL());
        dataOutputStream.writeInt(this.getMOScs());
        dataOutputStream.writeInt(this.getMOWT());
        dataOutputStream.writeInt(this.getMOFL());

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPQueryResp build(int sequenceId, String serviceId){

        CMPPQueryResp msg = new CMPPQueryResp();
        msg.setCommandId(CMPPCommandIdEnum.CMPP_QUERY_RESP.getCode());
        msg.setSequenceId(sequenceId);
        msg.setTime(CMPPUtil.generateTimestamp());
        msg.setQueryType(0);
        msg.setQueryCode(serviceId);
        msg.setMTTLMsg(1);
        msg.setMTTlusr(1);
        msg.setMTScs(1);
        msg.setMTWT(1);
        msg.setMTFL(1);
        msg.setMOScs(1);
        msg.setMOWT(1);
        msg.setMOFL(1);
        return msg;
    }
}
