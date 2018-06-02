package com.sinobest.framework.util.demo;

import com.sinobest.framework.util.StringUtil;
import org.junit.Test;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class StringUtilTest {

    @Test
    public void stringQdsz(){
        String str="2017-08-22 14:42:42.0";
        str = StringUtil.removeStringNoNumber(str);
               //str= str.substring(0,str.length()-6);
               str= str.substring(0,14);
        System.out.println(str);
    }

    @Test
    public void returnTest(){
        returnTest1();
        returnTest2();
    }

    private void returnTest1(){
        return;
    }

    private void returnTest2(){
        System.out.println("11111111111111");
    }

}
