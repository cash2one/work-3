package com.trust.privilege.endpoint.constants;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import io.jsonwebtoken.SignatureAlgorithm;

/***
 * @ClassName: LoginConstants 
 * @Description: 系统常量类
 * @author zyn161616@163.com
 * @version 创建时间：2017年2月15日 下午5:41:16
 */
public class LoginConstants {
	
	/**本地秘钥*/
	public static  Key key =null;
	
		static String keyStr="480a7dcb58fc216440bf3a622113e9ef98802df98a47afa87521aaf378ff9ac94779c614cfadfab613f71e77ada15fb1e48fd732c315131b5c65e2471f5357d1";
   
    static {
    	
       try {
         key =new SecretKeySpec(Hex.decodeHex(keyStr.toCharArray()),SignatureAlgorithm.HS512.getValue());
       } catch (Exception e) {
         e.printStackTrace();
       }
        System.setProperty("javax.net.ssl.trustStore","/home/admin/.keystore");
     }
	
	
}
