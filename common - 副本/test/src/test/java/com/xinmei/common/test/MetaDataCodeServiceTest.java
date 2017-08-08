package com.xinmei.common.test;

import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.service.MetaDataCodeService;
import com.xinmei.common.metadata.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/3.
 */
public class MetaDataCodeServiceTest extends BaseSpring{

    MetaDataCodeService metaDataCodeService=null;

    @Before
    public void beforeTest(){
        metaDataCodeService= (MetaDataCodeService) context.getBean("metaDataCodeService");
    }

    @Test
    public void queryAllMetaDataTest(){
        List<MetaDataCode> metaDataCodes=metaDataCodeService.queryAllMetaDataCode();
        System.out.println(metaDataCodes);
    }

    @Test
    public void queryMetaDataByIdTest(){
        MetaDataCode metaDataCode=metaDataCodeService.queryMetaDataById("CA100000");
        System.out.println(metaDataCode);
    }

    @Test
    public void updateMetaDataTest(){
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        //修改的metaDataCode
        MetaDataCode metaDataCode=new MetaDataCode();
        metaDataCode.setMetaDataId("CA100000");
        metaDataCode.setVersion(new BigDecimal("0.999"));
        metaDataCode.setTopicType("03");
        metaDataCode.setMetaDataCNName("中文名称2"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode.setMetaDataENName("en_name2"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode.setStatus("02");
        metaDataCode.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //修改数据
        MetaDataCodeValue metaDataCodeValue1=new MetaDataCodeValue();
        metaDataCodeValue1.setMetaDataId("CA100000");
        metaDataCodeValue1.setStatus("02");
        metaDataCodeValue1.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueVal("101");
        metaDataCodeValue1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue1.setCodeValueENName("ENNAME1");

        //新增数据
        MetaDataCodeValue metaDataCodeValue2=new MetaDataCodeValue();
        metaDataCodeValue2.setMetaDataId("CA100000");
        metaDataCodeValue2.setStatus("03");
        metaDataCodeValue2.setCodeValueDesc("测试新增描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue2.setCodeValueCNName("测试新增名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue2.setCodeValueVal("111");
        metaDataCodeValue2.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue2.setCodeValueENName("ENNAME2");

        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue1);
        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue2);


        //新增的metaDataCode
        MetaDataCode metaDataCode1=new MetaDataCode();
        metaDataCode1.setMetaDataId("CA100001");
        metaDataCode1.setVersion(new BigDecimal("0.908"));
        metaDataCode1.setTopicType("01");
        metaDataCode1.setMetaDataCNName("中文名称_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode1.setMetaDataENName("en_name_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode1.setStatus("03");
        metaDataCode1.setUpdateTime(new Timestamp(System.currentTimeMillis()));


        //修改数据
        MetaDataCodeValue metaDataCodeValue3=new MetaDataCodeValue();
        metaDataCodeValue3.setMetaDataId("CA100001");
        metaDataCodeValue3.setStatus("02");
        metaDataCodeValue3.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue3.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue3.setCodeValueVal("101");
        metaDataCodeValue3.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue3.setCodeValueENName("ENNAME3");

        //新增数据
        MetaDataCodeValue metaDataCodeValue4=new MetaDataCodeValue();
        metaDataCodeValue4.setMetaDataId("CA100001");
        metaDataCodeValue4.setStatus("03");
        metaDataCodeValue4.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue4.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue4.setCodeValueVal("111");
        metaDataCodeValue4.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue4.setCodeValueENName("ENNAME4");

        metaDataCode1.getMetaDataCodeValues().add(metaDataCodeValue3);
        metaDataCode1.getMetaDataCodeValues().add(metaDataCodeValue4);


        metaDataCodes.add(metaDataCode);
        metaDataCodes.add(metaDataCode1);

        metaDataCodeService.updateMetaData(metaDataCodes);

    }

    @Test
    public void updateMetaDataAllTest(){
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        //修改的metaDataCode
        MetaDataCode metaDataCode=new MetaDataCode();
        metaDataCode.setMetaDataId("CA110000");
        metaDataCode.setVersion(new BigDecimal("0.999"));
        metaDataCode.setTopicType("03");
        metaDataCode.setMetaDataCNName("中文名称2"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode.setMetaDataENName("en_name2"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode.setStatus("02");
        metaDataCode.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //修改数据
        MetaDataCodeValue metaDataCodeValue1=new MetaDataCodeValue();
        metaDataCodeValue1.setMetaDataId("CA110000");
        metaDataCodeValue1.setStatus("02");
        metaDataCodeValue1.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueVal("101");
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

        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue1);
        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue2);


        //新增的metaDataCode
        MetaDataCode metaDataCode1=new MetaDataCode();
        metaDataCode1.setMetaDataId("CA110001");
        metaDataCode1.setVersion(new BigDecimal("0.908"));
        metaDataCode1.setTopicType("01");
        metaDataCode1.setMetaDataCNName("中文名称_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode1.setMetaDataENName("en_name_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode1.setStatus("03");
        metaDataCode1.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //修改数据
        MetaDataCodeValue metaDataCodeValue3=new MetaDataCodeValue();
        metaDataCodeValue3.setMetaDataId("CA110001");
        metaDataCodeValue3.setStatus("02");
        metaDataCodeValue3.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue3.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue3.setCodeValueVal("101");
        metaDataCodeValue3.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue3.setCodeValueENName("enname3");

        //新增数据
        MetaDataCodeValue metaDataCodeValue4=new MetaDataCodeValue();
        metaDataCodeValue4.setMetaDataId("CA110001");
        metaDataCodeValue4.setStatus("03");
        metaDataCodeValue4.setCodeValueDesc("测试修改描述_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue4.setCodeValueCNName("测试修改名称_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue4.setCodeValueVal("111");
        metaDataCodeValue4.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        metaDataCodeValue4.setCodeValueENName("enname4");

        metaDataCode1.getMetaDataCodeValues().add(metaDataCodeValue3);
        metaDataCode1.getMetaDataCodeValues().add(metaDataCodeValue4);


        metaDataCodes.add(metaDataCode);
        metaDataCodes.add(metaDataCode1);

        metaDataCodeService.updateMetaDataCodeAll(metaDataCodes);
    }
}
