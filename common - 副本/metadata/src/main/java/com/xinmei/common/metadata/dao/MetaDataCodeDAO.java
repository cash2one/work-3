package com.xinmei.common.metadata.dao;

import com.xinmei.common.metadata.dto.MetaDataCode;

import java.util.List;

/**
 * Created by Toby on 2016/11/1.
 * 元数据dao
 */
public interface MetaDataCodeDAO {

   /**
    * 插入元数据标注码，插入前会进行insert not exists操作，验证条件为metaDataId
    * @param metaDataCode 元数据标注码实体对象
    * @return
    */
   int insertMetaDataCode(MetaDataCode metaDataCode);

   /**
    * 查询所有元数据标准码，包括码值
    * @return 元数据标注码集合
    */
   List<MetaDataCode> queryAllMetaDataCode();

   /**
    * 根据元数据标注码id，获取元数据标准码的信息，包括码值
    * @param id 元数据标准码id
    * @return
    */
   MetaDataCode queryMetaDataCodeById(String id);

   /**
    * 更新元数据标准码实体
    * @param metaDataCode  元数据标准码实体
    * @return
    */
   int updateMetaDataCode(MetaDataCode metaDataCode);

   /**
    * 批量更新元数据标准码
    * @param metaDataCodes 元数据标准码集合
    * @return
    */
   int batchUpdateMetaDataCode(List<MetaDataCode> metaDataCodes);

   /**
    * 批量删除元数据标准码
    * @param metaDataCode 元数据标注码集合
    * @return
    */
   int deleteMetaDataCode(MetaDataCode metaDataCode);

   /**
    * 删除所有元数据标准码
    */
   void deleteAllMetaDataCode();
}
