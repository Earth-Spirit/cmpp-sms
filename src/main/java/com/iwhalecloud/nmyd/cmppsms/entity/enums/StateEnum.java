package com.iwhalecloud.nmyd.cmppsms.entity.enums;

public class StateEnum {

//    PENDING(0, "待处理"),
//    SUCCESS(1, "处理成功"),
//    FAIL(2, "处理失败");

    private Integer code;
    private String message;

    public StateEnum(Integer code, String message) {
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
}
