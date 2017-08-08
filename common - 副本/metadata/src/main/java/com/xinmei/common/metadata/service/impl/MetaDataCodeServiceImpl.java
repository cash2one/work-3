package com.xinmei.common.metadata.service.impl;

import com.xinmei.common.metadata.dao.MetaDataCodeDAO;
import com.xinmei.common.metadata.dao.MetaDataCodeValueDAO;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.service.MetaDataCodeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 * 元数据码service
 */
public class MetaDataCodeServiceImpl implements MetaDataCodeService {


    private MetaDataCodeDAO metaDataCodeDAO;
    private MetaDataCodeValueDAO metaDataCodeValueDAO;

    /**
     * 查询全部的元数据码，返回包括码值
     * @return 元数据码集合
     */
    @Override
    public List<MetaDataCode> queryAllMetaDataCode() {
        return metaDataCodeDAO.queryAllMetaDataCode();
    }


    /**
     * 将集合中的元数据码集合，持久化更新到数据库中
     * @param metaDataCodes 元数据码集合
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void updateMetaData(List<MetaDataCode> metaDataCodes) {
        for(MetaDataCode metaDataCode:metaDataCodes){
            if(metaDataCodeDAO.queryMetaDataCodeById(metaDataCode.getMetaDataId())==null){
                metaDataCodeDAO.insertMetaDataCode(metaDataCode);
            }else{
                metaDataCodeDAO.updateMetaDataCode(metaDataCode);
            }
            metaDataCodeValueDAO.batchDeleteMetaDataCodeValue(metaDataCode.getMetaDataCodeValues());
            metaDataCodeValueDAO.batchInsertMetaDataCodeValue(metaDataCode.getMetaDataCodeValues());
        }
    }

    /**
     * 通过元数据码的id，去查询元数据码，返回包括码值列表
     * @param id 元数据码id
     * @return 元数据码
     */
    @Override
    public MetaDataCode queryMetaDataById(String id) {
        return metaDataCodeDAO.queryMetaDataCodeById(id);
    }


    /**
     * 更新所有的元数据码，与updateMetaDataCode的不同在于，
     * updateMetaDataCode中的参数为新增或修改的部分数据
     * updateMetaDataCodeAll为全量的数据，进行先删除，后持久化操作
     * @param metaDataCodes 元数据码集合
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void updateMetaDataCodeAll(List<MetaDataCode> metaDataCodes) {
        metaDataCodeValueDAO.deleteAllMetaDataCodeValue();
        metaDataCodeDAO.deleteAllMetaDataCode();
        for(MetaDataCode metaDataCode:metaDataCodes){
            metaDataCodeDAO.insertMetaDataCode(metaDataCode);
            if(metaDataCode.getMetaDataCodeValues().size()>0) {
                metaDataCodeValueDAO.batchInsertMetaDataCodeValue(metaDataCode.getMetaDataCodeValues());
            }
        }

    }

    public void setMetaDataCodeDAO(MetaDataCodeDAO metaDataCodeDAO) {
        this.metaDataCodeDAO = metaDataCodeDAO;
    }

    public void setMetaDataCodeValueDAO(MetaDataCodeValueDAO metaDataCodeValueDAO) {
        this.metaDataCodeValueDAO = metaDataCodeValueDAO;
    }
}
