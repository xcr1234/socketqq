/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qq.socket;

import com.qq.socket.lib.Session;

import java.io.IOException;

/**
 *
 * @author user
 */
public interface ServerSocketListener extends SocketListener{
    public void onConn(Session client) throws IOException;
   
    
}
