package com.oa.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.aspectj.weaver.ast.Literal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oa.common.excel.CalendarHelper;
import com.oa.common.excel.ExcelUtil;
import com.oa.common.excel.SimpleExportParameter;
import com.oa.service.UserService;

@Controller
public class ExportController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/exportUser")
	public void exportUser( HttpServletResponse response){
		List<Map<String, Object>> list = userService.exportUsers();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String object = map.get("userSex")+"";
			if ("1".equals(object)) {
				map.put("userSex", "男");
			}else if ("2".equals(object)) {
				map.put("userSex", "女");
			}else if ("3".equals(object)) {
				map.put("userSex", "保密");
			}else {
				map.put("userSex", "未知");
			}
			
		}
			
		//list--->excel
		//创建excel对象
		Workbook wbWorkbook = new  HSSFWorkbook();
		//创建sheet对象
		Sheet sheet = wbWorkbook.createSheet();
		//行  Row
		//单元格  Cell
		//创建文件导出参数对象
		SimpleExportParameter simpleExportParameter = new SimpleExportParameter();
		String filename = "千锋员工信息"+CalendarHelper.formatDatetime(new Date())+".xls";
		simpleExportParameter.setFileName(filename);
		simpleExportParameter.setDataList(list);
		simpleExportParameter.setSheetName("员工信息表");
		String strIds = "userChName,userSex,mobilePhone,email,provinceName,cityName,contryName,userBirthday,orgName";
		String strNames= "姓名,性别,电话号码,邮件,省份,城市,县城,生日,部门";
		String widths= "20,20,20,30,20,20,20,20,20";

		simpleExportParameter.setFieldsId(strIds.split(","));
		simpleExportParameter.setFieldsName(strNames.split(","));
		simpleExportParameter.setTitle("千锋员工信息");
		simpleExportParameter.setWidths(widths.split(","));
		
		ExcelUtil util = new ExcelUtil();
		util.simpleExport(wbWorkbook, sheet, simpleExportParameter);
		
		//excel文件存内存中
		//通过流 把文件从程序写到本地磁盘
		//怎么以文件下载的方式打开一个文件
		//设置响应头
		try {
			//通知浏览器以文件下载的方式打开
			response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
			//设置响应的内容的类型
			response.setContentType("application/octet-stream");///html/text   application/json
			System.out.println("exportUser--------------------------------------");
			//把文件写给客户端
			wbWorkbook.write(response.getOutputStream());	
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
