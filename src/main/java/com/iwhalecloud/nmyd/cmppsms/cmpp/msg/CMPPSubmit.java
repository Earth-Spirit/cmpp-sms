package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CMPPSubmit extends CMPPHeader implements CMPPIntf{

    public static int MAX_LENGTH = 140;
    /**
     * 信息标识，由SP侧短信网关本身产生，本处填空。-8-Unsigned Integer
     */
    private Long msgId = 0L;

    /**
     * 相同Msg_Id的信息总条数，从1开始 -1-Unsigned Integer
     */
    private byte pkTotal = 0x1;

    /**
     * 相同Msg_Id的信息序号，从1开始 -1-Unsigned Integer
     */
    private byte pkNumber = 0x1;

    /**
     * 是否要求返回状态确认报告：0：不需要  1：需要  2：产生SMC话单（该类型短信仅供网关计费使用，不发送给目的终端) -1-Unsigned Integer
     */
    private byte registeredDelivery = 0x00;

    /**
     * 信息级别 -1-Unsigned Integer
     */
    private byte msgLevel = 0x01;

    /**
     * 业务类型，是数字、字母和符号的组合(接入号?) -10-Octet String
     */
    private String serviceId;

    /**
     * 计费用户类型字段
     * 0：对目的终端MSISDN计费；
     * 1：对源终端MSISDN计费；
     * 2：对SP计费;
     * 3：表示本字段无效，对谁计费参见Fee_terminal_Id字段。 -1-Unsigned Integer
     */
    private byte feeUserType = 0x02;

    /**
     * 被计费用户的号码
     * （如本字节填空，则表示本字段无效，对谁计费参见Fee_UserType字段，本字段与Fee_UserType字段互斥）
     *  -32-Octet String        cmpp3.0
     *  -21-Unsigned Integer    cmpp2.0
     */
    private String feeTerminalId = "";

    /**
     * GSM协议类型。详细是解释请参考GSM03.40中的9.2.3.9-1-Unsigned Integer
     *
     */
    private byte tpPid = 0x00;

    /**
     * GSM协议类型。 详细是解释请参考GSM03.40中的9.2.3.23,仅使用1位，右对齐-1-Unsigned Integer
     * 0代表内容体里不含有协议头信息 1代表内容含有协议头信息
     * CMPP-_SUBMIT消息定义中有相应的参数配置：
     * TP_udhi ：0代表内容体里不含有协议头信息 1代表内容含有协议头信息（长短信，push短信等都是在内容体上含有头内容的,也就是说把基本参数(TP-MTI/VFP)值设置成0X51）当设置内容体包含协议头，需要根据协议写入相应的信息，长短信协议头有两种：
     * 6位协议头格式：05 00 03 XX MM NN
     * byte 1 : 05, 表示剩余协议头的长度
     * byte 2 : 00, 这个值在GSM 03.40规范9.2.3.24.1中规定，表示随后的这批超长短信的标识位长度为1（格式中的XX值）。
     * byte 3 : 03, 这个值表示剩下短信标识的长度
     * byte 4 : XX，这批短信的唯一标志(被拆分的多条短信,此值必需一致)，事实上，SME(手机或者SP)把消息合并完之后，就重新记录，所以这个标志是否唯
     * 一并不是很 重要。
     * byte 5 : MM, 这批短信的数量。如果一个超长短信总共5条，这里的值就是5。
     * byte 6 : NN, 这批短信的数量。如果当前短信是这批短信中的第一条的值是1，第二条的值是2。
     * 例如：05 00 03 39 02 01
     *
     * 包头一共6个字节，如下：
     * 1、字节一：包头长度，固定填写0x05；
     * 2、字节二：包头类型标识，固定填写0x00，表示长短信；
     * 3、字节三：子包长度，固定填写0x03，表示后面三个字节的长度；
     * 4、字节四到字节六：包内容：
     * 1）字节四：长消息参考号，每个SP给每个用户发送的每条参考号都应该不同，可以从0开始，每次加1，最大255，便于同一个终端对同一个SP的消息的不同的长短信进行识别；
     * 2）字节五：本条长消息的的总消息数，从1到255，一般取值应该大于2；
     * 3）字节六：本条消息在长消息中的位置或序号，从1到255，第一条为1，第二条为2，最后一条等于第四字节的值。
     * 7位的协议头格式：06 08 04 XX XX MM NN
     * byte 1 : 06, 表示剩余协议头的长度
     * byte 2 : 08, 这个值在GSM 03.40规范9.2.3.24.1中规定，表示随后的这批超长短信的标识位长度为2（格式中的XX值）。
     * byte 3 : 04, 这个值表示剩下短信标识的长度
     * byte 4-5 : XX XX，这批短信的唯一标志，事实上，SME(手机或者SP)把消息合并完之后，就重新记录，所以这个标志是否唯一并不是很重要。
     * byte 6 : MM, 这批短信的数量。如果一个超长短信总共5条，这里的值就是5。
     * byte 7 : NN, 这批短信的数量。如果当前短信是这批短信中的第一条的值是1，第二条的值是2。
     * 例如：06 08 04 00 39 02 01
     */
    private byte tpUdhi = 0x00;

    /**
     * 信息格式    0：ASCII串    3：短信写卡操作    4：二进制信息    8：UCS2编码15：含GB汉字-1-Unsigned Integer
     */
    private byte msgFmt = 0x0f;

    /**
     * 信息内容来源(SP_Id)-6-Octet String
     */
    private String msgSrc;

    /**
     * -2-Octet String
     * 资费类别01：对“计费用户号码”免费
     * 02：对“计费用户号码”按条计信息费
     * 03：对“计费用户号码”按包月收取信息费
     * 04：对“计费用户号码”的信息费封顶
     * 05：对“计费用户号码”的收费是由SP实现
     */
    private String feeType  = "01";

    /**
     * 资费代码（以分为单位）-6-Octet String
     */
    private String feeCode = "000000";

    /**
     * 存活有效期，格式遵循SMPP3.3协议-17-Octet String
     */
    private String validTime  = "";

    /**
     * 定时发送时间，格式遵循SMPP3.3协议-17-Octet String
     */
    private String atTime  = "";

    /**
     * 源号码SP的服务代码或前缀为服务代码的长号码,
     * 网关将该号码完整的填到SMPP协议Submit_SM消息相应的source_addr字段，
     * 该号码最终在用户手机上显示为短消息的主叫号码
     * -21-Octet String
     */
    private String srcId;

    /**
     * 1 (129 bytes) Unsigned Integer
     * 接收信息的用户数量(小于100个用户)
     *
     */
    private byte destUsrTl = 0x01;

    /**
     * 接收短信的MSISDN号码-32*DestUsr_tl-Octet String  3.0
     * 接收短信的MSISDN号码-21*DestUsr_tl-Octet String  2.0
     */
    private String destTerminalId;

    /**
     * 信息长度(Msg_Fmt值为0时：<160个字节；其它<=140个字节) -1-Unsigned Integer
     */
    private byte msgLength;

    /**
     * 信息内容--Msg_length --Octet String
     */
    private byte[] msgContent;

    /**
     * 点播业务使用的LinkID，非点播类业务的MT流程不使用该字段。-20-Octet String cmpp3.0独有
     */
    private String linkID = "00000000";

    /**
     * 8-Octet String 保留  cmpp2.0独有
     */
    private String reserve = "";

    public CMPPSubmit() {
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public byte getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(byte pkTotal) {
        this.pkTotal = pkTotal;
    }

    public byte getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(byte pkNumber) {
        this.pkNumber = pkNumber;
    }

    public byte getRegisteredDelivery() {
        return registeredDelivery;
    }

    public void setRegisteredDelivery(byte registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }

    public byte getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(byte msgLevel) {
        this.msgLevel = msgLevel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public byte getFeeUserType() {
        return feeUserType;
    }

    public void setFeeUserType(byte feeUserType) {
        this.feeUserType = feeUserType;
    }

    public String getFeeTerminalId() {
        return feeTerminalId;
    }

    public void setFeeTerminalId(String feeTerminalId) {
        this.feeTerminalId = feeTerminalId;
    }

    public byte getTpPid() {
        return tpPid;
    }

    public void setTpPid(byte tpPid) {
        this.tpPid = tpPid;
    }

    public byte getTpUdhi() {
        return tpUdhi;
    }

    public void setTpUdhi(byte tpUdhi) {
        this.tpUdhi = tpUdhi;
    }

    public byte getMsgFmt() {
        return msgFmt;
    }

    public void setMsgFmt(byte msgFmt) {
        this.msgFmt = msgFmt;
    }

    public String getMsgSrc() {
        return msgSrc;
    }

    public void setMsgSrc(String msgSrc) {
        this.msgSrc = msgSrc;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public byte getDestUsrTl() {
        return destUsrTl;
    }

    public void setDestUsrTl(byte destUsrTl) {
        this.destUsrTl = destUsrTl;
    }

    public String getDestTerminalId() {
        return destTerminalId;
    }

    public void setDestTerminalId(String destTerminalId) {
        this.destTerminalId = destTerminalId;
    }

    public byte getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(byte msgLength) {
        this.msgLength = msgLength;
    }

    public byte[] getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }

    public String getLinkID() {
        return linkID;
    }

    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    @Override
    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);

        dous.writeInt(this.getTotalLength());
        dous.writeInt(this.getCommandId());
        dous.writeInt(this.getSequenceId());
        dous.writeLong(this.msgId);
        dous.writeByte(this.pkTotal);//Pk_total 相同Msg_Id的信息总条数
        dous.writeByte(this.pkNumber);//Pk_number 相同Msg_Id的信息序号，从1开始
        dous.writeByte(this.registeredDelivery);//Registered_Delivery 是否要求返回状态确认报告
        dous.writeByte(this.msgLevel);//Msg_level 信息级别
        CMPPUtil.writeString(dous, this.serviceId, 10);//Service_Id 业务标识，是数字、字母和符号的组合。
        dous.writeByte(this.feeUserType);//Fee_UserType 计费用户类型字段 0：对目的终端MSISDN计费；1：对源终端MSISDN计费；2：对SP计费;3：表示本字段无效，对谁计费参见Fee_terminal_Id字段。
        CMPPUtil.writeString(dous, this.feeTerminalId, 21);//Fee_terminal_Id 被计费用户的号码
        dous.writeByte(this.tpPid);//TP_pId
        dous.writeByte(this.tpUdhi);//TP_udhi
        dous.writeByte(this.msgFmt);//Msg_Fmt
        CMPPUtil.writeString(dous, this.msgSrc, 6);//Msg_src 信息内容来源(SP_Id)
        CMPPUtil.writeString(dous, this.feeType, 2);//FeeType 资费类别
        CMPPUtil.writeString(dous, this.feeCode, 6);//FeeCode
        CMPPUtil.writeString(dous, this.validTime, 17);//存活有效期
        CMPPUtil.writeString(dous, this.atTime, 17);//定时发送时间
        CMPPUtil.writeString(dous, this.srcId, 21);//Src_Id spCode
        dous.writeByte(this.destUsrTl);//DestUsr_tl
        CMPPUtil.writeString(dous, this.destTerminalId, 21 * this.destUsrTl );//Dest_terminal_Id
        dous.writeByte(this.msgLength);//Msg_Length
        dous.write(this.msgContent);//信息内容
        CMPPUtil.writeString(dous, this.reserve, 8);// 保留

        dous.close();
        bous.close();
        return bous.toByteArray();
    }


    public static CMPPSubmit build(String serviceId, String spId, String[] phone, byte[] message){
        CMPPSubmit msg = new CMPPSubmit();
        int msglength = message.length;

        //一次只支持100个号码发送
        int phoneNumber = phone.length<100 ? phone.length: 100;
        StringBuilder phoneStr= new StringBuilder();
        for(int i=0; i< phoneNumber; i++){
            phoneStr.append(phone[i]);
        }

        msg.setTotalLength(12 + 8 + 1 + 1 + 1 + 1 + 10 + 1 + 21 + 1 + 1 + 1 + 6 + 2 + 6 + 17 + 17 + 21 + 1 + 21 * phoneNumber + 1 + msglength + 8);

        msg.setSequenceId(CMPPUtil.generateSequenceId());
        msg.setCommandId(CMPPCommandIdEnum.CMPP_SUBMIT.getCode());
        msg.setServiceId(serviceId);
        msg.setFeeUserType((byte) 0x00);
        msg.setFeeTerminalId("");

        msg.setDestUsrTl((byte) phoneNumber);
        msg.setDestTerminalId(phoneStr.toString());

        msg.setMsgSrc(spId);
        msg.setSrcId(serviceId);
        msg.setMsgLength((byte)msglength);
        msg.setMsgContent(message);
        return msg;
    }
}
