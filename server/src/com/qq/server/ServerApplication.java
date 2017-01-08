package com.qq.server;


import com.qq.socket.encoder.SocketEncoder;
import com.qq.socket.lib.Server;
import com.qq.util.Props;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        Props props = Props.load("server/socket");
        int port = props.getInt("port");

        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket,new ServerListener());
        server.start();
        System.out.println("服务器已经启动！端口："+port);
    }
}
