package com.xinmei.common.exception;

import com.dianping.cat.Cat;
import com.xinmei.common.basic.AppContext;
import com.xinmei.common.basic.response.DefaultFacadeResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: com.xinmei.common.exception.MpaasExceptionHandler
 * @Description: mpaas异常、埋点拦截器
 * @Author xbzhu
 * @Date 2017年03月25日 11:12
 */
public class MpaasExceptionHandler {

    private Logger logger= LoggerFactory.getLogger("com.xinmei.mpaas.logger");

    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint){
        try {
            return proceedingJoinPoint.proceed();
        }catch (CommonBizException e){
            logger.error(AppContext.getRequestId(),e);
            Cat.logError(e);
            DefaultFacadeResponse result=new DefaultFacadeResponse(false,null,e.getMessage());
            return result;
        }catch(Throwable throwable){
            logger.error(AppContext.getRequestId(),throwable);
            Cat.logError(throwable);
        }
        return DefaultFacadeResponse.getErrorDefaultResponse();
    }
}
