package com.xinmei.common.metadata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/2.
 * 元数据平台提供元数据信息实体
 */
public class MetaDataRespModel {

    private String metadataId;
    private String nameCn;
    private String nameEn;//码表英文名称
    private BigDecimal version;//码表版本
    private String statusCd;//码表状态代码
    private String codeThemeCd;//码表主题分类
    private List<MetaDataValueRespModel> codeValueList=new ArrayList<MetaDataValueRespModel>();//码值列表，直接预初始化对象，避免null判断


    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getCodeThemeCd() {
        return codeThemeCd;
    }

    public void setCodeThemeCd(String codeThemeCd) {
        this.codeThemeCd = codeThemeCd;
    }

    public List<MetaDataValueRespModel> getCodeValueList() {
        return codeValueList;
    }

    public void setCodeValueList(List<MetaDataValueRespModel> codeValueList) {
        this.codeValueList = codeValueList;
    }
}
