package com.xinmei.general.tools;

import com.xinmei.common.basic.tools.SpringContextUtils;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * Created by Toby on 2016/11/30.
 * ocs工具类
 */
public class OCSUtil {

    private static Logger logger= LoggerFactory.getLogger(OCSUtil.class);

    public static boolean addDataInCache(String key,Object value,int expireTime) {
        try {
            return SpringContextUtils.getBean(MemcachedClient.class).add(key, expireTime, value).get();
        }catch (InterruptedException | ExecutionException e) {
            logger.error("memcache add error",e);
            return false;
        }
    }

    public static void putDataInCache(String key,Object value,int expireTime){
        SpringContextUtils.getBean(MemcachedClient.class).set(key,expireTime,value);
    }


    public static boolean putDataInCacheResult(String key,Object value,int expireTime){
        try {
            return SpringContextUtils.getBean(MemcachedClient.class).set(key, expireTime, value).get();
        } catch (InterruptedException |ExecutionException e) {
            logger.error("memcache put error",e);
            return false;
        }
    }

    public static Object getDataInCacheByKey(String key){
        return SpringContextUtils.getBean(MemcachedClient.class).get(key);
    }

    public static void deleteCacheByKey(String key){
        SpringContextUtils.getBean(MemcachedClient.class).delete(key);
    }
}
