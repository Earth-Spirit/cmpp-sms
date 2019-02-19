package com.iwhalecloud.nmyd.cmppsms.cmpp.enums;

public enum CMPPCommandIdEnum {

    CMPP_CONNECT(0x00000001, "请求连接"),
    CMPP_CONNECT_RESP(0x80000001, "请求连接应答"),
    CMPP_TERMINATE(0x00000002, "终止连接"),
    CMPP_TERMINATE_RESP(0x80000002, "终止连接"),
    CMPP_SUBMIT(0x00000004, "提交短信"),
    CMPP_SUBMIT_RESP(0x80000004, "提交短信应答"),
    CMPP_DELIVER(0x00000005, "短信下发"),
    CMPP_DELIVER_RESP(0x80000005, "短信下发应答"),
    CMPP_QUERY(0x00000006, "发送短信状态查询"),
    CMPP_QUERY_RESP(0x80000006, "发送短信状态查询应答"),
    CMPP_CANCEL(0x00000007, "删除短信"),
    CMPP_CANCEL_RESP(0x80000007, "删除短信应答"),
    CMPP_ACTIVE_TEST(0x00000008, "激活测试"),
    CMPP_ACTIVE_TEST_RESP(0x80000008, "激活测试应答"),

    CMPP_FWD(0X00000009, "消息前转"),
    CMPP_FWD_RESP(0X80000009, "消息前转应答"),
    CMPP_MT_ROUTE(0X00000010, "MT路由请求"),
    CMPP_MT_ROUTE_RESP(0X80000010, "MT路由请求应答"),
    CMPP_MO_ROUTE(0X00000011, "MO路由请求"),
    CMPP_MO_ROUTE_RESP(0X80000011, "MO路由请求应答"),
    CMPP_GET_MT_ROUTE(0X00000012, "获取MT路由请求"),
    CMPP_GET_MT_ROUTE_RESP(0X80000012, "获取MT路由请求应答"),
    CMPP_MT_ROUTE_UPDATE(0X00000013, "MT路由更新"),
    CMPP_MT_ROUTE_UPDATE_RESP(0X80000013, "MT路由更新应答"),
    CMPP_MO_ROUTE_UPDATE(0X00000014, "MO路由更新"),
    CMPP_MO_ROUTE_UPDATE_RESP(0X80000014, "MO路由更新应答"),
    CMPP_PUSH_MT_ROUTE_UPDATE(0X00000015, "MT路由更新"),
    CMPP_PUSH_MT_ROUTE_UPDATE_RESP(0X80000015, "MT路由更新应答"),
    CMPP_PUSH_MO_ROUTE_UPDATE(0X00000016, "MO路由更新"),
    CMPP_PUSH_MO_ROUTE_UPDATE_RESP(0X80000016, "MO路由更新应答"),
    CMPP_GET_MO_ROUTE(0X00000017, "获取MO路由请求"),
    CMPP_GET_MO_ROUTE_RESP(0X80000017, "获取MO路由请求应答"),

    UNKNOWN(0X00000000, "未知");

    private Integer code;
    private String message;

    CMPPCommandIdEnum(Integer code, String message) {
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

    public static CMPPCommandIdEnum getEnum( int code){

        for (CMPPCommandIdEnum tmp : CMPPCommandIdEnum.values()){
            if (tmp.getCode().intValue() == code){
                return tmp;
            }
        }
        return null;
    }

    public static String getMessage(int code){

        return CMPPCommandIdEnum.getEnum(code) != null ? CMPPCommandIdEnum.getEnum(code).message : null;
    }

}
