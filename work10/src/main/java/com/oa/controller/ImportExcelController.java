package com.oa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.oa.common.excel.ExcelUtil;
import com.oa.common.excel.SimpleReadParameter;
import com.oa.service.OrgService;
import com.oa.service.UserService;

/**
 * 文件上传
 * 1）springmvc  文件上传解析器  CommonsMultipartResolver
 * 2）读取上传的文件
 * @author liliting
 *
 */
@Controller
public class ImportExcelController  extends BaseController{
	
	@Resource
	private  UserService userService;
	@Resource
	private OrgService orgService;
	/**
	 * 对excel文档进行读取
	 */
	public List<Map<String, Object>> readExcel(InputStream in) throws Exception {
		int startIndex = 2;
		String fieldsStr = "userChName,mobilePhone,email,userSex,userName,orgName,provinceName,cityName";
		String[] fields = fieldsStr.split(",");
		ExcelUtil util = new ExcelUtil();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		//WorkBook wwb = new HSSFWork();//创建空的excel文件
		//根据输入创建一个根excel文件有关的WorkBook对象
		Workbook wwb = WorkbookFactory.create(in);
		
		// 只读取第一个sheet页
		//getNumberOfSheets() 得到sheet的个数
		if (wwb != null && wwb.getNumberOfSheets() > 0) {
			Sheet wsheet = wwb.getSheetAt(0);//得到第一个sheet
			SimpleReadParameter readParameter = new SimpleReadParameter();
			readParameter.setFieldsId(fields);
			readParameter.setStartIndex(startIndex);
			dataList = util.readSimple(wsheet,readParameter);
		}
		return dataList;
	}
	
	@RequestMapping("/importUsers")
	public void importUsers( HttpServletRequest request,HttpServletResponse response) throws Exception{
		//读取上传的文件
		  Map<String,Object> map1 = new HashMap<String, Object>();
		  int i = 0;
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		//得到上传的文件列表
	     Map<String, MultipartFile> map = 	multipartHttpServletRequest.getFileMap();
	     for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
			//得到当个文件
	    	 	System.out.println(entry.getKey());
	    	 	MultipartFile fileObj = entry.getValue();//得到map中的值
	    	 	InputStream is =  fileObj.getInputStream();
	    	 	//读取excel文件中的内容  把内容存放到list集合中
	    	 	List<Map<String, Object>> list = this.readExcel(is);
	    	 	//根据组织名称查询组织的id
	    	 	for (int j = 0; j < list.size(); j++) {
	    	 		Map<String, Object> map3 = list.get(j);
	    	 		String orgName = map3.get("orgName")+"";
	    	 		int id = orgService.selectOrgIdByOrgName(orgName);
	    	 		map3.put("orgId", id);
	    	 		String sex = map3.get("userSex")+"";
	    	 		if ("男".equals(sex)) {
						map3.put("userSex", 1);
				}
	    	 		else if ("女".equals(sex)) {
						map3.put("userSex", 2);
				}
	    	 		else if ("保密".equals(sex)) {
						map3.put("userSex", 3);
				}else{
					map3.put("userSex", "");
				}
	    	 		
			}
	    	 	System.out.println("list-------------"+list);
	    	 	//调用service
	    	 	 i = userService.InsertUsers(list);
	    	 	
		}
	    if (i>0) {
	    		map1.put("isSuccess", true);
		}else{
			map1.put("isSuccess", false);
		}
		
		super.printJSON(response, new Gson().toJson(map1));
	}
	

}
