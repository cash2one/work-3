package com.xinmei.common.trace.sofa.interceptor;

import com.alibaba.fastjson.JSON;
import com.alipay.common.tracer.TracerException;
import com.alipay.common.tracer.util.TracerContextUtil;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.AbstractMessage;
import com.dianping.cat.message.internal.NullMessage;
import com.xinmei.common.trace.CatRemoteCallContext;
import com.xinmei.common.trace.constants.TraceConstant;
import com.xinmei.general.tools.ClazzUtil;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: com.xinmei.common.trace.sofa.interceptor.AbstractInvokeInterceptor
 * @Description: rpc调用抽象拦截器
 * @Author xbzhu
 * @Date 2017年03月03日 13:26
 */
public abstract class AbstractInvokeInterceptor {

    private Logger logger= LoggerFactory.getLogger(AbstractInvokeInterceptor.class);

    //当前线程调用上下文容器持有对象
    protected static final ThreadLocal<Cat.Context> contextHolder = new ThreadLocal<Cat.Context>();

    public abstract Object interceptor(JoinPoint joinPoint) throws Throwable;


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:29
     * Desc: 将切面参数序列化为可读参数
     * @param args 参数列表
     * @return
     */
    protected String getInvokeArg(Object[] args){
        StringBuilder builder=new StringBuilder();
        if(null!=args&&args.length>0){
            for(Object obj:args){
                if(null !=obj) {
                    if (ClazzUtil.isBaseDataType(obj.getClass())) {
                        builder.append(obj).append("|");
                    } else {
                        builder.append(JSON.toJSONString(obj)).append("|");
                    }
                }
            }
        }
        return builder.toString();
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:32
     * Desc: 获取当前线程绑定Cat穿透参数容器
     * @return
     */
    protected Cat.Context getContext() {
        Cat.Context context=contextHolder.get();
        if(null ==context){
            context=initContext();
            contextHolder.set(context);
        }
        return context;
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:50
     * Desc: 初始化上下文
     * @return
     */
    private Cat.Context initContext(){
        Cat.Context context=new CatRemoteCallContext();
        initMessageId(context);
        return context;
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/7 13:43
     * Desc: 将初始化消息id独立出来，用于子类可以进行重写，在不同情况下进行初始化消息的操作
     * @param context
     */
    protected void initMessageId(Cat.Context context) {
        try {
            initContextBySofaTracer(context, Cat.Context.ROOT);
            initContextBySofaTracer(context, Cat.Context.PARENT);
            initContextBySofaTracer(context, Cat.Context.CHILD);
        } catch (TracerException e) {
            logger.info("trace get error");
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:50
     * Desc: 获取随tracer穿透的数据
     * @param context
     * @param key
     * @throws TracerException
     */
    private void initContextBySofaTracer(Cat.Context context, String key) throws TracerException {
        String value=TracerContextUtil.getPenetrateAttribute(key);
        if(null !=value&&!"".equals(value)){
            context.addProperty(key,value);
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:49
     * Desc: 释放当前线程中调用上下文
     */
    protected void releaseContextHolder(){
        contextHolder.remove();
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 13:59
     * Desc: 完成事件记录，并绑定到响应的流程中
     * @param transaction
     * @param event
     */
    protected void completeEvent(Transaction transaction, Event event) {
        if(NullMessage.EVENT != event) {
            event.setStatus(Message.SUCCESS);
            ((AbstractMessage) event).setCompleted(true);
            transaction.addChild(event);
        }
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/3 14:02
     * Desc: 记录rpc返回数据
     * @param result
     * @param transaction
     */
    protected void logRpcInvokeResponse(Object result,Transaction transaction) {
//        Event resultEvent=Cat.newEvent(TraceConstant.EVENT_SOFA_RPC_RESULT,getInvokeArg(new Object[]{result}));
//        completeEvent(transaction,resultEvent);
        Cat.logEvent(TraceConstant.TYPE_SOFA_RPC_CLIENT,TraceConstant.EVENT_SOFA_RPC_RESULT,Message.SUCCESS,getInvokeArg(new Object[]{result}));
    }



}
