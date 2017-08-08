package com.xinmei.common.test;

import com.xinmei.general.tools.SubmitTokenUtil;
import org.junit.Test;

/**
 * Created by Toby on 2017/1/10.
 */
public class SubmitTokenUtilTest extends BaseSpring{

    @Test
    public void testGetSubmitToken(){
        SubmitTokenUtil.getSubmitToken("key",100);
    }

    @Test
    public void testCheckSubmitToken(){
        SubmitTokenUtil.checkSubmitToken("key","key");
    }

    @Test
    public void testGetSubmitTokenNoEx(){
        SubmitTokenUtil.getSubmitToken("key");
    }

    @Test
    public void testGetSubmitTokenByKeyNoEx(){
        SubmitTokenUtil.getSubmitTokenByKey("key");
    }

    @Test
    public void testGetSubmitTokenByKey(){
        SubmitTokenUtil.getSubmitTokenByKey("key",100);
    }

    @Test
    public void testReleaseSubmitToken(){
        SubmitTokenUtil.releaseSubmitToken("key");
    }

    @Test
    public void testPutVerifyCode(){
        SubmitTokenUtil.putVerifyCode("key");
    }
}
