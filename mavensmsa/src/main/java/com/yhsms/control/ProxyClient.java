package com.yhsms.control;

import java.lang.reflect.Proxy;

//通过网络连接服务器端获取代理对象
public class ProxyClient {
public static<T> T getClient(Class<T> clazz,String ip,int port){
	return (T) Proxy.newProxyInstance(ProxyClient.class.getClassLoader(), new Class[]{clazz}, new ClientHandler(ip, port));
	
}
}
