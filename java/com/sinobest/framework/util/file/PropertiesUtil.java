package com.sinobest.framework.util.file;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liulv on 2017/4/27.
 *
 * 配置文件参数工具类
 */
@Deprecated
public class PropertiesUtil
{
    private final static Logger logger = Logger.getLogger(PropertiesUtil.class);

    //判断应用 日志推送应用 配置文件
    private static String TYPE_YJTRZ_TS = "1";
    private static String confPathTs = "/rz_ts_rzsjpt_site.xml";

    public static String TYPE_WIDE_HBASE_TO_HBASE = "1";
    public static String TYPE_WIDE_ORACLE_TO_HBASE = "2";
    public static String TYPE_COMMON_ORACLE_TO_HBASE = "3";
    public static String TYPE_HBASE_TO_HBASE = "4";
    public static final String TYPE_HBASE_TO_HBASE_WIDTHTABLE = "5";
    public static final String TYPE_WIDE_HBASE_TO_HBASE_BSH = "6";
    public static final String TYPE_HBASE_TO_SECONDINDEX = "7";
    public static final String TYPE_HBASEDATEFORMAT = "8";
    public static String TYPE_BIG_ORACLE_TO_HBASE = "9";

    private static String confPath1 = "/java/bigdata/resources/hbase_wide_table-site.xml";
    private static String confPath2 = "/hbase_oracle_wide_table-site.xml";
    private static String confPath3 = "/hbase_common_table-site.xml";
    private static String confPath4 = "/hbase_to_hbase-site.xml";
    private static String confPath5 = "/hbase_to_hbase_widthtable.xml";
    private static String confPath6 = "/hbase_bsh_table-site.xml";
    private static String confPath7 = "/hbase_to_table-secondindex.xml";
    private static String confPath8 = "/Hbasedateformat.xml";
    private static String confPath9 = "/oracle_hbase_big_table.xml";

