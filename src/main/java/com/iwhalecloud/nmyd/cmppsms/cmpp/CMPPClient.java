package com.iwhalecloud.nmyd.cmppsms.cmpp;


import com.iwhalecloud.nmyd.cmppsms.cmpp.config.CMPPConfig;
import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPConnectErrorEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPSubmitErrorEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.*;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class CMPPClient {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ConcurrentHashMap<Integer, Object> msgMap = new ConcurrentHashMap<Integer, Object>();

    private Socket clientSocket;

    private CMPPConfig config; //短信网关配置

    private DataInputStream dis;

    private DataOutputStream dos;

    private int reconnections = 0;

    private volatile boolean running = false;

    private Thread recevieThread = null;

    private Thread keepAliveThread = null;

    /*
        Java通过Executors提供四种线程池，分别为：
        newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
        newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */

    ExecutorService threadPool = Executors.newFixedThreadPool(5);


    private CMPPClient() {
    }

    public CMPPClient(CMPPConfig config){
        this.config = config;

    }

    public void start(){

        if(running)return;

        if (config == null || config.getIp() == null|| config.getPort() == 0){

            log.error("没有读取到配置或者ip、port配置为空");
            return;
        }
        try {


            clientSocket = new Socket(config.getIp(), config.getPort());
            clientSocket.setKeepAlive(true);
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());



            if(recevieThread == null){
                CMPPReceiver receiver = new CMPPReceiver(this);
                recevieThread = new Thread(receiver);
                recevieThread.start();
            }
            //threadPool.execute(receiver);

            //建立连接
            connect();
            if(!running){
                close();
                return;
            }

            //启动心跳
            if(keepAliveThread == null){
                CMPPKeepAlive keepAlive = new CMPPKeepAlive(this);
                keepAliveThread = new Thread(keepAlive);
                keepAliveThread.start();
            }

            //threadPool.submit(keepAlive);

            //keepAlive();
            log.info("cmpp client start finished ...");

        } catch (IOException e) {

            log.error("建立连接失败ip={},port={}",config.getIp(), config.getPort());
            e.printStackTrace();
            close();
        }

    }


    public boolean connect(){

        this.running = false;
        CMPPConnect msg = CMPPConnect.bulid(config.getSpId(), config.getSharedSecret());

        try{

            CMPPSender sender = new CMPPSender(dos, msgMap, msg.toByteArray(),true);

            FutureTask<CMPPConnectResp> futureTask = new FutureTask<CMPPConnectResp>(sender);
            threadPool.submit(futureTask);

            CMPPConnectResp receive = futureTask.get(config.getTimeout(), TimeUnit.SECONDS);

            int status = receive.getStatus();
            if(status == CMPPConnectErrorEnum.SUCCESS.getCode()){

                log.info("connect success ...");
                this.running = true;
                return this.running;
            }else{

                log.error("connect failed ,status={},message={}", status, CMPPConnectErrorEnum.getMessage(status) );
                return false;
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            log.info("get return message timeout");
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public void shutdown() {

        if(isRunning()){
            terminal();
        }
        //keepAliveThread.interrupt();
        recevieThread.interrupt();
        while (recevieThread.isInterrupted()){
            break;
        }
        threadPool.shutdown();
        close();
    }

    public void terminal(){

        CMPPTerminal msg = CMPPTerminal.build();

        try{
            CMPPSender sender = new CMPPSender(dos, msgMap, msg.toByteArray(),true);
            FutureTask<CMPPTerminalResp> futureTask = new FutureTask<CMPPTerminalResp>(sender);
            threadPool.submit(futureTask);

            CMPPTerminalResp receive = futureTask.get(config.getTimeout(), TimeUnit.SECONDS);

            log.info("termial success ...");

        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        this.running = false;
    }

    public void close(){

        try{

            if(dis != null){
                dis.close();
            }
            if(dos != null ){
                dos.close();
            }

            if(clientSocket != null){
                clientSocket.close();
            }

        }catch (Exception e){
            log.info("终止连接异常, {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void reconnect(){

        this.running = false;
        //keepAliveThread.interrupt();
        recevieThread.interrupt();
        recevieThread = null;
        this.close();

        while (reconnections < config.getReconnections()) {
            this.reconnections += 1;
            log.info("进行第{}次重新连接。。。", reconnections);
            this.start();
            if (isRunning()) {
                break;
            }
        }
        log.info("reconnect fail, connection times > {}", reconnections);

    }
    public Map sendSms(String phones, String message) {

        Map resultMap = new HashMap();

        resultMap.put("retCode", 2);
        resultMap.put("msg", "fail");
        resultMap.put("msgId", -1);

        if(!this.running ){

            resultMap.put("msg", "cmpp client is not running");
            return resultMap;

        }

        try {

            byte[] messageUCS2 = message.getBytes("UnicodeBigUnmarked");
            int messageUCS2Len = messageUCS2.length;

            int maxMessageLen = CMPPSubmit.MAX_LENGTH; //UCS2编码最大长度140字节
            int messageUCS2Count = messageUCS2Len % (maxMessageLen - 6) == 0 ? messageUCS2Len / (maxMessageLen - 6): messageUCS2Len / (maxMessageLen - 6) + 1;

            //长短信分为多少条发送

            /**
             * 6位协议头格式：05 00 03 XX MM NN
             * byte 1 : 05, 表示剩余协议头的长度
             * byte 2 : 00, 这个值在GSM 03.40规范9.2.3.24.1中规定，表示随后的这批超长短信的标识位长度为1（格式中的XX值）。
             * byte 3 : 03, 这个值表示剩下短信标识的长度
             * byte 4 : XX，这批短信的唯一标志(被拆分的多条短信,此值必需一致)，事实上，SME(手机或者SP)把消息合并完之后，就重新记录，所以这个标志是否唯一并不是很重要。
             * byte 5 : MM, 这批短信的数量。如果一个超长短信总共5条，这里的值就是5。
             * byte 6 : NN, 这批短信的数量。如果当前短信是这批短信中的第一条的值是1，第二条的值是2。
             * 例如：05 00 03 39 02 01
             */
            byte[] tp_udhiHead = new byte[6];

            tp_udhiHead[0] = 0x05;
            tp_udhiHead[1] = 0x00;
            tp_udhiHead[2] = 0x03;
            tp_udhiHead[3] = 0x0A;
            tp_udhiHead[4] = (byte)messageUCS2Count;
            tp_udhiHead[5] = 0x01;

            int realMsgLength = maxMessageLen - tp_udhiHead.length;

            for(int i = 0; i < messageUCS2Count; i++){
                tp_udhiHead[5] = (byte)(i + 1);
                byte[] msgContent = null;

                if(i != messageUCS2Count -1){
                    //如果不是最后一条
                    msgContent = CMPPUtil.byteAdd(tp_udhiHead, messageUCS2, i*realMsgLength, realMsgLength);

                }else{
                    msgContent = CMPPUtil.byteAdd(tp_udhiHead, messageUCS2, i*realMsgLength, messageUCS2Len - i*realMsgLength);

                }

                CMPPSubmit submit = CMPPSubmit.build(config.getServiceId(),config.getSpId(), phones.split(","), msgContent);

                submit.setTpUdhi((byte) 0x01); //包含短信头
                submit.setMsgFmt((byte) 0x08); //UCS2编码
                submit.setPkTotal((byte) messageUCS2Count);
                submit.setPkNumber((byte) (i + 1));

                CMPPSender sender = new CMPPSender(dos, msgMap, submit.toByteArray(),true);

                FutureTask<CMPPSubmitResp> futureTask = new FutureTask<CMPPSubmitResp>(sender);
                threadPool.submit(futureTask);

                CMPPSubmitResp receive = futureTask.get(config.getTimeout(), TimeUnit.SECONDS);

                resultMap.put("msgId", receive.getMsgId());
                resultMap.put("retCode", receive.getResult());
                resultMap.put("msg", CMPPSubmitErrorEnum.getMessge(receive.getResult()));

            }


        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("msg", e.getMessage());

        } catch (InterruptedException e) {

            e.printStackTrace();
            resultMap.put("msg", e.getMessage());

        } catch (TimeoutException e) {
            log.info("get return message timeout");
            e.printStackTrace();
            resultMap.put("msg", e.getMessage());

        } catch (ExecutionException e) {
            e.printStackTrace();
            resultMap.put("msg", e.getMessage());

        }catch (Exception e){
            resultMap.put("msg", e.getMessage());
            e.printStackTrace();
        }

        return resultMap;
    }


    public String sendActive(){
        try{

            CMPPActiveTest msg = CMPPActiveTest.build();
            CMPPSender sender = new CMPPSender(dos, msgMap, msg.toByteArray(), true);

            FutureTask<CMPPActiveTestResp> futureTask = new FutureTask<CMPPActiveTestResp>(sender);
            threadPool.submit(futureTask);
            CMPPActiveTestResp receive = futureTask.get(config.getTimeout(), TimeUnit.SECONDS);

        }catch (InterruptedException e){
            log.error("停止线程" + e.getMessage());
        }catch (IOException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public CMPPConfig getConfig() {
        return config;
    }

    public void setConfig(CMPPConfig config) {
        this.config = config;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    public ConcurrentHashMap<Integer, Object> getMsgMap() {
        return msgMap;
    }

    public void setMsgMap(ConcurrentHashMap<Integer, Object> msgMap) {
        this.msgMap = msgMap;
    }

    public static void main(String[] args){

        CMPPConfig config = new CMPPConfig("10.211.55.4", 7890);
        config.setSpId("901234");
        config.setSharedSecret("1234");
        config.setLinkDetection(10);
        config.setTimeout(5);
        CMPPClient client = new CMPPClient(config);
        client.start();
    }
}
