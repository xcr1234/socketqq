package com.qq.socket.encoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 将java对象写到socket的i/o中
 */
public interface SocketEncoder {
    void write(OutputStream out,Object object) throws IOException;
    Object read(InputStream in) throws IOException, ClassNotFoundException;
}
