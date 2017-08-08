package com.xinmei.general.tools;

import com.xinmei.common.basic.annotation.BizDesc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName: com.xinmei.general.tools.ClazzUtil
 * @Description: 工具类
 * @Author xbzhu
 * @Date 2017年03月03日 10:16
 */
public class ClazzUtil {


    /**
     * 判断一个类是否为基本数据类型。
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    public static boolean isBaseDataType(Class clazz){
        return
                (
                        clazz.equals(String.class) ||
                                clazz.equals(Integer.class) ||
                                clazz.equals(Byte.class) ||
                                clazz.equals(Long.class) ||
                                clazz.equals(Double.class) ||
                                clazz.equals(Float.class) ||
                                clazz.equals(Character.class) ||
                                clazz.equals(Short.class) ||
                                clazz.equals(BigDecimal.class) ||
                                clazz.equals(BigInteger.class) ||
                                clazz.equals(Boolean.class) ||
                                clazz.equals(Date.class) ||
                                clazz.isPrimitive()
                );
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/11 11:04
     * Desc: 通过反射获取某一个方法上的业务描述信息
     * @param clazz
     * @param method 类对象
     * @return
     */
    public static String getBizDesc(Class<?> clazz, Method method){
        BizDesc bizDesc=method.getAnnotation(BizDesc.class);
        if(null==bizDesc){
            return clazz.getName()+"."+method.getName();
        }
        return bizDesc.desc();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 14:30
     * Desc: 通过反射获取某一个方法的作者
     * @param clazz
     * @param method
     * @return
     */
    public static String getBizAuthor(Class<?> clazz, Method method){
        BizDesc bizDesc=method.getAnnotation(BizDesc.class);
        if(null==bizDesc){
            return "未知作者";
        }
        return bizDesc.author();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/11 11:37
     * Desc: 获取一个业务是否需要通知
     * @param method 类对象
     * @return
     */
    public static boolean getBizNotice(Method method){
        BizDesc bizDesc=method.getAnnotation(BizDesc.class);
        if(null==bizDesc){
            return true;
        }
        return bizDesc.notice();
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/30 11:40
     * Desc: 获取当前代理执行的类
     * @param pjp 连接点
     * @return
     */
    public static Class getInvokeClass(ProceedingJoinPoint pjp){
        return pjp.getSignature().getDeclaringType();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/30 11:43
     * Desc: 获取当前代理执行的方法
     * @param pjp 连接点
     * @return
     */
    public static Method getInvokeMethod(ProceedingJoinPoint pjp){
        return ((MethodSignature)pjp.getSignature()).getMethod();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/30 11:54
     * Desc: 通过连接点获取方法作者
     * @param pjp
     * @return
     */
    public static String getBizAuthor(ProceedingJoinPoint pjp){
        return getBizAuthor(getInvokeClass(pjp),getInvokeMethod(pjp));
    }
}
