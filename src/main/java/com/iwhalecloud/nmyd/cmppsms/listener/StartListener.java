package com.iwhalecloud.nmyd.cmppsms.listener;

import com.iwhalecloud.nmyd.cmppsms.cmpp.CMPPClient;
import com.iwhalecloud.nmyd.cmppsms.context.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CMPPClient client;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        log.info("applicationStartedEvent execute ... ");

        Thread thread = new Thread(() -> client.start());
        thread.start();
    }
}
