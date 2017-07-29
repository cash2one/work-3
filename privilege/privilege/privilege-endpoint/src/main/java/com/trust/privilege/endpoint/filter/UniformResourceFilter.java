package com.trust.privilege.endpoint.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.CorsHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.trust.privilege.endpoint.constants.LoginConstants;
import com.trust.privilege.endpoint.token.CasClientUtil;
import com.trust.privilege.endpoint.token.TokenUtil;
import com.trust.privilege.facade.PrivilegeServerFacade;
import com.trust.privilege.facade.RoleServerFacade;
/***
 * @ClassName: UniformResourceFilter 
 * @Description: 统一资源过滤器
 * @author zyn161616@163.com
 * @version 创建时间：2017年2月20日 下午2:45:23
 */
@Provider
public class UniformResourceFilter implements ContainerRequestFilter{

	static {
		System.setProperty("javax.net.ssl.trustStore","/home/admin/.keystore");
	}
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(UniformResourceFilter.class.getName());
				
	/**依赖注入资源facade层*/
	@Resource 
	private PrivilegeServerFacade privilegeServerFacade;
	
	/**依赖注入角色facade层*/
	@Resource
	private RoleServerFacade roleServerFacade;
	
	/**Cas Client Local Path -> portal.trustlife.com:8341*/
	@Value("${casClientLocal}")
	public String casClientLocal;
	
	/**获取票据的接口   ->https://sso.trustlife.com:8443/v1/tickets*/
	@Value("${getTicketUrl}")
	private String getTicketUrl;
	
	/**客户端向服务端发送请求获取用户名需要的服务端地址   ->http://sso.trustlife.com:8080/serviceValidate?ticket= */
	@Value("${getUserName4Url}")
	private String getUserName4Url;
	
	
	/** 实现Filter方法  */
	@Override
 	public void filter(ContainerRequestContext requestContext )throws IOException {
		//声明请求前段
		String origin = null ;
		//声明请求的TGT
		String CASTGT = null ;
		
		//如果不为空说明是8080访问的
		if(requestContext.getHeaderString(CorsHeaders.ORIGIN) != null ){
			//获取请求的开端
			origin = requestContext.getHeaderString(CorsHeaders.ORIGIN) ;
		}else{
			//否则是8341接口调用
			origin = this.casClientLocal ;
		}
		
		//获取当前请求路径
		String reqPath = requestContext.getUriInfo().getPath(true);	
		//获取当前请求方式
		String reqMethod = requestContext.getMethod();
		//获取cookies
		Map<String,Cookie> cookies = requestContext.getCookies();
		
		//如果存在token
		if(cookies.containsKey("auth_token")){
			//从Cookie中获取token
			String auth_token =cookies.get("auth_token").getValue();
			//获取秘钥,目前写在静态常量中，暂时不用keyUtil
			Key key = LoginConstants.key;
			//处理Token
			String reqToken = TokenUtil.extrantJwtTokenFromtAuthorizationHeader(auth_token);
			//解析Token,如果返回为空，说明当前token已经过期
			Map<String,Object> tokenMap = TokenUtil.resolveToken(reqToken, key);
			
			//如果成功解析token
			if(! tokenMap.isEmpty() ){
				//获取解析token之后的用户名
				String  name = (String) tokenMap.get("userName");
				//获取解析token之后的角色
				String [] roles = (String[]) tokenMap.get("roles");
				//获取解析token之后的版本
				int version = (int) tokenMap.get("version");
				
				//如果token解析出参数不为空
				if(name !=null && roles.length != 0 && version != -1){
					//通过用户名获取该用户无权访问的url
					Set<String> forbiddens = privilegeServerFacade.selectSysValidatePrivilege(null, name);

					//如果是GET请求
					if(reqMethod.equals("GET")){
						//处理下路径
						reqPath = reqPath.substring(0, reqPath.lastIndexOf("/"));
					}
					
					//如果当前Url是合法的
					if(!forbiddens.contains(reqPath)){
						//将解密后的用户名放入request中
						requestContext.getHeaders().putSingle("userName", name);
						//直接跳出该Filter,去走正常的逻辑
						return ;
					}
					
					//否则的话进行日志记录
					logger.info("该用户没有访问此url权限");
					//返回403错误,禁止此请求
					throw new WebApplicationException(Response.Status.FORBIDDEN);
				}
					//否则日志记录下，参数不全
					logger.info("name,roles or version missing for auth_token");
			}
		}
		
		//如果没有token，那么获取TGT
		if(cookies.get("CASTGC") != null){
			//那么获取TGT
			CASTGT = cookies.get("CASTGC").getValue();
			//通过TGT获取用户名和token
			Map<String,Object> resultMap = getAccessToken(CASTGT, origin+reqPath);
			//如果返回map不为空
			if(! resultMap.isEmpty()){
				//获取用户名
				String userName = (String) resultMap.get("userName");
				//获取token
				String auth_token = (String) resultMap.get("accessToken");
				
				//在requestContext中放入用户名，后台需要使用
				requestContext.getHeaders().putSingle("userName", userName);
				//并且放入token
				requestContext.getHeaders().putSingle("auth_token", auth_token);
				
				//结束
				return ;
			}
		}
			
		//如果前台没有Token，也没有TGT，那么日志记录
		logger.info("TGT已经过期了");
		//返回前台401，未认证
		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	}
	
	/**
	 * @Title: getAccessToken 
	 * @Description: 通过TGT获取用户名并且生成token
	 * @param @param CASTGT
	 * @param @param requestPath
	 * @param @return   
	 * @return Map<String,Object>
	 */
	private Map<String,Object> getAccessToken(String CASTGT, String requestPath){
		//声明返回的Map
		Map<String,Object>  resultMap = new HashMap<>();
			
		try	{
			//如果TGT不为空
			if(CASTGT != null){
				//用于查询
				Map<String,Object> queryMap = new HashMap<>();
				
				//设置CASTGT
				queryMap.put("CASTGT", CASTGT);
				//设置请求路径
				queryMap.put("requestPath", requestPath);
				//设置向Server端发送请求获取ST票据的url
				queryMap.put("getTicketUrl", getTicketUrl);
				//设置向Server端发送请求获取用户名的url
				queryMap.put("getUserName4Url", getUserName4Url);
				
				//通过TGT获取ST，通过ST获取单点登录的到用户名
				String userName = new CasClientUtil().getUserName(queryMap);
				//清下
				queryMap.clear();
				//设置查询条件
				queryMap.put("userName", userName);
				//如果成功获取到用户名，说明TGT是有效的
				if(userName != null){
					
					//获取用户的角色
					String roles = roleServerFacade.selectRole4UserNameAndSystemID(queryMap);
					
					//设置toke剩余有效时间25分钟
					Date exipres = TokenUtil.getExpires(25);
			
					//通过用户名，和当前系统，生成token		
					String accessToken = TokenUtil.getAuthToken(
								userName, exipres, LoginConstants.key, roles);	
				
					//日志记录
					logger.info("Filter通过TGT产生的Token" + accessToken);
				
					//将SSO登录的用户名放入map中
					resultMap.put("userName", userName);
					//将通过TGT新生成的token放入map中
					resultMap.put("accessToken", accessToken);
				
					//返回这个token
					return resultMap ;
				}
			}
		}catch(Exception e){
			logger.info("获取用户名，生成token失败",e);
			//出现异常返回空的map
			return  resultMap;
		}
			return  resultMap;
	}
	
}
