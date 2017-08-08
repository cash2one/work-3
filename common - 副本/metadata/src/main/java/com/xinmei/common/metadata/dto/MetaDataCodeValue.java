package com.xinmei.common.metadata.dto;

import com.xinmei.common.metadata.model.MetaDataValueRespModel;

import java.sql.Timestamp;

/**
 * Created by Toby on 2016/11/1.
 * 元数据码值实体
 */
public class MetaDataCodeValue implements Comparable<MetaDataCodeValue>{

    //元数据唯一id，元数据平台提供
    private String metaDataId;
    //元数据具体类型码值
    private String codeValueVal;
    //元数据码值映射名称
    private String codeValueCNName;
    //元数据英文名称
    private String codeValueENName;
    //元数据码值描述
    private String codeValueDesc;
    //元数据码值状态
    private String status;
    //元数据码值更新时间
    private Timestamp updateTime;

    public MetaDataCodeValue() {
    }

    public static MetaDataCodeValue initByResp(String metaDataId,Timestamp updateTime,MetaDataValueRespModel respModel) {
        MetaDataCodeValue metaDataCodeValue =new MetaDataCodeValue();
        metaDataCodeValue.setCodeValueVal(respModel.getCodeValue());
        metaDataCodeValue.setCodeValueCNName(respModel.getCodeNameCn());
        metaDataCodeValue.setCodeValueENName(respModel.getCodeNameEn());
        metaDataCodeValue.setCodeValueDesc(respModel.getCodeDesc());
        metaDataCodeValue.setStatus(respModel.getValueStatusCd());
        metaDataCodeValue.setMetaDataId(metaDataId);
        metaDataCodeValue.setUpdateTime(updateTime);
        return metaDataCodeValue;
    }


    @Override
    public int compareTo(MetaDataCodeValue o) {
        return o.getCodeValueVal().compareTo(getCodeValueVal());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + metaDataId.hashCode();
        result = 37 * result + codeValueVal.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (null==obj||!(obj instanceof MetaDataCodeValue)) {
            return false;
        }
        if(obj==this){
            return true;
        }
        MetaDataCodeValue metaDataCodeValue= (MetaDataCodeValue) obj;
        return metaDataCodeValue.getCodeValueVal().equals(codeValueVal)
                &&metaDataCodeValue.getMetaDataId().equals(metaDataId);
    }

    @Override
    public String toString() {
        return "MetaDataCodeValue{" +
                "codeValueCNName='" + codeValueCNName + '\'' +
                ", metaDataId='" + metaDataId + '\'' +
                ", codeValueVal='" + codeValueVal + '\'' +
                ", codeValueENName='" + codeValueENName + '\'' +
                ", codeValueDesc='" + codeValueDesc + '\'' +
                ", status='" + status + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }


    public String getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(String metaDataId) {
        this.metaDataId = metaDataId;
    }

    public String getCodeValueVal() {
        return codeValueVal;
    }

    public void setCodeValueVal(String codeValueVal) {
        this.codeValueVal = codeValueVal;
    }

    public String getCodeValueCNName() {
        return codeValueCNName;
    }

    public void setCodeValueCNName(String codeValueCNName) {
        this.codeValueCNName = codeValueCNName;
    }

    public String getCodeValueDesc() {
        return codeValueDesc;
    }

    public void setCodeValueDesc(String codeValueDesc) {
        this.codeValueDesc = codeValueDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCodeValueENName() {
        return codeValueENName;
    }

    public void setCodeValueENName(String codeValueENName) {
        this.codeValueENName = codeValueENName;
    }
}
