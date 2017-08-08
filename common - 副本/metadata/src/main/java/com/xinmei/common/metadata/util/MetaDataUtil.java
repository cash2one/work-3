package com.xinmei.common.metadata.util;

import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.common.metadata.MetaDataContainer;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.model.ClassTypeEnum;

import java.util.List;

/**
 * Created by Toby on 2016/11/15.
 *
 * 元数据工具类
 */
public class MetaDataUtil {


    /**
     * 根据元数据代码id，获取元数据代码信息
     * @param metaDataId 代码英文名称
     * @return
     */
    public static MetaDataCode getCodeByMetaDataCodeId(String metaDataId){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeByMetaDataCodeId(metaDataId);
    }

    /**
     * 根据元数据代码英文名称，获取元数据代码信息
     * @param enName 代码英文名称
     * @return
     */
    public static MetaDataCode getCodeByMetaDataCodeEN(String enName){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeByMetaDataCodeEN(enName);
    }

    /**
     * 根据元数据代码id，获取元数据码值列表
     * @param metaDataId 元数据码id
     * @return
     */
    public static List<MetaDataCodeValue> getCodeValuesByMetaDataCodeId(String metaDataId){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValuesByMetaDataCodeId(metaDataId);
    }

    /**
     * 根据元数据代码英文名称，获取元数据码值列表
     * @param enName 元数据代码英文名称
     * @return
     */
    public static List<MetaDataCodeValue> getCodeValuesByMetaDataCodeEN(String enName){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValuesByMetaDataCodeEN(enName);
    }

    /**
     * 通过元数据码id，和码值获取码值对应中文描述信息
     * @param metaDataId 元数据码id
     * @param value 码值
     * @return
     */
    public static String getCodeValueDescByIDAndValue(String metaDataId,String value){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueDescByIDAndValue(metaDataId,value);
    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param value 码值
     * @return
     */
    public static MetaDataCodeValue getCodeValueByIDAndValue(String metaDataId,String value){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByIDAndValue(metaDataId,value);

    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param desc 码值描述
     * @return
     */
    public static MetaDataCodeValue getCodeValueByIDAndDesc(String metaDataId,String desc){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByIDAndDesc(metaDataId,desc);
    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param codeValueEnName 码值名称
     * @return
     */
    public static MetaDataCodeValue getCodeValueByIDAndENName(String metaDataId,String codeValueEnName){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByIDAndENName(metaDataId, codeValueEnName);
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param value 码值
     * @return
     */
    public static String getCodeValueDescByEnAndValue(String enName,String value){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueDescByEnAndValue(enName,value);
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param value 码值
     * @return
     */
    public static MetaDataCodeValue getCodeValueByEnAndValue(String enName,String value){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByEnAndValue(enName, value);
    }


    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param desc 码值描述
     * @return
     */
    public static MetaDataCodeValue getCodeValueByENAndDesc(String enName,String desc){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByENAndDesc(enName,desc);
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param codeValueEnName 码值名称
     * @return
     */
    public static MetaDataCodeValue getCodeValueByENAndENName(String enName,String codeValueEnName){
        return SpringContextUtils.getBean(MetaDataContainer.class).getCodeValueByENAndENName(enName, codeValueEnName);
    }

    public static String getEventCode(ClassTypeEnum type,String clazzName,String methodName,String unique){
        return SpringContextUtils.getBean(MetaDataContainer.class).getEventCode(type, clazzName, methodName, unique);
    }


}
