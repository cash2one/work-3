package com.xinmei.common.metadata;

import com.xinmei.common.metadata.constants.MetaDataConstants;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.model.ClassTypeEnum;
import com.xinmei.common.metadata.model.EventCodeModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Toby on 2016/11/1.
 * 元数据容器用于缓存全部的元数据信息
 */
public class MetaDataContainer {

    //用元数据标准码id，缓存id与所有标准码值得映射
    private ConcurrentHashMap<String,MetaDataCodeValueHolder> metaDataCodeHolderById =new ConcurrentHashMap<String,MetaDataCodeValueHolder>();
    //用元数据标准码中文名称，缓存中文名称与所有标准码值得映射
    private ConcurrentHashMap<String,MetaDataCodeValueHolder> metaDataCodeHolderByCNName =new ConcurrentHashMap<String,MetaDataCodeValueHolder>();
    //用元数据标准码英文名称，缓存英文名称与所有标准码值得映射
    private ConcurrentHashMap<String,MetaDataCodeValueHolder> metaDataCodeHolderByENName =new ConcurrentHashMap<String,MetaDataCodeValueHolder>();
    //用于元数据标准码id与元数据对象的映射
    private ConcurrentHashMap<String,MetaDataCode> metaDataCodeIdMap =new ConcurrentHashMap<String,MetaDataCode>();
    //用于元数据标准码英文名称与元数据对象的映射
    private ConcurrentHashMap<String,MetaDataCode> metaDataCodeENMap =new ConcurrentHashMap<String,MetaDataCode>();

    private ConcurrentHashMap<String,EventCodeModel> eventCodeMap=new ConcurrentHashMap<>();

    private MetaDataContainer() {
    }

    /**
     * 根据元数据代码id，获取元数据代码信息
     * @param metaDataId 代码英文名称
     * @return
     */
    public MetaDataCode getCodeByMetaDataCodeId(String metaDataId){
        MetaDataCode metaDataCode= metaDataCodeIdMap.get(metaDataId);
        if(metaDataCode !=null){
            metaDataCode.setMetaDataCodeValues(metaDataCodeHolderById.get(metaDataId).getMetaDataCodeValueList());
        }
        return metaDataCode;
    }

    /**
     * 根据元数据代码英文名称，获取元数据代码信息
     * @param enName 代码英文名称
     * @return
     */
    public MetaDataCode getCodeByMetaDataCodeEN(String enName){
        MetaDataCode metaDataCode= metaDataCodeENMap.get(enName);
        if(metaDataCode!=null){
            metaDataCode.setMetaDataCodeValues(metaDataCodeHolderByENName.get(enName).getMetaDataCodeValueList());
        }
        return metaDataCode;
    }

