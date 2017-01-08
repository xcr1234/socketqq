/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qq.socket;

import java.io.IOException;

/**
 *
 * @author user
 */
public interface SocketThread extends Runnable{
    public void shutdown() throws IOException;
    public void start() throws IOException;
    
}
