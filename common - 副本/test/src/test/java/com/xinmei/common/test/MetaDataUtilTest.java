package com.xinmei.common.test;

import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.model.ClassTypeEnum;
import com.xinmei.common.metadata.util.MetaDataUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Toby on 2017/1/10.
 */
public class MetaDataUtilTest extends BaseSpring{

    @Test
    public void testGetCodeValueDescByEnAndValue(){
        String desc=MetaDataUtil.getCodeValueDescByEnAndValue("en_name22016-11-02 19:10:00.0","103");
        Assert.assertNotNull(desc);
    }

    @Test
    public void testGetCodeValueDescByEnAndValueNull(){
        String desc=MetaDataUtil.getCodeValueDescByEnAndValue("en_name22016-11-02 19:10:00.0","1031");
        Assert.assertNull(desc);
    }

    @Test
    public void testGetCodeValueByENAndENName(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByENAndENName("en_name22016-11-02 19:10:00.0", "enname1");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByENAndENNameNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByENAndENName("en_name22016-11-02 19:10:00.0", "enname11");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValuesByMetaDataCodeEN(){
        List<MetaDataCodeValue> metaDataCodeValues=MetaDataUtil.getCodeValuesByMetaDataCodeEN("en_name22016-11-02 19:10:00.0");
        Assert.assertNotNull(metaDataCodeValues);
    }

    @Test
    public void testGetCodeValuesByMetaDataCodeENNull(){
        List<MetaDataCodeValue> metaDataCodeValues=MetaDataUtil.getCodeValuesByMetaDataCodeEN("en_name22016-11-0");
        Assert.assertNull(metaDataCodeValues);
    }

    @Test
    public void testGetCodeByMetaDataCodeEN(){
        MetaDataCode metaDataCode=MetaDataUtil.getCodeByMetaDataCodeEN("en_name22016-11-02 19:10:00.0");
        Assert.assertNotNull(metaDataCode);
    }

    @Test
    public void testGetCodeByMetaDataCodeENNull(){
        MetaDataCode metaDataCode=MetaDataUtil.getCodeByMetaDataCodeEN("en_name22016-11-02");
        Assert.assertNull(metaDataCode);
    }

    @Test
    public void testGetCodeByMetaDataCodeId(){
        MetaDataCode metaDataCode=MetaDataUtil.getCodeByMetaDataCodeId("CA110000");
        Assert.assertNotNull(metaDataCode);
    }

    @Test
    public void testGetCodeByMetaDataCodeIdNull(){
        MetaDataCode metaDataCode=MetaDataUtil.getCodeByMetaDataCodeId("CA1100001");
        Assert.assertNull(metaDataCode);
    }

    @Test
    public void testGetCodeValueByIDAndDesc(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndDesc("CA110000", "测试修改描述_2016-11-02 19:10:00.0");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndDescNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndDesc("CA110000", "测试修改描述_2016-1");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndDescCodeNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndDesc("CA130000", "测试修改描述_2016-1");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndENName(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndENName("CA110000", "enname1");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndENNameNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndENName("CA110000", "e1nname1");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndENNameCodeNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndENName("CA140000", "e1nname1");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueDescByIDAndValue(){
        String desc=MetaDataUtil.getCodeValueDescByIDAndValue("CA110000","101");
        Assert.assertNotNull(desc);
    }

    @Test
    public void testGetCodeValueDescByIDAndValueNull(){
        String desc=MetaDataUtil.getCodeValueDescByIDAndValue("CA110000","1011");
        Assert.assertNotNull(desc);
    }

    @Test
    public void testGetCodeValueDescByIDAndValueCodeNull(){
        String desc=MetaDataUtil.getCodeValueDescByIDAndValue("CA1100001","1011");
        Assert.assertNotNull(desc);
    }


    @Test
    public void testGetEventCode(){
        String eventCode=MetaDataUtil.getEventCode(ClassTypeEnum.SDK,"com.xinmei.clazz","test","test");
        Assert.assertNull(eventCode);
    }

    @Test
    public void testGetCodeValuesByMetaDataCodeId(){
        List<MetaDataCodeValue> metaDataCodeValues=MetaDataUtil.getCodeValuesByMetaDataCodeId("CA110000");
        Assert.assertNotNull(metaDataCodeValues);
    }

    @Test
    public void testGetCodeValuesByMetaDataCodeIdNull(){
        List<MetaDataCodeValue> metaDataCodeValues=MetaDataUtil.getCodeValuesByMetaDataCodeId("CA1110000");
        Assert.assertNull(metaDataCodeValues);
    }

    @Test
    public void testGetCodeValueByIDAndValue(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndValue("CA110000", "101");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndValueNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndValue("CA110000", "1011");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByIDAndValueCodeNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByIDAndValue("CA120000", "1011");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByEnAndValue(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByEnAndValue("en_name22016-11-02 19:10:00.0", "101");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByEnAndValueNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByEnAndValue("en_name22016-11-02 19:10:00.0", "1011");
        Assert.assertNull(metaDataCodeValue);
    }


    @Test
    public void testGetCodeValueByEnAndValueCodeNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByEnAndValue("en_1name22016-11-02 19:10:00.0", "1011");
        Assert.assertNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByENAndDesc(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByENAndDesc("en_name22016-11-02 19:10:00.0", "测试修改描述_2016-11-02 19:10:00.0");
        Assert.assertNotNull(metaDataCodeValue);
    }

    @Test
    public void testGetCodeValueByENAndDescNull(){
        MetaDataCodeValue metaDataCodeValue=MetaDataUtil.getCodeValueByENAndDesc("en_name22016-11-02 119:10:00.0", "测试修改描述_2016-11-02 19:10:00.0");
        Assert.assertNull(metaDataCodeValue);
    }


}
