package com.sinobest.framework.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by liulv on 2017/4/20.
 *
 * 将文件（XML等）转换为String：
 * 主要用于WebService的接口提供报文数据，或者解析XML使用
 */
public class FileUtil
{
   public static String getContent(String filePath) throws IOException
   {
       File file = new File(filePath);
       long fileSize = file.length();
       if(fileSize > Integer.MAX_VALUE){
           System.out.println("file too big...");
           return null;
       }

       FileInputStream fi = new FileInputStream(file);
       byte[] buffer = new byte[(int)fileSize];
       int offset = 0;
       int numRead;
       while(offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0){
            offset += numRead;
       }
       //确保所有数据全部被读取
       if(offset != buffer.length){
           throw new IOException("Could not completely read file " + file.getName());
       }
       fi.close();
       return new String(buffer, "UTF-8");
   }
}
