package com.sinobest.framework.util.demo;

import com.sinobest.framework.util.date.DateUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by liulv on 2017/7/5.
 *
 * DateUtil test
 */
public class DateUtilTest
{
    @Test
    public void formatTimeYearMonthDayByStringTest(){
        //String dateLongStr =  "1424859230000";
        String dateLongStr1 = "1500164400000";
        //String dateStr = DateUtil.formatTimeFullByLongString(dateLongStr);
        String dateStr1 = DateUtil.formatTimeFullByLongString(dateLongStr1);
        //System.out.println("dateStr ========================== " + dateStr);
        System.out.println("dateStr ========================== " + dateStr1);
    }

    @Test
    public void subString(){
        String str = "2016-05-11 21:30:00";
        System.out.println(str.substring(0, 10));
    }

    @Test
    public void dateStrTransformationMillisecondsStrTest() throws ParseException {
        String dataStr = "20170822144242";
        String msStr = DateUtil.dateStrTransformationMillisecondsStr(dataStr, 2);

        System.out.println(msStr);

        System.out.println(DateUtil.formatTimeFullByLongString(msStr));
    }

    @Test
    public void dateUtilDateFormatsStringTest() {
        Timestamp ts = null;
        String tsStr = "2011-05-09 11:49:45";
        try {
            ts = Timestamp.valueOf(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("11 : " + DateUtil.dateFormatString(ts));
    }
    @Test
    public void nowDateUtilDateFormatsStringTest() {
        System.out.println("11 : " + DateUtil.nowDateFormatString());
    }
}
