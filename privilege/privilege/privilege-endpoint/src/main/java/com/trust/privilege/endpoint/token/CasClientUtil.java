package com.trust.privilege.endpoint.token;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * @ClassName: CasClientUtil 
 * @Description: 单点登录客户端工具 
 * @author zyn161616@163.com
 * @version 创建时间：2017年2月13日 下午4:32:43
 */
public class CasClientUtil {
	
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(CasClientUtil.class);
	
	/**
	 * @Title: getST4TGT 
	 * @Description: 通过TGT和请求资源获取ST
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	public String getST4TGT(Map<String,Object> queryMap){
		//获取TGT
		String TGT = (String) queryMap.get("CASTGT");
		//获取requstPath
		String requestPath = (String) queryMap.get("requestPath");
		//获取请求跳狙url
		String getTicketUrl = (String) queryMap.get("getTicketUrl");
		
		//声明返回结果
		String result = null ;
		try {
			//处理service
			String encodedServiceURL = URLEncoder.encode("service","utf-8") + "=" + URLEncoder.encode(requestPath,"utf-8");
			//请求路径
			String reqUrl = getTicketUrl + "/" + TGT;
			logger.info("reqUrl,{}",reqUrl);
			//创建连接，并请求
			HttpURLConnection conn = (HttpsURLConnection) openConn(reqUrl);
                
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			BufferedWriter bwr = new BufferedWriter(out);
			bwr.write(encodedServiceURL);
			bwr.flush();
			bwr.close();
			out.close();
			
			//放入缓存区
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//赋值
			result = br.readLine();
			//关闭流
			br.close();
			//关闭连接
			conn.disconnect();				
		} catch (Exception e) {
			//出现日常，给出提示
			logger.warn("通过TGT获取ST失败,请检查TGT是否过期！",e);
		} 
			//返回这个结果，因为只有一个ST，所以这么返回
			return result ;
	}
	
	/**
	 * @Title: getUserNameByST 
	 * @Description: 通过ST票据获取单点登录的用户名
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	public  String getUserXml4ST(Map<String,Object> queryMap){
		//声明返回值
		String userName = null ;
		
		try {
			//获取ST
			String serviceTicket = getST4TGT(queryMap);
			//如果成功获取ST
			if(serviceTicket!= null){
				//向客户端发送ST，返回userName的URL
				String getUserNameUrl = queryMap.get("getUserName4Url")+
						serviceTicket+"&"+"service="+ queryMap.get("requestPath");
				logger.info("getUserNameUrl,{}",getUserNameUrl);
				//创建URL对象
				URL url = new URL(getUserNameUrl);
				//打开连接请求数据
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				//如果返回码为200，说明成功
				if(conn.getResponseCode() == 200){
					//获取输入流
					InputStream is = conn.getInputStream();
					//缓冲区
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//进行读取输入流
					byte [] by = new byte[1024];
					int len = 0;
	                while((len = is.read(by)) != -1){
	                    baos.write(by,0,len);
	                    baos.flush();
	                }
	                //关闭流
	                baos.close();
	                is.close();
	                userName = baos.toString("utf-8");
	                //返回数据
	                return userName;
				}
				//关闭连接
				conn.disconnect();
			}
		} catch (Exception e) {
			//如果出现异常，日志记录
			logger.warn("通过ST获取XML格式userName失败，请检查ST是否获取成功！",e);
		}
			return  userName ;
	}
	
	/**
	 * @Title: getUserName 
	 * @Description: 通过XML解析出用户名
	 * @param @param queryMap
	 * @param @return   
	 * @return String
	 */
	public String getUserName(Map<String,Object> queryMap) {
		//声明返回值
		String userName = null ;
		try {
			//获取CAS Server服务器返回的xml字符串
			String responseXML = getUserXml4ST(queryMap);
			//如果得到返回的XML不为空
			if(responseXML != null){
				//将字符串转换成xml
				Document doc = DocumentHelper.parseText(responseXML);
				//获取根节点
				Element rootElt = doc.getRootElement(); 
				// 获取根节点下的指定子节点下所有节点
				@SuppressWarnings("rawtypes")
				Iterator iter = (Iterator) rootElt.elementIterator("authenticationSuccess"); 
				//遍历根节点下的子节点
				while (iter.hasNext()) {
					//获取每一个节点
					Element recordEle = (Element) iter.next();
					//获取指定节点的值
		            userName = recordEle.elementTextTrim("user");
		        }
					//返回这个值
					return userName ;
			}
		} catch (Exception e) {
			//如果出现异常，日志记录
			logger.warn("通过ST获取userName失败，请检查ST是否获取成功！",e);
			//返回空值
			return userName;
		}
			return userName;
	}
	
	/**
	 * @Title: openConn 
	 * @Description: 打开连接(https)
	 * @param @param urlk
	 * @param @return
	 * @param @throws MalformedURLException
	 * @param @throws IOException   
	 * @return URLConnection
	 */
	public static URLConnection openConn(String urlk)throws MalformedURLException,IOException{
		//创建URL
		URL url = new URL(urlk);
		//打开https连接
		HttpsURLConnection hsu = (HttpsURLConnection) url.openConnection();
		hsu.setDoInput(true);
		hsu.setDoOutput(true);
		hsu.setRequestMethod("POST");
		
		//返回
		return hsu;
	}
	
}
