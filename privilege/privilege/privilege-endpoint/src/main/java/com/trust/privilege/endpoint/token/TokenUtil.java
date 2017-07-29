package com.trust.privilege.endpoint.token;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/***
 * @ClassName: TokenUtil 
 * @Description: Token工具
 * @author zyn161616@163.com
 * @version 创建时间：2017年1月22日 上午11:29:47
 */
public class TokenUtil{
	
	
	/**创建日志记录对象*/
	private static final Logger logger = 
					LoggerFactory.getLogger(TokenUtil.class.getName()); 
	
	/**
	 * @Title: isValid 
	 * @Description: 验证Token是否有效
	 * @param @param token
	 * @param @param key
	 * @param @return   
	 * @return boolean
	 */
	public static boolean isValid(String token,Key key){
		try {
			//jsonWebToken解析，先放入签名key，然后在解析这个token
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			//如果解析Token出现异常，那么记录下来
			logger.info("token验证错误,请检查是否在有效时间内或是否合法");
			//返回fasle
			return false;
		} 
	}
	
	
	/**
	 * @Title: resolveToken 
	 * @Description: 解析Token
	 * @param @param authToken
	 * @param @param key
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> resolveToken(String authToken,Key key){
		//声明返回的Map
		Map<String,Object> resultMap = null ;
		//如果参数都部位空
		if(authToken != null & key != null){
			//处理下token
			String auth_token = extrantJwtTokenFromtAuthorizationHeader(authToken);
			//那么创建返回的map
			resultMap = new HashMap<String, Object>();
			//校验token通过
			if(isValid(auth_token, key)){
				//反解出name
				String name = getName(auth_token, key);
				//反解出角色
				String [] roles = getRoles(auth_token, key);
				//反解出版本
				int version = getVersion(auth_token, key);
				//反解出token有效时间
				Date expires = getExpiresByAuthToken(auth_token, key);
				//将数据放入Map中
				resultMap.put("userName", name);
				resultMap.put("roles", roles);
				resultMap.put("version", version);
				resultMap.put("expires", expires);
			}
		}
		//返回这个Map
		return resultMap;
	}
	
	
	/**
	 * @Title: extrantJwtTokenFromtAuthorizationHeader 
	 * @Description: Bear token 转换 token
	 * @param @param auth
	 * @param @return   
	 * @return String
	 */
	public static String extrantJwtTokenFromtAuthorizationHeader(String auth){
		return auth.replace("[B|b][E|e][A|a][R|r][E|e][R|r]", "").replace("", "");
	}
	
	/**
	 * @Title: getName 
	 * @Description: 获取该JWT所面向的用户（用户名）
	 * @param @param token
	 * @param @param key
	 * @param @return   
	 * @return String
	 */
	public static String getName(String token,Key key){
		//如果token验证通过
		if(isValid(token, key)){
			//通过Key解析
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(key).parseClaimsJws(token);
			//获得主体，并且返回
			return claims.getBody().getSubject();				
		}
		//验证失败返回空
		return null;
	}
	
	/**
	 * @Title: getRoles 
	 * @Description: 获取该用户的所有角色
	 * @param @param token
	 * @param @param key
	 * @param @return   
	 * @return String[]
	 */
	public static String[] getRoles(String token,Key key){
		//如果token验证通过
		if(isValid(token, key)){
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(key).parseClaimsJws(token);
					
			//返回解析出来的角色
			return claims.getBody().getAudience().split(",");
		}
		//如果没通过，返回空数据
		return new String []{};
	}
	
	/**
	 * @Title: getVersion 
	 * @Description: 获取版本
	 * @param @param token
	 * @param @param key
	 * @param @return   
	 * @return int
	 */
	public static int getVersion(String token,Key key){
		//如果token验证通过
		if(isValid(token, key)){
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(key).parseClaimsJws(token);
			//返回版本号
			return Integer.parseInt(claims.getBody().getId());
		}
		//验证不通过，返回-1
		return -1;
	}
	
	/**
	 * @Title: getExpiresByAuthToken 
	 * @Description: 获取token有效时间至
	 * @param @param token
	 * @param @param key
	 * @param @return   
	 * @return Date
	 */
	public static Date getExpiresByAuthToken(String token,Key key){
		//如果token验证通过
		if(isValid(token, key)){
			//通过Key解析
			Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			//获得主体，并且返回
			return claims.getBody().getExpiration();
		}
		//否则返回空
		return null;
	}
	
	/**
	 * @Title: getExpires 
	 * @Description: 获取Token剩余时间
	 * @param @param minutes
	 * @param @return   
	 * @return Date
	 */
	public static Date getExpires(int minutes){
		//初始化日历实例
		Calendar calendar = Calendar.getInstance();
		//设置当前时间
		calendar.setTime(new Date());
		//放入token有效时间
		calendar.add(Calendar.MINUTE, minutes);
		//返回token剩余时间
		return calendar.getTime();
	}
	
	/**
	 * @Title: getAuthToken 
	 * @Description: 获取authToken字符串
	 * 该串是由用户名，有效时间，和key签名组成
	 * @param @param userName
	 * @param @param expires
	 * @param @param key
	 * @param @param role
	 * @param @return   
	 * @return String
	 */
	public static String getAuthToken(String userName,Date expires,Key key,String role){

		//如果主体，userName为空
		if(userName == null){
			//那么抛出空指针异常
			throw new NullPointerException("null userName is illegal");
		}
		//如果有效时间为空
		if(expires == null){
			//那么抛出空指针异常
			throw new NullPointerException("null expires is illegal");
		}
		//如果秘钥为空
		if(key == null){
			//那么抛出空指针异常
			throw new NullPointerException("null key is illegal");
		}
		
		//如果传入参数不为空，那么创建签名,采用HS256加密
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//创建token字符串，签名包含算法，加上秘钥，最终得到Base64编码的authToken字符串
		String authToken = Jwts.builder()
				.setIssuer("www.trust.com")			//设置发行人
				.setSubject(userName)				//设置该JWT面向的用户
				.setAudience(role)   				//设置接收该JWT的一方
				.setExpiration(expires)				//设置token有效时间
				.setIssuedAt(new Date()) 			//设置当前时间
				.setId("1") 						//设置版本号码
				.signWith(signatureAlgorithm, key)	//设置签名和秘钥
				.compact();
		
		//返回这个authToken字符串
		return authToken;
	}
	
		
}
