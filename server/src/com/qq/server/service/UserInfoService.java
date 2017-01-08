package com.qq.server.service;


import com.qq.bean.UserInfo;
import com.qq.server.util.JdbcUtil;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserInfoService {

    private static final String addSql = "insert into userinfo(qq,nickname,password,sex,birthday,star,study,blood,phone,email,address) values(?,?,?,?,?,?,?,?,?,?,?)";
    public boolean add(UserInfo userInfo) throws SQLException{
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(addSql);

            setState(ps,userInfo);

            return ps.executeUpdate() > 0;
        }
    }

    //将这段代码提取出来，因为update方法也要用到的
    private void setState(PreparedStatement ps,UserInfo userInfo) throws SQLException {
        ps.setLong(1,userInfo.getQq());
        ps.setString(2,userInfo.getNickname());
        ps.setString(3,userInfo.getPassword());
        ps.setBoolean(4,userInfo.isSex());
        ps.setString(5,userInfo.getBirthday());
        ps.setString(6,userInfo.getStar());
        ps.setString(7,userInfo.getStudy());
        ps.setString(8,userInfo.getBlood());
        ps.setString(9,userInfo.getPhone());
        ps.setString(10,userInfo.getEmail());
        ps.setString(11,userInfo.getAddress());
    }

    private static final String delSql = "delete FROM userinfo where id = ?";
    public boolean delete(UserInfo userInfo) throws SQLException{
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(delSql);
            ps.setInt(1,userInfo.getId());
            return ps.executeUpdate()>0;
        }
    }

    private static final String updateSql = "update userinfo set qq=?,nickname=?,password=?,sex=?,birthday=?,star=?,study=?,blood=?,phone=?,email=?,address=? WHERE id=?";
    public boolean update(UserInfo userInfo) throws SQLException{
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(updateSql);
            setState(ps,userInfo);
            ps.setInt(12,userInfo.getId());
            return ps.executeUpdate() > 0;
        }
    }

    private UserInfo get(ResultSet resultSet) throws SQLException{
        //从resultSet中提取UserInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setId(resultSet.getInt("id"));
        userInfo.setQq(resultSet.getLong("qq"));
        userInfo.setNickname(resultSet.getString("nickname"));
        userInfo.setPassword(resultSet.getString("password"));
        userInfo.setSex(resultSet.getBoolean("sex"));
        userInfo.setBirthday(resultSet.getString("birthday"));
        userInfo.setStar(resultSet.getString("star"));
        userInfo.setStudy(resultSet.getString("study"));
        userInfo.setBlood(resultSet.getString("blood"));
        userInfo.setPhone(resultSet.getString("phone"));
        userInfo.setEmail(resultSet.getString("email"));
        userInfo.setAddress(resultSet.getString("address"));
        return userInfo;
    }

    private final static String getSql = "select * from userinfo where id = ?";
    public UserInfo get(int id) throws SQLException{
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(getSql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return get(resultSet);
            }else{
                return null;
            }
        }
    }

    private static final String findSql = "select * from userinfo where qq = ? and password = ?";
    public UserInfo find(long qq,String pwd) throws SQLException{
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(findSql);
            ps.setLong(1,qq);
            ps.setString(2,pwd);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return get(resultSet);
            }else{
                return null;
            }
        }
    }

    private static final String findFriendsSql = "select * from userinfo where id in (select userinfo2 as id FROM friends where userinfo1 = ?) or id in (select userinfo1 as id from friends where userinfo2 = ?)";
    public List<UserInfo> findFriends(int id) throws SQLException{
        //查询一个用户所有的好友
        try (Connection connection = JdbcUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(findFriendsSql);
            ps.setInt(1,id);
            ps.setInt(2,id);
            List<UserInfo> userInfoList = new ArrayList<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                userInfoList.add(get(resultSet));
            }
            return userInfoList;
        }
    }
}
