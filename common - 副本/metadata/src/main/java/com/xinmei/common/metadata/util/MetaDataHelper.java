package com.xinmei.common.metadata.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xinmei.common.basic.CommonExtend;
import com.xinmei.common.metadata.MetaDataContainer;
import com.xinmei.common.metadata.constants.MetaDataConstants;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.dto.MetaDataOperation;
import com.xinmei.common.metadata.model.EventCodeModel;
import com.xinmei.common.metadata.model.MetaDataRespModel;
import com.xinmei.common.metadata.model.MetaDataValueRespModel;
import com.xinmei.common.metadata.model.RequestModel;
import com.xinmei.common.metadata.model.RespModel;
import com.xinmei.common.metadata.service.MetaDataCodeService;
import com.xinmei.common.metadata.service.MetaDataOperationService;
import com.xinmei.edata.metadata.facade.EventInformationSynServerFacade;
import com.xinmei.edata.metadata.facade.StdCodeTableRPCFacade;

/**
 * Created by Toby on 2016/11/2.
 * 元数据帮助类
 * 在spring容器中，配置时，应配置初始化方法为init，销毁调用方法为destroy
 */
public class MetaDataHelper {

    private Logger logger= LoggerFactory.getLogger(MetaDataHelper.class);


    private MetaDataCodeService metaDataCodeService;
    private MetaDataContainer metaDataContainer;
    private ExecutorService executorService;
    private MetaDataOperationService metaDataOperationService;
    private StdCodeTableRPCFacade stdCodeTableRPCFacade;
    private EventInformationSynServerFacade eventSyncFacade;
    private CommonExtend commonExtend;

