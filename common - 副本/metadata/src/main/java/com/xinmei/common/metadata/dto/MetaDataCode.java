package com.xinmei.common.metadata.dto;

import com.xinmei.common.metadata.model.MetaDataRespModel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 * 元数据实体类
 */
public class MetaDataCode {

    //元数据唯一id，元数据平台提供
    private String metaDataId;
    //元数据中文名称
    private String metaDataCNName;
    //元数据中文名称
    private String metaDataENName;
    //元数据版本
    private BigDecimal version;
    //元数据状态
    private String status;
    //元数据主题分类
    private String topicType;
    //元数据更新时间
    private Timestamp updateTime;

    private List<MetaDataCodeValue> metaDataCodeValues =new ArrayList<MetaDataCodeValue>();

    public MetaDataCode() {
    }

    /**
     * 通过返回报文初始化元数据模型
     * @param metaDataRespModel 元数据平台返回元数据信息
     * @return
     */
    public static MetaDataCode initByResp(Timestamp updateTime,MetaDataRespModel metaDataRespModel){
        MetaDataCode metaDataCode =new MetaDataCode();
        metaDataCode.setMetaDataId(metaDataRespModel.getMetadataId());
        metaDataCode.setStatus(metaDataRespModel.getStatusCd());
        metaDataCode.setMetaDataCNName(metaDataRespModel.getNameCn());
        metaDataCode.setMetaDataENName(metaDataRespModel.getNameEn());
        metaDataCode.setTopicType(metaDataRespModel.getCodeThemeCd());
        metaDataCode.setVersion(metaDataRespModel.getVersion());
        metaDataCode.setUpdateTime(updateTime);
        return metaDataCode;
    }

    public final static MetaDataCode DEFAULT_NULL_META_DATA_CODE=new MetaDataCode();

    @Override
    public String toString() {
        return "MetaDataCode{" +
                "metaDataId='" + metaDataId + '\'' +
                ", metaDataCNName='" + metaDataCNName + '\'' +
                ", metaDataENName='" + metaDataENName + '\'' +
                ", version=" + version +
                ", status='" + status + '\'' +
                ", topicType='" + topicType + '\'' +
                ", updateTime=" + updateTime +
                ", metaDataCodeValues=" + metaDataCodeValues +
                '}';
    }

    public String getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(String metaDataId) {
        this.metaDataId = metaDataId;
    }

    public String getMetaDataCNName() {
        return metaDataCNName;
    }

    public void setMetaDataCNName(String metaDataCNName) {
        this.metaDataCNName = metaDataCNName;
    }

    public String getMetaDataENName() {
        return metaDataENName;
    }

    public void setMetaDataENName(String metaDataENName) {
        this.metaDataENName = metaDataENName;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<MetaDataCodeValue> getMetaDataCodeValues() {
        return metaDataCodeValues;
    }

    public void setMetaDataCodeValues(List<MetaDataCodeValue> metaDataCodeValues) {
        this.metaDataCodeValues = metaDataCodeValues;
    }
}
