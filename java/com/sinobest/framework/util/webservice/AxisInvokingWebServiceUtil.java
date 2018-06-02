package com.sinobest.framework.util.webservice;

/*import org.apache.axis.client.Call;
import org.apache.axis.client.Service;*/

/**
 * Created by liulv on 2017/4/20.
 *
 * 使用Axis方式调用WebService工具类
 */
public class AxisInvokingWebServiceUtil
{
    public static String invokingWebService(String wsdl, String method, Object[] opAddEntryArgs) throws Exception
    {
        //Service service = new Service();
        //Call call = (Call) service.createCall();

        //call.setOperationName(method);

        //wsdl = "http://127.0.0.1:8080/aspt/services/LogService?wsdl"
        //call.setTargetEndpointAddress(new java.net.URL(wsdl));

        //返回报文
        //new Object[] {regId, sendId, logType, logs}
        //String resultCode = (String) call.invoke(opAddEntryArgs);
        //if(resultCode == null || resultCode.equals("")){
            throw new Exception("WebService interface wsdl " + wsdl + ", method " + method + ", result is null or '' ");
        //}

        //return resultCode;
    }

    public static void main(String[] args)
    {
        String wsdl = "";
        String method = "";
        String[] params = new String[0];
        try
        {
            String result = invokingWebService(wsdl, method, params) ;
            System.out.println("result:" + result);
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}
