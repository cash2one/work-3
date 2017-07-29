package com.trust.privilege.test.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * 测试基类,用于加载test配置文件,通过test.xml把所有当前模块中的配置文件加载进入到当前的spring上下文中
 *
 * <p/>
 * Created by yangguanchao on 16/8/27.
 */
public abstract class AbstractTestBase {

    protected ApplicationContext applicationContext = null;

    public AbstractTestBase() {
        this.applicationContext =  new ClassPathXmlApplicationContext("test/privilege/test.xml");
    }

    public ApplicationContext getApplicationContext() {
        Assert.isTrue(this.applicationContext != null);
        return applicationContext;
    }
}
