package com.iwhalecloud.nmyd.cmppsms.cmpp.msg;

public class CMPPDeliver extends CMPPHeader {

    //信息标识
    private Long msgId;

    //21- 目的号码,SP的服务代码，一般4--6位，或者是前缀为服务代码的长号码；该号码是手机用户短消息的被叫号码
    private String destId;

    //业务类型，是数字、字母和符号的组合 - 10
    private String serviceId;

    private byte tpPid;
    private byte tpUdhi;

    //信息格式    0：ASCII串 3：短信写卡操作    4：二进制信息    8：UCS2编码 15：含GB汉字
    private byte msgFmt;
    //自定义...用于消息内容转换
    private String charsetName;

    //源终端MSISDN号码（状态报告时填为CMPP_SUBMIT消息的目的终端号码
    private String srcTerminalId;

    //是否为状态报告0：非状态报告1：状态报告
    private byte registeredDeliver;

    //消息长度
    private byte msgLength;

    //消息内容--状态报告
    private MsgContent msgContent;

    //消息内容-其他
    private String otherMsgContent;

    //保留项
    private String reserved = "";


    public class MsgContent {
        //信息标识 SP提交短信（CMPP_SUBMIT）操作时，与SP相连的ISMG产生的Msg_Id
        private Long msgId;
        //发送短信的应答结果，含义与SMPP协议要求中stat字段定义相同，详见表一。SP根据该字段确定CMPP_SUBMIT消息的处理状态。
        private String stat;

        //自定义,结果string
        private String statString;

        //提交时间 YYMMDDHHMM（YY为年的后两位00-99，MM：01-12，DD：01-31，HH：00-23，MM：00-59）
        private String submitTime;
        //完成时间 YYMMDDHHMM
        private String doneTime;
        //目的终端MSISDN号码(SP发送CMPP_SUBMIT消息的目标终端
        private String destTerminalId;
        //取自SMSC发送状态报告的消息体中的消息标识。
        private Integer SmscSequence;
    }
}
