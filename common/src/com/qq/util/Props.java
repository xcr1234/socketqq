package com.qq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 处理properties文件的工具类；properties文件应该被放到com.qq包或子包下！
 */
public class Props {

    private static final String path;

    static {
        URL url = Props.class.getResource("../");
        if(url==null){
            throw new RuntimeException("找不到com.qq包！");
        }
        path = url.getFile();
    }

    public static Props load(String name){
        File file = new File(path,name+".properties");
        try {
            return new Props(file);
        } catch (IOException e) {
            throw new RuntimeException("无法加载properties",e);
        }
    }

    private Properties properties;

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Integer getInt(String key){
        return Integer.valueOf(getProperty(key));
    }

    private Props(File file) throws IOException{
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            properties = new Properties();
            properties.load(fileInputStream);
        }
    }
}
