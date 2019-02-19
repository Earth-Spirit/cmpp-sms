package com.iwhalecloud.nmyd.cmppsms.cmpp;


import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.*;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class
CMPPReceiver implements Runnable {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private CMPPClient client;


    public CMPPReceiver(CMPPClient client) {
        this.client = client;
    }

    @Override
    public void run() {

        log.info("start receive thread ...");
        try{
            while (!Thread.currentThread().isInterrupted()){

                receive();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("stop receive thread ...");

    }


    public void receive() throws IOException{


        if(client.getDis() != null && client.getDis().available() > 4 ){


            //int totalLength = in.readInt();
            byte[] msgByte = new byte[client.getDis().available()];
            client.getDis().read(msgByte);

            CMPPHeader header = new CMPPHeader(msgByte);

            Integer sequenceId = header.getSequenceId();
            CMPPCommandIdEnum commandIdEnum = CMPPCommandIdEnum.getEnum(header.getCommandId());
            log.info("receive {}消息, sequenceId={}, length ={},[{}]",commandIdEnum.getMessage(), sequenceId, header.getTotalLength(), CMPPUtil.toPrintHexString(msgByte));
            log.info("getMsgMap size ={}", client.getMsgMap().size());
            switch (commandIdEnum){

                case CMPP_CONNECT_RESP:
                    CMPPConnectResp connectResp = new CMPPConnectResp(msgByte);
                    client.getMsgMap().put(sequenceId, connectResp);
                    break;
                case CMPP_ACTIVE_TEST_RESP:
                    CMPPActiveTestResp activeTestResp = new CMPPActiveTestResp(msgByte);
                    client.getMsgMap().put(sequenceId, activeTestResp);

                    break;
                case CMPP_SUBMIT_RESP:
                    CMPPSubmitResp submitResp = new CMPPSubmitResp(msgByte);
                    client.getMsgMap().put(sequenceId, submitResp);
                    break;
                case CMPP_TERMINATE_RESP:
                    CMPPTerminalResp terminalResp = new CMPPTerminalResp(msgByte);
                    client.getMsgMap().put(sequenceId, terminalResp);
                    break;
                case CMPP_QUERY_RESP:
                    CMPPQueryResp queryResp = new CMPPQueryResp(msgByte);
                    client.getMsgMap().put(sequenceId, queryResp);
                    break;
                case CMPP_CANCEL_RESP:
                    CMPPCancelResp cancelResp = new CMPPCancelResp(msgByte);
                    client.getMsgMap().put(sequenceId, cancelResp);

                    break;
                case CMPP_ACTIVE_TEST:
                    CMPPActiveTest activeTest = new CMPPActiveTest(msgByte);
                    CMPPActiveTestResp activeTestResp1 = CMPPActiveTestResp.build(activeTest.getSequenceId());
                    CMPPSender activeSender = new CMPPSender(client.getDos(),client.getMsgMap(), activeTestResp1.toByteArray(), false);
                    client.getThreadPool().submit(activeSender);
                    break;
                case CMPP_TERMINATE:
                    CMPPTerminal terminal = new CMPPTerminal(msgByte);
                    CMPPTerminalResp terminalResp1 = CMPPTerminalResp.build(terminal.getSequenceId());
                    CMPPSender sender = new CMPPSender(client.getDos(),client.getMsgMap(), terminalResp1.toByteArray(),false);
                    client.getThreadPool().submit(sender);
                    client.close();
                    break;
                case CMPP_DELIVER:
                    break;
                default: UNKNOWN:
                    log.info("unkwon message ...");
                    break;
            }
        }
    }
}
