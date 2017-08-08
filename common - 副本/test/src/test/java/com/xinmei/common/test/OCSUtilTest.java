package com.xinmei.common.test;

import com.xinmei.general.tools.OCSUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Toby on 2017/1/10.
 */
public class OCSUtilTest extends BaseSpring{

    @Test
    public void testAddDataInCache(){
        boolean result=OCSUtil.addDataInCache("key","key",100);
        Assert.assertFalse(result);
    }

    @Test
    public void testPutDataInCache(){
        OCSUtil.putDataInCache("key","key",100);
    }

    @Test
    public void testDeleteCacheByKey(){
        OCSUtil.deleteCacheByKey("key");
    }

}
