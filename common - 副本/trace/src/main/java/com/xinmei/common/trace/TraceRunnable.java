package com.xinmei.common.trace;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.trace.constants.TraceConstant;
import com.xinmei.common.trace.util.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: com.xinmei.common.trace.TraceRunnable
 * @Description: 异步任务trace
 * @Author xbzhu
 * @Date 2017年03月21日 16:40
 */
public abstract class TraceRunnable implements Runnable{

    private Logger logger= LoggerFactory.getLogger(TraceRunnable.class);

    private Cat.Context context;

    public TraceRunnable(Cat.Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        TraceUtil.crossThreadCalled(context);
        Transaction transaction=Cat.newTransaction(TraceConstant.TYPE_CROSS_THREAD_CALLED,getInvokeName());
        try{
            invoke();
            transaction.setStatus(Message.SUCCESS);
        }catch (Exception e){
            logger.error("TraceRunnable run error",e);
            transaction.setStatus(e);
            throw e;
        }finally {
            transaction.complete();
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 16:50
     * Desc: 异步执行任务核心代码
     */
    protected abstract void invoke();

    /**
     *Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 16:50
     * Desc: 获取要调用的名称，英文命名即可
     */
    protected abstract String getInvokeName();



}
