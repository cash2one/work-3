package com.xinmei.common.sms.build;

import com.alibaba.fastjson.JSON;
import com.xinmei.common.basic.CommonExtend;
import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.common.sms.model.AlertMessageContext;
import com.xinmei.common.sms.model.AlertTemplate;
import com.xinmei.common.sms.model.MessageHeader;
import com.xinmei.common.sms.model.UmsSmsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: com.xinmei.common.sms.build.SmsMessageBuilder
 * @Description: sms消息
 * @Author xbzhu
 * @Date 2017年03月10日 16:51
 */
public class SmsMessageBuilder {


    private static CommonExtend commonExtend=null;

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/10 17:54
     * Desc: 生成预警短信消息
     * @param mobile 手机号
     * @param content 内容
     * @return
     */
    public static UmsSmsMessage buildAlertSmsMessage(String mobile,String content){
        UmsSmsMessage umsSmsMessage=new UmsSmsMessage();
        if(null==commonExtend){
            initCommonExtend();
        }
        umsSmsMessage.setSrcEventcode(commonExtend.getSrcEventcode());
        umsSmsMessage.setSrcTopic(commonExtend.getSrcTopic());

        AlertTemplate alertTemplate=new AlertTemplate();
        alertTemplate.setContext(content);

        MessageHeader messageHeader=new MessageHeader();
        messageHeader.setAccountName(commonExtend.getAccountName());
        messageHeader.setAccountType(commonExtend.getAccountType());
        messageHeader.setMappingKey1(commonExtend.getMappingKey1());
        messageHeader.setMappingKey2(commonExtend.getMappingKey2());
        messageHeader.setMappingKey3(commonExtend.getMappingKey3());
        messageHeader.setMappingKey4(commonExtend.getMappingKey4());
        messageHeader.setReceiver(mobile);

        List<MessageHeader> messageHeaders=new ArrayList<MessageHeader>();
        messageHeaders.add(messageHeader);

        AlertMessageContext alertMessageContext=new AlertMessageContext();
        alertMessageContext.setHeader(messageHeaders);
        alertMessageContext.setTemplateParams(alertTemplate);
        alertMessageContext.setSysId(commonExtend.getSysId());

        List<AlertMessageContext> alertMessageContexts=new ArrayList<AlertMessageContext>();
        alertMessageContexts.add(alertMessageContext);

        umsSmsMessage.setMessagePayload(JSON.toJSONString(alertMessageContexts));


        return umsSmsMessage;
    }


    private static synchronized void initCommonExtend(){
        if(null==commonExtend){
            commonExtend= SpringContextUtils.getBean(CommonExtend.class);
        }
    }
}