    /**
     * 初始化元数据缓存容器
     * 在spring中调用的初始化方法
     */
    public void init(){
        if(commonExtend.isInvokeMetaDataPlatform()){
            subscribeAllMetaDataCode();
        }else {
            reloadMetaDataCode();
        }
        if(commonExtend.isInvokeBusinessPlatform()) {
            reloadEventCode(commonExtend.getSysCode());
        }
        executorService= Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"META DATA SYNC THREAD");
            }
        });
    }

    /**
     * 加载事件码
     */
    public void reloadEventCode(String sysCode) {
        String result=eventSyncFacade.selectBySysCode(sysCode);
        metaDataContainer.initEventCode(JSON.parseArray(result, EventCodeModel.class));
    }


    /**
     * 重新加载元数据，从持久化中加载数据，初始化缓存容器的数据
     */
    public void reloadMetaDataCode(){
        List<MetaDataCode> metaDataCodes = metaDataCodeService.queryAllMetaDataCode();
        metaDataContainer.initContainer(metaDataCodes);
    }

    /**
     * 处理元数据平台消息队列推送消息
     * 消息格式为json
     * @param message 消息json字符串
     */
    public void handMessage(String message){
        executorService.submit(new SyncMetaDataCode(message));
    }

    /**
     * 从元数据平台订阅所有的元数据标准代码
     */
    public void subscribeAllMetaDataCode(){
//        BizLogUtil.caller();
        //1.调用元数据平台rpc服务获取数据信息
        String source=stdCodeTableRPCFacade.selectByCond(JSON.toJSONString(RequestModel.getAllRequestModel()));//此为rpc调用返回的json字符串对象
        //2.反序列化rpc调用返回json数据
        List<MetaDataCode> metaDataCodes=deserialize(source);
        //3.初始化metaData容器，内存中数据重新加载
        metaDataContainer.initContainer(metaDataCodes);
        //4.更新数据库中所有数据
        metaDataCodeService.updateMetaDataCodeAll(metaDataCodes);
    }



    /**
     * 从元数据平台订阅数据，根据元数据id进行组合类订阅
     * @param metaDataId 元数据唯一id
     */
    public void subscribeMetaDataCodeById(String topicType, String metaDataId){
        //1.调用元数据平台rpc服务获取数据信息
        String source=stdCodeTableRPCFacade.selectByCond(JSON.toJSONString(RequestModel.getRequestModel(metaDataId,topicType)));//此为rpc调用返回的json字符串对象
        modifyMetaDataCode(source);
    }

    /**
     * 更新内存以及数据库中的数据
     * @param message 收到json格式消息
     */
    private void modifyMetaDataCode(String message){
        try {
            //2.反序列化rpc调用返回json数据
            List<MetaDataCode> metaDataCodes = deserialize(message);
            //3.更新内存中的数据
            metaDataContainer.modifyMetaData(metaDataCodes);
            //4.数据库数据处理
            updateDataAndRecord(metaDataCodes);
        }catch (Exception e){
            logger.error("invoke  error "+message,e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化json字符串，并转换为对应的对象
     */
    private <T> List<MetaDataCode> deserialize(String receive){
        //1.json字符串反序列化为对象
        RespModel respModel= JSON.parseObject(receive,RespModel.class);
        //2.将消息报文对象，转换为数据实体对象
        return convertMsgToDO(respModel);
    }

    /**
     * 报文实体对象转换为数据实体对象
     * @param respModel 报文对象
     */
    private List<MetaDataCode> convertMsgToDO(RespModel respModel) {
        List<MetaDataCode> metaDataCodes =new ArrayList<MetaDataCode>();
        List<MetaDataCodeValue> metaDataCodeValues =null;
        MetaDataCode metaDataCode =null;
        MetaDataCodeValue metaDataCodeValue =null;
        Timestamp updateTime=DateUtil.convertStrToTime(respModel.getTime(), DateUtil.TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
        for(MetaDataRespModel metaDataRespModel:respModel.getData()){
            metaDataCode = MetaDataCode.initByResp(updateTime,metaDataRespModel);
            metaDataCodeValues =new ArrayList<MetaDataCodeValue>();
            for(MetaDataValueRespModel metaDataValueRespModel:metaDataRespModel.getCodeValueList()){
                metaDataCodeValue = MetaDataCodeValue.initByResp(metaDataCode.getMetaDataId(),updateTime,metaDataValueRespModel);
                metaDataCodeValues.add(metaDataCodeValue);
            }
            metaDataCode.setMetaDataCodeValues(metaDataCodeValues);
            metaDataCodes.add(metaDataCode);
        }
        return metaDataCodes;
    }

    //构造操作记录对象
    private MetaDataOperation buildOperation(Timestamp operationTime) throws UnknownHostException {
        MetaDataOperation operation=new MetaDataOperation();
        operation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_EXECUTING);
        operation.setOperationTime(operationTime);
        operation.setOperationNode(InetAddress.getLocalHost().toString());
        operation.setErrorInfo("");
        operation.setExecuteTime(new Timestamp(System.currentTimeMillis()));
        return operation;
    }

    /**
     * 多个节点为了避免重新数据库操作，在操作前插入一条操作记录数据，
     * 用时间戳来验证数据是否存在，再进行插入，如果插入成功，在进行数据库操作
     * 分为两个事务操作，如果不考虑系统异常，只是数据有问题，别的节点也无法处理
     */
    private MetaDataOperation updateDataAndRecord(List<MetaDataCode> metaDataCodes){
        MetaDataOperation operation= null;
        try {
            operation = buildOperation(metaDataCodes.get(0).getUpdateTime());
        } catch (UnknownHostException e) {
            logger.error("host err",e);
        }
        if(metaDataOperationService.insertOperation(operation)==1) {
            try {
                metaDataCodeService.updateMetaData(metaDataCodes);
            }catch (Exception e){
                operation.setErrorInfo(e.getMessage());
                operation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_FAILURE);
            }
            operation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_SUCCESS);
            metaDataOperationService.updateOperation(operation);
        }
        return operation;
    }

    /**
     * 同步元数据消息任务
     */
    private class SyncMetaDataCode implements Runnable{

        private String message;

        public SyncMetaDataCode(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            modifyMetaDataCode(message);
        }

    }


    /**
     * 对象销毁调用方法，在spring中配置调用
     */
    public void destroy(){
        executorService.shutdown();;
        executorService=null;
    }


    public void setMetaDataCodeService(MetaDataCodeService metaDataCodeService) {
        this.metaDataCodeService = metaDataCodeService;
    }

    public void setMetaDataContainer(MetaDataContainer metaDataContainer) {
        this.metaDataContainer = metaDataContainer;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setStdCodeTableRPCFacade(StdCodeTableRPCFacade stdCodeTableRPCFacade) {
        this.stdCodeTableRPCFacade = stdCodeTableRPCFacade;
    }

    public void setMetaDataOperationService(MetaDataOperationService metaDataOperationService) {
        this.metaDataOperationService = metaDataOperationService;
    }

    public void setEventSyncFacade(EventInformationSynServerFacade eventSyncFacade) {
        this.eventSyncFacade = eventSyncFacade;
    }

    public void setCommonExtend(CommonExtend commonExtend) {
        this.commonExtend = commonExtend;
    }
}
