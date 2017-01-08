package com.qq.socket.lib;

import com.qq.socket.SocketThread;
import com.qq.socket.encoder.BasicEncoder;
import com.qq.socket.encoder.SocketEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

/**
 * 对Socket客户端的封装，当服务端接受到客户端的请求后，通过它对客户端发送消息。
 *
 * @author user
 */
public class Session implements Serializable{

    private static final long serialVersionUID = -6648419680128146562L;
    private static HashMap<Socket, Session> factory = new HashMap<>();
    private Socket socket;

    private SocketThread thread;
    private SocketEncoder socketEncoder = new BasicEncoder();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.socket);
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
        final Session other = (Session) obj;
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        return true;
    }

    public SocketEncoder getSocketEncoder() {
        return socketEncoder;
    }

    public void setSocketEncoder(SocketEncoder socketEncoder) {
        this.socketEncoder = socketEncoder;
    }

    protected Session(Socket socket) {
        this.socket = socket;
    }

    public static Session createSession(Socket socket,SocketEncoder encoder) {
        Session session = factory.get(socket);
        if (session == null) {
            session = new Session(socket);
            factory.put(socket, session);
        }
        if(encoder!=null){
            session.setSocketEncoder(encoder);
        }
        return session;
    }

    public static Session createSession(Socket socket, SocketThread parent,SocketEncoder encoder) {
        Session session = createSession(socket,encoder);
        session.thread = parent;
        return session;
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public Socket getSocket() {
        return socket;
    }



    /**
     * 向目标发送消息
     * @param message
     * @throws IOException
     */
    public void send(Object message) throws IOException {
        this.socketEncoder.write(getOutputStream(),message);
    }

    /**
     * 接收目标的消息
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object receive() throws IOException, ClassNotFoundException {
        return this.socketEncoder.read(getInputStream());
    }

    public void shutdown() throws IOException {
        
        if (!socket.isClosed()) {
            socket.close();
        }

    }

    
}
