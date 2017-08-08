package com.xinmei.common.login.log.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import com.xinmei.common.basic.AppContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.interception.Precedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;

import com.alibaba.fastjson.util.ThreadLocalCache;
import com.xinmei.common.login.context.RequestContextUtil;

@Provider
@ServerInterceptor
@Precedence("SECURITY")
public class RestMessageReaderInterceptor implements ReaderInterceptor {

	/**
	 * @Fields logger : 日志记录
	 */
	private static final Logger logger = LogManager.getLogger(RestMessageReaderInterceptor.class.getName());

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		printRestRequestJson(context);
		Object obj = context.proceed();
		
		return obj;
	}

	private void printRestRequestJson(ReaderInterceptorContext context) throws IOException {
		// 从stream获取数据，并重置
		InputStream es = context.getInputStream();
		String jsonstr = "";
		if(es.markSupported()){
			es.mark(0);
			byte[] bytes = getInputBytes(es);
			es.reset();
			jsonstr = new String(bytes);
		}
		AppContext.setRequestMsg(jsonstr);
		// 打印获取报文
		String randomid = "";
		if (context.getProperty(RequestContextUtil.accessRandomId) != null)
			randomid = (String) context.getProperty(RequestContextUtil.accessRandomId);
		StringBuffer sb = new StringBuffer();
		
		sb.append(randomid).append(",")
		  .append(new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(new Date()))
		  .append(",").append(jsonstr);
		logger.debug(sb.toString());

	}
	/*20170123备份，方法弃用，获取报文中长度 
	private void printRestRequestJson(ReaderInterceptorContext context) {
		// 获取报文，并打印报文
		String randomid = "";
		if (context.getProperty(RequestContextUtil.accessRandomId) != null)
			randomid = (String) context.getProperty(RequestContextUtil.accessRandomId);
		char[] chars = ThreadLocalCache.getChars(10000);
		StringBuffer sb = new StringBuffer();
		
		sb.append(randomid).append(",")
		  .append(new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(new Date()))
		  .append(",").append(sb.append(chars).toString().trim());
		logger.debug(sb.toString());

	}*/
	
	private byte[] getInputBytes(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (; ; ) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        return bytes;
    }

}
