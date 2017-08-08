package com.xinmei.common.metadata.util;

import com.alipay.common.tracer.TracerException;
import com.alipay.common.tracer.util.DummyContextUtil;
import com.alipay.common.tracer.util.TracerContextUtil;
import com.xinmei.common.metadata.model.BusinessCode;
import com.xinmei.common.metadata.model.ClassTypeEnum;
import com.xinmei.general.tools.SerialGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Toby on 2016/11/24.
 * 两码一号日志工具类
 */
public class BizLogUtil {

    private static Logger logger= LoggerFactory.getLogger("com.xinmei.business.log");
    private static Logger defaultLogger=LoggerFactory.getLogger(BizLogUtil.class);

    private final static String BUSINESS_CODE_KEY="businessCode";

    /**
     * rest调用端初始化两码一号信息
     * @param productCode 产品编码
     * @param bizType 业务类型
     * @param channel   渠道
     * @param clazzName 类名
     * @param methodName 方法名
     * @param unique rest传空字符串即可
     */
    public static void initiator(String productCode,String bizType,String channel,String clazzName,String methodName,String unique){
        BusinessCodeHolder.initiator(productCode, SerialGenerator.getId(channel, bizType),getEventCode(ClassTypeEnum.rest,clazzName,methodName,unique));
    }

    /**
     * rest调用端初始化两码一号信息
     * @param productCode 产品编码
     * @param bizType 业务类型
     * @param extraCode 业务流水号
     * @param channel   渠道
     * @param clazzName 类名
     * @param methodName 方法名
     * @param unique rest传空字符串即可
     */
    public static void initiator(String productCode,String bizType,String extraCode,String channel,String clazzName,String methodName,String unique){
        BusinessCodeHolder.initiator(productCode,SerialGenerator.getId(channel,bizType,extraCode),getEventCode(ClassTypeEnum.rest,clazzName,methodName,unique));
    }

//    public static String caller(String clazzName,String methodName,String unique){
//        String businessCode=BusinessCodeHolder.caller(getEventCode(ClassTypeEnum.SDK,clazzName,methodName,unique));
//        try {
//            TracerContextUtil.putPenetrateAttribute(BUSINESS_CODE_KEY,businessCode);
//        } catch (TracerException e) {
//            defaultLogger.error("第一次RPC,获取TracerContext失败",e);
//            try {
//                DummyContextUtil.createDummyLogContext();
//                TracerContextUtil.putPenetrateAttribute(BUSINESS_CODE_KEY,businessCode);
//            } catch (Exception ex) {
//                defaultLogger.error("TracerContext操作失败",ex);
//            }
//        }
//        return businessCode;
//    }

    /**
     * rpc调用端，调用防范
     * @param productCode 产品编码、当前系统中定义的产品名称
     * @param clazzName 调用rpc接口名称，包括包名
     * @param methodName 方法名
     * @param unique rpc业务主键
     * @return
     */
    public static String caller(String productCode,String clazzName,String methodName,String unique){
        String businessCode=BusinessCodeHolder.caller(productCode,getEventCode(ClassTypeEnum.SDK, clazzName, methodName, unique));
        try {
            if(null!=businessCode) {
                TracerContextUtil.putPenetrateAttribute(BUSINESS_CODE_KEY, businessCode);
            }
        } catch (TracerException e) {
            defaultLogger.error("第一次RPC,获取TracerContext失败",e);
            try {
                DummyContextUtil.createDummyLogContext();
                TracerContextUtil.putPenetrateAttribute(BUSINESS_CODE_KEY,businessCode);
            } catch (Exception ex) {
                defaultLogger.error("TracerContext操作失败",ex);
            }
        }
        logger.info(businessCode);
        return businessCode;
    }


    /**
     * rpc调用端，两码一号日志打印。并返回两码日志字符串
     * @param clazzName 类名
     * @param methodName 方法名
     * @param unique 业务主键 rpc服务配置的
     * @return
     */
    public static String callee(String clazzName,String methodName,String unique){
        String businessCodeStr=null;
        try {
            businessCodeStr=TracerContextUtil.getPenetrateAttribute(BUSINESS_CODE_KEY);
        } catch (TracerException e) {
            defaultLogger.error("TracerContext获取两码一号失败",e);
            return null;
        }
        if(null!=businessCodeStr&&!"".equals(businessCodeStr)){
            BusinessCode businessCode=BusinessCodeHolder.callee(businessCodeStr, getEventCode(ClassTypeEnum.SDK, clazzName, methodName, unique));
            logger.info(businessCode.toString());
            return businessCode.toString();
        }
        return null;
    }



    private static String getEventCode(ClassTypeEnum type,String clazzName,String methodName,String unique){
        String eventCode= MetaDataUtil.getEventCode(type, clazzName, methodName, unique);
        if(null != eventCode){
            return eventCode;
        }
        return clazzName+"."+methodName;
    }

}
