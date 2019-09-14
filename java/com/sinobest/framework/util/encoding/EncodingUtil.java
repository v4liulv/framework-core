package com.sinobest.framework.util.encoding;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liulv on 2017/5/23.
 *
 * 字符集乱码判断
 */
public class EncodingUtil
{
    private static Logger logger = LoggerFactory.getLogger(EncodingUtil.class);

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
                return encode; // 是的话，返回GB2312，以下代码同理
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---GB2312", e);
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
                return encode;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---ISO-8859-1", e);
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8编码
                return encode;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---UTF-8", e);
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
                return encode;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---GBK", e);
        }
        return ""; // 到这一步，你就应该检查是不是其他编码啦

    }

    /**
     *  xml不支持的字符过滤
     */
    public static String decode(String in) {
        StringBuffer out = new StringBuffer(); // Used to hold the output.
        char current; // Used to reference the current character.
        if (in == null || ("".equals(in)))
            return ""; // vacancy test.
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD)
                    || ((current > 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF))
                    || (current < 0x0))
                out.append(current);
        }
        return out.toString().trim();
    }
}
