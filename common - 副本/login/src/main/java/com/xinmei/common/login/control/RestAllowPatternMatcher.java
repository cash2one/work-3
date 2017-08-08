package com.xinmei.common.login.control;

import org.springframework.util.StringUtils;

public class RestAllowPatternMatcher implements PatternMatcher{
	
	String asterisk = "\\*";
	
	/**
	* @Title: matches
	* @Description: 规则匹配
	* @param @param pattern 规则串
	* @param @param source 来源串
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	*/ 
	public boolean matches(String pattern, String source){
		boolean ret = true;
		if(StringUtils.isEmpty(pattern)||StringUtils.isEmpty(source))
			return false;
		int index = 0;
	    String[] patternsplit = pattern.split(asterisk);
	    for(String temp: patternsplit){
	    	index = source.indexOf(temp, index);
	    	if(index < 0){
	    		ret = false;
	    		break;
	    	}	    	
	    }
		
		return ret;
	}
	
	public static void main(String[] args) {
		String p = " ";
		String s = "accccaddabadddbbkkdkccaaa";
		RestAllowPatternMatcher rapm = new RestAllowPatternMatcher();
		System.out.println(rapm.matches(p, s));
	}
	
}
