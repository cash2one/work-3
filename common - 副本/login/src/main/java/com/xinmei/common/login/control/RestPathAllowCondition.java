package com.xinmei.common.login.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
* @ClassName: RestPathAllowCondition
* @Description: rest请求，不校验登录的规则容器
* @author jason.zhang
* @date 2016年12月17日 上午11:43:11
*
*/ 
public class RestPathAllowCondition implements AllowCondition{
	private static final Logger logger = LogManager.getLogger(RestPathAllowCondition.class.getName());
	/**
	* @Fields methodFilterConditionMap : 匹配提交方法的过滤条件，key为方法名称，value为条件列表，例如post，get等
	*/ 
	Map<String,List<String>> methodFilterConditionMap = new HashMap<String,List<String>>();
	/**
	* @Fields genarlFilterConditionMap : 通用过滤条件，使用所有提交方法
	*/ 
	List<String> genarlFilterConditionList = new ArrayList<String>();
	
	/**
	* @Fields patternMatcher : 规则校验执行器
	*/ 
	PatternMatcher  patternMatcher = (PatternMatcher) new RestAllowPatternMatcher();
	
	/**
	* @Fields splitStr : 配置分隔符，例如post:xinmei*pass
	*/ 
	private static String splitStr = ":";
	
	private static String allMethodStr = "all";
	
	
	@SuppressWarnings("unchecked")
	private List<String>[] getAllowCondition(String cond) {
		List<String>[] retlists = new ArrayList[2];
		
		retlists[0] = this.methodFilterConditionMap.get(cond);
		retlists[1] = this.genarlFilterConditionList;
			
		return retlists;
	}
	
	
	@Override
	public boolean isAllowPass(String method, String path) {
		for(List<String> patternlist : getAllowCondition(method)){
			if(patternlist!= null){
				for(String pattern : patternlist){
					if(patternMatcher.matches(pattern, path))
						return true;					
				}
			}
		}
		
		return false;
	}
	
	/**
	* @Title: setAllowPathDefinitions
	* @Description: 初始化规则map，格式如下
	* 				post:xinmei*pass*
	* 				all:xinmei*check
	* 				注入属性：allowPathDefinitions
	* @param @param iniConfig    设定文件
	* @return void    返回类型
	* @throws
	*/ 
	@SuppressWarnings("resource")
	public void setAllowPathDefinitions(String iniConfig){
		Scanner scanner = new Scanner(iniConfig);
		while(scanner.hasNextLine()){
			String info = scanner.nextLine();
			if(StringUtils.isEmpty(info))
				continue;
			
			String[] line = info.split(splitStr);
			if(line.length==2){
				String mstr = StringUtils.trimWhitespace(line[0].toLowerCase());
				String patternstr = StringUtils.trimWhitespace(line[1]);
				//拼装规则map，首先判断是否是通用规则，再判断具体method规则
				if(allMethodStr.equals(mstr)){
					genarlFilterConditionList.add(patternstr);
					
				}else{
					if(this.methodFilterConditionMap.containsKey(mstr)){
						this.methodFilterConditionMap.get(mstr).add(patternstr);
						
					}else{
						List<String> plist = new ArrayList<String>();
						plist.add(patternstr);
						this.methodFilterConditionMap.put(mstr, plist);
						
					}
					
				}
				
				
			}else{
				logger.info("错误配置 ： " + info);
				
			}
			
		}
				
	}
	
}
