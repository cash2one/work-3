package com.trust.privilege.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.xinmei.cms.oss.model.PresignedUrlResponseModel;
import com.xinmei.cms.oss.model.STSResponseModel;

/***
 * @ClassName: OSSUtil 
 * @Description: 操作OSS的工具
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月18日 上午11:08:07
 */
public class OSSUtil {
	
	/**
	 * @Title: readFile4Oss 
	 * @Description: 通过从OSS返回的对象创建OSS访问对象，进行文件的读取
	 * @param @param response
	 * @param @return
	 * @param @throws IOException   
	 * @return InputStream
	 */
	public static InputStream readFile4Oss(STSResponseModel response) throws IOException{
		//获取OSSClient对象
		OSSClient client = new OSSClient(response.getEndpoint(),
				response.getAccessKeyID(),response.getAccessKeySecret(),
					response.getSecurityToken());
		
		//获取OSS对象
		OSSObject object = client.getObject("protected-bucket",
				response.getDir()+response.getResName());
		
		//获取输入流
		InputStream is = object.getObjectContent();
		//返回从OSS读取到的流
		return is;
	}

	
	/**
	 * @Title: writeLocalFile 			JS
	 * @Description: 根据OSS服务器返回的model，将文件写入本地
	 * @param @param response      OSS服务器返回的对象
	 * @param @param outFilePath   将文件写入指定路径
	 * @return void
	 * @throws ClientException 
	 * @throws OSSException 
	 * @throws IOException 
	 */
	public static void writeLocalFile(PresignedUrlResponseModel response,String outFilePath) throws OSSException, ClientException, IOException{
		//获取OSSClient对象
		OSSClient client = new OSSClient(response.getEndpoint(),
				response.getAccessKeyId(),response.getAccessKeySecret());
		
		//用户存储指定标题
		Map<String,String> customHeaders = new HashMap<>();
		
		//客户端使用返回的url签名字符串发送请求,获取url
		String respUrl = response.getSignedUrl();
		
		//获取OSS返回的存储对象
		OSSObject object = client.getObject(new URL(respUrl),customHeaders);
	
		//获取输入流
		InputStream is = object.getObjectContent();
		
		//文件输出流
		FileOutputStream fos = new FileOutputStream(outFilePath);
		
		//写入指定位置
		writeIO4Local(is, fos);
		//关闭流
		closeAll(is, fos, null, client);
	}
	
	
	/**
	 * @Title: uploadOSS   				STS
	 * @Description: 将指定文件，上传到OSS服务器
	 * @param @param response   			 请求OSS服务器，返回的model
	 * @param @param String uploadFilePath   指定上传文件的路径
	 * @return void
	 * @throws FileNotFoundException 
	 */
	public static void uploadOSS(STSResponseModel response,String uploadFilePath) throws FileNotFoundException{
		//获取OSSClient对象,利用rsponse返回的数据做token
		OSSClient client = new OSSClient(response.getEndpoint(),
				response.getAccessKeyID(),response.getAccessKeySecret(),
					response.getSecurityToken());
		
		//通过需要上传到的文件路径获取需要上传的文件输入流
		FileInputStream fis = new FileInputStream(new File(uploadFilePath));
		
		//获取上传的key,是OSS返回的资源目录+目前自定义的资源名称
		String key = response.getDir()+"BulkImportExcelData";
		
		//开始上传
		client.putObject(response.getBucketName(), key, fis);
		
		//关闭流资源
		closeAll(null, null, fis, client);
		
	}

	
	/**
	 * @Title: uploadOSS 				JS
	 * @Description: 将指定文件，上传到OSS服务器
	 * @param @param response   			请求OSS服务器，返回的Model
	 * @param @param uploadFilepath			指定上传的文件路径   
	 * @return void
	 * @throws FileNotFoundException 
	 * @throws MalformedURLException 
	 * @throws ClientException 
	 * @throws OSSException 
	 */
	public static void uploadOSS(PresignedUrlResponseModel response ,String uploadFilepath) throws FileNotFoundException, OSSException, ClientException, MalformedURLException{
		//获取OSSClient对象
		OSSClient client = new OSSClient(response.getEndpoint(),
				response.getAccessKeyId(),response.getAccessKeySecret());
		//获取文件对象
		File file = new File(uploadFilepath);
		//获取文件，文件输入流
		FileInputStream fis = new FileInputStream(file);
		//用来存储指定的标题
		Map<String,String> customHeaders = new HashMap<>();
		
		//客户端，通过OSS返回的签名url字符串，发送请求到OSS服务器
		String respUrl = response.getSignedUrl();
		//发送文件对象到OSS服务器
		client.putObject(new URL(respUrl),fis,file.length(),customHeaders);
		//关闭流资源
		closeAll(null, null, fis, client);
	}
	
	
	/**
	 * @Title: writeIO4Local 
	 * @Description: 读取输入流，将文件写入本地
	 * @param @param is
	 * @param @param fos   
	 * @return void
	 * @throws IOException 
	 */
	private static void writeIO4Local(InputStream is,FileOutputStream fos) throws IOException{
		//创建数组，用来装在数据
		byte [] buffer = new byte[1024];
		//声明，读取的长度
		int len = 0;
		//开始读取数据
		while((len = is.read(buffer)) != -1){
			fos.write(buffer,0,len);
		}
	}
	
	
	/**
	 * @Title: ioClose 
	 * @Description: 关闭流
	 * @param @param is
	 * @param @param fos
	 * @param @param client   
	 * @return void
	 */
	private static  void closeAll(InputStream is , FileOutputStream fos , FileInputStream fis , OSSClient client){
		try {
			if(is != null){
				is.close();
			}
			if(fos != null){
				fos.close();
			}
			if(client != null){
				client.shutdown();
			}
			if(fis != null){
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
