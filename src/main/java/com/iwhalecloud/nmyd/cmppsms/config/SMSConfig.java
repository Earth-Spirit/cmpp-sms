package com.iwhalecloud.nmyd.cmppsms.config;


import com.iwhalecloud.nmyd.cmppsms.cmpp.CMPPClient;
import com.iwhalecloud.nmyd.cmppsms.cmpp.config.CMPPConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SMSConfig {

    //异常重新连接次数
    @Value("${sms.reconnections:3}")
    private int reconnections;

    //超时时长60s
    @Value("${sms.timeout:60}")
    private int timeout;

    //心跳检测C=180s
    @Value("${sms.linkDetection:180}")
    private int linkDetection;

    //即SP的企业代码
    @Value("${sms.spId}")
    private String spId;

    //shared secret 由中国移动与源地址实体事先商定
    @Value("${sms.sharedSecret}")
    private String sharedSecret;

    //业务标识
    @Value("${sms.serviceId}")
    private String serviceId;

    //短信网关IP
    @Value("${sms.ip}")
    private String ip;

    //短信网关端口
    @Value("${sms.port:7890}")
    private int port;

    //cmpp协议
    @Value("${sms.version:2}")
    private int version;

    @Bean
    public CMPPConfig cmppConfig(){

        CMPPConfig config = new CMPPConfig();

        config.setIp(ip);
        config.setPort(port);
        config.setSharedSecret(sharedSecret);
        config.setServiceId(serviceId);
        config.setSpId(spId);
        config.setTimeout(timeout);
        config.setLinkDetection(linkDetection);
        config.setReconnections(reconnections);
        config.setVersion(version);
        config.setTimeout(timeout);
        config.setLinkDetection(linkDetection);
        return config;

    }

    @Bean
    public CMPPClient cmppClient(){
        CMPPClient client = new CMPPClient(cmppConfig());
        return client;
    }

}
