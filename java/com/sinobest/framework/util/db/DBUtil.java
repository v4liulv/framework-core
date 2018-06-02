package com.sinobest.framework.util.db;

import java.sql.*;

/**
 * Created by liulv on 2017/8/31.
 *
 * MapReduce底层无法通过ssh获取对应的ORACLE连接
 * 使用原生态的连接方式，读取HBASE的CONFIG的数据库连接URL和用户密码
 */
public class DBUtil
{
    /**
     * 获取ORACLE连接，根据gxksh-site.xml的配置
     *
     * @return Oracle Connection
     */
    public static Connection getConnection(){
           return ConnectionPool.getConnection();
    }

    /**
     * 关闭数据库相关资源对象
     * <p>
     * 请在finally里面调用此方法，依次关闭resultset/statement/connection
     *
     * @param obj  Connection Statement ResultSet
     */
    public static void disConnect(Object obj) {
        if (obj == null)
            return;
        try {
            if (obj instanceof Connection) {
                Connection conn = (Connection) obj;
                if (!conn.isClosed()) {
                    conn.close();
                }
            } else if (obj instanceof Statement) {
                ((Statement) obj).close();
            } else if (obj instanceof PreparedStatement) {
                ((PreparedStatement) obj).close();
            }else if (obj instanceof ResultSet) {
                ((ResultSet) obj).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据应用平台的数据库生成主键
     */
    public static String getId() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rtst = null;
        String id = null;
        try {
            conn = ConnectionPool.getConnection();
            stmt = conn != null ? conn.createStatement() : null;
            rtst = stmt != null ? stmt.executeQuery(DBUtilCONF.SELCT_GET_ID) : null;
            if (rtst != null && rtst.next()) {
                id = rtst.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect(rtst);
            disConnect(stmt);
            disConnect(conn);
        }
        return id;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getId());
    }
}
