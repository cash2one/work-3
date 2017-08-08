package com.xinmei.general.tools;

import java.util.UUID;

/**
* @ClassName: RandomIdUtil
* @Description: 生成随机ID，当前使用UUID实现
* @author jason.zhang
* @date 2017年1月17日 下午7:23:25
*
*/ 
public class RandomIdUtil {
	
	public static String getRandomId(){
		
		return createUUID();
	}
	
	private static String createUUID(){
		
		String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		
	}
}