    /**
     *
     * @param type 根据类型获取对应的xml配置文件 和 类型
     * @return Map<参数名.值>
     */
    public static Map<String, String> getValues(String type, String jdbcName){
        String confPath;
        if( "".equals(type))return null;
        if( TYPE_YJTRZ_TS.equalsIgnoreCase(type) ){
            confPath = confPathTs;
        }else{
            logger.error("Error PropertyUtil is Method getValues param 'type' not matching return null, please look param 'type' whether or not correct !!!");
            return null;
        }

        InputStream is = PropertiesUtil.class.getResourceAsStream(confPath);
        SAXReader reader = new SAXReader();
        Map<String, String> resMap = new HashMap<>();

        try
        {
            Document doc = reader.read(is);
            // is yjtrz ts
            if( TYPE_YJTRZ_TS.equalsIgnoreCase(type)){
                //IS_START_YJTRZTS
                Element yjtrztsElement = (Element)doc.selectSingleNode("configuration/commonConfig");
                List<Element> yjtrztsElementElements = yjtrztsElement.elements("property");
                if(yjtrztsElementElements==null || yjtrztsElementElements.size()<0)return null;
                for(Element property : yjtrztsElementElements){
                    String key = property.element("name").getText();
                    String value = property.element("value").getText();
                    resMap.put(key, value);
                }

                //rztspt code
                Element yyxtElement = (Element)doc.selectSingleNode("configuration/yyxtCode");
                List<Element> yyxtElements = yyxtElement.elements("property");
                if(yyxtElements==null || yyxtElements.size()<0)return null;
                for(Element property : yyxtElements){
                    String key = property.element("name").getText();
                    String value = property.element("value").getText();
                    resMap.put(key, value);
                }

                //jdbcConfig
                if(jdbcName != null && !jdbcName.equals("")){
                    Element jdbcsElment = (Element) doc.selectSingleNode("configuration/jdbcConfig[@name=\"" + jdbcName + "\"]");
                    if (jdbcsElment == null){
                        logger.error("Error PropertyUtil is Method getValues error , please check configurationfile 'rz_ts_rzsjpt_site.xml'");
                        return null;
                    }
                    resMap.put("TASK_NAME", jdbcName);
                    List<Element> propertyElements = jdbcsElment.elements("property");
                    if (propertyElements == null || propertyElements.size() < 0) return null;
                    for (Element property : propertyElements)
                    {
                        String key = property.element("name").getText();
                        String value = property.element("value").getText();
                        resMap.put(key, value);
                    }
                }
            }

        } catch ( DocumentException e )
        {
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * 获取相关配置文件的参数
     * @param type-PropertyUtil.TYPE_WIDE_HBASE_TO_HBASE,TYPE_WIDE_ORACLE_TO_HBASE,TYPE_COMMON_ORACLE_TO_HBASE
     * @param taskName
     * @return
     */
    public static Map<String, String> getValuesByTask(String type, String taskName){

        String confPath = "";


        if( "".equals(type) || "".equals(taskName) )return null;

        if( TYPE_WIDE_HBASE_TO_HBASE.equalsIgnoreCase(type) ){
            confPath = confPath1;
        }else if( TYPE_WIDE_ORACLE_TO_HBASE.equalsIgnoreCase(type) ){
            confPath = confPath2;
        }else if( TYPE_COMMON_ORACLE_TO_HBASE.equalsIgnoreCase(type) ){
            confPath = confPath3;
        }else if( TYPE_HBASE_TO_HBASE.equalsIgnoreCase(type) ){
            confPath = confPath4;
        }else if( TYPE_HBASE_TO_HBASE_WIDTHTABLE.equalsIgnoreCase(type) ){
            confPath = confPath5;
        }else if( TYPE_WIDE_HBASE_TO_HBASE_BSH.equalsIgnoreCase(type) ){
            confPath = confPath6;
        }else if(TYPE_HBASEDATEFORMAT.equalsIgnoreCase(type)){
            confPath = confPath8;
        }else if(TYPE_BIG_ORACLE_TO_HBASE.equalsIgnoreCase(type)){
            confPath = confPath9;
        }else{
            return null;
        }

        System.out.println(">>>>>>>>>>>>>>>>>confPath:"+confPath);

        InputStream is = PropertiesUtil.class.getResourceAsStream(confPath);

        //File xmlconFile = new File("");//WebRoot\\WEB-INF\\classes\\hbase_wide_table-site.xml
        SAXReader reader = new SAXReader();
        Map<String, String> resMap = new HashMap<String, String>();
        try {
            Document doc = reader.read(is);

            //jdbc element
            if(TYPE_BIG_ORACLE_TO_HBASE.equalsIgnoreCase(type) || TYPE_WIDE_ORACLE_TO_HBASE.equalsIgnoreCase(type) || TYPE_COMMON_ORACLE_TO_HBASE.equalsIgnoreCase(type) ){
                Element jdbcElement = (Element)doc.selectSingleNode("configuration/jdbcConfig");

                List<Element> jdbcElements = jdbcElement.elements("property");
                if(jdbcElements==null || jdbcElements.size()<0)return null;
                for(Element property : jdbcElements){
                    String key = property.element("name").getText();
                    String value = property.element("value").getText();
                    resMap.put(key, value);
                }
            }

            //common element
            Element commonElement = (Element)doc.selectSingleNode("configuration/commonConfig");
            if(commonElement!=null){
                List<Element> propertyElements = commonElement.elements("property");
                if(propertyElements!=null && propertyElements.size()>0){
                    for(Element property : propertyElements){
                        String key = property.element("name").getText();
                        String value = property.element("value").getText();
                        resMap.put(key, value);
                    }
                }
            }

            //task element
            Element taskElement = (Element)doc.selectSingleNode("configuration/task[@name=\""+taskName+"\"]");
            if(taskElement==null)return null;

            resMap.put("TASK_NAME", taskName);
            List<Element> propertyElements = taskElement.elements("property");
            if(propertyElements==null || propertyElements.size()<0)return null;
            for(Element property : propertyElements){
                String key = property.element("name").getText();
                String value = property.element("value").getText();
                resMap.put(key, value);
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resMap;
    }

    public static void main(String[] args)
    {
        //System.out.println(PropertyUtil.getValues("1", "").get("IS_START_YJTRZTS").equals("1"));
        System.out.println(PropertiesUtil.getValues("1", "").get("CODE"));
        //System.out.println(PropertyUtil.getValues("1", "BASIC_JDBC").get("DRIVER"));

    }
}
