package com.qq.socket.Thread;

import com.qq.socket.SocketThread;
import com.qq.socket.ServerSocketListener;
import com.qq.socket.lib.Session;
import java.io.IOException;
import java.util.Objects;


public class ServerClient extends Thread  implements SocketThread {
    
    
    
    private Session session;
    private boolean isrunning = true;
    private ServerSocketListener listener;
   
    public ServerClient(Session session,ServerSocketListener listener) {
        this.session = session;
        this.listener = listener;
     
    }
    @Override
    public void shutdown() throws IOException{
        synchronized(this){
            session.shutdown();
            isrunning = false;
        }
    }

    @Override
    public void run() {
        while (isrunning) {            
            try {
                session.getSocket().sendUrgentData(0xff); //发送心跳包，如果发送失败说明断线
                Object recv = session.receive();
                if(recv!=null){
                    listener.onMessage(recv, session);
                }
                
                
            } catch (IOException e) {
                if(!isrunning){
                    return;
                }
                if (e.getMessage()==null||e.getMessage().toLowerCase().equals("connection reset") || e.getMessage().toLowerCase().equals("socket closed")||e.getMessage().equals("Software caused connection abort: socket write error")) {
                    listener.onClose(session);
                   
                    isrunning = false;
                } else if(isrunning){
                    listener.onError(e);
                }
            } catch (ClassNotFoundException ex) {
               if(isrunning) listener.onError(ex);
            }catch (NullPointerException e3) {
                listener.onClose(session);
              
                isrunning = false;
            }
        }
        
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.session);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServerClient other = (ServerClient) obj;
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        return true;
    }

}
