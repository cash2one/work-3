package com.xinmei.common.metadata.service.impl;

import com.xinmei.common.metadata.dao.MetaDataOperationDAO;
import com.xinmei.common.metadata.dto.MetaDataOperation;
import com.xinmei.common.metadata.service.MetaDataOperationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Toby on 2016/11/3.
 */
public class MetaDataOperationServiceImpl implements MetaDataOperationService{

    private MetaDataOperationDAO metaDataOperationDAO;


    /**
     * 插入元数据消息操作记录
     * @param metaDataOperation 元数据操作记录实体
     * @return 数据库自增主键
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int insertOperation(MetaDataOperation metaDataOperation) {
        return metaDataOperationDAO.insertOperation(metaDataOperation);

    }

    /**
     * 更新元数据消息操作记录
     * @param metaDataOperation 元数据操作记录实体
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateOperation(MetaDataOperation metaDataOperation) {
        metaDataOperationDAO.updateOperation(metaDataOperation);
    }

    public void setMetaDataOperationDAO(MetaDataOperationDAO metaDataOperationDAO) {
        this.metaDataOperationDAO = metaDataOperationDAO;
    }
}
