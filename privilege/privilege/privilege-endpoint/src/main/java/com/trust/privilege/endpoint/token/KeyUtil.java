package com.trust.privilege.endpoint.token;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.impl.crypto.MacProvider;

/***
 * @ClassName: KeyUtil 
 * @Description: 秘钥工具类
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午2:24:24
 */
public class KeyUtil{
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(KeyUtil.class.getName());
	
	/**
	 * @Title: getKey 
	 * @Description: 获取秘钥
	 * @param @param context
	 * @param @return   
	 * @return Key
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static Key getKey(ServletContext context) throws ClassNotFoundException, IOException{
		//获取路径
		String path = context.getRealPath("/key");
		//创建文件对象
		File file = new File(path,"key.txt");
		//声明输入流
		ObjectInputStream ois = null;
		
		try {
			//如果文件不存在
			if(! file.exists()){
				//那么创建秘钥
				Key key = MacProvider.generateKey();
				//创建为恩建输出流
				ObjectOutputStream oos = 
						new ObjectOutputStream(new FileOutputStream(file));
				//将秘钥写入本地网恩建
				oos.writeObject(key);
				//关闭流
				oos.close();
				//返回这个秘钥
				return key;
			}//如果文件存在，那么直接从文件中读取秘钥，并且返回
			ois = new ObjectInputStream(new FileInputStream(file));
			return (Key) ois.readObject();
		} catch (Exception e) {
			//如果出现异常，那么记录
			logger.info("获取秘钥失败",e);
			return null;
		}finally{
			if(ois != null){
				ois.close();
			}
		}
	}
	
}
