package com.trust.privilege.endpoint.token;

import java.security.Principal;
/***
 * @ClassName: AuthoriPrincipal 
 * @Description: 安全主体
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 上午11:06:40
 */
public class AuthoriPrincipal implements Principal {

	/**私有属性*/
	private String name;
	
	/**有参构造器*/
	public AuthoriPrincipal(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
