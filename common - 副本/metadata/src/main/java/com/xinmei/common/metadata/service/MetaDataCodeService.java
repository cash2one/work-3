package com.xinmei.common.metadata.service;

import com.xinmei.common.metadata.dto.MetaDataCode;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 */
public interface MetaDataCodeService {

    /**
     * 查询全部的元数据码，返回包括码值
     * @return 元数据码集合
     */
    public List<MetaDataCode> queryAllMetaDataCode();

    /**
     * 将集合中的元数据码集合，持久化更新到数据库中
     * @param metaDataCodes 元数据码集合
     */
    public void updateMetaData(List<MetaDataCode> metaDataCodes);

    /**
     * 通过元数据码的id，去查询元数据码，返回包括码值列表
     * @param id 元数据码id
     * @return 元数据码
     */
    public MetaDataCode queryMetaDataById(String id);

    /**
     * 更新所有的元数据码，与updateMetaDataCode的不同在于，
     * updateMetaDataCode中的参数为新增或修改的部分数据
     * updateMetaDataCodeAll为全量的数据，进行先删除，后持久化操作
     * @param metaDataCodes 元数据码集合
     */
    public void updateMetaDataCodeAll(List<MetaDataCode> metaDataCodes);
}
