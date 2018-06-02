package com.sinobest.framework.util.encoding;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liulv on 2017/5/23.
 *
 * 判断字符串是否乱码
 */
public class MessyCodeCheck
{
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {

                if (!isChinese(c)) {
                    count = count + 1;
                    //System.out.print(c);
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isMessyCodeSu(String strName) {
        try
        {
            strName = new String(strName.getBytes("GBK"), "GBK");
            //System.out.println("strName===" + strName);
        } catch (UnsupportedEncodingException e )
        {
            //e.printStackTrace();
            return true;
        }
        char ws[] = new char[]{'ㄨ' ,'℃', '?', '？', '&'};
        for(int i = 0; i < strName.length(); i++)
        {
            char c = strName.charAt(i);
            for (char v : ws)
            {
                if (c == v)
                {
                    return true;
                }
            }
            if ((int)c == 0xfffd){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isMessyCode("*��JTP.jar�ļ����JTP�ļ���ȡ��ͼƬ��Դ"));
        //System.out.println(isMessyCode("你好"));
    }
}
