package com.xinmei.common.test;

import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.service.MetaDataCodeValueService;
import com.xinmei.common.metadata.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/3.
 */
public class MetaDataCodeValueServiceTest extends BaseSpring{

    private MetaDataCodeValueService metaDataCodeValueService;

    @Before
    public void beforeTest(){
        metaDataCodeValueService= (MetaDataCodeValueService) context.getBean("metaDataCodeValueService");
    }

    @Test
    public void batchInsertMetaDataValueTest(){
        //修改数据
        MetaDataCodeValue metaDataCodeValue1=new MetaDataCodeValue();
        metaDataCodeValue1.setMetaDataId("CA110000");
        metaDataCodeValue1.setStatus("02");
        metaDataCodeValue1.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueVal("103");
        metaDataCodeValue1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue1.setCodeValueENName("enname1");

        //新增数据
        MetaDataCodeValue metaDataCodeValue2=new MetaDataCodeValue();
        metaDataCodeValue2.setMetaDataId("CA110000");
        metaDataCodeValue2.setStatus("03");
        metaDataCodeValue2.setCodeValueDesc("测试新增描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue2.setCodeValueCNName("测试新增名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue2.setCodeValueVal("111");
        metaDataCodeValue2.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue2.setCodeValueENName("enname2");

        List<MetaDataCodeValue> metaDataCodeValueList=new ArrayList<MetaDataCodeValue>();
        metaDataCodeValueList.add(metaDataCodeValue1);
        metaDataCodeValueList.add(metaDataCodeValue2);

        metaDataCodeValueService.batchInsertMetaDataCodeValue(metaDataCodeValueList);
    }

    @Test
    public void insertMetaDataCodeValueTest(){
        MetaDataCodeValue metaDataCodeValue1=new MetaDataCodeValue();
        metaDataCodeValue1.setMetaDataId("CA110000");
        metaDataCodeValue1.setStatus("02");
        metaDataCodeValue1.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueVal("104");
        metaDataCodeValue1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue1.setCodeValueENName("enname1");
        metaDataCodeValueService.insertMetaDataCodeValue(metaDataCodeValue1);
    }
}
