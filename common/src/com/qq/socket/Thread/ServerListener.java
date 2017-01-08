package com.qq.socket.Thread;

import com.qq.socket.SocketThread;
import com.qq.socket.ServerSocketListener;
import com.qq.socket.lib.Server;
import com.qq.socket.lib.Session;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;


/**
 * 服务器启动后监听连接的线程，当有client连接后构建一个ServerRunning线程与客户端交互。
 * @author user
 */
public class ServerListener extends Thread implements SocketThread {
    private boolean  isrunning = true;
    private ServerSocket serverSocket;  
    private ServerSocketListener listener;
    private Server server;
    
    private HashSet<Session> sessions = new HashSet<>();
    private HashSet<ServerClient> runnings = new HashSet<>();
    public ServerListener(ServerSocket serverSocket, ServerSocketListener listener, Server server) {
        this.serverSocket = serverSocket;
        this.listener = listener;
        this.server = server;
    }

    public HashSet<Session> getSessions() {
        return sessions;
    }

    public HashSet<ServerClient> getRunnings() {
        return runnings;
    }

    
    
    
    @Override
    public void run() {
        while (isrunning) {   
            Socket client = null;
            if(serverSocket.isClosed()){
                isrunning = false;
                break;
            }
            try {
                client = serverSocket.accept();
                Session session = Session.createSession(client,this,server.getEncoder());
                sessions.add(session);
                listener.onConn(session);
                ServerClient serverClient = new ServerClient(session, listener);
                runnings.add(serverClient);
                serverClient.start();
            } catch (IOException e) {
                if(!isrunning){
                    return;
                }
                
                if (e.getMessage().toLowerCase().equals("connection reset") || e.getMessage().toLowerCase().equals("socket closed")||e.getMessage().equals("Software caused connection abort: socket write error")) {
                   Session session = Session.createSession(client,server.getEncoder());
                    listener.onClose(session);
                   
                } else if(isrunning) {
                   
                    listener.onError(e);
                }
                
            } 
            
        }
       
        
        
    }
    
    @Override
    public void shutdown() throws IOException{
        synchronized(this){
            serverSocket.close();
            isrunning = false;
        }
    }
   

}
