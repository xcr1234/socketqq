package com.qq.util;


public class StringUtil {
    public static String maxlen(String str,int maxLength){
        if(str.length()>maxLength){
            return str.substring(0,maxLength+1)+"..";
        }else{
            return str;
        }
    }
}
