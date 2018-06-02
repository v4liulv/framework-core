package com.sinobest.framework.webservice.demo;

import com.sinobest.framework.util.webservice.AxisInvokingWebServiceUtil;

/**
 * Created by liulv on 2017/4/27.
 *
 * 使用RPC方式调用WebService工具类 的测试类
 */
public class AxisInvokingWebServiceTest1
{
    public static void main(String[] args)
    {
        String wsdl = "http://192.168.1.110:7001/base/services/SyzyglService?wsdl";   //webervice地址
        String localName = "scanByTableRowkeysStartEndKeyLimit";  //方法名
        String tablename = "T_TP_RXZT_NEW";
        String[] rowkeys = {"520103198011056732"};
        String limit = 6 + "";
        Object [] opAddEntryArgs = new Object[]{tablename, rowkeys, limit}; //参数数组

        //String wsdl, String method, String[] params
        String returnMessage = null;
        try
        {
            returnMessage = AxisInvokingWebServiceUtil.invokingWebService(wsdl, localName, opAddEntryArgs);
        } catch ( Exception e )
        {
            e.printStackTrace();
        }

        System.out.println("图片接口测试RPC调用，返回报文结果：" + returnMessage);
    }
}
