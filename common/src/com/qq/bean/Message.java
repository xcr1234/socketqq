package com.qq.bean;

import java.io.Serializable;



public class Message implements Serializable{
    private static final long serialVersionUID = 7738549396061297091L;
    private UserInfo from;
    private UserInfo to;
    private String content;
    private String time;
    private Object data;

    public Message() {
    }

    public Message(MessageType type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                ", type=" + type +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Message(UserInfo from, UserInfo to, String content, String time, MessageType type) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public UserInfo getFrom() {
        return from;
    }

    public void setFrom(UserInfo from) {
        this.from = from;
    }

    public UserInfo getTo() {
        return to;
    }

    public void setTo(UserInfo to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    private MessageType type;
}
