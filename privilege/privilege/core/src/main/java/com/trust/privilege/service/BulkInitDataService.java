package com.trust.privilege.service;

import java.util.Map;

/***
 * @ClassName: BulkInitDataService 
 * @Description: 批量初始化数据（Excel表格导入）
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月30日 下午4:06:04
 */
public interface BulkInitDataService {
	
	/**
	 * @Title: bulkImportInitData 
	 * @Description: 校验导入Excel数据
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> authenticationExcelData4ImportFile(Map<Integer,String[][]> excelMap);
	
	/**
	 * @Title: bulkInsertDataToDB 
	 * @Description: 向数据库批量添加初始化数据
	 * @param @param ExcelMap
	 * @param @param userName
	 * @param @return   
	 * @return String
	 */
	public String bulkInsertDataToDB(Map<Integer,String[][]> ExcelMap,String userName);
	
	
}
