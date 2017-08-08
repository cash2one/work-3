package com.xinmei.common.login.context;

import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;

import com.alipay.sofa.rest.server.netty.support.NettyHttpRequest;
import com.xinmei.common.basic.AppContext;

/**
* @ClassName: RestRequestContextUtil
* @Description: 获取Rest请求的各种Context资源
* @author ZHY
* @date 2016年10月17日 上午10:34:05
*
*/ 
public class RequestContextUtil {
	
	private static String clientIPStr = "C_IP";
	private static String clientUserName = "C_USERNAME";
	private static String accessStartTimeStr = "A_STARTTIME";
	public static String accessRandomId = "A_RandomId";
	
	public static void setRestRandomId(ContainerRequestContext crc,String randomId){
		crc.setProperty(accessRandomId, randomId);
	}
	
	public static void setRestClientIP(NettyHttpRequest nhr,ContainerRequestContext crc){
		crc.setProperty(clientIPStr, nhr.getRemoteAddress());
		AppContext.setRestIP(nhr.getRemoteAddress());
	}

	public static void setRestClientIP(String realIp,ContainerRequestContext crc){
		crc.setProperty(clientIPStr, realIp);
		AppContext.setRestIP(realIp);
	}

	public static void setRestClientStartTime(ContainerRequestContext crc,Date time){
		crc.setProperty(accessStartTimeStr, time);
	}
	
	public static String getRestClientIP(ContainerRequestContext crc){
		String ipstr = "";
		if(crc.getProperty(clientIPStr)!=null)
			ipstr = crc.getProperty(clientIPStr).toString();
		return ipstr;
	}
	
	public static String getRestUserName(ContainerRequestContext crc){
		String userstr = "";
		if(crc.getProperty(clientUserName)!=null)
			userstr = crc.getProperty(clientUserName).toString();
		return userstr;
	}
	
	public static Date getRestAccessStartTime(ContainerRequestContext crc){
		Date timedate = null;
		if(crc.getProperty(accessStartTimeStr)!=null)
			timedate = (Date)crc.getProperty(accessStartTimeStr);
		return timedate;
	}
	
	public static String getRestRandomId(ContainerRequestContext crc){
		String randomstr = "";
		if(crc.getProperty(accessRandomId)!=null)
			randomstr = (String)crc.getProperty(accessRandomId);
		return randomstr;
	}
}
