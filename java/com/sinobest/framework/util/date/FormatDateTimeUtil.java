package com.sinobest.framework.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liulv on 2017/5/22.
 * <p>
 * SimpleDateFormat 时间转换工具类
 */
public class FormatDateTimeUtil
{
    public static String toLongDateString(Date dt)
    {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        return myFmt.format(dt);
    }

    public static String toShortDateString(Date dt)
    {
        SimpleDateFormat myFmt = new SimpleDateFormat("yy年MM月dd日 HH时mm分");
        return myFmt.format(dt);
    }

    public static String toLongTimeString(Date dt)
    {
        SimpleDateFormat myFmt = new SimpleDateFormat("HH mm ss SSSS");
        return myFmt.format(dt);
    }

    public static String toShortTimeOneString(Date dt)
    {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFmt.format(dt);
    }

    public static String toShortTimeString(Date dt)
    {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return myFmt.format(dt);
    }

    public static void main(String[] args) {

        Date now=new Date();

        System.out.println(FormatDateTimeUtil.toLongDateString(now));
        System.out.println(FormatDateTimeUtil.toShortDateString(now));
        System.out.println(FormatDateTimeUtil.toLongTimeString(now));
        System.out.println(FormatDateTimeUtil.toShortTimeOneString(now));

        System.out.println(FormatDateTimeUtil.toShortTimeString(now));
    }
}
