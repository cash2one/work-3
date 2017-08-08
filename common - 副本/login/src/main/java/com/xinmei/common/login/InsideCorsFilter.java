package com.xinmei.common.login;



import java.io.IOException;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.xinmei.common.basic.AppContext;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.core.interception.PreMatchContainerRequestContext;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.jboss.resteasy.spi.CorsHeaders;

import com.alipay.sofa.rest.server.netty.support.NettyHttpRequest;
import com.xinmei.common.login.context.RequestContextUtil;

/**
 * 功能1：解决内部跨域问题
 * 增加一个自定义 filter 能够保证 8080 端口访问 8341 的 rest 数据
 * 功能2：初始化客户端IP
 *
 * 测试方式: http://localhost:8080/static/index.html#/actived?_k=u4relp
 * <p/>
 * Created by jason.zhang on 16/9/12.
 */
/**
* @ClassName: InsideCorsFilter
* @Description: 解决跨域问题，并获取ip
* @author ZHY
* @date 2017年2月3日 下午7:24:54
*
*/ 
@Provider
public class InsideCorsFilter extends CorsFilter {
	
	/**
	* @Fields initRestContextUtil : 判断是否获取用户IP，暂时预留
	*/ 
	private boolean initRestContextUtil = true;

    private static final String CLIENT_IP_FROM_NGINX="X-Real-IP";
	
	
	private String authToken = "authToken";
	
	public void setInitRestContextUtil(boolean initRestContextUtil) {
		this.initRestContextUtil = initRestContextUtil;
	}

	public void setAllowedOrigins(Set<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        super.filter(requestContext);
        if(!"OPTIONS".equals(requestContext.getMethod())){
            //设置打印日志用访问ip
            setClientIP(requestContext);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		//跨域验证options请求不进行记录 --20161229 jason.zhang
		if(!"OPTIONS".equals(requestContext.getMethod())){
    		//设置打印日志用访问ip
			setClientIP(requestContext);
    	}
		//释放当前容器中的数据
//        AppContext.releaseContext();
    	//校验跨域列表
    	checkCorsHeadersORIGIN(requestContext,responseContext);
    }
    
    private void checkCorsHeadersORIGIN(ContainerRequestContext requestContext, ContainerResponseContext responseContext){
    	String origin = requestContext.getHeaderString(CorsHeaders.ORIGIN);
        if (origin == null || requestContext.getProperty("cors.failure") != null) {
            // don't do anything if origin is null, its an OPTIONS request, or cors.failure is set
            return;
        }
        
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        StringBuilder allowHeaders = new StringBuilder();
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        for (String headerKey : headers.keySet()) {
            allowHeaders.append(headerKey).append(",");
        }
        //Content-Type
        allowHeaders.append(authToken);
        allowHeaders.append(",content-type");
        responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_HEADERS, allowHeaders.toString());


        if (allowCredentials) {
            responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }

        if (exposedHeaders != null) {
            responseContext.getHeaders().putSingle(CorsHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, exposedHeaders);
        }
    	
    }
    
    private void setClientIP(ContainerRequestContext requestContext) throws IOException {
        String realIp=requestContext.getHeaderString(CLIENT_IP_FROM_NGINX);
        if(StringUtils.isNotBlank(realIp)){
            RequestContextUtil.setRestClientIP(realIp,requestContext);
        }else {
            //super.filter(requestContext);
            PreMatchContainerRequestContext pmcrc = (PreMatchContainerRequestContext) requestContext;
            if (pmcrc.getHttpRequest() instanceof NettyHttpRequest) {
                // 获取当前线程请求context
                NettyHttpRequest nhr = (NettyHttpRequest) pmcrc.getHttpRequest();
                RequestContextUtil.setRestClientIP(nhr, requestContext);
            }
        }

	}
	
}
