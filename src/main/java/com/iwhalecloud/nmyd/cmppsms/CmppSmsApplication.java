package com.iwhalecloud.nmyd.cmppsms;

import com.iwhalecloud.nmyd.cmppsms.listener.StartListener;
import com.iwhalecloud.nmyd.cmppsms.listener.StopListenter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.iwhalecloud.nmyd.cmppsms.mapper")
public class CmppSmsApplication {

    public static void main(String[] args) {

//        SpringApplication application = new SpringApplication(CmppSmsApplication.class);
//        application.addListeners(new StartListener());
//        application.addListeners(new StopListenter());
//        application.run(args);
        SpringApplication.run(CmppSmsApplication.class, args);
    }

}

