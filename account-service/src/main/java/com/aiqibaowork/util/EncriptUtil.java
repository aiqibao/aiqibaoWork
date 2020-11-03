package com.aiqibaowork.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 密钥加解密
 * @Author:aiqibao
 * @Date:2020/11/3 14:28
 * Best wish!
 */
@Slf4j
public class EncriptUtil {
    private static final String KEY = "pio-tech" ;

    /**
     * 加密数据
     * @param message
     * @return
     */
    public static byte[] encrypt(String message){
        try {
            //采用3DES加密
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding") ;
            int i = KEY.getBytes("UTF-8").length ;
            System.out.println("==========="+i);
            DESKeySpec deSedeKeySpec = new DESKeySpec(KEY.getBytes("UTF-8")) ;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES") ;
            SecretKey secretKey = keyFactory.generateSecret(deSedeKeySpec) ;
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8")) ;
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);
            return cipher.doFinal(message.getBytes("UTF-8")) ;
        }catch (Exception e){
            e.printStackTrace();
            return new byte[0] ;
        }
    }

    /**
     * 解密数据
     * @param message
     * @return
     */
    public static String decrypt(String message){
        try {
            byte[] bytes = covertHexString(message) ;
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding") ;
            DESKeySpec deSedeKeySpec = new DESKeySpec(KEY.getBytes("UTF-8")) ;
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES") ;
            SecretKey secretKey = keyFactory.generateSecret(deSedeKeySpec) ;
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8")) ;
            cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
            byte[] reByte = cipher.doFinal(bytes) ;
            return new String(reByte) ;
        }catch (Exception e){
            e.printStackTrace();
            return "" ;
        }
    }

    /**
     * 将String的偶数位转为16进制的byte
     * @param ss
     * @return
     */
    public static byte[] covertHexString(String ss){
        byte[] digest = new byte[ss.length() / 2] ;
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2*i,2*i+2) ;
            int byteValue = Integer.parseInt(byteString,16) ;
            digest[i] = (byte) byteValue ;
        }
        return digest ;
    }

    /**
     * 将byte转为String
     * @param b
     * @return
     */
    public static String toHexString(byte[] b){
        StringBuffer hexString = new StringBuffer() ;
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]) ;
            if (plainText.length()<2){
                plainText = "0" + plainText ;
            }
            hexString.append(plainText) ;
        }
        return hexString.toString() ;
    }

    /**
     * 手机号加密
     * @param phoneNum
     * @return
     */
    public static String encryptionPhoneNumer(String phoneNum){
        log.info("手机号==={}",phoneNum);
        String passengerPhoneNum = null ;
        try {
            String phoneNums = URLEncoder.encode(phoneNum,"utf-8").toLowerCase() ;
            passengerPhoneNum = toHexString(EncriptUtil.encrypt(phoneNums)).toUpperCase() ;
            log.info("加密手机号=={}",passengerPhoneNum);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return passengerPhoneNum ;
    }

    public static String decryptionPhoneNum(String phoneNum){
        log.info("加密的手机号==={}",phoneNum);
        String phoneNumers = null ;
        try {
            phoneNumers = URLDecoder.decode(decrypt(phoneNum),"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("解密后的手机号为==={}",phoneNumers);
        return phoneNumers ;
    }

    public static void main(String[] args) {

        String phone = "3071D031690025BE062B04B2B5D59652";
        String newPhone = decryptionPhoneNum(phone);
        System.out.println("==="+newPhone);


        String phoneNum = "18682248362" ;
        String newPhoneNum = encryptionPhoneNumer(phoneNum) ;
        System.out.println("加密后密文为==="+newPhoneNum);


    }

}
