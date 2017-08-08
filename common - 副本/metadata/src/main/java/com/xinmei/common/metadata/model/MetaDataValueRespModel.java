package com.xinmei.common.metadata.model;

/**
 * Created by Toby on 2016/11/2.
 */
public class MetaDataValueRespModel {

    private String codeValue;
    private String codeNameCn;
    private String codeDesc;
    private String valueStatusCd;
    private String codeNameEn;

    public MetaDataValueRespModel() {
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeNameCn() {
        return codeNameCn;
    }

    public void setCodeNameCn(String codeNameCn) {
        this.codeNameCn = codeNameCn;
    }

    public String getCodeNameEn() {
        return codeNameEn;
    }

    public void setCodeNameEn(String codeNameEn) {
        this.codeNameEn = codeNameEn;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getValueStatusCd() {
        return valueStatusCd;
    }

    public void setValueStatusCd(String valueStatusCd) {
        this.valueStatusCd = valueStatusCd;
    }

}
