package com.xinmei.general.tools;

import com.alibaba.fastjson.JSON;
import com.xinmei.common.basic.tools.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

/**
 * https rest调用封装类
 *
 * @Author xbzhu
 * @Mail toby.zhu@trustlife.com
 * @Date 2017年02月14日 10:31
 */
public class HttpsRestUtil {

    private static Logger logger= LoggerFactory.getLogger(HttpsRestUtil.class);

    public static RestTemplate restTemplate=null;

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/14 10:37
     * Desc: get方式发送rest请求
     * @param path 请求路径
     * @param headerMap 请求头
     * @param paramMap 参数
     * @return
     */
    public static String getRequest(String path,Map<String,String> headerMap,Map<String,String> paramMap){
        HttpHeaders headers = new HttpHeaders();
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            if(Objects.equals("Content-Type",entry.getKey())){
                MediaType type = MediaType.parseMediaType(entry.getValue());
                headers.setContentType(type);
            }else {
                headers.add(entry.getKey(),entry.getValue());
            }
        }
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        if(null == restTemplate) {
            initRestTemple();
        }
        if(null==paramMap||paramMap.size()==0){
            return restTemplate.exchange(path, HttpMethod.GET,entity,String.class).getBody();
        }
        return restTemplate.exchange(path,HttpMethod.GET,entity,String.class,paramMap).getBody();
    }



    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/14 11:06
     * Desc: post方式发送rest请求
     * @param path 请求路径
     * @param headerMap 请求头
     * @param paramMap 参数
     * @return
     */
    public static String postRequest(String path,Map<String,String> headerMap,Map paramMap){
        HttpHeaders headers = new HttpHeaders();
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            if(Objects.equals("Content-Type",entry.getKey())){
                MediaType type = MediaType.parseMediaType(entry.getValue());
                headers.setContentType(type);
            }else {
                headers.add(entry.getKey(),entry.getValue());
            }
        }
        HttpEntity<String> entity=null;
        if(null==paramMap||paramMap.isEmpty()){
            entity=new HttpEntity<String>(headers);
        }else {
            entity=new HttpEntity<String>(JSON.toJSONString(paramMap),headers);
        }
        if(null == restTemplate) {
            initRestTemple();
        }
        return restTemplate.postForEntity(path,entity,String.class).getBody();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/14 11:27
     * Desc: 用于连接push服务器rest服务
     * @param path 请求路径
     * @param formJson 报文
     */
    public static String postRequestForMobilePush(String path,String formJson){
        logger.info("postRequestForMobilePush receive path {} data {}",path,formJson);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity=new HttpEntity<String>(formJson,headers);
        if(null == restTemplate) {
            initRestTemple();
        }
        ResponseEntity<String> response=restTemplate.postForEntity(path, formEntity, String.class);
        logger.info("mpaas push response {}", response.getBody());
        return response.getBody();
    }

    private static synchronized void initRestTemple(){
        if(null==restTemplate){
            restTemplate= SpringContextUtils.getBean("restTemplate");
        }
    }
}
