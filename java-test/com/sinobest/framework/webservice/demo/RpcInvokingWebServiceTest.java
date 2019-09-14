package com.sinobest.framework.webservice.demo;

import com.sinobest.framework.util.webservice.RPCInvokingWebServiceUtil;

/**
 * Created by liulv on 2017/4/28.
 * <p>
 * 简单Axis调用WebService接口方法测试类
 */
public class RpcInvokingWebServiceTest {
    public static void main(String[] args) {
        //String address, String namespaceURI, String localName, Object[] opAddEntryArgs
        String address = "http://localhost:8001/services/neo4j";   //webervice地址
        String namespaceURI = "http://impl.neo4j.servers.com.sinobest.kshfx.webservice.sinobest.com/";  //命名空间
        String localName = "createNodesOfPerson";  //方法名
        String tablename = "T_TP_RXZT_NEW";
        String[] rowkeys = {"520103198011056732"};
        String limit = 6 + "";
        Object[] opAddEntryArgs = new Object[]{tablename}; //参数数组

        String returnMessage = RPCInvokingWebServiceUtil.invokingWebService(address, namespaceURI, localName, opAddEntryArgs);

        System.out.println("图片接口测试RPC调用，返回报文结果：" + returnMessage);
    }

}
