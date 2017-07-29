package com.trust.privilege.endpoint.token;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/***
 * @ClassName: Token 
 * @Description: XML格式token实例
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 下午2:35:12
 */
public class Token implements Serializable{

	/**版本号*/
	private static final long serialVersionUID = 1L;
	
	/**XML元素中authToken*/
	@XmlElement(name="auth_token")
	private String authToken;
	
	/**XML元素中token有效时间*/
	@XmlElement(name="expires")
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date expires;
	
	/**XML元素中携带传送数据*/
	@XmlElement(name="payload")
	private Object object;
	
	/**公有属性的取值赋值方法*/
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	

}
