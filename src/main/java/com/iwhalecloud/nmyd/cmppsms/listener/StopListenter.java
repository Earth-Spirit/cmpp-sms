package com.iwhalecloud.nmyd.cmppsms.listener;

import com.iwhalecloud.nmyd.cmppsms.cmpp.CMPPClient;
import com.iwhalecloud.nmyd.cmppsms.config.SMSConfig;
import com.iwhalecloud.nmyd.cmppsms.context.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class StopListenter implements ApplicationListener<ContextClosedEvent> {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CMPPClient client;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

        log.info("StopListenter execute ...");
        //CMPPClient client = SpringContextUtil.getBean(CMPPClient.class);

        if(client != null){
            client.shutdown();
        }

    }
}
