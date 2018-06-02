package com.sinobest.framework.util.uuid;

import java.util.UUID;

/**
 * Created by liulv on 2017/4/10.
 * 获取UUID字符串
 */
public class UUIDCreate
{
    public static synchronized String getUUIDString(){
        String s  = UUID.randomUUID().toString();
        //去掉其中-
        String ss = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        String uuidStr = "";
        //如果大于31位 截取其中前31位
        if(ss.length() >= 32){
            uuidStr = ss.substring(0, 31);
        }
        return uuidStr;
    }
}
