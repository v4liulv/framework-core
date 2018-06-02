package com.sinobest.framework.util.webservice;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by liulv on 2017/4/20.
 * <p>
 * 使用RPC方式调用WebService工具类
 */
public class RPCInvokingWebServiceUtil
{
    /**
     * @param address   com.sinobest.kshfx.webservice uri地址
     * @param namespaceURI 命名空间
     * @param localName 方法名
     * @param opAddEntryArgs 参数 Object对象
     * @return WebService响应返回结果
     */
    public static String invokingWebService(String address, String namespaceURI, String localName, Object[] opAddEntryArgs)
    {
        //使用RPC方式调用WebService
        String resultStr;
        try
        {
            RPCServiceClient serviceClient = new RPCServiceClient();

            Options options = serviceClient.getOptions();
            //设置超时
            options.setTimeOutInMilliSeconds(20 * 1000L);
            //指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(address);
            options.setTo(targetEPR);
            //指定WebService 方法的参数值
            //Object[] opAddEntryArgs = new Object[]{regID, sendID};
            //指定bigdataService方法返回值的数据类型的Class对象
            Class[] classes = new Class[]{String.class};
            //指定要调用的bigdataService方法及WSDL文件的命名空间
            QName opAddEntry = new QName(namespaceURI, localName);
            //调用bigdataService方法并输出该方法的返回值,
            //返回对象是一个Object的数组,拿数组的第一个值，转换强转即可
            resultStr = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0].toString();

            if (resultStr == null || resultStr.equals(""))
            {
                resultStr = "Error 返回的报文无数据，返回为空！！！！";
            }
        } catch (Exception e )
        {
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            e.printStackTrace(writer);
            StringBuffer buffer = stringWriter.getBuffer();
            String errMsg = buffer.toString();
            resultStr = "查询出异常，查看异常信息：\n" + errMsg;
        }
        return resultStr;
    }
}

