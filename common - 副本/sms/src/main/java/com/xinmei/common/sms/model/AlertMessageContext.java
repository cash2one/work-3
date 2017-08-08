package com.xinmei.common.sms.model;

import java.util.List;

/**
 * @ClassName: com.xinmei.common.sms.model.AlertMessageContext
 * @Description: 发送消息实际内容实体
 * @Author xbzhu
 * @Date 2017年03月10日 16:35
 */
public class AlertMessageContext {

    private List<MessageHeader> header;
    private String sysId;
    private AlertTemplate templateParams;


    public List<MessageHeader> getHeader() {
        return header;
    }

    public void setHeader(List<MessageHeader> header) {
        this.header = header;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public AlertTemplate getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(AlertTemplate templateParams) {
        this.templateParams = templateParams;
    }
}
