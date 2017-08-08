package com.xinmei.common.metadata.dao;

import com.xinmei.common.metadata.dto.MetaDataOperation;

/**
 * Created by Toby on 2016/11/3.
 */
public interface MetaDataOperationDAO {

    /**
     * 插入消息处理记录
     * @param metaDataOperation 消息操作记录实体
     * @return 主键自增id
     */
    int insertOperation(MetaDataOperation metaDataOperation);

    /**
     * 更新消息处理记录
     * @param metaDataOperation 消息操作记录实体
     * @return 影响行数
     */
    int updateOperation(MetaDataOperation metaDataOperation);
}
