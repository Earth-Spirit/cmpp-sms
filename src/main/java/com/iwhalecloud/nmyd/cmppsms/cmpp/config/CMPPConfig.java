package com.iwhalecloud.nmyd.cmppsms.cmpp.config;

public class CMPPConfig {

    //异常重新连接次数
    private int reconnections = 3;

    //超时时长60s
    private int timeout = 10;

    //心跳检测C=3分钟
    private int linkDetection = 180;

    //窗口大小参数W可配置，现阶段建议为16，即接收方在应答前一次收到的消息最多不超过16条。
    private int window = 16;

    //即SP的企业代码
    private String spId;

    //shared secret 由中国移动与源地址实体事先商定
    private String sharedSecret;

    private String serviceId; //业务标识

    //短信网关IP
    private String ip;

    //短信网关端口
    private int port;

    //cmpp协议版本
    private int version;

    public CMPPConfig() {
    }

    public CMPPConfig(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public int getReconnections() {
        return reconnections;
    }

    public void setReconnections(int reconnections) {
        this.reconnections = reconnections;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getLinkDetection() {
        return linkDetection;
    }

    public void setLinkDetection(int linkDetection) {
        this.linkDetection = linkDetection;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
