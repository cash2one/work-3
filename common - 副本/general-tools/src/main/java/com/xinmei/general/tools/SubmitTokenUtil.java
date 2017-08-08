package com.xinmei.general.tools;

import com.xinmei.common.basic.CommonExtend;
import com.xinmei.common.basic.tools.SpringContextUtils;

import java.util.UUID;

/**
 * Created by Toby on 2016/11/30.
 * 防重复提交，生成以及校验工具类
 */
public class SubmitTokenUtil {


    /**
     * 获取token，token会存入共享缓存中，默认失效时间是10分钟
     * @param businessKey 业务主键，业务主键的构成唯一key，如果应用共用同一个缓存，要考虑唯一，
     *                    推荐appName.ClassName.id
     * @return
     */
    public static String getSubmitToken(String businessKey){
        return getSubmitToken(businessKey, 600);
    }

    /**
     * 获取token，token会存入共享缓存中
     * @param businessKey 业务主键，业务主键的构成唯一key，如果应用共用同一个缓存，要考虑唯一，
     *                    推荐appName.ClassName.id
     * @param expireTime 失效时间，单位妙
     * @return
     */
    public static String getSubmitToken(String businessKey,int expireTime){
        String token=UUID.randomUUID().toString();
        OCSUtil.putDataInCache(businessKey,token,expireTime);
        return token;
    }

    /**
     * 校验token是否存在
     * @param businessKey 放入缓存的业务主键一致
     * @param token
     * @return
     */
    public static boolean checkSubmitToken(String businessKey,String token){
        String cache=(String)OCSUtil.getDataInCacheByKey(businessKey);
        if(null==cache||!cache.equals(token)){
            return false;
        }
        OCSUtil.deleteCacheByKey(businessKey);
        return true;
    }

    @Deprecated
    public static String putVerifyCode(String verifyCode){
        String key=UUID.randomUUID().toString();
        OCSUtil.putDataInCache(key,verifyCode,1000);
        return key;
    }

    @Deprecated
    public static boolean checkVerifyCode(String key,String verifyCode){
        String cache=(String)OCSUtil.getDataInCacheByKey(key);
        if(null==cache||!cache.equals(verifyCode)){
            return false;
        }
        return true;
    }

    /**
     * 获取某一业务唯一锁，必须保证key全局唯一
     * @param businessKey 业务主键，业务主键的构成唯一key，如果应用共用同一个缓存，要考虑唯一，
     *                    推荐appName.ClassName.id
     * @return 是否获取到唯一锁
     */
    public static boolean getSubmitTokenByKey(String businessKey){
        return getSubmitTokenByKey(businessKey, 600);
    }

    /**
     *获取某一业务唯一锁，必须保证key全局唯一
     * @param businessKey 业务主键，业务主键的构成唯一key，如果应用共用同一个缓存，要考虑唯一，
     *                    推荐appName.ClassName.id
     * @param expireTime 失效时间，单位妙
     * @return 是否获取到唯一锁
     */
    public static boolean getSubmitTokenByKey(String businessKey,int expireTime){
        //如果在本地环境，不使用memcache直接返回true
        if(!SpringContextUtils.getBean(CommonExtend.class).isUseMemcache()){
            return true;
        }
        return OCSUtil.addDataInCache(businessKey,businessKey,expireTime);
    }

    /**
     * 释放某一业务唯一锁
     * @param businessKey 业务主键，业务主键的构成唯一key，如果应用共用同一个缓存，要考虑唯一，
     *                    推荐appName.ClassName.id
     */
    public static void releaseSubmitToken(String businessKey){
        OCSUtil.deleteCacheByKey(businessKey);
    }
}
