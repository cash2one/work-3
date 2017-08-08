package com.xinmei.common.login.control;

public interface PatternMatcher {
	
	
	/**
	* @Title: matches
	* @Description: 规则匹配
	* @param @param pattern 规则串
	* @param @param source 来源串
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	*/ 
	boolean matches(String pattern, String source);
}
