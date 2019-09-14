package com.sinobest.framework.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liulv
 * @date 2019/9/5
 * @time 14:59
 *
 * @description 读取XML配置文件，单个或多个子节点的code
 */
public class XMLPropertyUtil {
    private static final Logger LOG = LoggerFactory.getLogger(XMLPropertyUtil.class);

    /**
     * 读取properties配置文件，全部Map<code,value>
     *
     * @param propertyFile properties配置文件路径
     * @return Map<Key,Value>
     */
    public static Map<String, String> getValues(String propertyFile) throws UnsupportedEncodingException {
        return getTopNodesValues(propertyFile, null);
    }


    /**
     * configuration/property
     * 获取对应的配置文件的code和value，返回map
     *
     * @param filePath properties的文件路径,也可以为文件名
     * @param names   属性值 code值，数组形式
     * @return 返回对应的code和value的map
     */
    public static Map<String, String> getTopNodesValues(String filePath, String[] names)
            throws UnsupportedEncodingException {
        return getValues(true, filePath, "configuration", null);
    }

    /**
     * configuration/property
     * 获取对应的配置文件的code和value，返回map
     *
     * @param filePath properties的文件路径,也可以为文件名
     * @param nodeName  格式：topnode/sonnode[@name="node的name属性名"] 如： "configuration/jdbcConfig[@name=\"" + jdbcName + "\"]
     * @return 返回对应的code和value的map
     */
    public static Map<String, String> getValueByNode(String filePath, String nodeName)
            throws UnsupportedEncodingException {
        return getValues(true, filePath, nodeName, null);
    }

    /**
     * @param filePath 根据类型获取对应的xml配置文件 和 类型
     * @return Map<参数名.值>
     */
    public static Map<String, String> getValues(boolean classpath, String filePath, String node, String[] names)
            throws UnsupportedEncodingException {
        if(classpath){
            filePath = PathUtil.CLASS_PATH + File.separator + filePath;
        }
        filePath = URLDecoder.decode(filePath, "UTF-8");

        InputStream is = XMLPropertyUtil.class.getResourceAsStream(filePath);
        SAXReader reader = new SAXReader();
        Map<String, String> resMap = new HashMap<>();

        try {
            Document doc = reader.read(is);
            Element element = (Element) doc.selectSingleNode(node);
            List<Element> propertyElement = element.elements("property");
            if (propertyElement == null || propertyElement.size() == 0) return null;
            for (Element property : propertyElement) {
                String key = property.element("name").getText();
                String value = property.element("value").getText();
                if(names == null)
                    resMap.put(key, value);
                else if(Arrays.asList(names).contains(key)){
                    resMap.put(key, value);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return resMap;
    }


    /**
     * 根据文件输入流和对应code key，
     * 返回配置文件中对应的值
     */
    public static String getValue(InputStream inputStream, String code) {
        SAXReader reader = new SAXReader();
        String codeValue = "";
        try {
            Document doc = reader.read(inputStream);

            Element setXmlElement = (Element) doc.selectSingleNode("configuration");
            List<Element> setXmlElementElements = setXmlElement.elements("property");
            if (setXmlElementElements == null || setXmlElementElements.size() < 0) return null;
            for (Element property : setXmlElementElements) {
                String key = property.element("name").getText();
                if (!code.equals(key)) {
                    continue;
                }
                codeValue = property.element("value").getText();
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return codeValue;
    }
}
