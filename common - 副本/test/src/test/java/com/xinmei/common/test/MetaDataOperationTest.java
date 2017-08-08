package com.xinmei.common.test;

import com.alibaba.fastjson.JSON;
import com.xinmei.common.metadata.constants.MetaDataConstants;
import com.xinmei.common.metadata.dto.MetaDataCode;
import com.xinmei.common.metadata.dto.MetaDataOperation;
import com.xinmei.common.metadata.model.response.RestResponse;
import com.xinmei.common.metadata.service.MetaDataOperationService;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

/**
 * Created by Toby on 2016/11/3.
 */
public class MetaDataOperationTest extends BaseSpring {

    MetaDataOperationService metaDataOperationService=null;

    @Before
    public void setUp() throws Exception {
        metaDataOperationService= (MetaDataOperationService) context.getBean("metaDataOperationService");
    }

    @Test
    public void insertOperationTest() throws UnknownHostException {
        MetaDataOperation metaDataOperation=new MetaDataOperation();
        metaDataOperation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_EXECUTING);
        metaDataOperation.setErrorInfo("");
        metaDataOperation.setOperationNode(InetAddress.getLocalHost().toString());
        metaDataOperation.setExecuteTime(new Timestamp(System.currentTimeMillis()));
        metaDataOperation.setOperationTime(new Timestamp(System.currentTimeMillis()));
        metaDataOperationService.insertOperation(metaDataOperation);
    }

    @Test
    public void updateOperationTest() throws UnknownHostException {
        MetaDataOperation metaDataOperation=new MetaDataOperation();
        metaDataOperation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_EXECUTING);
        metaDataOperation.setErrorInfo("");
        metaDataOperation.setOperationNode(InetAddress.getLocalHost().toString());
        metaDataOperation.setExecuteTime(new Timestamp(System.currentTimeMillis()));
        metaDataOperation.setOperationTime(new Timestamp(System.currentTimeMillis()));
        metaDataOperationService.insertOperation(metaDataOperation);

        metaDataOperation.setErrorInfo("err info test for update");
        metaDataOperation.setStatus(MetaDataConstants.META_DATA_OPERATION_STATUS_FAILURE);
        metaDataOperationService.updateOperation(metaDataOperation);
    }


}
