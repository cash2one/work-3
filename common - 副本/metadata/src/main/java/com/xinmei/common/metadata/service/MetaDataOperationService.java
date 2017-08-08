package com.xinmei.common.metadata.service;

import com.xinmei.common.metadata.dto.MetaDataOperation;

/**
 * Created by Toby on 2016/11/3.
 */
public interface MetaDataOperationService {

    /**
     * 插入元数据消息操作记录
     * @param metaDataOperation 元数据操作记录实体
     * @return 数据库自增主键
     */
    public int insertOperation(MetaDataOperation metaDataOperation);


    /**
     * 更新元数据消息操作记录
     * @param metaDataOperation 元数据操作记录实体
     */
    public void updateOperation(MetaDataOperation metaDataOperation);
}
