package com.xinmei.common.test;

import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.util.MetaDataUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by Toby on 2016/12/3.
 */
public class MetaDataTest extends BaseSpring{

    @Test
    public void getSexList(){
        List<MetaDataCodeValue> codeValues=MetaDataUtil.getCodeValuesByMetaDataCodeEN("T_GENDER_CD");
        if(codeValues!=null) {
            for (MetaDataCodeValue metaDataCodeValue : codeValues) {
                System.out.println(metaDataCodeValue);
            }
        }

    }

    @Test
    public void getSexManValue(){
        MetaDataCodeValue codeValue=MetaDataUtil.getCodeValueByENAndENName("T_GENDER_CD", "MAN");
        if(codeValue!=null) {
            System.out.println(codeValue.getCodeValueVal());
        }
    }

    @Test
    public void getSexManDesc(){
        String desc=MetaDataUtil.getCodeValueDescByEnAndValue("T_GENDER_CD","M");
        System.out.println(desc);
    }
}
