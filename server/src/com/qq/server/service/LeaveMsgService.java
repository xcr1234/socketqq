package com.qq.server.service;

import com.qq.bean.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理离线消息，离线消息可以存到数据库中，但这里暂时就放在内存中吧！
 */
public class LeaveMsgService {

    private Map<Integer,List<Message>> msgMap = new ConcurrentHashMap<>();

    public List<Message> get(int id){
        //获得离线消息,id为用户的id.
        return msgMap.get(id);
    }

    public void put(int id,Message message){
        //放入离线消息
        List<Message> list = msgMap.get(id);
        if(list == null){
            msgMap.put(id,list = new ArrayList<>());
        }
        list.add(message);
    }

}
