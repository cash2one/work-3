package com.xinmei.common.metadata.service;

import com.xinmei.common.metadata.dto.MetaDataCodeValue;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 */
public interface MetaDataCodeValueService {

    /**
     * 批量插入元数据码值
     * @param metaDataCodeValues 元数据码值集合
     */
    public void batchInsertMetaDataCodeValue(List<MetaDataCodeValue> metaDataCodeValues);

    /**
     * 插入元数据码值
     * @param metaDataCodeValue 元数据码值
     */
    public void insertMetaDataCodeValue(MetaDataCodeValue metaDataCodeValue);

}
