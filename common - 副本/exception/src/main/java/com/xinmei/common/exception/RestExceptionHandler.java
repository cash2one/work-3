package com.xinmei.common.exception;


import com.dianping.cat.Cat;
import com.xinmei.common.basic.AppContext;
import com.xinmei.common.basic.response.DefaultFacadeResponse;
import org.jboss.resteasy.spi.DefaultOptionsMethodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @ClassName: com.xinmei.common.exception.RestExceptionHandler
 * @Description: rest异常处理器
 * @Author xbzhu
 * @Date 2017年03月16日 21:35
 */
@Provider
public class RestExceptionHandler implements ExceptionMapper<Throwable>{

    private static final Logger logger= LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    public Response toResponse(Throwable throwable) {
        //当跨域问题、resteasy抛出异常可以绕过。因为options不被支持、但不影响_xbzhu_20170426
        if(throwable instanceof DefaultOptionsMethodException){
            return Response.status(Response.Status.OK.getStatusCode()).build();
        }
        Cat.logError(throwable);
        //如果是业务异常，获取业务信息封装
        if(throwable instanceof RuntimeException && throwable.getClass().getName().equals("com.xinmei.gfi.facade.exception.GFIException")){
            DefaultFacadeResponse result=new DefaultFacadeResponse(false,null,throwable.getMessage());
            logger.warn(AppContext.getRequestId(),throwable);
            return Response.status(Response.Status.OK.getStatusCode()).entity(result).build();
        }else{
            logger.error(AppContext.getRequestId(),throwable);
        }
        return Response.status(Response.Status.OK.getStatusCode()).entity(DefaultFacadeResponse.getErrorDefaultResponse()).build();
    }
}
