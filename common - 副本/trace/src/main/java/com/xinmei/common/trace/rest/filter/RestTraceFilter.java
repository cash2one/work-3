package com.xinmei.common.trace.rest.filter;

import com.alipay.sofa.rest.server.netty.support.NettyHttpRequest;
import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.basic.AppContext;
import com.xinmei.common.basic.response.AbstractFacadeResp;
import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.common.trace.MessageIDCreator;
import com.xinmei.common.trace.TraceContext;
import com.xinmei.common.trace.constants.TraceConstant;
import com.xinmei.general.tools.ClazzUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.jboss.resteasy.core.interception.PostMatchContainerRequestContext;
import org.jboss.resteasy.core.interception.PreMatchContainerRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: RestTraceFilter
 * @Description: rest trace filter 用于rest请求埋点操作
 * @Author xbzhu
 * @Date 2017年03月01日 18:59
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class RestTraceFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static String LINE_SEPARATOR=System.getProperty("line.separator");

    private static Logger logger= LoggerFactory.getLogger(RestTraceFilter.class);


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //绕过跨域问题
        if(!"OPTIONS".equals(requestContext.getMethod())) {
            Transaction transaction = Cat.newTransaction("URL", getURLMapping(requestContext,null));
            TraceContext.setTransaction(transaction);
            logRequestToken(requestContext);
            logRequestHealthCheck(requestContext);
            logRequestMapping(requestContext,transaction);
            logRequestClientInfo(requestContext);
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:37
     * Desc: 打印健康检测的token
     * @param requestContext
     */
    private void logRequestHealthCheck(ContainerRequestContext requestContext) {
        if(null !=requestContext.getHeaderString("healthCheck")) {
            Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".HealthCheck", Message.SUCCESS, requestContext.getHeaderString("healthCheck"));
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:51
     * Desc: 打印token
     * @param requestContext
     */
    private void logRequestToken(ContainerRequestContext requestContext) {
        if(null !=requestContext.getHeaderString("authtoken")) {
            Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".Token", Message.SUCCESS, requestContext.getHeaderString("authtoken"));
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:51
     * Desc: 记录请求URL对应的类和方法，并为埋点加入数据
     * @param requestContext
     * @param transaction
     */
    private void logRequestMapping(ContainerRequestContext requestContext,Transaction transaction) {
        String mapping=getURLMapping(requestContext,transaction);
        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL +".url",Message.SUCCESS,requestContext.getUriInfo().getPath());
        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL +".Mapping",Message.SUCCESS,mapping);
        transaction.addData(TraceConstant.TRACE_INVOKE_METHOD,mapping);
    }

//    private void logRequestUser() {
//        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL +".User",Message.SUCCESS,TraceContext.getUser()==null?"nouser":TraceContext.getUser());
//    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:52
     * Desc: 获取URL映射的类和方法
     * @param requestContext
     * @param transaction
     * @return
     */
    private String  getURLMapping(ContainerRequestContext requestContext, Transaction transaction){
        StringBuilder builder=new StringBuilder();
        builder.append(requestContext.getUriInfo().getMatchedResources().get(0).getClass().getName());
        builder.append(".");
        PostMatchContainerRequestContext postMatchContainerRequestContext= (PostMatchContainerRequestContext) requestContext;
        Method method=postMatchContainerRequestContext.getResourceMethod().getMethod();
        Object object=requestContext.getUriInfo().getMatchedResources().get(0);
        if(null != transaction) {
            transaction.addData(TraceConstant.BIZ_INFO, ClazzUtil.getBizDesc(object.getClass(), method));
            transaction.addData(TraceConstant.ERROR_NOTICE_SWITCH, ClazzUtil.getBizNotice(method));
            transaction.addData(TraceConstant.BIZ_AUTHOR, ClazzUtil.getBizAuthor(object.getClass(), method));
        }
        builder.append(method.getName());
        if(null == transaction){
            builder.append("(").append( ClazzUtil.getBizAuthor(object.getClass(), method)).append(")");
        }
        return builder.toString();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:52
     * Desc: 记录请求的头数据
     * @param requestContext
     */
    private void logRequestClientInfo(ContainerRequestContext requestContext) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("Host=").append(requestContext.getUriInfo().getBaseUri());
        sb.append("&IPS=").append(getClientIp(requestContext));
        sb.append("&Referer=").append(requestContext.getHeaders().getFirst("referer"));
        sb.append("&Agent=").append(requestContext.getHeaders().getFirst("user-agent"));
        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".Server", Message.SUCCESS, sb.toString());
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/18 15:53
     * Desc: 记录请求数据
     * @param requestContext
     */
    protected void logRequestPayload(ContainerRequestContext requestContext) {
        StringBuilder sb = new StringBuilder(256);
        sb.append(requestContext.getMethod()).append(' ').append(requestContext.getUriInfo().getPath());
        String payload=TraceContext.getRequestMsg();
        if (payload != null) {
            sb.append(LINE_SEPARATOR).append(payload);
        }
        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL+ ".Param", Message.SUCCESS, sb.toString());
        Cat.logEvent(CatConstants.TYPE_URL,CatConstants.TYPE_URL+".requestId",Message.SUCCESS,AppContext.getRequestId());
    }

    /**
     * 获取请求的ip
     * @param requestContext
     * @return
     */
    private String getClientIp(ContainerRequestContext requestContext){
        PreMatchContainerRequestContext pmcrc = (PreMatchContainerRequestContext) requestContext;
        if (pmcrc.getHttpRequest() instanceof NettyHttpRequest) {
            // 获取当前线程请求context
            NettyHttpRequest nhr = (NettyHttpRequest) pmcrc.getHttpRequest();
            return nhr.getRemoteAddress();
        }
        return "未知";
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if(!"OPTIONS".equals(requestContext.getMethod())) {
            logRequestPayload(requestContext);
            Transaction transaction = TraceContext.getTransaction();
            try {
                Object object = responseContext.getEntity();
                if (null != object && object instanceof AbstractFacadeResp) {
                    AbstractFacadeResp abstractFacadeResp = (AbstractFacadeResp) object;
                    if (null != abstractFacadeResp.getException()) {
                        transaction.setStatus(abstractFacadeResp.getException());
                        Cat.logError(abstractFacadeResp.getException());
                        if(!abstractFacadeResp.getException().getClass().getName().equals("com.xinmei.etrust.exception.BizException")) {
                            logger.error(AppContext.getRequestId(),abstractFacadeResp.getException());
                            buildError(transaction);
                        }else{
                            logger.warn(AppContext.getRequestId(),abstractFacadeResp.getException());
                        }
                        abstractFacadeResp.setException(null);
//                        transaction.complete();
                        return;
                    }
                } else {
                    String status=String.valueOf(responseContext.getStatus());
                    if(null !=status&&(Objects.equals('4',status.charAt(0))||Objects.equals('5',status.charAt(0)))){
                        //返回实体为空，说明鉴权失败
                        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".ERROR", "error", responseContext.getStatus() + "");
                        transaction.setStatus(responseContext.getStatus() + "");
                        return;
                    }
                }
                transaction.setStatus(Message.SUCCESS);

            } catch (Exception e) {
                if (e instanceof WebApplicationException) {
                    WebApplicationException webApplicationException = (WebApplicationException) e;
                    Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".ERROR", "error", webApplicationException.getResponse().getStatus() + "");
                    transaction.setStatus(e);
                    return;
                }
                transaction.setStatus(e);
                Cat.logError(e);
            } finally {
                if (transaction != null) {
                    transaction.complete();
                }
                TraceContext.releaseContext();
            }
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/11 11:34
     * Desc: 记录调用错误信息，用于穿透数据进行短信发送、
     * @param transaction
     */
    private void buildError(Transaction transaction) {
        transaction.addData(TraceConstant.ERROR_USER,TraceContext.getCurrentUserInfo());
        transaction.addData(TraceConstant.ERROR_TIME, DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        transaction.addData(TraceConstant.REQUEST_ID,AppContext.getRequestId());
        transaction.addData(TraceConstant.CURRENT_ENV_PROFILE, SpringContextUtils.getEnvActiveProfile());
    }
}
