package com.xinmei.common.test;

import com.xinmei.common.metadata.model.BusinessCode;
import com.xinmei.common.metadata.util.BusinessCodeHolder;
import org.junit.Test;

/**
 * Created by Toby on 2017/1/10.
 */
public class BusinessHolderTest {

    @Test
    public void testCallerNull(){
        BusinessCodeHolder.caller("111");
    }


    @Test
    public void testCaller(){
        BusinessCode businessCode=new BusinessCode();
        BusinessCode.InitiatorBusinessCode initiatorBusinessCode=new BusinessCode.InitiatorBusinessCode();
        initiatorBusinessCode.setTransactionNum("1");
        initiatorBusinessCode.setEventCode("2");
        initiatorBusinessCode.setProductCode("3");
        businessCode.setInitiatorCodeBusinessCode(initiatorBusinessCode);
        BusinessCode.CallerBusinessCode callerBusinessCode=new BusinessCode.CallerBusinessCode();
        callerBusinessCode.setEventCode("4");
        callerBusinessCode.setProductCode("5");
        businessCode.setCallerCode(callerBusinessCode);
        BusinessCodeHolder.setBusinessCode(businessCode);
        BusinessCodeHolder.caller("111");
    }
}
