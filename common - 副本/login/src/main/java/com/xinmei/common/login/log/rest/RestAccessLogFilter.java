package com.xinmei.common.login.log.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.xinmei.common.basic.AppContext;

import com.xinmei.common.login.context.RequestContextUtil;
import com.xinmei.general.tools.RandomIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: LogAccessRestFilter
 * @Description: rest访问日志记录
 * @author jason.zhang
 * @date 2016年10月29日 下午2:55:45
 *
 */
@Provider
public class RestAccessLogFilter implements ContainerRequestFilter, ContainerResponseFilter {

	/**
	 * @Fields logger : 日志记录
	 */
	private static final Logger logger= LoggerFactory.getLogger(RestAccessLogFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// 获取rest requestid
		String randomid = RandomIdUtil.getRandomId();
		RequestContextUtil.setRestRandomId(requestContext, randomid);
		RequestContextUtil.setRestClientStartTime(requestContext, new Date());
		//将当前请求唯一id放入当前线程
		AppContext.setRequestId(randomid);
		AppContext.setChannel(AppContext.REQUEST_CHANNEL_REST);
		logger.info("rest,request,{},{}",AppContext.getUser(),AppContext.getRequestId());
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		// 跨域验证options请求不进行记录 --20161229 jason.zhang
		if (!"OPTIONS".equals(request.getMethod())) {
			StringBuffer sb = new StringBuffer();
			sb.append(RequestContextUtil.getRestRandomId(request)).append(",")
					.append(RequestContextUtil.getRestClientIP(request)).append(",").append(request.getMethod())
					.append(",").append(request.getUriInfo().getPath()).append(",")
					.append(RequestContextUtil.getRestUserName(request)).append(",")
					.append(formatTime(RequestContextUtil.getRestAccessStartTime(request)))
					.append(",").append(formatTime(new Date()));
			logger.info(sb.toString());
		}
//		AppContext.releaseContext();
	}
	
	
	/**
	* @Title: formatTime
	* @Description: 格式化时间，判断输入时间是否为空
	* @param @param date
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	String formatTime(Date date){
		String datestr = "";
		if(date != null){
			
			datestr = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(date);
		}
		
		return datestr;
	}

}
