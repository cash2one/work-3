/**
 * Copyright (c) 2005-2012 yuchengtech.comn
 */
package com.xinmei.common.basic.tools;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * 
 * @author zhangyuan
 */
public class SpringContextUtils implements ApplicationContextAware, DisposableBean {

	private static final String ACTIVE_PROFILE_LOCAL="local";

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型. 例如：
	 * getBean（“custService”），前提是对bean定义别名。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型. *
	 * 例如：getBean(ICustService.class),这种方式，不需要考虑ICustService的别名
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * Author: xbzhu
	 * Mail: toby.zhu@trustlife.com
	 * Date: 2017/3/10 10:47
	 * Desc: 获取当前环境的激活profile，用于不同环境间区分
	 * @return
	 */
	public static String getEnvActiveProfile(){
		if(applicationContext == null){
			return ACTIVE_PROFILE_LOCAL;
		}
		ConfigurableApplicationContext configurableApplicationContext= (ConfigurableApplicationContext) applicationContext;
		String[] activeProfiles= configurableApplicationContext.getEnvironment().getActiveProfiles();
		if(null ==activeProfiles||activeProfiles.length==0){
			return ACTIVE_PROFILE_LOCAL;
		}
		return activeProfiles[0];
	}

	/**
	 * 清除SpringContextUtils中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		logger.debug("清除SpringContextUtils中的ApplicationContext:"
				+ applicationContext);
		applicationContext = null;
	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("注入ApplicationContext到SpringContextUtils:{}",
				applicationContext);

		if (SpringContextUtils.applicationContext != null) {
			logger.warn("SpringContextUtils中的ApplicationContext被覆盖, 原有ApplicationContext为:"
					+ SpringContextUtils.applicationContext);
		}

		SpringContextUtils.applicationContext = applicationContext; // NOSONAR
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	public void destroy() throws Exception {
		SpringContextUtils.clearHolder();
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		Validate.isTrue(
				applicationContext != null,
				"applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextUtils,同时set lazy-init is false.");
	}
}
