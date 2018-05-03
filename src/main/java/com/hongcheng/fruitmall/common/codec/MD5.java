package com.hongcheng.fruitmall.common.codec;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1,digest);
            String hashtext = number.toString(16);
            // 用0填充为32位的数据.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        System.out.println(getMD5("aaaaaaaaaaaaaaaaa+2018-03-21 00:00:00"));
//    }
}
