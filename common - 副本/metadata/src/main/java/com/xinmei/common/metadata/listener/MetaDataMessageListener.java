package com.xinmei.common.metadata.listener;

import com.alipay.common.event.UniformEvent;
import com.alipay.common.event.UniformEventContext;
import com.alipay.common.event.UniformEventMessageListener;
import com.xinmei.common.metadata.util.MetaDataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Toby on 2016/11/2.
 * 元数据消息队列监听器用于处理元数据平台推送消息
 */
public class MetaDataMessageListener implements UniformEventMessageListener {

    private Logger logger= LoggerFactory.getLogger(MetaDataMessageListener.class);

    private MetaDataHelper metaDataHelper;

    @Override
    public void onUniformEvent(UniformEvent uniformEvent, UniformEventContext uniformEventContext) throws Exception {
        // 获取topic，eventCode和msgBody
        final String topic = uniformEvent.getTopic();
        final String eventCode = uniformEvent.getEventCode();
        final String msgBody = (String) uniformEvent.getEventPayload();
        logger.info("[Receive a message] topic {} eventcode {} eventId {} payload {}", new Object[] { topic, eventCode, uniformEvent.getId(), msgBody});
        metaDataHelper.handMessage(msgBody);
    }

    public void setMetaDataHelper(MetaDataHelper metaDataHelper) {
        this.metaDataHelper = metaDataHelper;
    }
}
