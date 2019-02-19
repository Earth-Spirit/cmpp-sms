package com.iwhalecloud.nmyd.cmppsms;
/**
 * 如果单独部署到tomcat时候，经测试部署在tomcat9可用
 *
 * 1、修改打包类型为war：<packaging>war</packaging>
 * 2、pom中添加spring-boot-starter-tomcat依赖，scope设置为provided
 * 3、注册启动类。创建ServletInitializer.java，继承SpringBootServletInitializer ，覆盖configure()，把启动类Application注册进去
 *
 */

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CmppSmsApplication.class);
    }
}
