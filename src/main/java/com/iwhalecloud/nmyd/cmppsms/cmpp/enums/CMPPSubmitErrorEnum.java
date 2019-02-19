package com.iwhalecloud.nmyd.cmppsms.cmpp.enums;

public enum CMPPSubmitErrorEnum {

    SUCCESS(0, "正确"),
    MSG_ERR(1, "消息结构错误"),
    COMMANDID_ERROR(2, "命令字错误"),
    MSGID_DUPLICATE(3, "消息序号重复"),
    MSG_LENGTH_ERR(4, "消息长度错误"),
    FEE_CODE_ERROR(5, "资费代码错误"),
    MSG_OVER_MAX_LENGTH(6, "超过最大信息长度"),
    SEVICE_CODE_ERROR(7, "业务代码错误"),
    TRAFFIC_CONTROL_ERROR(8, "流量控制错误"),
    NOT_SUPROT_NBR(9, "本网关不负责此计费号码"),
    SRC_ID_ERR(10, "Src_ID错"),
    MSG_SRC_ERROR(11, "Msg_src错"),
    BILL_ADDR_ERROR(12, "计费地址错"),
    DEST_ADDR_ERROR(13, "目的地址错"),
    UNCONNECT(51, "尚未建立连接"),
    OTHER_ERROR(9, "其他错误"),
    ;
    private Integer code;
    private String message;

    CMPPSubmitErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CMPPSubmitErrorEnum getEnum(int code){

        for(CMPPSubmitErrorEnum tmpEnum: CMPPSubmitErrorEnum.values()){

            if (tmpEnum.getCode().intValue() == code){
                return tmpEnum;
            }
        }

        return null;
    }

    public static String getMessge(int code){

        return CMPPSubmitErrorEnum.getEnum(code) != null ? CMPPSubmitErrorEnum.getEnum(code).message : null;
    }
}
