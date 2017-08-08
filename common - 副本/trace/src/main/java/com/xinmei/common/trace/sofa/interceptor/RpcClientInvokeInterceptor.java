package com.xinmei.common.trace.sofa.interceptor;

import com.alipay.common.tracer.TracerException;
import com.alipay.common.tracer.util.DummyContextUtil;
import com.alipay.common.tracer.util.TracerContextUtil;
import com.alipay.sofa.rpc.api.context.RpcContextManager;
import com.alipay.sofa.rpc.api.context.RpcReferenceContext;
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
 * @ClassName: com.xinmei.common.trace.sofa.interceptor.RpcClientInvokeInterceptor
 * @Description: 用于跨jvm进程 sofa rpc调用拦截器埋点
 * @Author xbzhu
 * @Date 2017年03月03日 9:42
 */
public class RpcClientInvokeInterceptor extends AbstractInvokeInterceptor {

    private Logger logger= LoggerFactory.getLogger(RpcClientInvokeInterceptor.class);

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:19
     * Desc: 环绕在rpc 客户端调用进行埋点操作
     *       对于异常抓取后，不进行处理，避免破坏原有业务逻辑
     * @param joinPoint 连接点
     */
    @Override
    public Object interceptor(JoinPoint joinPoint) throws Throwable {
        Object result = null;
        Transaction transaction = Cat.newTransaction(TraceConstant.TYPE_SOFA_RPC_CLIENT, getInvokeRpcInfo(joinPoint));
        try {
            logRpcInvokeRequest(joinPoint.getArgs());
            Cat.Context context = getContext();
            Cat.logRemoteCallClient(context);
            initRpcAttachment(context);
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            logRpcInvokeConsumerCross(transaction);
            logRpcInvokeResponse(result,transaction);
            transaction.setStatus(Message.SUCCESS);
        } catch (Throwable e) {
            logRpcInvokeConsumerCross(transaction);
            logger.error("rpc invoke error ",e);
            transaction.setStatus(e);
            Cat.logError(e);
            if(e instanceof Exception){
                throw e;
            }
            throw new Exception(e);
        } finally {
            transaction.complete();
            releaseContextHolder();
        }
        return result;
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 10:41
     * Desc: 将消息生成的父子id放入rpc可传递容器，进行穿透传递
     * @param context
     */
    private void initRpcAttachment(Cat.Context context) {
        try {
            TracerContextUtil.putPenetrateAttribute(Cat.Context.ROOT,context.getProperty(Cat.Context.ROOT));
            TracerContextUtil.putPenetrateAttribute(Cat.Context.PARENT,context.getProperty(Cat.Context.PARENT));
            TracerContextUtil.putPenetrateAttribute(Cat.Context.CHILD,context.getProperty(Cat.Context.CHILD));
        } catch (TracerException e) {
            logger.info("cat trace put error");
            try {
                DummyContextUtil.createDummyLogContext();
                initRpcAttachment(context);
            } catch (Exception ex) {
                logger.info("createDummyLogContext error");
            }
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 10:29
     * Desc: 记录rpc调用者子事件，目前没有注入机制、暂时空实现
     * @param transaction
     */
    private void logRpcInvokeConsumerCross(Transaction transaction) {
        RpcReferenceContext referenceContext = RpcContextManager.lastReferenceContext(false);
        if(null !=referenceContext) {
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT, TraceConstant.EVENT_SOFA_RPC_INVOKE_APP, Message.SUCCESS, referenceContext.getTargetAppName());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT, TraceConstant.EVENT_SOFA_RPC_TRACE_ID, Message.SUCCESS, referenceContext.getTraceId());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT, TraceConstant.EVENT_SOFA_RPC_INVOKE_SERVICE_NAME, Message.SUCCESS, referenceContext.getServiceName());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT, TraceConstant.EVENT_SOFA_RPC_INVOKE_TARGET_URL, Message.SUCCESS, referenceContext.getTargetUrl());
            Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT, TraceConstant.EVENT_SOFA_RPC_COST_TIME, Message.SUCCESS, String.valueOf(referenceContext.getCostTime()));
        }
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 10:13
     * Desc: 记录rpc调用参数
     * @param args 接口参数
     */
    private void logRpcInvokeRequest(Object[] args) {
        Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT,TraceConstant.EVENT_SOFA_RPC_REQUEST, Message.SUCCESS,getInvokeArg(args));
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 10:02
     * Desc: 获取调用rpc的类和方法信息，但目前存在一个问题，
     * 因为sofa没有提供钩子机制，切面只能拿到基本信息无法获取调用点信息
     * sofa2版本提供功能，待升级后解决
     * @param joinPoint
     * @return
     */
    private String getInvokeRpcInfo(JoinPoint joinPoint){
        StringBuilder builder=new StringBuilder();
        builder.append(joinPoint.getSignature().getDeclaringType().getName()).append(".");
        builder.append(((MethodSignature)joinPoint.getSignature()).getName());
        return builder.toString();
    }

    /**
     * client rpc调用不初始化消息ID，由CAT保证
     * @param context
     */
    @Override
    protected void initMessageId(Cat.Context context) {

    }
}
