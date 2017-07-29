package com.trust.privilege.endpoint.token;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;
/***
 * @ClassName: JaxbDateSerializer 
 * @Description: 时间格式转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 上午10:59:51
 */
public class JaxbDateSerializer extends XmlAdapter<String, Date>{

	/**指定时间格式*/
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**无参构造器*/
	public JaxbDateSerializer() {}
	
	/**将字符串转换指定的时间格式*/
	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}

	/**将此时间格式转换指定格式*/
	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

}
