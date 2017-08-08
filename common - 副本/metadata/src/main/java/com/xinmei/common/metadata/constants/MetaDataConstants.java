package com.xinmei.common.metadata.constants;

/**
 * Created by Toby on 2016/11/2.
 * 元数据常量类
 */
public class MetaDataConstants {


    private MetaDataConstants() {
    }

    /**
     * 元数据状态
     *  00  待审批
     *  01  试行
     *  02  有效
     *  03  废止
     */
    public final static String  META_DATA_VALUE_STATUS_NEED_REVIEW="00";
    public final static String  META_DATA_VALUE_STATUS_TEST_RUN="01";
    public final static String  META_DATA_VALUE_STATUS_VALID="02";
    public final static String  META_DATA_VALUE_STATUS_INVALID="03";


    /**
     * 元数据平台消息处理状态
     * 310 正在处理
     * 320 操作成功
     * 330 操作失败
     */
    public final static short META_DATA_OPERATION_STATUS_EXECUTING=310;
    public final static short META_DATA_OPERATION_STATUS_SUCCESS=320;
    public final static short META_DATA_OPERATION_STATUS_FAILURE=330;


    /**
     * rest 前缀地址
     */
    public static final String REST_API_PREFIX = "/meta-data";

}
