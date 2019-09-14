package com.sinobest.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author liulv
 * @date 2019/9/5
 * @time 14:40
 *
 * @description *.properties类别配置文件读取工具类
 * 配置文件必须在classpath的目录下，或者其子目录下，默认配置文件为{@link #DEFAULT_PROPERTIES_FILENAME}
 * 调用方式：PropertiesUtil.getProperties("/props/test.properties")就是读取classpath下props/test.properties文件
 * 调研方式，读取默认文件PropertiesUtil.getProperties() 就是读取classpath下的{@link #DEFAULT_PROPERTIES_FILENAME}文件
 *
 */
public class PropertiesUtil {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 默认properties文件名，通过调用{@link #getProperties}
     */
    public static String DEFAULT_PROPERTIES_FILENAME = "application.properties";

    /**
     * 加载Properties文件，返回{@link java.util.Properties}
     *
     * @param configFile classpath路径下的路径+文件名称
     * @return java.util.Properties
     */
    public static java.util.Properties loadProperties(String configFile) {
        java.util.Properties properties = new java.util.Properties();
        InputStream in = null;
        try {
            String configFilePath = PathUtil.CLASS_PATH + File.separator + configFile;
            configFilePath = URLDecoder.decode(configFilePath, "UTF-8");
            in = new BufferedInputStream(new FileInputStream(new File(configFilePath)));
            //properties.load(is);//直接这么写，如果properties文件中有汉子，则汉字会乱码。因为未设置编码格式。
            properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOG.error("加载配置文件{}异常", configFile, e);
            throw new RuntimeException("加载配置文件 {" + configFile + "} 异常", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return properties;
    }

    public static Properties getProperties(String configFile) {
        try {
            configFile = URLDecoder.decode(configFile, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return loadProperties(configFile);
    }

    public static Properties getProperties() {
        return getProperties(DEFAULT_PROPERTIES_FILENAME);
    }

}
