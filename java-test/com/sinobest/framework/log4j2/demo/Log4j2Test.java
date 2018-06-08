package com.sinobest.framework.log4j2.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liulv
 * @date 2018/6/8
 */
public class Log4j2Test {

    private static Logger logger =
            //LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
            LoggerFactory.getLogger(Log4j2Test.class);

    public static void main(String[] args) {
        logger.info("{}以及{}","张三","李四");
    }
}
