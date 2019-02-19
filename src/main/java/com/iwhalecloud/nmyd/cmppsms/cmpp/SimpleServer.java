package com.iwhalecloud.nmyd.cmppsms.cmpp;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.*;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {


    private static int port = 7890;
    private static Logger log = LoggerFactory.getLogger(SimpleServer.class);

    public static void server(){

        try{


            ServerSocket serverSocket = new ServerSocket(SimpleServer.port);
            log.info("start server on port {} ...", port);

            DataInputStream in = null;
            DataOutputStream out = null;
            while (true){

                Socket socket = null;

                try{

                    socket = serverSocket.accept();
                    log.info("accept client {}" ,socket.getRemoteSocketAddress());
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());

                    while(true){
                        if(in.available() > 0 ){

                            byte[] msgByte = new byte[in.available()];
                            in.read(msgByte);

                            CMPPHeader header = new CMPPHeader(msgByte);
                            Integer sequenceId = header.getSequenceId();
                            final CMPPCommandIdEnum commandIdEnum = CMPPCommandIdEnum.getEnum(header.getCommandId());

                            log.info("receive {}, message_id = {}, length = {}, [{}]", commandIdEnum.getMessage() ,header.getSequenceId(), header.getTotalLength(), CMPPUtil.toPrintHexString(msgByte));
                            switch (commandIdEnum){
                                case CMPP_CONNECT:
                                    CMPPConnectResp connectResp = CMPPConnectResp.bulid(sequenceId,"123123");
                                    out.write(connectResp.toByteArray());
                                    break;
                                case CMPP_ACTIVE_TEST:
                                    CMPPActiveTestResp activeTestResp = CMPPActiveTestResp.build(sequenceId);
                                    out.write(activeTestResp.toByteArray());
                                    break;
                                case CMPP_SUBMIT:
                                    CMPPSubmitResp submitResp = CMPPSubmitResp.build(sequenceId,0L);
                                    out.write(submitResp.toByteArray());
                                    break;
                                case CMPP_TERMINATE:
                                    CMPPTerminalResp terminalResp = CMPPTerminalResp.build(sequenceId);
                                    out.write(terminalResp.toByteArray());
                                    break;
                                case CMPP_QUERY:
                                    CMPPQueryResp queryResp = CMPPQueryResp.build(sequenceId, "111");
                                default:
                                    log.info("unkwon message ...");
                                    break;

                            }
                        }

                    }

                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(in != null){
                        in.close();
                    }
                    if(out != null){
                        out.close();
                    }
                    if(socket != null){
                        socket.close();
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){

        server();

    }
}
