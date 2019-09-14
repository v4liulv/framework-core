package com.sinobest.framework.util;

import java.io.File;
import java.util.Objects;

/**
 * @author liulv
 * @date 2019/9/5
 * @time 15:09
 *
 * @description 读取程序执行相对的路径的工具类
 */
public class PathUtil {
    /**
     * Classpath
     */
    public static String CLASS_PATH = Objects.requireNonNull(
            Thread.currentThread().getContextClassLoader().getResource("")).getPath();

    /**
     * 当前执行命令目录的同级目录config
     */
    public static String RUN_CONFIG_PATH = System.getProperty("user.dir").substring(0,
            System.getProperty("user.dir").lastIndexOf(File.separator)) + File.separator + "config";
}
