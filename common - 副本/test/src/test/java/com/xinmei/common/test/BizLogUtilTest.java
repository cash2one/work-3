package com.xinmei.common.test;

import com.alipay.common.tracer.util.DummyContextUtil;
import com.alipay.common.tracer.util.TracerContextUtil;
import com.xinmei.common.metadata.model.BusinessCode;
import com.xinmei.common.metadata.util.BizLogUtil;
import com.xinmei.common.metadata.util.BusinessCodeHolder;
import org.junit.Test;

/**
 * Created by Toby on 2017/1/10.
 */
public class BizLogUtilTest extends BaseSpring{

    @Test
    public void testInitiator(){
        BizLogUtil.initiator("1","2","3","com.xinmei","test","test");
    }

    @Test
    public void testInitiatorEx(){
        BizLogUtil.initiator("1","2","code","3","com.xinmei","test","test");
    }

    public void testCallerData(){
        BusinessCode businessCode=BusinessCode.initiator("1","2","3");
        BusinessCodeHolder.setBusinessCode(businessCode);
        BizLogUtil.caller("1","com.xinmei","test","test");

    }

    @Test
    public void testCaller(){
        BizLogUtil.caller("1","com.xinmei","test","test");
    }

    @Test
    public void testCallee(){
        BizLogUtil.callee("com.xinmei","test","test");
    }

    @Test
    public void testCalleeData(){
        try {
            DummyContextUtil.createDummyLogContext();
            String business="1|2|3|4|5|6";
            TracerContextUtil.putPenetrateAttribute("businessCode", business);
            BizLogUtil.callee("com.xinmei","test","test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
