package com.qq.socket.lib;

import com.qq.socket.ServerSocketListener;
import com.qq.socket.Thread.ServerListener;
import com.qq.socket.encoder.SocketEncoder;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.HashSet;

/**
 * 处理服务端服务的类，开启后将创建一个线程，接收客户端的请求并处理其中的消息。
 * @author user
 */
public class Server implements SocketService{
    private ServerSocket serverSocket;
    private ServerListener thread;
    private ServerSocketListener listener;
    private SocketEncoder encoder;
    /**
     * 创建服务端服务
     * @param serverSocket new ServerSocket(端口号）
     * @param listener 事件监听器
     */
    public Server(ServerSocket serverSocket,ServerSocketListener listener) {
        this.serverSocket = serverSocket;
        this.listener = listener;
    }

    public SocketEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(SocketEncoder encoder) {
        this.encoder = encoder;
    }

    public ServerSocketListener getListener() {
        return listener;
    }

    public void setListener(ServerSocketListener listener) {
        this.listener = listener;
    }

    
    @Override
    public void start(){
        thread = new ServerListener(serverSocket, this.listener,this);
        thread.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public ServerListener getThread() {
        return thread;
    }
    public HashSet<Session> getSessions(){
        return thread.getSessions();
    }
    
    @Override
    public void shutdown() throws IOException{
        thread.shutdown();
        serverSocket.close();
    }
    
}
