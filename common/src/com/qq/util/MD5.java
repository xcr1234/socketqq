package com.qq.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);     //如果出现了这个错误，你可以去买彩票了！
        }

        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }
}
