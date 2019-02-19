package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.*;

public class CMPPQuery extends CMPPHeader implements CMPPIntf{

    //8-Octet String-时间YYYYMMDD(精确至日)。
    private String time;

    //1-Unsigned Integer-查询类别：0：总数查询；1：按业务类型查询。
    private int queryType;

    //10-Octet String-查询码。当Query_Type为0时，此项无效；当Query_Type为1时，此项填写业务类型Service_Id.。
    private String queryCode;

    //8-Octet String-保留
    private String reserve;

    public CMPPQuery() {
    }

    public CMPPQuery(byte[] data) throws IOException {

        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);

        //CMPPUtil.setHeader(dins, this);
        this.setTotalLength(dins.readInt());
        this.setCommandId(dins.readInt());
        this.setSequenceId(dins.readInt());
        this.setTime(CMPPUtil.readString(dins, 8));
        this.setQueryType(dins.readByte());
        this.setQueryCode(CMPPUtil.readString(dins, 10));
        this.setReserve(CMPPUtil.readString(dins, 8));

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

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
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
        CMPPUtil.writeString(dataOutputStream, this.getReserve(), 8);

        byteArrayOutputStream.close();
        dataOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static CMPPQuery build(String serviceId){

        CMPPQuery msg = new CMPPQuery();

        msg.setTotalLength(12 + 8 + 1 + 10 + 8);
        msg.setCommandId(CMPPCommandIdEnum.CMPP_QUERY.getCode());
        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setTime(CMPPUtil.generateTimestamp());
        msg.setQueryType(1);
        msg.setQueryCode(serviceId);
        msg.setReserve(null);
        return msg;
    }



}
