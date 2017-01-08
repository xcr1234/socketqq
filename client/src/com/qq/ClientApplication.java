package com.qq;

import com.qq.bean.Message;
import com.qq.socket.lib.Client;
import com.qq.ui.MyOptionPane;
import com.qq.util.Props;
import com.qq.view.LoginFrame;
import com.qq.view.MainFrame;


import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ClientApplication {

    private static Client client;
    private static ClientListener clientListener;

    private static final int timeout = 5; //5秒没有消息返回，就认为超时
    private static final TimeUnit timeoutUnit = TimeUnit.SECONDS;   //timeunit是一个很方便的类，统一了时间单位

    public static Client getClient() {
        return client;
    }


    public static synchronized MainFrame getMainFrame() {
        return clientListener.getMainFrame();
    }

    public static synchronized void setMainFrame(MainFrame mainFrame) {
        clientListener.setMainFrame(mainFrame);
    }

    /**
     * 阻塞并等待消息的到来，等待直到消息到来或者异常/或者错误为止。
     * @return 接收到的消息
     * @throws InterruptedException 超时后抛出该异常
     * @throws ExecutionException 接收服务器发生的异常，可以通过{@link Throwable#getCause()}方法得到具体的异常（通常情况下这不会发生）
     */
    public static Message await() throws InterruptedException,ExecutionException{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        clientListener.countDownLatch = countDownLatch;
        countDownLatch.await(timeout, timeoutUnit);
        if(clientListener.getThrowable()!=null){
            throw new ExecutionException(clientListener.getThrowable());
        }
        return clientListener.getMessage();
    }

    public static void shutdown(){
        if(client!=null){
            try {
                client.shutdown();
            }catch (Exception e){}
        }
    }



    public static void main(String[] args) {
        //先连接服务器；
        Props props = Props.load("client");
        String ip = props.getProperty("server.ip");
        int port = props.getInt("server.port");

        try {
            Socket socket = new Socket(ip,port);
            client = new Client(socket,clientListener = new ClientListener());
            client.start();
            System.out.println("客户端已启动！");

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            MyOptionPane.showMessageDialog(null,"连接服务器失败："+e.getLocalizedMessage(),"系统错误");

        }
    }
}
