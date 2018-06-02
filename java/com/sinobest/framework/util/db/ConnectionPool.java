package com.sinobest.framework.util.db;

import com.sinobest.framework.util.BaseConfig;
import com.sinobest.framework.util.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author liulv
 * @date 2017/10/23
 *
 * Oracle数据库池 简单实现
 */
public class ConnectionPool {

    private static LinkedList<Connection> connectionQueue;

    static{
        try {
            Class.forName(DBUtilCONF.dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public synchronized static Connection getConnection() {
        try {
            if(connectionQueue == null){
                connectionQueue = new LinkedList<>();
                int dbConnectionPoolNum;
                try{
                   dbConnectionPoolNum = Integer.parseInt(PropertyUtil.getValue(BaseConfig.PROPERTIES_TYPE, DBUtilCONF.DB_CONNECTION_POOLNUM));
                }catch (NumberFormatException | NullPointerException e ){
                    System.err.println("core-site.xml的CODE为DB_CONNECTION_POOLNUM 的value为空或者非整数数值！");
                    //默认连接池的连接数为5
                    dbConnectionPoolNum = 5;
                }

                for(int i = 0; i < dbConnectionPoolNum; i++){
                    String dbUrl = PropertyUtil.getValue(BaseConfig.PROPERTIES_TYPE, DBUtilCONF.DB_URL_CODE);
                    String dbUsername = PropertyUtil.getValue(BaseConfig.PROPERTIES_TYPE, DBUtilCONF.DB_USERNAME_CODE);
                    String dbPassword = PropertyUtil.getValue(BaseConfig.PROPERTIES_TYPE, DBUtilCONF.DB_PASSWORD_CODE);

                    assert dbUrl != null;
                    Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                    connectionQueue.push(conn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connectionQueue.poll();
    }

    public static void returnConnection(Connection conn) {
        if(conn != null)
        connectionQueue.push(conn);
    }
}
