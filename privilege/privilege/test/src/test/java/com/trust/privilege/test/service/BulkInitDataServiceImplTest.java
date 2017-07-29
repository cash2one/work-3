package com.trust.privilege.test.service;


import com.trust.privilege.service.BulkInitDataService;
import com.trust.privilege.test.base.AbstractTestBase;

/***
 * @ClassName: BulkInitDataServiceImplTest 
 * @Description: 批量插入初始化数据（Excel表格导入）单侧类
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月30日 下午4:07:02
 */
public class BulkInitDataServiceImplTest extends AbstractTestBase{
	
	/**IOC容器中获取BulkInitDataService*/
	BulkInitDataService BulkInitDataService = (BulkInitDataService) 
						this.applicationContext.getBean("bulkInitDataService");

	
//	/**单测批量导入初始化的数据*/
//	@Test
//	public void bulkImportInitDataTest(){
//		Map<String,Object> map = BulkInitDataService.bulkImportInitData("d:\\ptest.xls");
//		 String errorStr = (String) map.get("error");
//		 int resultSize = map.size();
//		if(errorStr != null && errorStr.equals("")){
//			System.out.println(errorStr);
//		}else{
//			System.out.println(resultSize);
//		}
//	}
//
	/**单测批量向数据库插入初始化的Excel数据
	 * @throws FileNotFoundException */
//	@Test
//	public void bulkInsertDataToDBTest() throws FileNotFoundException{
//		//导入文件，转换成二位数组
//		Map<Integer,String[][]> ExcelMap = ExcelUtil
//					.getAllExcelData(new FileInputStream(new File("d:\\privilegeData.xls")));
//		//先校验文件
//		String result = BulkInitDataService.bulkInsertDataToDB(ExcelMap, "admin");
//		
//		System.out.println("结果--->"+result);
//
//	}
	
}
