package com.xinmei.common.metadata.facade;

import com.xinmei.common.basic.constant.CommonConstant;
import com.xinmei.common.metadata.constants.MetaDataConstants;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.model.response.RestResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Toby on 2016/11/3.
 */

@Path(MetaDataConstants.REST_API_PREFIX)
@Consumes(CommonConstant.DEFAULT_CONTENT_TYPE)
@Produces(CommonConstant.DEFAULT_CONTENT_TYPE)
public interface MetaDataRestFacade {

    /**
     * 通过元数据标准码id，获取元数据标准码的信息，包括码值列表
     * @param metaDataCodeId 元数据标准码id
     * @return 元数据标准码的信息，包括码值列表
     */
    @GET
    @Path(value = "/getCode/id/{metaDataCodeId}")
    public RestResponse<MetaDataCode> getCodeById(@PathParam("metaDataCodeId")String metaDataCodeId);

    /**
     * 获取多个元数据标准码，通过标准码id列表，数组形式
     * 通过元数据标准码id集合，获取元数据标准码列表的信息，包括码值列表
     * @param metaDataCodeIds 元数据标准码id数组
     * @return 元数据标准码列表的信息，包括码值列表
     */
    @GET
    @Path(value = "/getCode/ids/{metaDataCodeIds}")
    public RestResponse<List<MetaDataCode>> getCodeByIds(@PathParam("metaDataCodeIds")String metaDataCodeIds);


    /**
     * 通过元数据标准码英文名称，获取元数据标准码的信息，包括码值列表
     * @param metaDataENName 元数据标准码英文名称
     * @return 数据标准码的信息，包括码值列表
     */
    @GET
    @Path(value = "/getCode/en/{metaDataENName}")
    public RestResponse<MetaDataCode> getCodeByEN(@PathParam("metaDataENName")String metaDataENName);


    /**
     * 获取多个元数据标准码，通过标准码英文名称列表，数组形式
     * 通过元数据标准码英文名称集合，获取元数据标准码列表的信息，包括码值列表
     * @param metaDataENNames 元数据标准码英文名称数组
     * @return 元数据标准码列表的信息，包括码值列表
     */
    @GET
    @Path(value = "/getCode/ens/{metaDataENNames}")
    public RestResponse<List<MetaDataCode>> getCodeByENS(@PathParam("metaDataENNames")String metaDataENNames);

    /**
     * 通过元数据标准码id，获取码值列表
     * @param metaDataCodeId 元数据标准码id
     * @return 码值列表
     */
    @GET
    @Path(value = "/getCodeValuesById/{metaDataCodeId}")
    public RestResponse<List<MetaDataCodeValue>> getCodeValuesById(@PathParam("metaDataCodeId")String metaDataCodeId);

    /**
     * 通过元数据标准码英文名称，获取码值列表
     * @param metaDataENName 元数据标准码英文名称
     * @return 码值列表
     */
    @GET
    @Path(value = "/getCodeValuesByEN/{metaDataENName}")
    public RestResponse<List<MetaDataCodeValue>> getCodeValuesByEN(@PathParam("metaDataENName")String metaDataENName);

    /**
     * 通过元数据标准码的id，码值的value，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeValue 码值
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByValue/id/{metaDataCodeId}/{codeValue}")
    public RestResponse<MetaDataCodeValue> getCodeValueByIdAndValue(@PathParam("metaDataCodeId")String metaDataCodeId,@PathParam("codeValue")String codeValue);

    /**
     * 通过元数据标准码的英文名称，码值的value，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeValue 码值
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByValue/en/{metaDataENName}/{codeValue}")
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndValue(@PathParam("metaDataENName")String metaDataENName,@PathParam("codeValue")String codeValue);

    /**
     * 通过元数据标准码的id，码值的名称，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeName 码值名称
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByName/id/{metaDataCodeId}/{codeName}")
    public RestResponse<MetaDataCodeValue> getCodeValueByIDAndName(@PathParam("metaDataCodeId")String metaDataCodeId,@PathParam("codeName")String codeName);

    /**
     * 通过元数据标准码的英文名称，码值的名称，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeName 码值名称
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByName/en/{metaDataENName}/{codeName}")
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndName(@PathParam("metaDataENName")String metaDataENName,@PathParam("codeName")String codeName);

    /**
     * 通过元数据标准码的id，码值的描述，获取码值实体信息
     * @param metaDataCodeId 元数据标准码id
     * @param codeDesc 码值描述
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByDesc/id/{metaDataCodeId}/{codeDesc}")
    public RestResponse<MetaDataCodeValue> getCodeValueByIDAndDesc(@PathParam("metaDataCodeId")String metaDataCodeId,@PathParam("codeDesc")String codeDesc);

    /**
     * 通过元数据标准码的英文名称，码值的描述，获取码值实体信息
     * @param metaDataENName 标准码的英文名称
     * @param codeDesc 码值描述
     * @return 码值实体信息
     */
    @GET
    @Path(value = "/getCodeValueByDesc/en/{metaDataENName}/{codeDesc}")
    public RestResponse<MetaDataCodeValue> getCodeValueByENAndDesc(@PathParam("metaDataENName")String metaDataENName,@PathParam("codeDesc")String codeDesc);

    /**
     * 通过元数据标准码的id，码值的value，获取码值实体的描述
     * @param metaDataCodeId 元数据标准码id
     * @param codeValue 码值
     * @return 码值的描述
     */
    @GET
    @Path(value = "/getCodeValueDescByValue/id/{metaDataCodeId}/{codeValue}")
    public RestResponse<String> getCodeValueDescByIdAndValue(@PathParam("metaDataCodeId")String metaDataCodeId,@PathParam("codeValue")String codeValue);

    /**
     * 通过元数据标准码的英文名称，码值的value，获取码值实体的描述
     * @param metaDataENName 标准码的英文名称
     * @param codeValue 码值
     * @return 码值实描述
     */
    @GET
    @Path(value = "/getCodeValueDescByValue/en/{metaDataENName}/{codeValue}")
    public RestResponse<String> getCodeValueDescByENAndValue(@PathParam("metaDataENName")String metaDataENName,@PathParam("codeValue")String codeValue);

}
