package com.qq.socket.Thread;

import com.qq.socket.SocketThread;
import com.qq.socket.SocketListener;
import com.qq.socket.lib.Session;
import java.io.IOException;

/**
 * 客户端运行的线程，不断从服务端接受消息。
 *
 * @author user
 */
public class ClientRunning extends Thread implements SocketThread {

    private Session session;
    private boolean running = true;
    private SocketListener listener;

    public ClientRunning(Session session, SocketListener listener) {
        this.session = session;
        this.listener = listener;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Object recv = session.receive();
                if (recv != null) {
                    listener.onMessage(recv, session);
                }
            } catch (IOException e) {
                if(!running){
                    return;
                }
                
                if (e.getMessage()==null||e.getMessage().toLowerCase().equals("connection reset") || e.getMessage().toLowerCase().equals("socket closed")) {
                    listener.onClose(session);
                    running = false;
                } else if(running){
                    listener.onError(e);
                }
            } catch (ClassNotFoundException ex) {
                listener.onError(ex);

            }
        }
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            running = false;
            listener.onClose(session);
        }
    }
}
