package com.qq.server.service;


import com.qq.bean.UserInfo;
import com.qq.server.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FriendService {

    public boolean add(UserInfo userInfo1,UserInfo userInfo2) throws SQLException{
        return add(userInfo1.getId(),userInfo2.getId());
    }

    private static final String addSql = "insert into friends(userinfo1,userinfo2) values (?,?)";
    public boolean add(int id1,int id2) throws SQLException{
        //加好友
        try (Connection connection = JdbcUtil.getConnection()){
            int i = Integer.min(id1,id2);
            int j = Integer.max(id1,id2);
            PreparedStatement ps = connection.prepareStatement(addSql);
            ps.setInt(1,i);
            ps.setInt(2,j);
            return ps.executeUpdate() > 0;
        }
    }

    private static final String delSql = "delete from friends where userinfo1 = ? and userinfo2 = ?";
    public boolean delete(int id1,int id2) throws SQLException{
        //删除好友关系
        try (Connection connection = JdbcUtil.getConnection()){
            int i = Integer.min(id1,id2);
            int j = Integer.max(id1,id2);
            PreparedStatement ps = connection.prepareStatement(delSql);
            ps.setInt(1,i);
            ps.setInt(2,j);
            return ps.executeUpdate()>0;
        }
    }

    public boolean delete(UserInfo u1,UserInfo u2) throws SQLException{
        return delete(u1.getId(),u2.getId());
    }

}
