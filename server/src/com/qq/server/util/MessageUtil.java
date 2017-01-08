package com.qq.server.util;


import com.qq.bean.Message;
import com.qq.bean.MessageType;

public class MessageUtil {
    public static Message error(String content){
        Message message = new Message(MessageType.ERROR);
        message.setContent(content);
        return message;
    }

    public static Message success(){
        return new Message(MessageType.SUCCESS);
    }
}
