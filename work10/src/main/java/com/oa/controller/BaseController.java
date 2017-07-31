package com.oa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.apache.bcel.Repository;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller层的工具类
 * 1，接收页面的所有参数 并且封装成Map
 * 
 * 
 * @author liliting
 *
 */
public class BaseController {
	
	/**
	 * 一次性把所有请求参数得到  封装到map中
	 * @param request
	 * @return
	 */
	public  Map<String,Object> getParam(HttpServletRequest request){
		// request.getParameter()  request.getParameterNames();  request.getParameterMap()
		 Map<String,Object> resultMap = new HashMap<String, Object>();
		//得到页面的所有的参数名  name:张三  
		Enumeration<String> keys = request.getParameterNames();
		//得到客户端的请求方法
		String method = request.getMethod();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = request.getParameter(key);//根据key得到值
			if ("GET".equalsIgnoreCase(method)) {
				try {
					//System.out.println("前"+value);
					value = new String(value.getBytes("iso-8859-1"),"utf-8");
					//System.out.println("后"+value);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("没有改编码");
				}
			}
			resultMap.put(key, value);
		}
		
		return resultMap;
		
	}

	/**
	 * 返回列表分页的下角页码
	 * @param request
	 * @param response
	 * @return
	 * 首页 上一页 下一页 末页
	 * 上一页  1 2 3 4 下一页
	 */
	public ModelAndView getPageNumberInfo(int total,int startIndex,int pageSize ,ModelAndView result) {
		//Math.ceil整数则为该整数，Math.ceil小数则为靠近大的整数
		/**
		 * 1.0/3 = 0.33    Math.ceil(0.3333)=1.0
		 * current:第几页
		 */
        int current =  (int) Math.ceil((startIndex + 1.0) / pageSize);
        
		result.addObject("start", startIndex);
		result.addObject("limit", pageSize);
		result.addObject("total", total);
		result.addObject("current", current);
		if(total > 0) {
			//求总的页数
			int page =  (int) Math.ceil(total/ pageSize);
			
			double totald= total; //20
			if(totald/pageSize>total/pageSize){
				page =page +1;
			}
			
			//int page = total%pageSize==0?total/ pageSize:total/ pageSize+1;
			
			result.addObject("page", page);
			int startPage = 0;//开始下标
			int endPage = 0;//结束的下标   1 2 3     4  5  6
			if (page<8) {
				startPage = 1;
				endPage = page; // 1 2 3 4 5 6 7 
			} else {//page大于等于8
				if (current<5) { //展示前6页
					startPage = 1;
					endPage = 6;// 1 2 3 4 5 6 
				} else if (page-current<6) {//展示最后6页，例如：current=10 page=15   10-15
					startPage = page-5;
					endPage = page;//10 11 12 13 14 15 
				} else {//current=50    page 100
					startPage = current - 2;
					endPage = current + 2;// 18 19 20 21 22
				}
			}
			result.addObject("startPage", startPage);
			result.addObject("endPage", endPage);
		} else {
			result.addObject("page", 0);
		}
		return result;
	}
	
	
	public  void  printJSON(HttpServletResponse response,String content){
		response.setContentType("application/json;charset=utf-8");
		 try {
			response.getWriter().println(content);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
