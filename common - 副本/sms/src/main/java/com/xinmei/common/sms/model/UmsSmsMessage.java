package com.xinmei.common.sms.model;

/**
 * @ClassName: com.xinmei.common.sms.model.UmsSmsMessage
 * @Description: ums发送消息模板
 * @Author xbzhu
 * @Date 2017年03月10日 16:41
 */
public class UmsSmsMessage {

    private String srcTopic;
    private String srcEventcode;
    private String messagePayload;


    public String getMessagePayload() {
        return messagePayload;
    }

    public void setMessagePayload(String messagePayload) {
        this.messagePayload = messagePayload;
    }

    public String getSrcEventcode() {
        return srcEventcode;
    }

    public void setSrcEventcode(String srcEventcode) {
        this.srcEventcode = srcEventcode;
    }

    public String getSrcTopic() {
        return srcTopic;
    }

    public void setSrcTopic(String srcTopic) {
        this.srcTopic = srcTopic;
    }
}
