package com.qq.socket.encoder;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public final class BasicEncoder implements SocketEncoder{

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @Override
    public void write(OutputStream out, Object object) throws IOException {
        if(oos==null){
            oos = new ObjectOutputStream(out);
        }
        oos.writeObject(object);
    }

    @Override
    public Object read(InputStream in) throws IOException, ClassNotFoundException {
        if(ois==null){
            ois = new ObjectInputStream(in);
        }
        return ois.readObject();
    }
}