    /**
     * 根据元数据代码id，获取元数据码值列表
     * @param metaDataId 元数据码id
     * @return
     */
    public List<MetaDataCodeValue> getCodeValuesByMetaDataCodeId(String metaDataId){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataId);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueList();
    }

    /**
     * 根据元数据代码英文名称，获取元数据码值列表
     * @param enName 元数据代码英文名称
     * @return
     */
    public List<MetaDataCodeValue> getCodeValuesByMetaDataCodeEN(String enName){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderByENName.get(enName);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueList();
    }

    /**
     * 通过元数据码id，和码值获取码值对应中文描述信息
     * @param metaDataId 元数据码id
     * @param value 码值
     * @return
     */
    public String getCodeValueDescByIDAndValue(String metaDataId,String value){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataId);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        MetaDataCodeValue metaDataCodeValue=metaDataCodeValueHolder.getMetaDataCodeValueByValue(value);
        return metaDataCodeValue==null?null:metaDataCodeValue.getCodeValueDesc();
    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param value 码值
     * @return
     */
    public MetaDataCodeValue getCodeValueByIDAndValue(String metaDataId,String value){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataId);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByValue(value);
    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param desc 码值描述
     * @return
     */
    public MetaDataCodeValue getCodeValueByIDAndDesc(String metaDataId,String desc){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataId);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByDesc(desc);
    }

    /**
     * 通过元数据码id，和码值获取码值实体信息
     * @param metaDataId 元数据码id
     * @param codeValueEnName 码值名称
     * @return
     */
    public MetaDataCodeValue getCodeValueByIDAndENName(String metaDataId, String codeValueEnName){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataId);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByName(codeValueEnName);
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param value 码值
     * @return
     */
    public String getCodeValueDescByEnAndValue(String enName,String value){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderByENName.get(enName);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        MetaDataCodeValue metaDataCodeValue=metaDataCodeValueHolder.getMetaDataCodeValueByValue(value);
        return metaDataCodeValue==null?null:metaDataCodeValue.getCodeValueDesc();
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param value 码值
     * @return
     */
    public MetaDataCodeValue getCodeValueByEnAndValue(String enName,String value){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderByENName.get(enName);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByValue(value);
    }


    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param desc 码值描述
     * @return
     */
    public MetaDataCodeValue getCodeValueByENAndDesc(String enName,String desc){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderByENName.get(enName);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByDesc(desc);
    }

    /**
     * 通过元数据码英文名称，和码值获取码值实体信息
     * @param enName 元数据码英文名称
     * @param codeValueEnName 码值名称
     * @return
     */
    public MetaDataCodeValue getCodeValueByENAndENName(String enName, String codeValueEnName){
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderByENName.get(enName);
        if(null== metaDataCodeValueHolder){
            return null;
        }
        return metaDataCodeValueHolder.getMetaDataCodeValueByName(codeValueEnName);
    }


    public String getEventCode(ClassTypeEnum type,String clazzName,String method,String unique){
        StringBuilder keyBuffer=new StringBuilder();
        switch (type){
            case SDK:
                keyBuffer.append(clazzName).append(":1.0");
                if(null!=unique&&!"".equals(unique)){
                    keyBuffer.append(":").append(unique);
                }
                keyBuffer.append("@DEFAULT");
                break;
            case rest:
                keyBuffer.append(clazzName);
                break;
            default:
                break;
        }
        keyBuffer.append("-").append(method);
        if(eventCodeMap.containsKey(keyBuffer.toString())){
            return eventCodeMap.get(keyBuffer.toString()).getEventCode();
        }
        return null;
    }

    /**
     * 初始化容器：要清理原有的容器持有
     * 为每种类型的标准码实例化一个MetaDataHolder对象用于持有标注码值的数据，
     * 用标准码的id、中文名称、英文名称建立与MetaDataHolder的关联
     * @param metaDataCodes 持久化到数据库中的值
     */
    public void initContainer(List<MetaDataCode> metaDataCodes) {
        if (null == metaDataCodes) {
            throw new IllegalArgumentException("metaData is null,can not init");
        }
        clearCache();
        MetaDataCodeValueHolder metaDataCodeValueHolder =null;
        for (MetaDataCode metaDataCode : metaDataCodes) {
            metaDataCodeValueHolder =new MetaDataCodeValueHolder(metaDataCode.getMetaDataCodeValues());
            metaDataCodeHolderById.put(metaDataCode.getMetaDataId(), metaDataCodeValueHolder);
            metaDataCodeHolderByCNName.put(metaDataCode.getMetaDataCNName(), metaDataCodeValueHolder);
            metaDataCodeHolderByENName.put(metaDataCode.getMetaDataENName(), metaDataCodeValueHolder);
            metaDataCodeIdMap.put(metaDataCode.getMetaDataId(), metaDataCode);
            metaDataCodeENMap.put(metaDataCode.getMetaDataENName(),metaDataCode);
        }

    }

    /**
     * 初始化事件码
     * @param eventCodeModels
     */
    public void initEventCode(List<EventCodeModel> eventCodeModels){
        StringBuilder builder=new StringBuilder(200);
        if(eventCodeModels!=null) {
            for (EventCodeModel eventCodeModel : eventCodeModels) {
                builder.append(eventCodeModel.getEventPackageUrl()).append("-").append(eventCodeModel.getEventMethod());
                eventCodeMap.put(builder.toString(), eventCodeModel);
                builder.delete(0, builder.length());
            }
        }
    }

    //清理缓存容器
    private void clearCache() {
        metaDataCodeHolderById.clear();
        metaDataCodeHolderByENName.clear();
        metaDataCodeHolderByCNName.clear();
        metaDataCodeIdMap.clear();
        metaDataCodeENMap.clear();;
    }

    /**
     * 标准码值缓存容器数据更新
     * @param metaDataCode 标准码持久化对象包含对应的码值
     */
    public void modifyMetaData(MetaDataCode metaDataCode){
        if(null== metaDataCode){
            throw new IllegalArgumentException("metaDataCode is null,can not init");
        }
        //1.获取元数据码值持有容器，通过元数据码值的id，这个值是不会变更，由元数据平台保证
        MetaDataCodeValueHolder metaDataCodeValueHolder = metaDataCodeHolderById.get(metaDataCode.getMetaDataId());
        //2.获取元数据码对象，通过元数据码值的id
        MetaDataCode metaDataCodeCache = metaDataCodeIdMap.get(metaDataCode.getMetaDataId());
        synchronized (this) {
            //3.如果根据元数据标准码的id获取的元数据码值容器为null，则新建元数据码值对应的容器，并调用容器初始化方法进行初始化
            if (null== metaDataCodeValueHolder) {
                metaDataCodeValueHolder =new MetaDataCodeValueHolder(metaDataCode.getMetaDataCodeValues());
                metaDataCodeHolderById.put(metaDataCode.getMetaDataId(), metaDataCodeValueHolder);
                metaDataCodeHolderByCNName.put(metaDataCode.getMetaDataCNName(), metaDataCodeValueHolder);
                metaDataCodeHolderByENName.put(metaDataCode.getMetaDataENName(), metaDataCodeValueHolder);
                metaDataCodeENMap.put(metaDataCode.getMetaDataENName(),metaDataCode);
                metaDataCodeIdMap.put(metaDataCode.getMetaDataId(),metaDataCode);
            }else{
                //4.如果元数据标准码码值容器不为null，则更新元数据标准码码值容器，以及容器映射对应关系
                if(null!= metaDataCode.getMetaDataCNName()&&!metaDataCode.getMetaDataCNName().equals(metaDataCodeCache.getMetaDataCNName())){
                    metaDataCodeHolderByCNName.remove(metaDataCodeCache.getMetaDataCNName());
                    metaDataCodeHolderByCNName.put(metaDataCode.getMetaDataCNName(), metaDataCodeValueHolder);
                }
                if(null!= metaDataCode.getMetaDataENName()&&!metaDataCode.getMetaDataENName().equals(metaDataCodeCache.getMetaDataENName())){
                    metaDataCodeHolderByENName.remove(metaDataCodeCache.getMetaDataENName());
                    metaDataCodeHolderByENName.put(metaDataCode.getMetaDataENName(), metaDataCodeValueHolder);
                    metaDataCodeENMap.remove(metaDataCodeCache.getMetaDataENName());
                    metaDataCodeENMap.put(metaDataCode.getMetaDataENName(),metaDataCode);
                }
                metaDataCodeValueHolder.modifyMetaDataHolder(metaDataCode.getMetaDataCodeValues());
            }
        }
    }

    /**
     * 标准码值缓存容器数据更新
     * @param metaDataCodes 标准码持久化对象包含对应的码值列表
     */
    public void modifyMetaData(List<MetaDataCode> metaDataCodes){
        //循环遍历元数据标准码的列表，调用单个标准码对象的更改方法
        for(MetaDataCode metaDataCode : metaDataCodes) {
            modifyMetaData(metaDataCode);
        }
    }

    /**
     * 每一类元数据码，码值持有容器
     */
    private class MetaDataCodeValueHolder {

        //元数据码值列表
        private ConcurrentSkipListSet<MetaDataCodeValue> metaDataCodeValues = null;
        //用元数据码值为key，对应元数据码值对象
        private ConcurrentHashMap<String, MetaDataCodeValue> metaDataCodeValueByValue = new ConcurrentHashMap<String, MetaDataCodeValue>();
        //用元数据码值中文名称为key，对应元数据码值对象
        private ConcurrentHashMap<String, MetaDataCodeValue> metaDataCodeValueByName = new ConcurrentHashMap<String, MetaDataCodeValue>();
        //用元数据码值英文名称为key，对应元数据码值对象
        private ConcurrentHashMap<String, MetaDataCodeValue> metaDataCodeValueByDesc = new ConcurrentHashMap<String, MetaDataCodeValue>();
        //元数据码值生效状态列表
        private ConcurrentSkipListSet<MetaDataCodeValue> metaDataCodeValidValues = new ConcurrentSkipListSet<MetaDataCodeValue>();

        public MetaDataCodeValueHolder() {
        }

        //初始化元数据码值容器
        public MetaDataCodeValueHolder(List<MetaDataCodeValue> metaDataCodeValues) {
            //1.将容器列中的元数据码值直接加入到set容器中
            this.metaDataCodeValues =new ConcurrentSkipListSet<MetaDataCodeValue>(metaDataCodeValues);
            //2.进行相应的不同key的码值映射
            for(MetaDataCodeValue metaDataCodeValue : metaDataCodeValues){
                metaDataCodeValueByValue.put(metaDataCodeValue.getCodeValueVal(), metaDataCodeValue);
                metaDataCodeValueByName.put(metaDataCodeValue.getCodeValueENName(), metaDataCodeValue);
                metaDataCodeValueByDesc.put(metaDataCodeValue.getCodeValueDesc(), metaDataCodeValue);
                if(MetaDataConstants.META_DATA_VALUE_STATUS_VALID.equals(metaDataCodeValue.getStatus())||
                        MetaDataConstants.META_DATA_VALUE_STATUS_TEST_RUN.equals(metaDataCodeValue.getStatus())){
                    metaDataCodeValidValues.add(metaDataCodeValue);
                }
            }
        }

        //变更元数据码值容器
        public void modifyMetaDataHolder(List<MetaDataCodeValue> metaDataCodeValues){
            MetaDataCodeValue metaDataCodeValueByHolder =null;
            //1.遍历元数据码值列表
            for(MetaDataCodeValue metaDataCodeValue : metaDataCodeValues){
                //2.从元数据码持有容器中，获取是否存在该元数据码值，通过元数据码值的value获取，这个字段是元数据平台保证不会变更字段
                metaDataCodeValueByHolder = metaDataCodeValueByValue.get(metaDataCodeValue.getCodeValueVal());
                //3.如果获取的元数据码值对象为null，说明是新增的码值
                if(null== metaDataCodeValueByHolder){
                    //4.将元数据码值放入码值持有容器中
                    this.metaDataCodeValues.add(metaDataCodeValue);
                    //5.如果码值的状态为正常或者试运行，放入生效码值列表中，用于获取源数据列表返回
                    if(MetaDataConstants.META_DATA_VALUE_STATUS_VALID.equals(metaDataCodeValue.getStatus())||
                            MetaDataConstants.META_DATA_VALUE_STATUS_TEST_RUN.equals(metaDataCodeValue.getStatus())){
                        this.metaDataCodeValidValues.add(metaDataCodeValue);
                    }
                    //6.将元数据码值，按描述、名称、值，依次放入响应的容器中
                    this.metaDataCodeValueByDesc.put(metaDataCodeValue.getCodeValueDesc(),metaDataCodeValue);
                    this.metaDataCodeValueByName.put(metaDataCodeValue.getCodeValueENName(),metaDataCodeValue);
                    this.metaDataCodeValueByValue.put(metaDataCodeValue.getCodeValueVal(),metaDataCodeValue);
                }else{
                    //7.如果获取的元数据码值对象不为null，与3互斥，则判断获取的码值更新时间与已有码值时间进行比较，如果时间为更新
                    if(metaDataCodeValue.getUpdateTime().getTime()> metaDataCodeValueByHolder.getUpdateTime().getTime()) {
                        //8.判断原有码值与现有码值的名称和描述是否相同，如果不同，进行新的映射，放入的对象依然是原有的对象，后面进行赋值操作
                        if(metaDataCodeValue.getCodeValueDesc()!=null&&!metaDataCodeValue.getCodeValueDesc().equals(metaDataCodeValueByHolder.getCodeValueDesc())){
                            this.metaDataCodeValueByDesc.put(metaDataCodeValue.getCodeValueDesc(),metaDataCodeValueByHolder);
                            this.metaDataCodeValueByDesc.remove(metaDataCodeValueByHolder.getCodeValueDesc());
                        }
                        if(metaDataCodeValue.getCodeValueENName()!=null&&!metaDataCodeValue.getCodeValueENName().equals(metaDataCodeValueByHolder.getCodeValueENName())){
                            this.metaDataCodeValueByName.put(metaDataCodeValue.getCodeValueENName(),metaDataCodeValueByHolder);
                            this.metaDataCodeValueByName.remove(metaDataCodeValueByHolder.getCodeValueENName());
                        }
                        //9.将新码值对象的值，赋值到原有码值对象中，后面映射容器的对象的属性也随着变更
                        BeanUtils.copyProperties(metaDataCodeValue, metaDataCodeValueByHolder);
                        //10.判断元数据码值的状态既不是生效也不是试运行，便从生效队列中移除原有对象
                        if(!MetaDataConstants.META_DATA_VALUE_STATUS_VALID.equals(metaDataCodeValue.getStatus())
                                &&!MetaDataConstants.META_DATA_VALUE_STATUS_TEST_RUN.equals(metaDataCodeValue.getStatus())){
                            this.metaDataCodeValidValues.remove(metaDataCodeValueByHolder);
                        }
                    }
                }
            }
        }

        public List<MetaDataCodeValue> getMetaDataCodeValueList(){
            return new ArrayList<>(metaDataCodeValidValues);
        }

        public MetaDataCodeValue getMetaDataCodeValueByValue(String value){
            return metaDataCodeValueByValue.get(value);
        }

        public MetaDataCodeValue getMetaDataCodeValueByDesc(String desc){
            return metaDataCodeValueByDesc.get(desc);
        }

        public MetaDataCodeValue getMetaDataCodeValueByName(String name){
            return metaDataCodeValueByName.get(name);
        }
    }

//    private static class EventCodeHolder{
//
//    }
}
