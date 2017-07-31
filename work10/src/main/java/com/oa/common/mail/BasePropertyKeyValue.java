package com.oa.common.mail;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取mail.properties配置文件
 * Properties 
 * @author liliting
 *
 */
public class BasePropertyKeyValue {
	Properties properties;
	
	private final  static BasePropertyKeyValue onlyOne = new BasePropertyKeyValue();
	
	private BasePropertyKeyValue(){
		try {
			properties = new Properties();
			properties.load(BasePropertyKeyValue.class.getClassLoader().getResourceAsStream("mail.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BasePropertyKeyValue getInstance(){
		return onlyOne;	
	}
	
	/**
	 * 根据key
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	
	public static void main(String[] args) {
		System.out.println(BasePropertyKeyValue.getInstance().getProperty("mail.host"));
	}
	
	
	

}
