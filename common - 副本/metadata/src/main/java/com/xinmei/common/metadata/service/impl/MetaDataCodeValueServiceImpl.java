package com.xinmei.common.metadata.service.impl;

import com.xinmei.common.metadata.dao.MetaDataCodeValueDAO;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.service.MetaDataCodeValueService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 */
public class MetaDataCodeValueServiceImpl implements MetaDataCodeValueService {

    private MetaDataCodeValueDAO metaDataCodeValueDAO;


    /**
     * 批量插入元数据码值
     * @param metaDataCodeValues 元数据码值集合
     */
    @Transactional(rollbackFor = Throwable.class)
    public void batchInsertMetaDataCodeValue(List<MetaDataCodeValue> metaDataCodeValues){
        metaDataCodeValueDAO.batchInsertMetaDataCodeValue(metaDataCodeValues);
    }

    /**
     * 插入元数据码值
     * @param metaDataCodeValue 元数据码值
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insertMetaDataCodeValue(MetaDataCodeValue metaDataCodeValue){
        metaDataCodeValueDAO.insertMetaDataCodeValue(metaDataCodeValue);
    }


    public void setMetaDataCodeValueDAO(MetaDataCodeValueDAO metaDataCodeValueDAO) {
        this.metaDataCodeValueDAO = metaDataCodeValueDAO;
    }
}
