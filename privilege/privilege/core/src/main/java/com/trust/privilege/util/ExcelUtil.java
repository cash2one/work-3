package com.trust.privilege.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.model.complex.excel.ExcelDataRoleAndMenu;
import com.trust.privilege.model.complex.excel.ExcelDataRoleAndPrivilegeRes;
import com.trust.privilege.model.complex.excel.ExcelDataUserAndRoleAndGroup;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/***
 * @ClassName: ExcelUtil 
 * @Description: 批量导入excel数据
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月27日 上午11:07:50
 */
public class ExcelUtil {

	/**创建日志记录对象*/
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	/**用来存储返回的提示*/
	public static StringBuffer sb = new StringBuffer(); 
	
	/**
	 * @Title: getAllExcelData 
	 * @Description: 获取Excel表所有数据存放String类型的二维数组中
	 * @param @param file
	 * @param @return   
	 * @return Map<Integer,String[][]> key为Sheet坐标,value为其值
	 * @throws IOException 
	 */
	public static Map<Integer,String[][]> getAllExcelData(InputStream is) {
		//用来存储Excel导入的数据
		Map<Integer,String[][]> ExcelMap = new HashMap<>();
		
		//声明一个二维数组
		String [][] tab  = null ;
			try {
				//通过输入流获取表格数据
				Workbook rwd = Workbook.getWorkbook(is);
				//获取Excel中表的数量
				int sheets = rwd.getNumberOfSheets();
				
				//循环每一个sheet表
				for(int s = 0 ; s < sheets ; s ++){
					//获取当前Sheet表
					Sheet rs = rwd.getSheet(s);
					//获取当前页的所有列
					int column = rs.getColumns();
					//获取当前页的所有行
					int rows = rs.getRows();
					//创建数组,用于存储Excel表格数据
					tab = new String [rows][column];

					//循环获取每一行
					for(int i = 0 ; i < rows ; i ++){
						//获取每一行中单元个数
						Cell[] rowCells = rs.getRow(i);
						//循环获取每一列
						for(int j = 0 ; j < rowCells.length ; j ++){
							//获取每一个具体的单元格
							Cell cell = rowCells[j];
							//获取单元格的value值
							tab [i][j] = cell.getContents();
						}
					}	
					//每循环一张表,放入二维数组,然后放入map中
					ExcelMap.put(s, tab);
				}
				is.close();
		} catch(Exception e){
			//记录这个异常
			logger.info("读取Excel表出错",e);
		}
			//返回这个map
			return  ExcelMap;
	}
	
