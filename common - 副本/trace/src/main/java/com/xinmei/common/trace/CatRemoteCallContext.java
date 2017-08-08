package com.xinmei.common.trace;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: com.xinmei.common.trace.CatRemoteCallContext
 * @Description: 远程调用承载消息ID
 * @Author xbzhu
 * @Date 2017年03月02日 14:17
 */
public class CatRemoteCallContext implements Cat.Context{

    private Map<String,String> properties = new HashMap<String, String>();

    @Override
    public void addProperty(String key, String value) {
        properties.put(key,value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
