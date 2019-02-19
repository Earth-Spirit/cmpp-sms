package com.iwhalecloud.nmyd.cmppsms.cmpp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


    public static byte[] MD5(String input) {

        try {

            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");

            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();

            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);

            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();

            // 字符数组转换成字符串返回
            return resultByteArray;

        } catch (NoSuchAlgorithmException e) {
            return null;

        }
    }


    public static byte[] md5(String spid, String password, String timestamp) {
        byte sp[] = spid.getBytes();
        byte bzero[] = new byte[9];
        byte[] bSPpassword = password.getBytes();
        byte btimestamp[] = timestamp.getBytes();
        byte bmd5[] = new byte[sp.length + 9 + bSPpassword.length + btimestamp.length];
        int cur = 0;
        System.arraycopy(sp, 0, bmd5, cur, sp.length);
        cur += sp.length;
        System.arraycopy(bzero, 0, bmd5, cur, 9);
        cur += bzero.length;
        System.arraycopy(bSPpassword, 0, bmd5, cur, bSPpassword.length);
        cur += bSPpassword.length;
        System.arraycopy(btimestamp, 0, bmd5, cur, btimestamp.length);
        byte[] result = new byte[16];
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(bmd5);
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static byte[] MD5(byte[] input) {

        try {

            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");

            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input;

            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);

            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            return  resultByteArray;

        } catch (NoSuchAlgorithmException e) {
            return null;

        }
    }
    /**
     * 下面这个函数用于将字节数组换成成16进制的字符串
     * @param byteArray
     * @return
     */
    public static String byteArrayToHex(byte[] byteArray) {

        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };

        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];

        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;

        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];

        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
