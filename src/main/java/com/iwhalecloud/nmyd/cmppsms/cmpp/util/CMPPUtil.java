package com.iwhalecloud.nmyd.cmppsms.cmpp.util;

import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.CMPPHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CMPPUtil {


    public static final String FORMAT_MMddHHmmss = "MMddHHmmss";

    private static final AtomicInteger sequence = new AtomicInteger(1);
    private static final Integer SEQUENCE_MAX_NUM = Integer.MAX_VALUE - 10000;

    private static Logger log = LoggerFactory.getLogger(CMPPUtil.class);
    /**
     * 自增序列-线程安全
     */
    public static Integer generateSequenceId() {
        int sequenceId = sequence.getAndIncrement();
        //序列最大值,超过后,复位序列
        if (sequenceId > SEQUENCE_MAX_NUM) {
            synchronized (sequence) {
                //其set()方法不是原子方法.
                sequence.set(1);
            }
        }

        return sequenceId;
    }

    public static String generateTimestamp(){

        SimpleDateFormat format = new SimpleDateFormat(CMPPUtil.FORMAT_MMddHHmmss);
        return format.format(new Date());
    }



    /**
     * 向流中写入指定字节长度的字符串，不足时补0
     *
     * @param dous:要写入的流对象
     * @param s:要写入的字符串
     * @param len:写入长度,不足补0
     */
    public static void writeString(DataOutputStream dous, String s, int len) {

        try {
            byte[] data = s.getBytes("gb2312");
            if (data.length > len) {
                log.error("向流中写入的字符串超长！要写" + len + " 字符串是:" + s);
            }
            int srcLen = data.length;
            dous.write(data);
            //如果长度不足,补若干个0
            while (srcLen < len) {
                dous.write('\0');
                srcLen++;
            }
        } catch (IOException e) {
            log.error("向流中写入指定字节长度的字符串失败:{}", e.getMessage(), e);
        }
    }

    public static byte[] readByte(DataInputStream inputStream, int len) throws IOException{

        byte[] bytes = new byte[len];
        inputStream.read(bytes);
        return bytes;

    }

    public static String readString(DataInputStream inputStream, int len) throws IOException{
        byte[] bytes = new byte[len];
        inputStream.read(bytes);
        String s = new String(bytes);
        return s.trim();
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }


    /**
     * 从输入流中读取字节注入CMPPHeader对象
     */
    public static void setHeader(DataInputStream inputStream, CMPPHeader header) throws IOException {
        header.setTotalLength(inputStream.readInt());
        header.setCommandId(inputStream.readInt());
        header.setSequenceId(inputStream.readInt());
    }

    public static byte[] readMsgByte(DataInputStream inputStream) throws IOException {

        if (inputStream != null ) {
            int len = inputStream.readInt();

            log.info("msg length = {}", len);

            byte[] lenBtye = intToByteArray(len);

            byte[] msgbyte = new byte[len - 4];

            inputStream.read(msgbyte);


            byte[] totalMsgByte = new byte[lenBtye.length + msgbyte.length];
            System.arraycopy(lenBtye, 0, totalMsgByte, 0, lenBtye.length);
            System.arraycopy(msgbyte, 0, totalMsgByte, lenBtye.length, msgbyte.length);

            return totalMsgByte;
        }
        return null;
    }

    public static byte[] byteAdd(byte[] src, byte[] add, int start, int length){
        int srcLength = src.length;
        byte[] dst = new byte[srcLength + length];

        for(int i = 0; i< srcLength; i++){
            dst[i] = src[i];
        }

        for(int i = 0; i< length; i++){
            dst[srcLength + i ] = add[i+start];
        }

        //System.arraycopy(dst, 0, src, 0, src.length);
        //System.arraycopy(dst, src.length, add, start, length);
        return dst;

    }

    public static String toPrintHexString(byte[] bytes){

        if(bytes == null || bytes.length <= 0){
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        for(byte oneByte: bytes){

            String hex = Integer.toHexString(oneByte & 0xFF);
            if(hex.length() == 1){
                hex = '0' + hex;
            }
            if(count %16 == 0 ){
                stringBuilder.append(hex.toUpperCase()).append("\n");

            }else{
                stringBuilder.append(hex.toUpperCase()).append(" ");

            }
            count ++;

        }
        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        return stringBuilder.toString();
    }
}
