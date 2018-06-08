package com.sinobest.framework.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * Created by liulv on 2017/6/9.
 * <p>
 * read property file
 */
public class PropertyUtil {
    private final static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    //判断应用 日志推送应用 配置文件
    private static int gxksh_type = BaseConfig.PROPERTIES_TYPE;
    private static String confPath1 = "/core-site.xml";

    /**
     * 读取properties返回Properties实例
     *
     * @param propertyFile properties文件路径
     * @return 返回Properties
     */
    public static Properties readPropertiesFile(String propertyFile) {
        Properties prop = new Properties();
        //InputStream in = new BufferedInputStream(new FileInputStream(propertyFileName));
        InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile);
        //加载属性列表
        try {
            if(in == null){
                logger.error("读取Properties文件【" + propertyFile + "】 文件不存在 !");
                return prop;
            }
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * 读取properties配置文件，全部Map<code,value>
     *
     * @param propertyFile properties配置文件路径
     * @return Map<Key,Value>
     */
    public static Map<String, String> readPropertiesFileMap(String propertyFile) {
        return readPropertiesFileMap(propertyFile, null);
    }


    /**
     * 读取properties对应的文件的地址，
     * 获取对应的配置文件的code和value，返回map
     *
     * @param propertyFile properties的文件路径
     * @param attributes   属性值 code值，数组形式
     * @return 返回对应的code和value的map
     */
    public static Map<String, String> readPropertiesFileMap(String propertyFile, String[] attributes) {
        Properties prop;
        Map<String, String> resultMap = new HashMap<>();
        try {
            //读取属性文件propertyFileName
            prop = readPropertiesFile(propertyFile);

            assert prop != null;
            for (Object o : prop.stringPropertyNames()) {
                {
                    String key = (String) o;
                    String value = prop.getProperty(key);
                    if (null == attributes || attributes.length == 0) {
                        resultMap.put(key, value);
                    } else {
                        for (String attribute : attributes) {
                            if (key.equals(attribute.trim())) {
                                resultMap.put(key, value);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap.size() > 0 ? resultMap : null;
    }

    /**
     * 默认读取core-site.xml配置
     *
     * @return Map<参数名.值>
     */
    public static Map<String, String> getValues() {
        return getValues(null);
    }

    /**
     * @param filePath 根据类型获取对应的xml配置文件 和 类型
     * @return Map<参数名.值>
     */
    public static Map<String, String> getValues(String filePath) {
        String confPath;

        if(filePath == null || filePath.trim().equals("")){
            logger.info("The configuration file is empty and the core.site file is read by default");
            confPath = filePath;
        }else {
            confPath = confPath1;
        }

        InputStream is = PropertyUtil.class.getResourceAsStream(confPath);
        SAXReader reader = new SAXReader();
        Map<String, String> resMap = new HashMap<>();

        try {
            Document doc = reader.read(is);
            Element yjtrztsElement = (Element) doc.selectSingleNode("configuration");
            List<Element> yjtrztsElementElements = yjtrztsElement.elements("property");
            if (yjtrztsElementElements == null || yjtrztsElementElements.size() < 0) return null;
            for (Element property : yjtrztsElementElements) {
                String key = property.element("name").getText();
                String value = property.element("value").getText();
                resMap.put(key, value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * 根据对应的type和code,获取对应文件的code的value值
     *
     * @param type 类型，如果是1 则是读取gxksh-site.xml文件
     * @param code 根据对应的code获取但对于的值
     * @return 返回文件中读取的code的值
     */
    public static String getValue(int path, String code) {
        String confPath;
        if (gxksh_type == path) {
            confPath = confPath1;
        } else {
            logger.error("Error PropertyUtil is Method getValues param 'type' not matching return null, please look param 'type' whether or not correct !!!");
            return null;
        }
        InputStream inputStream = PropertyUtil.class.getResourceAsStream(confPath);
        return PropertyUtil.getValue(inputStream, code);
    }

    /**
     * 根据提供配置文件路径和code key值返回对应的配置value值
     *
     * @param inputPath 读取配置文件的路径
     * @param code      查询的code key值
     * @return 返回code key 对应的Value值
     */
    public static String getValue(String filePath, String code) {
        InputStream inputStream = PropertyUtil.class.getResourceAsStream(filePath);
        return PropertyUtil.getValue(inputStream, code);
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


    public static void main(String[] args) {
       /* String relativelyPath=System.getProperty("csv.dir");
        String cql1 = readFille(relativelyPath + "/base/cql/cql.properties", new String[]{"cql1"}).get("cql1");
        System.out.println(cql1);*/

        // System.out.println(PropertyUtil.getValue("1", "CODE"));

    }
}
