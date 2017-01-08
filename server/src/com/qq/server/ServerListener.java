package com.qq.server;

import com.qq.bean.Message;

import com.qq.bean.MessageType;
import com.qq.bean.UserInfo;
import com.qq.server.service.LeaveMsgService;
import com.qq.server.service.UserInfoService;
import com.qq.server.util.MessageUtil;
import com.qq.socket.ServerSocketListener;
import com.qq.socket.lib.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class ServerListener implements ServerSocketListener {

    private Map<Integer,Session> sessionMap = new ConcurrentHashMap<>();

    private UserInfoService userInfoService = new UserInfoService();
    private LeaveMsgService leaveMsgService = new LeaveMsgService();


    public ServerListener(){

    }

    @Override
    public void onMessage(Object m, Session source) throws IOException {

        Message message = (Message)m;

        //根据message的不同，转发到不同的处理流程中
        switch (message.getType()){
            case LOGIN:
                doLogin(message,source);
                break;
            case POLL:
                pollFriends(message,source);
                break;
            case MESSAGE:
                sendMessage(message,source);
                break;


        }

    }


    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onClose(Session client) {
        System.out.println("客户已离线！");

        Set<Map.Entry<Integer,Session>> entrySet = sessionMap.entrySet();
        Iterator<Map.Entry<Integer,Session>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,Session> entry = iterator.next();
            if(entry.getValue() == client){
                iterator.remove();
            }
        }
    }

    @Override
    public void onConn(Session client) throws IOException {
        System.out.println("客户已连接！");
    }

    private void doLogin(Message m, Session source) throws IOException{

        if(m.getFrom()==null){
            source.send(MessageUtil.error("您的输入有误！"));
        }

        long qq = m.getFrom().getQq();
        String pwd = m.getFrom().getPassword();

        try {
            UserInfo userInfo = userInfoService.find(qq,pwd);
            if(userInfo==null){
                source.send(MessageUtil.error("QQ号和密码不正确！"));
                return;
            }
            sessionMap.put(userInfo.getId(),source);
            Message message = MessageUtil.success();
            userInfo.setPassword(null);//密码是绝密，不能发送出去！
            message.setFrom(userInfo);
            source.send(message);
        } catch (SQLException e) {
            e.printStackTrace();
            source.send(MessageUtil.error("服务器发生了异常！"));
        }

    }

    private void pollFriends(Message message, Session source) throws IOException{
        if(message.getFrom()==null){
            source.send(MessageUtil.error("您的输入有误！"));
        }
        int id = message.getFrom().getId();
        try {
            List<UserInfo> userInfos = userInfoService.findFriends(id);
            Message res = new Message(MessageType.SUCCESS);
            res.setData(userInfos);
            source.send(res);
        } catch (SQLException e) {
            e.printStackTrace();
            source.send(MessageUtil.error("服务器发生了异常！"));
        }
    }

    private void sendMessage(Message message, Session source) throws IOException{

        UserInfo target = message.getTo();
        if(target==null){
            source.send(MessageUtil.error("参数错误"));
            return;
        }

        //取出对方的Session，如果对方在线，则直接发送；如果不在线，则发送到离线消息中，在该用户上线后发送出去
        Session session = sessionMap.get(target.getId());
        if(session == null){
            leaveMsgService.put(target.getId(),message);
        }else{
            session.send(message);
        }
        source.send(MessageUtil.success());
    }






}
