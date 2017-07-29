package com.trust.privilege.model;

public class StateType {
    private String stateTypeCd;

    private String stateTypeName;

    public String getStateTypeCd() {
        return stateTypeCd;
    }

    public void setStateTypeCd(String stateTypeCd) {
        this.stateTypeCd = stateTypeCd == null ? null : stateTypeCd.trim();
    }

    public String getStateTypeName() {
        return stateTypeName;
    }

    public void setStateTypeName(String stateTypeName) {
        this.stateTypeName = stateTypeName == null ? null : stateTypeName.trim();
    }
}