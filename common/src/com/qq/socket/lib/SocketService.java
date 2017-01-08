/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qq.socket.lib;

import java.io.IOException;

/**
 *
 * @author user
 */
public interface SocketService {
    public void start() throws IOException;
    public void shutdown() throws IOException;
    public Thread getThread();
}
