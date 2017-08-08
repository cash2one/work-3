package com.xinmei.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.xinmei.common.exception.code.ExcepitonCodeEnum;

/**
* @ClassName: CommonExceptionHandler
* @Description: 异常处理拦截器
* @author jason.zhang
* @date 2016年11月15日 下午3:45:07
*
*/ 
public class CommonExceptionHandler {
	private static final Logger logger = LogManager.getLogger(CommonExceptionHandler.class.getName());
	private boolean stackShow = false;
	
	/**
	* @Title: around
	* @Description: 环绕拦截方法，注册到某个切面上
	* @param @param joinPoint
	* @param @return    设定文件
	* @return Object    返回类型
	* @throws
	*/ 
	public Object around(JoinPoint joinPoint) {
		try {
			return ((ProceedingJoinPoint) joinPoint).proceed();
		} catch (BusinessException e) {
			StringBuffer sb = new StringBuffer();
			
			sb.append("BusinessException ErrorCode:")
			.append(e.getErrorCode())
			.append(",ErrorName:")
			.append(e.getErrorName())
			.append(",ErrorDesc:")
			.append(e.getErrorDesc());
			logger.error(sb.toString());
			
			//输出异常方法
			/*sb.setLength(0);
			sb.append("BusinessException ErrorMethod:").append(e.getStackTrace()[1].getClassName()).append(".").append(e.getStackTrace()[1].getMethodName());
			logger.error(sb.toString());*/
			getThrowableString(e);
			
			return new ExceptionFacadeResp(e.getErrorCode(),e.getErrorMsg(),e.getErrorDesc());
		} catch (Throwable e) {
			getThrowableString(e);
			return new ExceptionFacadeResp(ExcepitonCodeEnum.RUNTIMEERROR.getErrorCode(),ExcepitonCodeEnum.RUNTIMEERROR.getErrorMsg());

		}
	}
	
	private void getThrowableString(Throwable e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logger.error(sw.getBuffer().toString());
		
	}

}
