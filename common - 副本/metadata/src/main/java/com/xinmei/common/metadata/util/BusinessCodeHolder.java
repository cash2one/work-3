package com.xinmei.common.metadata.util;


import com.xinmei.common.metadata.model.BusinessCode;

/**
 * Created by Toby on 2016/11/24.
 * 使用ThreadLocal保存与当前线程绑定的两码一号信息
 */
public class BusinessCodeHolder {

    private static ThreadLocal<BusinessCode> businessCodeHolder=new ThreadLocal<>();


    public static void initiator(String productCode,String transactionNum,String eventCode){
        BusinessCode businessCode=BusinessCode.initiator(productCode,transactionNum,eventCode);
        BusinessCodeHolder.setBusinessCode(businessCode);
    }


    public static String caller(String eventCode){
        BusinessCode businessCode=getBusinessCode();
        if(null==businessCode){
            return null;
        }else{
            businessCode.caller(eventCode);
        }
        return businessCode.toString();
    }

    public static String caller(String productCode,String eventCode){
        BusinessCode businessCode=getBusinessCode();
        if(null==businessCode){
            return null;
        }
        businessCode.caller(productCode,eventCode);
        return businessCode.toString();
    }

    public static BusinessCode callee(String businessCodeStr, String eventCode) {
        BusinessCode businessCode=BusinessCode.callee(businessCodeStr, eventCode);
        setBusinessCode(businessCode);
        return businessCode;
    }

    public static BusinessCode getBusinessCode(){
        return businessCodeHolder.get();
    }

    public static void setBusinessCode(BusinessCode businessCode){
        businessCodeHolder.set(businessCode);
    }


}
