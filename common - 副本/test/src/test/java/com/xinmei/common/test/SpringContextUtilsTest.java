package com.xinmei.common.test;

import com.xinmei.common.basic.tools.SpringContextUtils;
import org.junit.Test;

/**
 * Created by Toby on 2017/1/10.
 */
public class SpringContextUtilsTest extends BaseSpring{

    @Test
    public void testGetApplicationContext(){
        SpringContextUtils.getApplicationContext();
    }

    @Test
    public void testClearHolder(){
        SpringContextUtils.clearHolder();
    }

}
