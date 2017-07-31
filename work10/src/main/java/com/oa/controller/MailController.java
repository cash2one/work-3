package com.oa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oa.common.mail.SendEmail;

@Controller
public class MailController extends BaseController {
	
	@RequestMapping("/sendMail")
	public  void sendMail(HttpServletRequest request,HttpServletResponse response){
		String sendMail = request.getParameter("sendMail");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SendEmail.SendEmailInfoUser163(sendMail, title, content);
			resMap.put("isSuccess", true);
		} catch (MessagingException e) {
			resMap.put("isSuccess", false);
		}
		
		
		super.printJSON(response, new Gson().toJson(resMap));
	}

}