	/**
	 * @Title: verifyExcelRoleAndMenuData 
	 * @Description: 校验导入的角色和菜单数据
	 * @param @param ExcelMap
	 * @param @return
	 * @param @throws IOException   
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> verifyExcelRoleAndMenuData(Map<Integer,String[][]> ExcelMap) throws IOException{
		//声明map,用来存储返回的数据
		Map<String,Object> resultMap = null;
		
			//获取角色和菜单Excel数据
			String [][] roleAndMenus = ExcelMap.get(0);
			//如果数据不为空
			if(CommonUtil.isNotNull(roleAndMenus)){
				//创建map,用来存储返回的数据
				resultMap = new HashMap<>();
				//创建集合用于存放得到的数据
				List<Object> respModels = new ArrayList<>();
				//循环这个数组,来获取每一行数据,进行数据校验
				for (int i = 1 ; i <roleAndMenus.length ; i ++) {
					//获取到每行的每个单元格数据
					for (int j = 0 ; j < roleAndMenus[i].length; j ++) {
						
						String roleName = roleAndMenus[i][j];
						String roleDesc = roleAndMenus[i][++j];
						String menuName = roleAndMenus[i][++j];
						String menuDesc = roleAndMenus[i][++j];
						String menuReqMethod = roleAndMenus[i][++j];
						String menuReqUrl = roleAndMenus[i][++j];
						String systemPlatformCd = roleAndMenus[i][++j];
						
						//如果角色名不为为空,并且没有非法字符
						if(CommonUtil.isNotNull(roleName)&& CommonUtil.stringCheck(roleName)){
							//如果菜单名不为空,并且没有非法字符
							if(CommonUtil.isNotNull(menuName)&& CommonUtil.stringCheck(menuName)){
								//如果请求方式合法
								if(CommonUtil.isRightfulString(menuReqMethod)){
									//如果请求路径不为空
									if(CommonUtil.isNotNull(menuReqUrl)){
										//如果所属系统不为空
										if(CommonUtil.stringCheck(systemPlatformCd)){
											//那么将数据封装成对象并且返回
											respModels.add(new ExcelDataRoleAndMenu
													(roleName, roleDesc, systemPlatformCd, 
															menuName, menuDesc, menuReqMethod, menuReqUrl));
										}else{
											sb.append("第一张角色菜单表,第"+i+"行"+roleAndMenus[0][6]+",");
										}
									}else{
										sb.append("第一张角色菜单表,第"+i+"行"+roleAndMenus[0][5]+",");
									}
								}else{
									sb.append("第一张角色菜单表,第"+i+"行"+roleAndMenus[0][4]+",");
								}
							}else{
								sb.append("第一张角色菜单表,第"+i+"行"+roleAndMenus[0][2]+",");
							}
						}else{
							sb.append("第一张角色菜单表,第"+i+"行"+roleAndMenus[0][0]+",");
						}
					}
				}
				//循环完了,集合中都是excel对象
				resultMap.put("model",respModels);
				//如果有错误信息
				if(CommonUtil.isNotNull(CommonUtil.onlyString(sb.toString()))){
					//那么放入错误信息
					resultMap.put("error", CommonUtil.onlyString(sb.toString())+",不合法请重新填写");
				}
			}
		
			//执行完毕,返回这个map
			return resultMap ;
	}
	
	/**
	 * @Title: verifyExcelRoleAndResData 
	 * @Description: 校验导入的角色和资源数据
	 * @param @param ExcelMap
	 * @param @return
	 * @param @throws IOException   
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> verifyExcelRoleAndResData(Map<Integer,String[][]> ExcelMap) throws IOException{
		//声明map,用来存储返回的数据
		Map<String,Object> resultMap = null;

			//获取到角色和资源数据
			String [] [] RoleAndRes =ExcelMap.get(1);
			//如果读取的Excel表格数据不为空
			if(CommonUtil.isNotNull(RoleAndRes)){
				//创建map,用来存储返回的数据
				resultMap = new HashMap<>();
				//创建集合用于存放得到的数据
				List<Object> respModels = new ArrayList<>();
				//获取到每行数据
				for (int i = 1 ; i <RoleAndRes.length ; i ++) {
					//获取到每行的每个单元格数据
					for (int j = 0 ; j < RoleAndRes[i].length; j ++) {
						
						String roleName = RoleAndRes[i][j];
						String roleDesc = RoleAndRes[i][++j];
						String resName = RoleAndRes[i][++j];
						String resDesc = RoleAndRes[i][++j];
						String resReqMethod = RoleAndRes[i][++j];
						String resReqUrl = RoleAndRes[i][++j];
						String resTypeCd = RoleAndRes[i][++j];
						String resMark = RoleAndRes[i][++j];
						String menuName = RoleAndRes[i][++j];
								
						//如果角色名不为为空,并且合法
						if(CommonUtil.stringCheck(roleName)){
							//如果资源名称不为空,并且合法
							if(CommonUtil.stringCheck(resName)){
								//如果请求方式合法
								if(CommonUtil.isRightfulString(resReqMethod)){
									//如果请求路径不为空
									if(CommonUtil.isNotNull(resReqUrl)){
										//如果系统类型代码不为空,并且没有合法
										if(CommonUtil.stringCheck(resTypeCd)){
											//如果资源标识不为空，并且合法
											if(CommonUtil.isNotNull(resMark)){
												//如果所属菜单不为空,并且合法
												if(CommonUtil.stringCheck(menuName)){
													//将数据封装成对象,并放入集合中
													respModels.add(new ExcelDataRoleAndPrivilegeRes(roleName,
																 roleDesc, null, resName, resDesc, resReqMethod,
																			resReqUrl, resTypeCd, resMark, menuName));
																
												}else{
													sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][8]+",");
												}
											}else{
												sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][7]+",");
											}
										}else{
											sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][6]+",");
										}
									}else{
										sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][5]+",");
									}
								}else{
									sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][4]+",");
								}
							}else{
								sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][2]+",");
							}
						}else{
							sb.append("第二张角色资源表,第"+i+"行"+RoleAndRes[0][0]+",");
						}
					}
				}
				//循环完了,集合中都是excel对象
				resultMap.put("model",respModels);
				//如果有错误信息
				if(CommonUtil.isNotNull(CommonUtil.onlyString(sb.toString()))){
					//那么放入错误信息
					resultMap.put("error", CommonUtil.onlyString(sb.toString())+",不合法请重新填写");
				}
			}
		//执行完毕,返回这个map
		return resultMap;
	}
	
	/**
	 * @Title: verifyExcelUserAndGroupAndRoleData 
	 * @Description: 校验导入的角色,角色组,用户数据
	 * @param @param ExcelMap
	 * @param @return
	 * @param @throws IOException   
	 * @return Map<String,Object>
	 */
	public static Map<String,Object> verifyExcelUserAndGroupAndRoleData(Map<Integer,String[][]> ExcelMap) throws IOException{
		//声明map,用来存储返回的数据
		Map<String,Object> resultMap = null;
		
			//获取到用户,角色,用户组的数据
			String [] [] UserAndGroupAndRole =	ExcelMap.get(2);
			//如果得到的数据不为空
			if(CommonUtil.isNotNull(UserAndGroupAndRole)){
				//创建map,用来存储返回的数据
				resultMap = new HashMap<>();
				//创建集合用于存放得到的数据
				List<Object> respModels = new ArrayList<>();
				//获取到每行数据
				for (int i = 1 ; i <UserAndGroupAndRole.length ; i ++) {
					//获取到每行的每个单元格数据
					for (int j = 0 ; j < UserAndGroupAndRole[i].length; j ++) {
						String realName = UserAndGroupAndRole[i][j];
						String userName = UserAndGroupAndRole[i][++j];
						String userDesc = UserAndGroupAndRole[i][++j];
						String idCard = UserAndGroupAndRole[i][++j];
						String memberType=UserAndGroupAndRole[i][++j];
						String phoneNumber=UserAndGroupAndRole[i][++j];
						String roleName = UserAndGroupAndRole[i][++j];
						String groupName = UserAndGroupAndRole[i][++j];
						String groupDesc = UserAndGroupAndRole[i][++j];
						String parentGroupId = UserAndGroupAndRole[i][++j];
						
						//如果用户名(邮箱)不为空,并且合法
						if(CommonUtil.emailCheck(userName)){
							//如果身份证号不为空
							if(CommonUtil.isIdCardNo(idCard)){
								//如果证件类型不为空,并且合法
								if(CommonUtil.stringCheck(memberType)){
									//如果电话号码合法(电话号码可以不填写)
									if(CommonUtil.phoneNumberCheck(phoneNumber)){
										//如果角色名不为空,并且合法
										if(CommonUtil.stringCheck(roleName)){
											//如果用户组不为空,并且合法
											if(CommonUtil.stringCheck(groupName)){
												//如果系统平台不为空
													respModels.add(new ExcelDataUserAndRoleAndGroup(
															roleName, null, null, realName, userName, userDesc, 
																memberType, idCard, phoneNumber, groupName, groupDesc, parentGroupId));
											}else{
												sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][7]+",");
											}
										}else{
											sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][6]+",");
										}
									}else{
										sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][5]+",");
									}
								}else{
									sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][4]+",");
								}
							}else{
								sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][3]+",");
							}
						}else{
							sb.append("第三张用户与用户组和角色表,第"+i+"行"+UserAndGroupAndRole[0][1]+",");
						}
					}
				}	
				//循环完了,集合中都是excel对象
				resultMap.put("model",respModels);
				//如果有错误信息
				if(CommonUtil.isNotNull(CommonUtil.onlyString(sb.toString()))){
					//那么放入错误信息
					resultMap.put("error", CommonUtil.onlyString(sb.toString())+",不合法请重新填写");
				}
			}
			//返回这个map
			return resultMap;
	}

	
}
