package com.xinmei.common.trace.util;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.basic.AppContext;
import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.common.trace.CatRemoteCallContext;
import com.xinmei.common.trace.TraceContext;
import com.xinmei.common.trace.constants.TraceConstant;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * @ClassName: com.xinmei.common.trace.util.TraceUtil
 * @Description: trace工具类
 * @Author xbzhu
 * @Date 2017年03月21日 16:17
 */
public class TraceUtil {


    /**
     * 用于手动报错
     * @param name 业务名称
     * @param bizDesc 业务描述
     * @param user 错误用户
     * @param errorInfo 错误信息
     */
    public static void traceBizError(String name,String bizDesc,String user,String errorInfo){
        Transaction transaction=Cat.newTransaction(CatConstants.TYPE_TRACE,name);
        transaction.addData(TraceConstant.BIZ_INFO,bizDesc);
        transaction.addData(TraceConstant.ERROR_NOTICE_SWITCH,true);
        transaction.addData(TraceConstant.ERROR_USER,user);
        transaction.addData(TraceConstant.ERROR_TIME, DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        transaction.addData(TraceConstant.ERROR_INFO,errorInfo);
        transaction.addData(TraceConstant.CURRENT_ENV_PROFILE, SpringContextUtils.getEnvActiveProfile());
        transaction.setStatus(errorInfo);
        transaction.complete();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 17:00
     * Desc: 用于调用第三方服务时，进行手动埋点操作开始
     * @param callName
     * @return
     */
    public static Transaction traceCallBegin(String callName){
        return Cat.newTransaction(CatConstants.TYPE_CALL,callName);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 17:07
     * Desc: 用户调用第三方服务时，进行手动埋点操作结束
     * @param transaction 当前的执行事务抽象
     * @param status 返回结果，必须为字符串或者异常类型
     */
    public static void traceCallEnd(Transaction transaction,Object status){
        if(status instanceof String){
            transaction.setStatus(String.valueOf(status));
        }else if(status instanceof Throwable){
            transaction.setStatus((Throwable)status);
        }
        transaction.complete();
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/22 15:16
     * Desc: 当任务以异常方式结束时，记录异常
     * @param transaction
     * @param status
     */
    public static void traceCallEndError(Transaction transaction,Exception status){
        Cat.logError(status);
        traceCallEnd(transaction,status);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 16:20
     * Desc: 发起异步调用时，生成包含代用三个消息ID的上下文
     * @return
     */
    public static Cat.Context crossThreadCall(){
        Cat.Context context=new CatRemoteCallContext();
        Cat.logRemoteCallClient(context);
        return context;
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 16:22
     * Desc: 跨线程被调用者埋点
     * @param context
     */
    public static void crossThreadCalled(Cat.Context context){
        Cat.logRemoteCallServer(context);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/4/7 19:57
     * Desc: 记录rest方式当前登录用户
     * @param loginUserNo 登录用户名
     * @param userName 当前用户名
     */
    public void traceRestLoginUser(String loginUserNo,String userName){
        AppContext.setUser(loginUserNo);
        AppContext.setUserName(userName);
        Cat.logEvent(CatConstants.TYPE_URL, CatConstants.TYPE_URL + ".User", Message.SUCCESS, TraceContext.getUser() == null ? "nouser" : TraceContext.getUser());
    }

}
