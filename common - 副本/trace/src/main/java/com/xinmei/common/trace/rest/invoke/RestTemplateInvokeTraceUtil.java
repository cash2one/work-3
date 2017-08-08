package com.xinmei.common.trace.rest.invoke;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.xinmei.common.trace.CatRemoteCallContext;
import com.xinmei.common.trace.constants.TraceConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: com.xinmei.common.trace.rest.invoke.RestTemplateInvokeTraceUtil
 * @Description: 使用restTemplate调用时，调用工具类进行埋点操作
 * @Author xbzhu
 * @Date 2017年03月13日 14:13
 */
public class RestTemplateInvokeTraceUtil {

    private static RestTemplate restTemplate=new RestTemplate();

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/13 14:28
     * Desc: rest调用帮助类封装，会进行trace埋点操作
     * @param url 请求url
     * @param request 请求体
     * @param headerMap 请求头
     * @param responseType 返回类型
     * @param <T> 返回类型泛型
     * @return
     */
    public static <T> T postForObject(String url,String request,Map<String,String> headerMap, Class<T> responseType){
        HttpHeaders headers = new HttpHeaders();
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            if(Objects.equals("Content-Type", entry.getKey())){
                MediaType type = MediaType.parseMediaType(entry.getValue());
                headers.setContentType(type);
            }else {
                headers.add(entry.getKey(),entry.getValue());
            }
        }
        Transaction transaction=Cat.newTransaction(CatConstants.TYPE_URL,url);
        Cat.logEvent(CatConstants.TYPE_URL, TraceConstant.EVENT_REST_INVOKE_REQUEST, Message.SUCCESS,request);
        Cat.Context context=new CatRemoteCallContext();
        Cat.logRemoteCallClient(context);
        initTraceId(headers, context);
        Cat.logEvent(CatConstants.TYPE_URL, TraceConstant.EVENT_REST_INVOKE_HEADER, Message.SUCCESS,headers.toString());
        HttpEntity<String> entity=null;
        if(null==request||"".equals(request)){
            entity=new HttpEntity<String>(headers);
        }else {
            entity=new HttpEntity<String>(request,headers);
        }
        try {
            T t = restTemplate.postForEntity(url, entity, responseType).getBody();
            Cat.logEvent(CatConstants.TYPE_URL,TraceConstant.EVENT_REST_INVOKE_RESPONSE,Message.SUCCESS,t.toString());
            transaction.setStatus(Message.SUCCESS);
            return t;
        }finally {
            transaction.complete();
        }
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/13 14:44
     * Desc: 将cat产生的traceId放入http请求头中，通过http协议带到对端
     * @param headers 头对象
     * @param context cat traceId容器
     */
    private static void initTraceId(HttpHeaders headers, Cat.Context context) {
        headers.add(Cat.Context.ROOT,context.getProperty(Cat.Context.ROOT));
        headers.add(Cat.Context.PARENT,context.getProperty(Cat.Context.PARENT));
        headers.add(Cat.Context.CHILD,context.getProperty(Cat.Context.CHILD));
    }
}
