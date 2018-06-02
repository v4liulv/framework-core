package com.sinobest.framework.util.demo;

/**
 * Created by liulv on 2017/6/29.
 *
 * log4j 不起作用检查
 */
public class Log4jChackTest
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(ClassLoader.getSystemResource("log4j.properties"));
        System.out.println(ClassLoader.getSystemResource("log4j.xml"));
    }
}
