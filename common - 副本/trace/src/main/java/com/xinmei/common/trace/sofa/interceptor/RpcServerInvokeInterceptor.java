package com.xinmei.common.trace.sofa.interceptor;

import com.alipay.sofa.rpc.api.context.RpcContextManager;
import com.alipay.sofa.rpc.api.context.RpcServiceContext;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.trace.constants.TraceConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: com.xinmei.common.trace.sofa.interceptor.RpcServerInvokeInterceptor
 * @Description: 用于跨jvm进程 sofa rpc调用server端埋点
 * @Author xbzhu
 * @Date 2017年03月03日 13:16
 */
public class RpcServerInvokeInterceptor extends AbstractInvokeInterceptor {


    private Logger logger= LoggerFactory.getLogger(RpcServerInvokeInterceptor.class);


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:19
     * Desc: 环绕在rpc服务端响应进行埋点
     *       对于异常不处理，保持原有业务逻辑交由sofa框架处理
     * @param joinPoint
     * @return
     */
    @Override
    public Object interceptor(JoinPoint joinPoint) throws Throwable {
        Object result =null;
        Transaction transaction= Cat.newTransaction(TraceConstant.TYPE_SOFA_RPC_SERVER,getInvokeRpcInfo(joinPoint));
        try{
            logRpcInvokeReceive(joinPoint.getArgs());
            Cat.Context context=getContext();
            Cat.logRemoteCallServer(context);
            logRpcInvokeProviderCross(transaction);
            result=((ProceedingJoinPoint)joinPoint).proceed();
            logRpcInvokeResponse(result,transaction);
            transaction.setStatus(Message.SUCCESS);
        }catch (Throwable e){
            logger.error("rpc invoke error",e);
            transaction.setStatus(e);
            Cat.logError(e);
            if(e instanceof Exception){
                throw e;
            }
            throw new Exception(e);
        }finally {
            transaction.complete();
            releaseContextHolder();
        }
        return result;
    }



    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:51
     * Desc: 记录rpc server事件信息
     * @param transaction
     */
    private void logRpcInvokeProviderCross(Transaction transaction) {
        RpcServiceContext serviceContext = RpcContextManager.currentServiceContext(false);
//        Event callerAppEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_INVOKE_APP,serviceContext.getCallerAppName());
//        Event traceIdEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_TRACE_ID,serviceContext.getTraceId());
//        Event serviceNameEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_INVOKE_SERVICE_NAME,serviceContext.getServiceName());
//        Event callURLEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_INVOKE_CALL_URL,serviceContext.getCallerUrl());
//        Event methodNameEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_INVOKE_CALL_METHOD,serviceContext.getMethodName());
//        completeEvent(transaction,callerAppEvent);
//        completeEvent(transaction,traceIdEvent);
//        completeEvent(transaction,serviceNameEvent);
//        completeEvent(transaction,callURLEvent);
//        completeEvent(transaction,methodNameEvent);
        if(null !=serviceContext) {
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER, TraceConstant.EVENT_SOFA_RPC_INVOKE_APP, Message.SUCCESS, serviceContext.getCallerAppName());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER, TraceConstant.EVENT_SOFA_RPC_TRACE_ID, Message.SUCCESS, serviceContext.getTraceId());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER, TraceConstant.EVENT_SOFA_RPC_INVOKE_SERVICE_NAME, Message.SUCCESS, serviceContext.getServiceName());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER, TraceConstant.EVENT_SOFA_RPC_INVOKE_CALL_URL, Message.SUCCESS, serviceContext.getCallerUrl());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER, TraceConstant.EVENT_SOFA_RPC_INVOKE_CALL_METHOD, Message.SUCCESS, serviceContext.getMethodName());
        }
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:30
     * Desc: 记录rpc服务端收到参数事件
     * @param args 参数列表
     */
    private void logRpcInvokeReceive(Object[] args) {
        Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_SERVER,TraceConstant.EVENT_SOFA_RPC_REQUEST, Message.SUCCESS,getInvokeArg(args));
    }

    private String getInvokeRpcInfo(JoinPoint joinPoint) {
        StringBuilder builder=new StringBuilder();
        builder.append(joinPoint.getTarget().getClass().getName()).append(".");
        builder.append(((MethodSignature)joinPoint.getSignature()).getName());
        return builder.toString();
    }
}
