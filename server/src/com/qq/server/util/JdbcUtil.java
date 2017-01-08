package com.qq.server.util;

import com.qq.util.Props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库
 */
public class JdbcUtil {

    private static final String url,user,pwd;

    static {
        Props props = Props.load("server/jdbc");
        url = props.getProperty("jdbc.url");
        user = props.getProperty("jdbc.user");
        pwd = props.getProperty("jdbc.pwd");

        try {
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("无法加载数据库驱动！",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pwd);
    }


}
