package com.xinmei.common.trace;

import com.dianping.cat.message.Transaction;
import com.xinmei.common.basic.AppContext;

/**
 * @ClassName: com.xinmei.common.trace.TraceContext
 * @Description: trace当前线程容器对象
 * @Author xbzhu
 * @Date 2017年03月01日 19:48
 */
public class TraceContext extends AppContext{

    //当前请求线程transaction对象
    private static ThreadLocal<Transaction> transactionHolder =new ThreadLocal<Transaction>();

    //当前请求线程client transaction对象
    private static ThreadLocal<Transaction> clientTransactionHolder=new ThreadLocal<Transaction>();


    public static void setTransaction(Transaction transaction){
        transactionHolder.set(transaction);
    }

    public static void setClientTransaction(Transaction transaction){
        clientTransactionHolder.set(transaction);
    }


    public static Transaction getTransaction(){
        return transactionHolder.get();
    }

    public static Transaction getClientTransaction(){
        return clientTransactionHolder.get();
    }


    public static void releaseContext(){
        AppContext.releaseContext();
        transactionHolder.remove();
        clientTransactionHolder.remove();
    }
}
