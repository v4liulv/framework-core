package com.sinobest.framework.util.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liulv on 2017/5/19.
 * <p>
 * Date时间类型的工具类
 */
public class DateUtil {

    /**
     * 对时间字符串进行格式转换为毫秒格式，
     * 并如果存在.的后面全部去掉
     *
     * 如果类型为0 代表输入的时间字符串格式为yyyy/MM/dd HH:mm:ss
     * 如果类型为1 代表输入的时间字符串格式为yyyy-MM-dd HH:mm:ss
     * 如果类型为2 代表输入的时间字符串格式为yyyyMMddHHmmss
     *
     * @param dateStr 时间字符串，如yyyy-MM-dd HH:mm:ss.00000 的kettle时间格式，或者yyyy/MM/dd HH:mm:ss
     * @param dataStrType 时间字符串的类型，0代表yyyy/MM/dd HH:mm:ss 1代表yyyy-MM-dd HH:mm:ss， 2代表yyyyMMddHHmmss
     * @return 转换后的时间格式
     * @throws ParseException 抛出异常
     */
    public static String dateStrTransformationMillisecondsStr(String dateStr, int dataStrType) throws ParseException {

        SimpleDateFormat sdf = null;
        if(dataStrType == 0 ){
            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//小写的mm表示的是分钟
        }else if(dataStrType == 1){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        }else if(dataStrType == 2){
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");//小写的mm表示的是分钟
        }
        Date date;
        if (dateStr.split("\\.").length > 0) {
            dateStr = dateStr.split("\\.")[0];
        }
        if(sdf == null ) return null;
        date = sdf.parse(dateStr);
        return date.getTime() + "";
    }

    public static String dateStrTransformationMillisecondsStr(String dateStr) throws ParseException {
       return dateStrTransformationMillisecondsStr(dateStr, 0);
    }

    /**
     * yyyyMMddHHmmss年月日时分秒 格式转换为毫秒
     */
    public static String dateStrTransformationDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//小写的mm表示的是分钟
        java.util.Date date = sdf.parse(dateStr);
        return date.getTime() + "";
    }

    /**
     * 毫秒转换为yyyyMMddHHmmss格式
     */
    public static String formatTimeFullByLongString(String msStr) throws NumberFormatException {
        long ms = Long.parseLong(msStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date(ms));
    }

    /**
     * 毫秒转换为年月日
     *
     * @param msStr 毫秒的str类型
     * @return 转换后的yyyyMMdd
     */
    public static String formatTimeYMdLongString(String msStr) throws NumberFormatException {
        long ms = Long.parseLong(msStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(ms));
    }

    /**
     * 年 转换为毫秒
     */
    public static String convertDateStrByYear(String msStr) throws NumberFormatException {
        String patternYmdh = "yyyy";
        return convertDateStr(msStr, patternYmdh);
    }

    /**
     * 毫秒转换为年月日时
     *
     * @param msStr 毫秒的str类型
     * @return 转换后的yyyyMMddHH
     */
    public static String convertDateStrByYTD(String msStr) throws NumberFormatException{
        String patternYmdh = "yyyyMMddHH";
        return convertDateStr(msStr, patternYmdh);
    }

    /**
     * 毫秒转换为年月日时分
     *
     * @param msStr 毫秒的str类型
     * @param pattern 转换时间格式模型，如"yyyyMMddHHmm"
     * @return long
     */
    public static String convertDateStr(String msStr, String pattern) throws NumberFormatException{
        long ms = Long.parseLong(msStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.format(new Date(ms));
    }


    /**
     * 毫秒转换为分和秒  ,多少分多少秒
     *
     * @param ms 毫秒long
     * @return String返回 10分10类型  如果超过一小时，1小时1分1秒类型
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }

    /**
     * timestamp 转换为String
     *
     * @param timestamp Timestamp
     * @return String
     */
    public static String dateFormatString(Timestamp timestamp) {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String tsStr = "";
        try {
            tsStr = sdf.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }


    /**
     * Date 转换为String
     *
     * @param date Date
     * @return String
     */
    public static String dateFormatString(Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String tsStr = "";
        try {
            tsStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * timestamp 转换为String
     *
     * @return Date
     */
    public static Date nowDate() {
        Date ts = new Date(System.currentTimeMillis());
        return ts;
    }

    /**
     * timestamp 转换为String
     *
     * @return String
     */
    public static String nowDateFormatString() {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date ts = new Date(System.currentTimeMillis());
        String tsStr = "";
        try {
            tsStr = sdf.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

}
