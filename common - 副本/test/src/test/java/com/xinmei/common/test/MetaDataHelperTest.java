package com.xinmei.common.test;

import com.xinmei.common.metadata.MetaDataContainer;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.model.ClassTypeEnum;
import com.xinmei.common.metadata.model.EventCodeModel;
import com.xinmei.common.metadata.util.DateUtil;
import com.xinmei.common.metadata.util.MetaDataHelper;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/3.
 */
public class MetaDataHelperTest extends BaseSpring{

    MetaDataHelper metaDataHelper;
    MetaDataContainer metaDataContainer;

    @Before
    public void setUp() throws Exception {
        metaDataHelper= (MetaDataHelper) context.getBean("metaDataHelper");
        metaDataContainer= (MetaDataContainer) context.getBean("metaDataContainer");
    }

    /**
     * 测试缓存容器是否与spring容器一起加载
     */
    @Test
    public void initTest(){
        //1.获取元数据标准码，包括码值
        System.out.println("----------------------------------------------------");
        System.out.println(metaDataContainer.getCodeByMetaDataCodeId("CA110000"));
        System.out.println(metaDataContainer.getCodeByMetaDataCodeEN("en_name_2016-11-02 19:10:00.0"));
        //2获取元数据标注码的码值
        System.out.println("----------------------------------------------------");
        System.out.println(metaDataContainer.getCodeValuesByMetaDataCodeId("CA110000"));
        System.out.println(metaDataContainer.getCodeValuesByMetaDataCodeEN("en_name_2016-11-02 19:10:00.0"));
        //3.获取元数据标准码信息
        System.out.println("----------------------------------------------------");
        System.out.println(metaDataContainer.getCodeValueByENAndDesc("en_name_2016-11-02 19:10:00.0","测试修改描述_2016-11-02 19:10:00.0"));
        System.out.println(metaDataContainer.getCodeValueByENAndENName("en_name_2016-11-02 19:10:00.0", "测试修改名称_2016-11-02 19:10:00.0"));
        System.out.println(metaDataContainer.getCodeValueByEnAndValue("en_name_2016-11-02 19:10:00.0", "101"));
        System.out.println(metaDataContainer.getCodeValueByIDAndDesc("CA110000","测试修改描述_2016-11-02 19:10:00.0"));
        System.out.println(metaDataContainer.getCodeValueByIDAndENName("CA110000", "测试修改名称_2016-11-02 19:10:00.0"));
        System.out.println(metaDataContainer.getCodeValueByIDAndValue("CA110000", "101"));
        System.out.println(metaDataContainer.getCodeValueDescByEnAndValue("en_name_2016-11-02 19:10:00.0","101"));
        System.out.println(metaDataContainer.getCodeValueDescByIDAndValue("CA110000","101"));

    }

    @Test
    public void testReloadMetaDataCode(){
        metaDataHelper.reloadMetaDataCode();
    }

    @Test
    public void testDestroy(){
        metaDataHelper.destroy();
    }

    @Test
    public void testModify(){
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        //修改的metaDataCode
        MetaDataCode metaDataCode=new MetaDataCode();
        metaDataCode.setMetaDataId("CA120000");
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

        metaDataContainer.modifyMetaData(metaDataCodes);
    }

    @Test
    public void testModify2(){
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
        metaDataCodeValue1.setCodeValueDesc("测试修改描述_1" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueCNName("测试修改名称_1" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCodeValue1.setCodeValueVal("1011");
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
        metaDataCodeValue2.setCodeValueENName("enname22");

        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue1);
        metaDataCode.getMetaDataCodeValues().add(metaDataCodeValue2);


        //新增的metaDataCode
        MetaDataCode metaDataCode1=new MetaDataCode();
        metaDataCode1.setMetaDataId("CA110001");
        metaDataCode1.setVersion(new BigDecimal("0.908"));
        metaDataCode1.setTopicType("01");
        metaDataCode1.setMetaDataCNName("中文名称1_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
        metaDataCode1.setMetaDataENName("en_name1_"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
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

        metaDataContainer.modifyMetaData(metaDataCodes);
    }

    @Test
    public void testModify3(){
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        //修改的metaDataCode
        MetaDataCode metaDataCode=new MetaDataCode();
        metaDataCode.setMetaDataId("CA110000");
        metaDataCode.setVersion(new BigDecimal("0.999"));
        metaDataCode.setTopicType("03");
        metaDataCode.setMetaDataCNName("中文名称12"+ DateUtil.convertStrToTime("2016-11-2 19:10:00",DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
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
        metaDataCodeValue2.setStatus("02");
        metaDataCodeValue2.setCodeValueDesc("测试新增描述1_" + DateUtil.convertStrToTime("2016-11-2 19:10:00", DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS));
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

        metaDataContainer.modifyMetaData(metaDataCodes);
    }

    @Test
    public void testInitEventCode(){
        List<EventCodeModel> eventCodeModels=new ArrayList<>();
        EventCodeModel eventCodeModel=new EventCodeModel();
        eventCodeModel.setEventCode("1111");
        eventCodeModel.setEventMethod("test");
        eventCodeModel.setEventPackageUrl("com.xinmei.");
        eventCodeModel.setSysCode("test");
        eventCodeModels.add(eventCodeModel);
        metaDataContainer.initEventCode(eventCodeModels);
    }

    @Test
    public void testGetEventCode(){
        metaDataContainer.getEventCode(ClassTypeEnum.SDK,"com.xinmei","test","");
    }
}
