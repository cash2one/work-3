package com.trust.privilege.endpoint.token;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
/***
 * @ClassName: SecurityContextAuthorizer 
 * @Description: 批准的安全上下文
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 上午11:09:57
 */
public class SecurityContextAuthorizer implements SecurityContext {

	/**标识一个主体*/
	private Principal principal;
	
	/**URI*/
	private javax.inject.Provider<UriInfo> uriInfo ;
	
	/**角色集合*/
	private Set<String> roles ;
	
	/**获取用户的主体*/
	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	/**判断该用户是有此角色的*/
	@Override
	public boolean isUserInRole(String role) {
		return this.roles.contains(role == null ? "" : role);
	}

	/**判断是否为https安全协议*/
	@Override
	public boolean isSecure() {
		return "https".equals(uriInfo.get().getRequestUri().getScheme());
	}

	/**获取验证的方法*/
	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.DIGEST_AUTH;
	}
	
	
	/**有参构造器*/
	public SecurityContextAuthorizer(final Principal principal ,
				final javax.inject.Provider<UriInfo> uriInfo , final String[] roles ){
		this.principal = principal;
		//如果主体为空
		if(principal == null){
			this.principal = new Principal() {
				//那么就设置为匿名
				@Override
				public String getName() {
					return "anoymous";
				}
				@SuppressWarnings("unused")
				public boolean implies(Subject subject){
					return true;
				}
			};
		}
		this.uriInfo = uriInfo;
		this.roles = new HashSet<>(Arrays.asList((roles != null) ? roles : new String[]{}));
	}
	

}
