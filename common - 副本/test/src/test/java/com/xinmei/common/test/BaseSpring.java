package com.xinmei.common.test;

import com.xinmei.common.metadata.util.MetaDataHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Toby on 2017/1/9.
 */
public class BaseSpring {

    ApplicationContext context;

    String sql="create table T_META_DATA_CODE" +
            "(\n" +
            "   META_DATA_ID         varchar(10) not null comment '元数据标准码唯一id',\n" +
            "   META_DATA_CN_NAME    varchar(200) not null default '' comment '元数据标准码名称',\n" +
            "   META_DATA_EN_NAME    varchar(200) not null default '' comment '元数据标准码英文名称',\n" +
            "   META_DATA_VERSION    decimal(3,3) not null comment '元数据标准码版本',\n" +
            "   META_DATA_STATUS     varchar(3) not null default '' comment '元数据标准码状态',\n" +
            "   META_DATA_TOPIC_TYPE varchar(3) not null default '' comment '元数据标准码主题',\n" +
            "   MODIFIED_TIME        datetime not null comment '元数据标准码更新时间',\n" +
            "   primary key (META_DATA_ID)\n" +
            ");\n" +
            "create table T_META_DATA_CODE_VALUE" +
            "(\n" +
            "   META_DATA_ID         varchar(10) not null comment '元数据标准码id',\n" +
            "   META_DATA_VALUE      varchar(20) not null comment '元数据标准码码值',\n" +
            "   META_DATA_VALUE_CN_NAME varchar(100) not null default '' comment '元数据标准码码值中文名称',\n" +
            "   META_DATA_VALUE_EN_NAME varchar(100) not null default '' comment '元数据标准码码值英文名称',\n" +
            "   META_DATA_VALUE_DESC varchar(200) not null default '' comment '元数据标准码码值描述',\n" +
            "   META_DATA_VALUE_STATUS varchar(3) not null default '' comment '元数据标准码码值状态',\n" +
            "   MODIFIED_TIME        datetime not null comment '元数据标准码码值更新时间',\n" +
            "   unique key META_DATA_VALUE_COMPOSITE_KEY_AK (META_DATA_VALUE, META_DATA_ID)\n" +
            ");\n" +
            "create table T_META_DATA_OPERATION\n" +
            "(\n" +
            "   META_DATA_OPERATION_ID int not null auto_increment comment '操作记录id',\n" +
            "   META_DATA_OPERATION_NODE varchar(40) not null comment '操作节点名称',\n" +
            "   MODIFIED_TIME        datetime not null comment '标准码更新时间',\n" +
            "   META_DATA_OPERATION_STATUS smallint not null comment '标准码状态',\n" +
            "   ERROR_INFO           varchar(2000) not null default '' comment '错误信息',\n" +
            "   CREATED_TIME         datetime not null comment '创建时间',\n" +
            "   primary key (META_DATA_OPERATION_ID)\n" +
            ");\n" +
            "alter table T_META_DATA_CODE_VALUE add constraint META_DATA_ID_FK foreign key (META_DATA_ID)\n" +
            "      references T_META_DATA_CODE (META_DATA_ID) on delete restrict on update restrict;\n" +
            "      \n" +
            "      ALTER TABLE t_meta_data_code MODIFY META_DATA_VERSION DECIMAL(6,3);\n" +
            "\n";

    /**
     * 初始化context，并初始化数据库
     */
    @Before
    public void init() throws Exception {
        context=new ClassPathXmlApplicationContext("/test.xml");
//        DataSource dataSource= (DataSource) context.getBean("dataSource");
//        Connection connection=dataSource.getConnection();
//        Statement statement=connection.createStatement();
//        statement.execute(sql);
//        statement.close();
//        connection.close();
        MetaDataHelper metaDataHelper= (MetaDataHelper) context.getBean("metaDataHelper");
        metaDataHelper.init();

    }

    @Test
    public void testInit(){
        System.out.println(context);
    }
}
