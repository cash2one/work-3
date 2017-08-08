package com.xinmei.common.metadata.facade.impl;

import com.xinmei.common.metadata.MetaDataContainer;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.facade.MetaDataRestFacade;
import com.xinmei.common.metadata.model.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toby on 2016/11/3.
 * rest接口实现类
 */
public class MetaDataRestFacadeImpl implements MetaDataRestFacade{

    private Logger logger= LoggerFactory.getLogger(MetaDataRestFacadeImpl.class);

    private MetaDataContainer metaDataContainer;

    /**
     * 通过元数据标准码id，获取元数据标准码的信息，包括码值列表
     * @param metaDataCodeId 元数据标准码id
     * @return 元数据标准码的信息，包括码值列表
     */
    @Override
    public RestResponse<MetaDataCode> getCodeById(String metaDataCodeId) {
        logger.info("receive metaDataCodeId:" + metaDataCodeId);
        RestResponse<MetaDataCode> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeByMetaDataCodeId(metaDataCodeId));
        }catch (Exception e){
            logger.error("getCodeById error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 获取多个元数据标准码，通过标准码id列表，数组形式
     * 通过元数据标准码id集合，获取元数据标准码列表的信息，包括码值列表
     * @param metaDataCodeIds 元数据标准码id数组
     * @return 元数据标准码列表的信息，包括码值列表
     */
    @Override
    public RestResponse<List<MetaDataCode>> getCodeByIds(String metaDataCodeIds) {
        logger.info("receive metaDataCodeIds:" + metaDataCodeIds);
        String[] metaDataIdArray=metaDataCodeIds.split(",");
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        RestResponse<List<MetaDataCode>> restResponse=null;
        try{
            for(String metaDataCodeId:metaDataIdArray){
                if(metaDataContainer.getCodeByMetaDataCodeId(metaDataCodeId)!=null){
                    metaDataCodes.add(metaDataContainer.getCodeByMetaDataCodeId(metaDataCodeId));
                }
            }
            if(metaDataCodes.size()==0) {
                restResponse = new RestResponse(true, null);
            }else{
                restResponse=new RestResponse<>(true,metaDataCodes);
            }
        }catch (Exception e){
            logger.error("getCodeByIds error",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码英文名称，获取元数据标准码的信息，包括码值列表
     * @param metaDataENName 元数据标准码英文名称
     * @return 数据标准码的信息，包括码值列表
     */
    @Override
    public RestResponse<MetaDataCode> getCodeByEN(String metaDataENName) {
        logger.info("receive metaDataENName:" + metaDataENName);
        RestResponse<MetaDataCode> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeByMetaDataCodeEN(metaDataENName));
        }catch (Exception e){
            logger.error("getCodeByEN error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 获取多个元数据标准码，通过标准码英文名称列表，数组形式
     * 通过元数据标准码英文名称集合，获取元数据标准码列表的信息，包括码值列表
     * @param metaDataENNames 元数据标准码英文名称数组
     * @return 元数据标准码列表的信息，包括码值列表
     */
    @Override
    public RestResponse<List<MetaDataCode>> getCodeByENS(String metaDataENNames) {
        logger.info("receive metaDataENNames:" + metaDataENNames);
        String[] metaDataENNameArray=metaDataENNames.split(",");
        List<MetaDataCode> metaDataCodes=new ArrayList<MetaDataCode>();
        RestResponse<List<MetaDataCode>> restResponse=null;
        try{
            for(String metaDataENName:metaDataENNameArray){
                if(metaDataContainer.getCodeByMetaDataCodeEN(metaDataENName)!=null){
                    metaDataCodes.add(metaDataContainer.getCodeByMetaDataCodeEN(metaDataENName));
                }
            }
            if(metaDataCodes.size()==0) {
                restResponse = new RestResponse(true, null);
            }else {
                restResponse = new RestResponse(true, metaDataCodes);
            }
        }catch (Exception e){
            logger.error("getCodeByIds error",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码id，获取码值列表
     * @param metaDataCodeId 元数据标准码id
     * @return 码值列表
     */
    @Override
    public RestResponse<List<MetaDataCodeValue>> getCodeValuesById(String metaDataCodeId) {
        logger.info("receive metaDataCodeId:" + metaDataCodeId);
        RestResponse<List<MetaDataCodeValue>> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValuesByMetaDataCodeId(metaDataCodeId));
        }catch (Exception e){
            logger.error("getCodeValuesById error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码英文名称，获取码值列表
     * @param metaDataENName 元数据标准码英文名称
     * @return 码值列表
     */
    @Override
    public RestResponse<List<MetaDataCodeValue>> getCodeValuesByEN(String metaDataENName) {
        logger.info("receive metaDataENName:" + metaDataENName);
        RestResponse<List<MetaDataCodeValue>> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValuesByMetaDataCodeEN(metaDataENName));
        }catch (Exception e){
            logger.error("getCodeValuesByEN error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的id，码值的value，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeValue 码值
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByIdAndValue(String metaDataCodeId, String codeValue) {
        logger.info("receive metaDataCodeId,codeValue:"+metaDataCodeId+","+codeValue);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByIDAndValue(metaDataCodeId,codeValue));
        }catch (Exception e){
            logger.error("getCodeValueByIdAndValue error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的英文名称，码值的value，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeValue 码值
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndValue(String metaDataENName, String codeValue) {
        logger.info("receive metaDataENName,codeValue:"+metaDataENName+","+codeValue);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByEnAndValue(metaDataENName, codeValue));
        }catch (Exception e){
            logger.error("getCodeValueByENAndValue error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的id，码值的名称，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeName 码值名称
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByIDAndName(String metaDataCodeId, String codeName) {
        logger.info("receive metaDataCodeId,codeName:"+metaDataCodeId+","+codeName);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByIDAndENName(metaDataCodeId, codeName));
        }catch (Exception e){
            logger.error("getCodeValueByIDAndENName error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }


    /**
     * 通过元数据标准码的英文名称，码值的名称，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeName 码值名称
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndName(String metaDataENName, String codeName) {
        logger.info("receive metaDataENName,codeName:"+metaDataENName+","+codeName);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByENAndENName(metaDataENName, codeName));
        }catch (Exception e){
            logger.error("getCodeValueByENAndENName error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的id，码值的描述，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeDesc 码值描述
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByIDAndDesc(String metaDataCodeId, String codeDesc) {
        logger.info("receive metaDataCodeId,codeDesc:"+metaDataCodeId+","+codeDesc);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByIDAndDesc(metaDataCodeId, codeDesc));
        }catch (Exception e){
            logger.error("getCodeValueByIDAndDesc error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的英文名称，码值的描述，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeDesc 码值描述
     * @return 码值实体信息
     */
    @Override
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndDesc(String metaDataENName, String codeDesc) {
        logger.info("receive metaDataENName,codeDesc:"+metaDataENName+","+codeDesc);
        RestResponse<MetaDataCodeValue> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueByENAndDesc(metaDataENName, codeDesc));
        }catch (Exception e){
            logger.error("getCodeValueByENAndDesc error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的id，码值的value，获取码值描述
     * @param metaDataCodeId 元数据标准码id
     * @param codeValue 码值
     * @return 码值的描述
     */
    @Override
    public RestResponse<String> getCodeValueDescByIdAndValue(String metaDataCodeId, String codeValue) {
        logger.info("receive metaDataCodeId,codeValue:"+metaDataCodeId+","+codeValue);
        RestResponse<String> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueDescByIDAndValue(metaDataCodeId,codeValue));
        }catch (Exception e){
            logger.error("getCodeValueDescByIdAndValue error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    /**
     * 通过元数据标准码的英文名称，码值的value，获取码值描述
     * @param metaDataENName 标准码的英文名称
     * @param codeValue 码值
     * @return 码值实描述
     */
    @Override
    public RestResponse<String> getCodeValueDescByENAndValue(String metaDataENName, String codeValue) {
        logger.info("receive metaDataENName,codeValue:"+metaDataENName+","+codeValue);
        RestResponse<String> restResponse=null;
        try{
            restResponse=new RestResponse(true,metaDataContainer.getCodeValueDescByEnAndValue(metaDataENName,codeValue));
        }catch (Exception e){
            logger.error("getCodeValueDescByENAndValue error:",e);
            restResponse=new RestResponse("internal error");
        }
        return restResponse;
    }

    public void setMetaDataContainer(MetaDataContainer metaDataContainer) {
        this.metaDataContainer = metaDataContainer;
    }
}
