package com.qq;

import com.qq.bean.Message;
import com.qq.bean.MessageType;
import com.qq.socket.SocketListener;
import com.qq.socket.lib.Session;
import com.qq.view.MainFrame;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ClientListener implements SocketListener {

    private Message message;
    private Throwable throwable;
    private boolean closed = false;
    private MainFrame mainFrame;
    CountDownLatch countDownLatch;

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isClosed() {
        return closed;
    }

    public Message getMessage() {
        return message;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void clear(){
        this.message = null;
        this.throwable = null;
    }

    @Override
    public void onMessage(Object message, Session source) throws IOException {
        this.message = (Message)message;
        System.out.println("收到消息"+message);
        if(this.countDownLatch!=null){
            countDownLatch.countDown();
        }
        if(this.message.getType() == MessageType.MESSAGE && this.mainFrame!=null&&this.mainFrame.isDisplayable()){
            this.mainFrame.onMessage(this.message);
        }
    }

    @Override
    public void onError(Exception e) {
        this.throwable = e;
        if(this.countDownLatch!=null){
            countDownLatch.countDown();
        }
    }

    @Override
    public void onClose(Session client) {
        this.closed = true;
    }
}
