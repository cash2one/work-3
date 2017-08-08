package com.xinmei.common.metadata.dao;

import com.xinmei.common.metadata.dto.MetaDataCodeValue;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 */
public interface MetaDataCodeValueDAO {

    /**
     * 插入元数据码值实体，插入前会进行insert not exists操作，验证条件为metaDataId，metaDataCodeValue
     * @param metaDataCodeValue 元数据标准码码值实体
     * @return
     */
    int insertMetaDataCodeValue(MetaDataCodeValue metaDataCodeValue);

    /**
     * 批量插入元数据码值实体，插入前会进行insert not exists操作，验证条件为metaDataId，metaDataCodeValue
     * @param metaDataCodeValues 元数据标准码码值实体集合
     * @return
     */
    int batchInsertMetaDataCodeValue(List<MetaDataCodeValue> metaDataCodeValues);

    /**
     * 批量更新元数据码值实体
     * @param metaDataCodeValues 元数据标准码码值实体集合
     * @return
     */
    int batchUpdateMetaDataCodeValue(List<MetaDataCodeValue> metaDataCodeValues);

    /**
     * 批量删除元数据码值实体
     * @param metaDataCodeValues 元数据标准码码值实体集合
     */
    void batchDeleteMetaDataCodeValue(List<MetaDataCodeValue> metaDataCodeValues);

    /**
     * 删除全部元数据码值
     */
    void deleteAllMetaDataCodeValue();

}
