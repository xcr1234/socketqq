package com.qq.util;

import com.qq.bean.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 保存密码
 */
public class PasswordSave {
    private static final File file = new File(System.getProperty("java.io.tmpdir"),"swingqq.bin");

    public static UserInfo load(){
        if(!file.exists()){
            return null;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (UserInfo)objectInputStream.readObject();
        }catch (Exception e){
            return null;
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                }catch (IOException e){}
            }
        }
    }

    public static void save(long qq,String pwd){
        UserInfo userInfo = new UserInfo();
        userInfo.setQq(qq);
        userInfo.setPassword(pwd);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }catch (IOException e){}
            }
        }
    }

    public static void remove(){
        if(file.exists()){
            file.delete();
        }
    }
}
