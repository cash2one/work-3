package com.xinmei.common.basic;

import com.xinmei.common.basic.tools.SpringContextUtils;

/**
 * Created by Toby on 2017/2/6.
 * 业务key生成工具类
 */
public class BusinessKeyUtil {


    public final static String SEPARATOR="-";

    /**
     * 生成某一业务数据唯一key
     * @param model 实体
     * @param modelId 实体id
     * @return 业务key
     */
    public static String buildBusinessKey(String model,String modelId){
        StringBuilder builder=new StringBuilder(SpringContextUtils.getBean(CommonExtend.class).getSysCode());
        builder.append(SEPARATOR).append(model).append(SEPARATOR).append(modelId);
        return builder.toString();
    }

    /**
     * 生成某一业务数据某一业务动作唯一key
     * @param model 实体
     * @param modelId 实体id
     * @param action 动作类型
     * @return
     */
    public static String buildBusinessKey(String model,String modelId,String action){
        StringBuilder builder=new StringBuilder(SpringContextUtils.getBean(CommonExtend.class).getSysCode());
        builder.append(SEPARATOR).append(model).append(SEPARATOR).append(modelId).append(SEPARATOR).append(action);
        return builder.toString();
    }
}
