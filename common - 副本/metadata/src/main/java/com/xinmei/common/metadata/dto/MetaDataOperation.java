package com.xinmei.common.metadata.dto;

import java.sql.Timestamp;

/**
 * Created by Toby on 2016/11/3.
 * 元数据数据库操作记录实体
 */
public class MetaDataOperation {

    //操作记录主键ID，数据库自增生成
    private Integer operationId;
    //操作节点
    private String  operationNode;
    //操作时间戳，是元数据平台提供的排他时间戳
    private Timestamp operationTime;
    //操作状态
    private short status;
    //错误信息
    private String errorInfo;
    //消息执行时间
    private Timestamp executeTime;

    public MetaDataOperation() {
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationNode() {
        return operationNode;
    }

    public void setOperationNode(String operationNode) {
        this.operationNode = operationNode;
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }
}
