package com.sinobest.framework.util.db;

/**
 * Created by liulv on 2017/8/31.
 *
 * DB数据库连接的常量配置
 * DB查询SQL
 */
public class DBUtilCONF {

    /**
     * OracleDriver
     */
    static String dbDriver = "oracle.jdbc.driver.OracleDriver";
    /**
     * 获取系列ID
     */
    final static String SELCT_GET_ID = "select getid() from dual";
    /**
     * 获取当前系统时间
     */
    final static String GET_SYSDATE_SQL = "select sysdate from dual";
    /**
     * DB_URL_CODE
     */
    static final String DB_URL_CODE = "o_db_url";
    /**
     * DB_CONNECTION_POOLNUM
     */
    static final String DB_CONNECTION_POOLNUM = "db_conn_pool_num";
    /**
     *DB_USERNAME_CODE
     */
    static final String DB_USERNAME_CODE = "o_db_username";
    /**
     *DB_PASSWORD_CODE
     */
    static final String DB_PASSWORD_CODE = "o_db_password";
    /**
     * P_TYPE 用作参数传递读取 xxx-site.xml文件，默认为1读取core-site.xml文件配置
     */
    static final Integer P_TYPE = 1;

}
