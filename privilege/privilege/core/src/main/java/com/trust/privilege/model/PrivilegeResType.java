package com.trust.privilege.model;

public class PrivilegeResType {
    private String resTypeCd;

    private String resTypeMark;

    private String resTypeName;

    private String resTypeDesc;

    public String getResTypeCd() {
        return resTypeCd;
    }

    public void setResTypeCd(String resTypeCd) {
        this.resTypeCd = resTypeCd == null ? null : resTypeCd.trim();
    }

    public String getResTypeMark() {
        return resTypeMark;
    }

    public void setResTypeMark(String resTypeMark) {
        this.resTypeMark = resTypeMark == null ? null : resTypeMark.trim();
    }

    public String getResTypeName() {
        return resTypeName;
    }

    public void setResTypeName(String resTypeName) {
        this.resTypeName = resTypeName == null ? null : resTypeName.trim();
    }

    public String getResTypeDesc() {
        return resTypeDesc;
    }

    public void setResTypeDesc(String resTypeDesc) {
        this.resTypeDesc = resTypeDesc == null ? null : resTypeDesc.trim();
    }
}