package com.trust.privilege.endpoint.filter;


import java.io.IOException;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.jboss.resteasy.spi.CorsHeaders;
import org.springframework.beans.factory.annotation.Value;

/**
 * 增加一个自定义 filter 能够保证 8080 端口访问 8341 的 rest 数据
 *
 * 测试方式: http://localhost:8080/static/index.html#/actived?_k=u4relp
 * <p/>
 * Created by yangguanchao on 16/9/12.
 */
@Provider
public class CommonContainerResponseFilter extends CorsFilter {
	
	@Value("${casClientPath}")
	private String doMain;
	
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String origin = requestContext.getHeaderString(CorsHeaders.ORIGIN);
        if (origin == null || requestContext.getProperty("cors.failure") != null) {
            return;
        }
        
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, true);
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        StringBuilder allowHeaders = new StringBuilder();
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        for (String headerKey : headers.keySet()) {
            allowHeaders.append(headerKey).append(",");
        }
        //Content-Type
        allowHeaders.append("content-type");
        allowHeaders.append(",Set-Cookie");
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, allowHeaders.toString());
       

        if (allowCredentials) {
            responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }

        if (exposedHeaders != null) {
            responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, exposedHeaders);
        }
        
        if( headers.containsKey("auth_token")){
        	String auth_token = headers.get("auth_token").get(0) ;
        	responseContext.getHeaders().putSingle(
        			"Set-Cookie", "auth_token="+auth_token+";Domain="+doMain+";Path=/;");
        }
    }
    public void setAllowedOrigins(Set<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

}
