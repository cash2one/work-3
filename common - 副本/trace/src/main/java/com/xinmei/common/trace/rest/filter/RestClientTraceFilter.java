package com.xinmei.common.trace.rest.filter;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.trace.TraceContext;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @ClassName: com.xinmei.common.trace.rest.filter.RestClientTraceFilter
 * @Description: client filter的目的是用于在LoginFilter 之前记录鉴权失败情况
 * @Author xbzhu
 * @Date 2017年03月02日 10:26
 */
@Provider
public class RestClientTraceFilter implements ClientRequestFilter,ClientResponseFilter{


    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        Transaction transaction= Cat.newTransaction("URL", requestContext.getUri().getPath());
        TraceContext.setClientTransaction(transaction);
        logRequestToken(requestContext);
        logRequestHealthCheck(requestContext);
    }

    private void logRequestHealthCheck(ClientRequestContext requestContext) {
        if(null !=requestContext.getHeaderString("healthCheck")) {
            Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".HealthCheck", Message.SUCCESS, requestContext.getHeaderString("healthCheck"));
        }
    }

    private void logRequestToken(ClientRequestContext requestContext) {
        if(null !=requestContext.getHeaderString("authtoken")) {
            Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".Token", Message.SUCCESS, requestContext.getHeaderString("authtoken"));
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        Transaction transaction=TraceContext.getClientTransaction();
        try{

        }catch (Exception e){
            if(e instanceof WebApplicationException){
                WebApplicationException webApplicationException= (WebApplicationException) e;
                Cat.logEvent(CatConstants.TYPE_URL,CatConstants.TYPE_URL+".ERROR","error",webApplicationException.getResponse().getStatus()+"");
                return;
            }
            Cat.logError(e);
        }finally {
            transaction.complete();
            TraceContext.releaseContext();
        }
    }
}
