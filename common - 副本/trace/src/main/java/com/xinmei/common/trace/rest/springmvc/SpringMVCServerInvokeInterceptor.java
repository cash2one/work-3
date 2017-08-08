package com.xinmei.common.trace.rest.springmvc;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.trace.CatRemoteCallContext;
import com.xinmei.common.trace.TraceContext;
import com.xinmei.common.trace.constants.TraceConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @ClassName: com.xinmei.common.trace.rest.springmvc.SpringMVCServerInvokeInterceptor
 * @Description: 用于Springmvc提供的rest服务，服务端埋点拦截器
 * @Author xbzhu
 * @Date 2017年03月13日 11:20
 */
public class SpringMVCServerInvokeInterceptor extends HandlerInterceptorAdapter {

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/14 8:55
     * Desc: springmvc框架提供接口注入，责任链模式。用于接收请求埋点操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Objects.equals(TraceConstant.TRACE_HTTP_REQUEST_VALUE,request.getHeader(TraceConstant.TRACE_HTTP_REQUEST_VALUE))) {
            Transaction transaction = Cat.newTransaction(CatConstants.TYPE_URL, request.getRequestURI());
            TraceContext.setTransaction(transaction);
            Cat.logEvent(CatConstants.TYPE_CALL,CatConstants.TYPE_CALL+".client",Message.SUCCESS,request.getRemoteAddr());
            Cat.Context context = initContext(request);
            Cat.logRemoteCallServer(context);
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/14 9:39
     * Desc: 在spring mvc请求返回时用于埋点操作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Transaction transaction=TraceContext.getTransaction();
        if(null !=transaction){
            try {
                Cat.logEvent(CatConstants.TYPE_URL, TraceConstant.EVENT_REST_INVOKE_RESPONSE_STATUS, Message.SUCCESS, response.getStatus() + "");
                Cat.logEvent(CatConstants.TYPE_URL, TraceConstant.EVENT_REST_INVOKE_RESPONSE);
                if (null != ex) {
                    Cat.logError(request.getRequestURI(),ex);
                    transaction.setStatus(ex);
                    return;
                }
            }finally {
                transaction.complete();
                TraceContext.releaseContext();
            }
        }

    }

    private Cat.Context initContext(HttpServletRequest request){
        Cat.Context context=new CatRemoteCallContext();
        context.addProperty(Cat.Context.ROOT,request.getHeader(Cat.Context.ROOT));
        context.addProperty(Cat.Context.PARENT,request.getHeader(Cat.Context.PARENT));
        context.addProperty(Cat.Context.CHILD,request.getHeader(Cat.Context.CHILD));
        return context;
    }
}
