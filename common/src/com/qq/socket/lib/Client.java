package com.qq.socket.lib;

import com.qq.socket.SocketListener;
import com.qq.socket.Thread.ClientRunning;
import java.io.IOException;

import java.net.Socket;

/**
 * 处理客户端初始化的类，该类用于给服务端发送消息和接收服务端的消息。
 * 该类继承于Session。该类中同时还维护了一个线程，用于不停地接收消息以及产生Listener事件。
 * @author user
 */
public class Client extends Session implements SocketService{
    private SocketListener listener;
    private ClientRunning thread;
    /**
     * 创建客户端服务
     * @param socket new Socket(IP地址，端口号）
     * @param listener 事件监听器
     */
    public Client(Socket socket,SocketListener listener){
        super(socket);
        this.listener = listener;
        
    }
    
    
    @Override
    public void start(){
        thread = new ClientRunning(this,this.listener);
        thread.start();
    }
    
    @Override
    public void shutdown() throws IOException{
        thread.shutdown();
        this.getSocket().close();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Thread getThread() {
        return thread;
    }
    
    
}
