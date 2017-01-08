package com.qq.util;


import java.io.File;
import java.net.URL;

public class Audio {

    public static final String SYSTEM;
    public static final String MSG;

    static {
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
            throw new RuntimeException("你运行的是"+os+",系统，目前只支持windows!");
        }
        int bits = Integer.valueOf(System.getProperty("sun.arch.data.model"));
        if(bits==32){
            URL url = Audio.class.getResource("native/player32.dll");
            if(url == null){
                throw new RuntimeException("找不到com.qq.util.native.player32.dll!");
            }
            System.load(url.getPath());
        }else if(bits==64){
            URL url = Audio.class.getResource("native/player64.dll");
            if(url == null){
                throw new RuntimeException("找不到com.qq.util.native.player64.dll!");
            }
            System.load(url.getPath());
        }else{
            System.out.println("不支持的系统位数"+bits);
        }

        URL u1 = Audio.class.getResource("../sound/msg.wav");
        if(u1 == null){
            throw new RuntimeException("找不到com.qq.sound.msg.wav!");
        }
        MSG = new File(u1.getPath()).getAbsolutePath();

        URL u2 = Audio.class.getResource("../sound/system.wav");
        if(u2 == null){
            throw new RuntimeException("找不到com.qq.sound.system.wav!");
        }
        SYSTEM = new File(u2.getPath()).getAbsolutePath();

    }

    /**
     * c++ native播放音乐
     * @param file 音乐路径，目前只支持wav格式
     */
    private static native void play(String file);


    public static void playWav(String file){
        File f = new File(file);
        if(!f.exists() || f.isDirectory()){
            throw new RuntimeException(file+" is not a valid file");
        }
        play(file);
    }

    public static void playAsync(String file){
        //play是同步方法，直接调用会阻塞主线程，故需要新开一个线程！
        new Thread(new Runnable() {
            @Override
            public void run() {
                playWav(file);
            }
        }).start();
    }


    public static void main(String[] args) {

    }


}
