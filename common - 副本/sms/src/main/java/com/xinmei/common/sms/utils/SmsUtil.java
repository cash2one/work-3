package com.xinmei.common.sms.utils;

import com.alibaba.fastjson.JSON;
import com.trust.ums.facade.UmsMessageManagerFacade;
import com.trust.ums.modelvo.DmsMessageVO;
import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.common.sms.build.SmsMessageBuilder;
import com.xinmei.common.sms.model.UmsSmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName: com.xinmei.common.sms.utils.SmsUtil
 * @Description: 发短信工具类、调用ums rpc服务发送服务
 * @Author xbzhu
 * @Date 2017年03月10日 16:19
 */
public class SmsUtil {


    private static Logger logger= LoggerFactory.getLogger(SmsUtil.class);

    private static UmsMessageManagerFacade umsMessageManagerFacade;



    public static boolean sendSms(String mobile,String content){
        if(null==umsMessageManagerFacade){
            initUms();
        }
        UmsSmsMessage umsSmsMessage= SmsMessageBuilder.buildAlertSmsMessage(mobile,content);
        DmsMessageVO dmsMessageVO=new DmsMessageVO();
        BeanUtils.copyProperties(umsSmsMessage,dmsMessageVO);
        System.out.println(JSON.toJSONString(dmsMessageVO));
        List<String> result=umsMessageManagerFacade.sendMessage(dmsMessageVO);
        System.out.println(result);
        logger.info("SmsUtil.sendSms,result,{}",result);
        return true;
    }


    private static synchronized void initUms(){
        if(null==umsMessageManagerFacade) {
            umsMessageManagerFacade = SpringContextUtils.getBean("umsMessageManagerFacade");
        }
    }
}
