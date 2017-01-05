package com.qq.server.util;

import com.qq.util.Props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库
 */
public class JdbcUtil {
    private static final Props props = Props.load("server/jdbc");

    static {
        try {
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("无法加载数据库驱动！",e);
        }
    }

    public static Connection connect() throws SQLException{
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String pwd = props.getProperty("jdbc.pwd");
        return DriverManager.getConnection(url,user,pwd);
    }


}
