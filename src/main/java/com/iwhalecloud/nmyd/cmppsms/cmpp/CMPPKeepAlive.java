package com.iwhalecloud.nmyd.cmppsms.cmpp;


import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.CMPPActiveTest;
import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.CMPPActiveTestResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.concurrent.*;

public class CMPPKeepAlive implements Runnable {

    private CMPPClient client;
    private Logger log = LoggerFactory.getLogger( this.getClass());
    public CMPPKeepAlive(CMPPClient client) {

       this.client = client;
    }



    @Override
    public void run() {

        try{
            log.info("start keepAlive thread ...");
            while (true){

                try{

                    CMPPActiveTest msg = CMPPActiveTest.build();
                    CMPPSender sender = new CMPPSender(client.getDos(), client.getMsgMap(), msg.toByteArray(),true);

                    FutureTask<CMPPActiveTestResp> futureTask = new FutureTask<CMPPActiveTestResp>(sender);
                    client.threadPool.submit(futureTask);

                    CMPPActiveTestResp receive = futureTask.get(client.getConfig().getTimeout(), TimeUnit.SECONDS);
                    Thread.sleep(client.getConfig().getLinkDetection() * 1000);


                }catch (TimeoutException e){

                    log.warn(e.getMessage(), e);
                    //client.reconnect();
                }

            }
        }catch (InterruptedException e) {
            log.info("Interrupted keepAlive thread ...");
        }catch (IOException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("stop keepAlive thread ...");

    }
}
