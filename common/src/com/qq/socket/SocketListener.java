/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qq.socket;

import com.qq.socket.lib.Session;
import java.io.IOException;


public interface SocketListener {
    public void onMessage(Object message, Session source) throws IOException;
    public void onError(Exception e);
    public void onClose(Session client);
}
