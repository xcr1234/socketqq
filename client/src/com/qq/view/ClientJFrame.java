package com.qq.view;


import com.qq.ClientApplication;
import com.qq.bean.Message;
import com.qq.socket.lib.Client;
import com.qq.ui.MyOptionPane;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClientJFrame extends JFrame{

    private static final long serialVersionUID = -3635522672258974136L;

    private static List<JFrame> windows = new LinkedList<>(); //保持所有窗口的引用，当所有窗口被dispose后，关闭client application

    protected Client client;

    public Message awaitOrAlert(){
        try {
            return ClientApplication.await();
        } catch (InterruptedException e) {
            alertError("连接服务器超时！");
        } catch (ExecutionException e) {
            alertError("服务器异常："+e.getCause().getLocalizedMessage());
        }
        return null;
    }

    public void alertError(String content){
        MyOptionPane.showMessageDialog(this,content,"错误");
    }

    public void alert(String content){
        MyOptionPane.showMessageDialog(this,content,"提示");
    }

    public boolean confirm(String content){
        return MyOptionPane.showConfirmDialog(this,"询问",content,"是","否")==0;
    }


    public void sendOrAlert(Message message){
        try {
            ClientApplication.getClient().send(message);
        } catch (IOException e) {
            e.printStackTrace();
            alertError("发送消息失败："+e.getLocalizedMessage());
        }
    }

    protected ClientJFrame(){
        super();
        this.client = ClientApplication.getClient();
        synchronized (ClientJFrame.class){
            windows.add(this);
        }
    }



    @Override
    public void dispose() {
        synchronized (ClientJFrame.class){
            windows.remove(this);
            if(windows.isEmpty()){
                ClientApplication.shutdown();
            }
        }
        super.dispose();
    }
}
