package com.xinmei.general.tools;


import com.xinmei.common.basic.CommonExtend;
import com.xinmei.common.basic.tools.SpringContextUtils;
import com.xinmei.general.model.PropertiesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单流水信息
 * @author think
 *
 */
public class SerialGenerator {

	private static Logger logger= LoggerFactory.getLogger(SerialGenerator.class);

	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	//日期
	private static Date date =new Date();
	//时间戳
	private static long currentTime =System.currentTimeMillis();
	//序列号
	private static Integer sequence = 0;
	//步进值
	private static Integer seqStep = 1;

//	private static AtomicInteger sequenceNum=new AtomicInteger(0);
	
//	private static PropertiesModel model;


	static{
		try {
			date = format.parse("20160919");
		} catch (ParseException e) {
			logger.error("date convert error",e);
		}
		//读配置文件进行赋值
//		 setValue();
	}
	
//	private static void setValue(){
//	 //读取属性文件a.properties
//		Properties prop = new Properties();
//        InputStream in = null;
//		try {
//			model = new PropertiesModel();
//			in = new BufferedInputStream (Thread.currentThread().getContextClassLoader().getResourceAsStream("/parameter.properties"));
//			prop.load(in);     ///加载属性列表
//	        Iterator<String> it=prop.stringPropertyNames().iterator();
//	        while(it.hasNext()){
//	            String key=it.next();
//	            if("extraCd".equals(key)){
//	            	model.setExtraCd(prop.getProperty(key));
//	            }
//	            if("version".equals(key)){
//	            	model.setVersion(prop.getProperty(key));
//	            }
//			    if("appSysId".equals(key)){
//			    	model.setAppSysId(prop.getProperty(key));
//				 }
//			    if("appDisId".equals(key)){
//			    	model.setAppDisId(prop.getProperty(key));
//			    }
//			    if("ecsId".equals(key)){
//			    	model.setEcsId( prop.getProperty(key));
//			    }
//			    if("tblId".equals(key)){
//			    	model.setTblId(prop.getProperty(key));
//			    }
//	         }
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			  try {
//				in.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	 
	/**
	 * 
	 * @Title: getSerialNumber 
	 * @Description: 获取下一个编号
	 * @author louyujie
	 * @param channel  渠道编码
	 * @param bizType 业务标示码
	 * @return 返回长度为53位的字符串类型的交易流水号
	 */
	public static synchronized String getId(String channel, String bizType) {
		return getId(channel,bizType,SpringContextUtils.getBean(CommonExtend.class).getExtraCd());
	}
	
    public static synchronized String getId(String channel, String bizType, String extraCode) {
		CommonExtend commonExtend= SpringContextUtils.getBean(CommonExtend.class);
    	//隔天重置
    	Date now = new Date();
    	if(!format.format(now).equals(format.format(date))){
    		date =now;
    		currentTime = System.currentTimeMillis();
    		sequence=0;
    	}
    	//赋值
//    	if (!"".equals(extraCode)){
//    		model.setExtraCd(extraCode);
//    	}
    	sequence +=  seqStep;
    	StringBuffer buffer = new StringBuffer();
    	buffer = new StringBuffer();
    	buffer.append(format.format(date))
    	.append(commonExtend.getVersion())
    	.append(channel)
				.append(bizType)
		.append(commonExtend.getAppSysId())
		.append(commonExtend.getAppDisId())
				.append(commonExtend.getEcsId())
		.append(commonExtend.getTblId())
				.append(extraCode)
    	.append(currentTime)
    	.append(String.format("%010d", sequence));
    	return buffer.toString();
    }

}
