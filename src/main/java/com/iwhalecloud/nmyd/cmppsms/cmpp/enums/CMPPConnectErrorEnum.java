package com.iwhalecloud.nmyd.cmppsms.cmpp.enums;

public enum  CMPPConnectErrorEnum {

    SUCCESS(0, "正确"),
    MSG_ERR(1, "消息结构错"),
    INVALID_SRC_ADDR(2, "非法源地址"),
    AUTH_ERROR(3, "认证错"),
    HIGH_VERSION(4, "版本太高"),
    OTHER_ERROR(5, "其他错误");

    int code;
    String Message;

    CMPPConnectErrorEnum(int code, String message) {
        this.code = code;
        Message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public static CMPPConnectErrorEnum getEnum(int code){
        for (CMPPConnectErrorEnum tmp : CMPPConnectErrorEnum.values()){

            if( tmp.getCode() == code){
                return tmp;
            }
        }
        return null;
    }

    public static String getMessage(int code){

        return CMPPConnectErrorEnum.getEnum(code) != null ? CMPPConnectErrorEnum.getEnum(code).getMessage() :null;
    }

}
