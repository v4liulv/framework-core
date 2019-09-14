package com.sinobest.framework.util.webservice;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * Created by liulv on 2017/4/20.
 * <p>
 * 使用Axis方式调用WebService工具类
 *
 * 针对使用ajax 1方式
 */
public class AxisInvokingWebServiceUtil {
    public static String invokingWebService(String wsdl, String method, Object[] opAddEntryArgs) throws Exception {
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setOperationName(method);
        call.setTargetEndpointAddress(new java.net.URL(wsdl));
        //返回报文
        //new Object[] {regId, sendId, logType, logs}
        String resultCode = (String) call.invoke(opAddEntryArgs);
        if (resultCode == null || resultCode.equals("")) {
            throw new Exception("WebService interface wsdl " + wsdl + ", method " + method + ", result is null or '' ");
        }

        return resultCode;
    }

    public static String invokingWebService(String wsdl, String namespace, String method, Object[] opAddEntryArgs) throws Exception {
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(wsdl));
        call.setOperationName(new QName(namespace,method));
        call.addParameter("arg0",
                XMLType.XSD_STRING, ParameterMode.IN);

        String resultCode = (String) call.invoke(opAddEntryArgs);
        //call.setUseSOAPAction(true);
        if (resultCode == null || resultCode.equals("")) {
            throw new Exception("WebService interface wsdl " + wsdl + ", method " + method + ", result is null or '' ");
        }

        return resultCode;
    }

    public static void main(String[] args) {
        String wsdl = "";
        String method = "";
        String[] params = new String[0];
        try {
            String result = invokingWebService(wsdl, method, params);
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
