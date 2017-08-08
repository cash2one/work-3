package com.xinmei.common.basic;

import com.xinmei.common.basic.tools.SpringContextUtils;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toby on 2016/11/25.
 */
public class CommonExtend implements InitializingBean{

    private boolean invokeMetaDataPlatform=false;
    private boolean invokeBusinessPlatform=true;

    private Map<String,Object> initParams=new HashMap<>();

    //系统编号，为元数据平台配置的应用名称
    private String sysCode="";

    //流水号扩展编码 6位	默认值"0000000"
    private  String extraCd="0000000";
    //流水号编码版本 1位  默认值"0"
    private String version="0";
    //流水号应用服务编码	3位	默认值"000"
    private String appSysId="000";
    //流水号应用部署序号	3位	默认值"000"
    private String appDisId="000";
    //流水号应用ECS编号	2位	默认值"00"
    private String ecsId="00";
    //流水号分表编号	2位	默认值"00"
    private String tblId="00";
    private String prepareUser;

    private String host = "82ce1696bc8c41b1.m.cnhzalicm10finance001.ocs.aliyuncs.com";//控制台上的“内网地址”
    private String port ="11211"; //默认端口 11211，不用改
    private String username = "82ce1696bc8c41b1";//控制台上的“访问账号”
    private String password = "Trust1234";//创建OCS时输入的“密码”

    //是否使用memcache
    private boolean useMemcache;

    //ums 发送报警短信 相关数据
    private String srcEventcode;
    private String srcTopic;
    private String accountName;
    private String accountType;
    private String mappingKey1;
    private String mappingKey2;
    private String mappingKey3;
    private String mappingKey4;
    private String sysId;
    //ums相关数据结束

    @Override
    public void afterPropertiesSet() throws Exception {
        if(useMemcache) {
            MemcachedClientFactoryBean memcachedClientFactory = SpringContextUtils.getBean("&memcachedClient");
            memcachedClientFactory.setServers(host + ":" + port);
            memcachedClientFactory.setProtocol(ConnectionFactoryBuilder.Protocol.BINARY);
            AuthDescriptor ad = new AuthDescriptor(new String[]{"PLAIN"}, new PlainCallbackHandler(username, password));
            memcachedClientFactory.setAuthDescriptor(ad);
        }
    }

    public Object getInitParamByKey(String key){
        return this.initParams.get(key);
    }

    public String getAppDisId() {
        return appDisId;
    }

    public void setAppDisId(String appDisId) {
        this.appDisId = appDisId;
    }

    public String getAppSysId() {
        return appSysId;
    }

    public void setAppSysId(String appSysId) {
        this.appSysId = appSysId;
    }

    public String getEcsId() {
        return ecsId;
    }

    public void setEcsId(String ecsId) {
        this.ecsId = ecsId;
    }

    public String getExtraCd() {
        return extraCd;
    }

    public void setExtraCd(String extraCd) {
        this.extraCd = extraCd;
    }

    public String getTblId() {
        return tblId;
    }

    public void setTblId(String tblId) {
        this.tblId = tblId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isInvokeBusinessPlatform() {
        return invokeBusinessPlatform;
    }

    public void setInvokeBusinessPlatform(boolean invokeBusinessPlatform) {
        this.invokeBusinessPlatform = invokeBusinessPlatform;
    }

    public boolean isInvokeMetaDataPlatform() {
        return invokeMetaDataPlatform;
    }

    public void setInvokeMetaDataPlatform(boolean invokeMetaDataPlatform) {
        this.invokeMetaDataPlatform = invokeMetaDataPlatform;
    }

    public void setPrepareUser(String prepareUser) {
        this.prepareUser = prepareUser;
    }

    public String getPrepareUser() {
        return prepareUser;
    }

    public Map<String, Object> getInitParams() {
        return initParams;
    }

    public void setInitParams(Map<String, Object> initParams) {
        this.initParams = initParams;
    }


    public boolean isUseMemcache() {
        return useMemcache;
    }

    public void setUseMemcache(boolean useMemcache) {
        this.useMemcache = useMemcache;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMappingKey1() {
        return mappingKey1;
    }

    public void setMappingKey1(String mappingKey1) {
        this.mappingKey1 = mappingKey1;
    }

    public String getMappingKey2() {
        return mappingKey2;
    }

    public void setMappingKey2(String mappingKey2) {
        this.mappingKey2 = mappingKey2;
    }

    public String getMappingKey3() {
        return mappingKey3;
    }

    public void setMappingKey3(String mappingKey3) {
        this.mappingKey3 = mappingKey3;
    }

    public String getMappingKey4() {
        return mappingKey4;
    }

    public void setMappingKey4(String mappingKey4) {
        this.mappingKey4 = mappingKey4;
    }

    public String getSrcEventcode() {
        return srcEventcode;
    }

    public void setSrcEventcode(String srcEventcode) {
        this.srcEventcode = srcEventcode;
    }

    public String getSrcTopic() {
        return srcTopic;
    }

    public void setSrcTopic(String srcTopic) {
        this.srcTopic = srcTopic;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
}
