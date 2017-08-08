package com.xinmei.common.sms.model;

/**
 * @ClassName: com.xinmei.common.sms.model.MessageHeader
 * @Description: ums发送消息，消息头
 * @Author xbzhu
 * @Date 2017年03月10日 16:32
 */
public class MessageHeader {

    //账号
    private String accountName;
    //账号类型
    private String accountType;
    /**
     * 模板相关参数
     */
    private String mappingKey1;
    private String mappingKey2;
    private String mappingKey3;
    private String mappingKey4;
    //接收人
    private String receiver;
    //发送时间
    private String sendTime;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMappingKey1() {
        return mappingKey1;
    }

    public void setMappingKey1(String mappingKey1) {
        this.mappingKey1 = mappingKey1;
    }

    public String getMappingKey2() {
        return mappingKey2;
    }

    public void setMappingKey2(String mappingKey2) {
        this.mappingKey2 = mappingKey2;
    }

    public String getMappingKey3() {
        return mappingKey3;
    }

    public void setMappingKey3(String mappingKey3) {
        this.mappingKey3 = mappingKey3;
    }

    public String getMappingKey4() {
        return mappingKey4;
    }

    public void setMappingKey4(String mappingKey4) {
        this.mappingKey4 = mappingKey4;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
