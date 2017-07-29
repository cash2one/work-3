package com.trust.privilege.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.trust.privilege.dao.GroupRoleMapper;
import com.trust.privilege.dao.MemBerMapper;
import com.trust.privilege.dao.MenuMapper;
import com.trust.privilege.dao.MenuRoleMapper;
import com.trust.privilege.dao.PrivilegeResMapper;
import com.trust.privilege.dao.PrivilegeResTypeMapper;
import com.trust.privilege.dao.RoleMapper;
import com.trust.privilege.dao.RolePrivilegeResMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.SystemUserMapper;
import com.trust.privilege.dao.UserGroupMapper;
import com.trust.privilege.dao.UserRoleInstanceMapper;
import com.trust.privilege.dao.complexMapper.GroupDao;
import com.trust.privilege.dao.complexMapper.RoleComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.model.GroupRole;
import com.trust.privilege.model.GroupRoleExample;
import com.trust.privilege.model.MemBer;
import com.trust.privilege.model.MemBerExample;
import com.trust.privilege.model.Menu;
import com.trust.privilege.model.MenuExample;
import com.trust.privilege.model.MenuRole;
import com.trust.privilege.model.MenuRoleExample;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.PrivilegeResExample;
import com.trust.privilege.model.PrivilegeResTypeExample;
import com.trust.privilege.model.Role;
import com.trust.privilege.model.RoleExample;
import com.trust.privilege.model.RolePrivilegeRes;
import com.trust.privilege.model.RolePrivilegeResExample;
import com.trust.privilege.model.SystemPlatformExample;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.UserRoleInstanceExample;
import com.trust.privilege.model.complex.GroupDo;
import com.trust.privilege.model.complex.UserDo;
import com.trust.privilege.model.complex.excel.ExcelDataRoleAndMenu;
import com.trust.privilege.model.complex.excel.ExcelDataRoleAndPrivilegeRes;
import com.trust.privilege.model.complex.excel.ExcelDataUserAndRoleAndGroup;
import com.trust.privilege.service.BulkInitDataService;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.DBInstanceUtil;
import com.trust.privilege.util.ExcelUtil;

/***
 * @ClassName: BulkInitDataServiceImpl 
 * @Description: 批量插入初始化数据（Excel表格导入）
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月30日 下午4:06:20 getDBGroup
 */
public class BulkInitDataServiceImpl implements BulkInitDataService {
	
	/**创建日志对象*/
	private static final Logger logger = LoggerFactory.getLogger(BulkInitDataServiceImpl.class);

	/**依赖注入角色dao*/
	@Resource
	private RoleComplexMapper roleComplexMapper;
	
	/**依赖注入操作用户组dao*/
	@Resource
	private GroupDao groupDao;
	
	/**依赖注入操作用户dao层*/
	@Resource
	private SysUserDao sysUserDao;
	
	/**依赖注入用户Mapper*/
	@Resource
	private SystemUserMapper systemUserMapper;
	
	/**依赖注入用户组Mapper*/
	@Resource
	private UserGroupMapper userGroupMapper;
	
	/**依赖注入操作角色Mapper*/
	@Resource
	private RoleMapper roleMapper;
	
	/**依赖注入操作菜单Mapper*/
	@Resource
	private MenuMapper menuMapper;
	
	/**依赖注入操作菜单角色Mapper*/
	@Resource
	private MenuRoleMapper menuRoleMapper;
	
	/**依赖注入操作权限资源Mapper*/
	@Resource
	private PrivilegeResMapper privilegeResMapper;
	
	/**依赖注入操作角色权限资源Mapper*/
	@Resource
	private RolePrivilegeResMapper rolePrivilegeResMapper;
	
	/**依赖注入操作用户组与角色关联Mapper*/
	@Resource
	private GroupRoleMapper GroupRoleMapper;
	
	/**依赖注入操作成员Mapper*/
	@Resource
	private MemBerMapper memBerMapper;
	
	/**依赖注入操作用户角色实例Mapper*/
	@Resource
	private UserRoleInstanceMapper userRoleInstanceMapper;
	
	/**依赖注入操作系统表的Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**依赖注入操作权限类型Mapper*/
	@Resource
	private PrivilegeResTypeMapper privilegeResTypeMapper;
	
	/**因为插入的数据都是同系统的，所以在这边用全局变量*/
	private String systemPlatFromCd = null ;
	
	/**
	 * @Title: bulkImportInitData 
	 * @Description: 校验导入的Excel数据
	 * 	获取各类数据的List
	 * @param @param filePath
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> authenticationExcelData4ImportFile(Map<Integer,String[][]> excelMap) {
		//创建map，用于存储数据
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> resultMap = new HashMap<>();
		String errorStr = null;
		try {
			
			//获取Excel表格优先得到角色与菜单数据数据
			map = ExcelUtil.verifyExcelRoleAndMenuData(excelMap);
			//如果没有错误信息，说明成功获取到角色和菜单数据
			if(CommonUtil.isNull(map.get("error"))){
				//那么获取到Excel封装的角色和菜单数据封装的Model集合
				 @SuppressWarnings("unchecked")
				List<ExcelDataRoleAndMenu> ExcelDataRoleAndMenus = 
						 		(List<ExcelDataRoleAndMenu>) map.get("model");	
				 
				//接下来获取角色和权限资源的数据
				map = ExcelUtil.verifyExcelRoleAndResData(excelMap);
				//如果没有错误信息，说明成功获取到角色和权限资源的数据
				if(CommonUtil.isNull(map.get("error"))){
					//那么获取Excel封装的角色和权限资源数据封装的model
					@SuppressWarnings("unchecked")
					List<ExcelDataRoleAndPrivilegeRes> excelDataRoleAndPrivilegeRess = 
									(List<ExcelDataRoleAndPrivilegeRes>) map.get("model");
					
					//接下来获取角色，用户，用户组的数据
					map = ExcelUtil.verifyExcelUserAndGroupAndRoleData(excelMap);
					//如果没有错误信息，说明成功获取到角色和用户以及用户组的数据
					if(CommonUtil.isNull(map.get("error"))){
						//那么获取Excel封装的的角色和用户以及用户组数据的model
						@SuppressWarnings("unchecked")
						List<ExcelDataUserAndRoleAndGroup> excelDataUserAndRoleAndGroups = 
											 (List<ExcelDataUserAndRoleAndGroup>) map.get("model");
						
						//成功获取到需要的数据了，那么直接返回该数据
						resultMap.put("ExcelDataRoleAndMenus", ExcelDataRoleAndMenus);
						resultMap.put("excelDataRoleAndPrivilegeRess", excelDataRoleAndPrivilegeRess);
						resultMap.put("excelDataUserAndRoleAndGroups", excelDataUserAndRoleAndGroups);
					
					}else{
						//返回错误信息
						errorStr = (String) map.get("error");
						resultMap.put("error", errorStr);
					}
				}else{
					//返回错误信息
					errorStr = (String) map.get("error");
					resultMap.put("error", errorStr);
				}
			}else{
				//返回错误信息
				errorStr = (String) map.get("error");
				resultMap.put("error", errorStr);
			}
		}catch(Exception e){
			//如果出现异常那么记录
			logger.info("批量导入数据出错",e);
		}	
			//没有则返回null
			return resultMap;
	}
	
	/**
	 * @Title: bulkInsertDataToDB 
	 * @Description: 向数据库批量插入数据（Excel表格导入的系统初始化数据）
	 * @param @param filePath
	 * @param @return   
	 * @return String
	 */
	@Override
	public String bulkInsertDataToDB(Map<Integer,String[][]> ExcelMap,String userName){
		//声明返回对象
		String result = null ;
		
		//如果参数不为空
		if(CommonUtil.isNotNull(ExcelMap)){
			//校验下Excel表格的数据
			Map<String,Object> map = this.authenticationExcelData4ImportFile(ExcelMap);
			
			//获取错误消息
			String error = (String) map.get("error");
			
			//如果校验数据通过
			if(CommonUtil.isNull(error)){
				//用作承载数据
				StringBuffer sb = new StringBuffer();
						
				//获取角色菜单数据
				@SuppressWarnings("unchecked")
				List<ExcelDataRoleAndMenu> ExcelDataRoleAndMenus = 
							(List<ExcelDataRoleAndMenu>) map.get("ExcelDataRoleAndMenus");
				
				//获取角色权限资源数据
				@SuppressWarnings("unchecked")
				List<ExcelDataRoleAndPrivilegeRes> excelDataRoleAndPrivilegeRess = 
								(List<ExcelDataRoleAndPrivilegeRes>) map.get("excelDataRoleAndPrivilegeRess");
				
				//获取用户，用户组，角色数据
				@SuppressWarnings("unchecked")
				List<ExcelDataUserAndRoleAndGroup> excelDataUserAndRoleAndGroups = 
										(List<ExcelDataUserAndRoleAndGroup>) map.get("excelDataUserAndRoleAndGroups");
						
						
				//如果角色和菜单数据不为空
				if(CommonUtil.isNotNull(ExcelDataRoleAndMenus)){
					//那么插入角色和菜单数据,并设置返回结果
					sb.append(insertRoleAndMenuData(ExcelDataRoleAndMenus,userName)).append("\n");
				}
				//如果角色和菜单资源数据不为空
				if(CommonUtil.isNotNull(excelDataRoleAndPrivilegeRess)){
					//这会的角色表,菜单资源表，菜单角色关联表已经完成插入工作，接下来开始插入权限资源表,并设置返回结果
					sb.append(insertRoleAndPrivilegeResData(excelDataRoleAndPrivilegeRess,userName)).append("\n");
				}
				//如果用户组与用户和角色数据不为空
				if(CommonUtil.isNotNull(excelDataUserAndRoleAndGroups)){
					//接下来开始插入用户组与用户角色关联表,并设置返回结果
					sb.append(insertUserAndRoleAndGroupData(excelDataUserAndRoleAndGroups,userName)).append("\n");
				}
				//直接返回结果
				result =  sb.toString();
			}else{
				//否则返回错误信息
				result = error;
			}
		}
			return result;
	}	

	/**
	 * @Title: insertRoleAndMenuData 
	 * @Description: 通过Excel向数据库插入角色和菜单数据
	 * @param @param ExcelDataRoleAndMenus   
	 * @return void
	 */
	private String insertRoleAndMenuData(List<ExcelDataRoleAndMenu> ExcelDataRoleAndMenus,String userName){
		//创建返回数据
		StringBuffer sb = new StringBuffer();
		//如果参数不为空
		if(CommonUtil.isNotNull(ExcelDataRoleAndMenus)){
			//先获取到每一个角色菜单数据
			for (ExcelDataRoleAndMenu excelDataRoleAndMenu : ExcelDataRoleAndMenus) {
				
				//获取角色实例
				Role role = DBInstanceUtil.getRole(excelDataRoleAndMenu);
				//设置创建人
				role.setCreateUserId(userName);
				//因为插入数据的都是一个平台，所以用角色中的系统平台名就可以查到所属系统平台CD
				this.systemPlatFromCd = getSystemPlatform(role.getSystemPlatformCd());
				//如果系统CD为空
				if(CommonUtil.isNull(systemPlatFromCd)){
					//返回错误信息
					return "Excel数据中，系统平台名称填写错误，请重新填写";
				}
				//在这里做一个关于所属系统CD的处理
				role.setSystemPlatformCd(systemPlatFromCd);
				
				//通过Excel中数据的角色名查询数据库中的角色信息
				Role dbRole = this.getDBRole(role.getRoleName(),systemPlatFromCd);
				
				String roleId = null ;
				
				//如果数据库中是存在角色数据的，所以不再进行添加数据的操作
				if(CommonUtil.isNotNull(dbRole)){
					//roleId为数据库中数据的id
					roleId = dbRole.getRoleId() ;
					//回应
					sb.append(role.getRoleName()+"数据库中已存在，不再进行添加操作,");
				}else{
					//因为是新数据，所以是新产生的角色id
					roleId = role.getRoleId();
					//设置该数据创建时间为当前时间
					role.setCreateDt(new Date());
					//那么将Excel中的角色信息加入到数据库
					roleMapper.insert(role);
				}
				//接着获取菜单表实例
				Menu menu = DBInstanceUtil.getMenu(excelDataRoleAndMenu);
				//设置创建人
				menu.setCreateUserId(userName);
				//因为是同一张表中同一行数据，所以肯定是同属一个系统
				menu.setSystemPlatformCd(systemPlatFromCd);
				
				//通过菜单名，查询数据库菜单表数据
				Menu dbMenu = this.getDBMenu(menu.getMenuName(),systemPlatFromCd);
						
				//默认menuId为空
				String menuId = null ;
				
				//如果数据库中存在该菜单数据，那么不进行添加数据操作
				if(CommonUtil.isNotNull(dbMenu)){
					//那么赋值menuId
					menuId = dbMenu.getMenuId() ;
					//做出回应
					sb.append(menu.getMenuName()+"数据库中已存在，不再进行添加操作,");
				}else{
					//如果没有查询到数据,那么说明是新数据，所以使用Excel导入menu数据生成的新 id
					menuId = menu.getMenuId();
					//否则的话，设置当前系统CD为上级菜单
					menu.setParentId(systemPlatFromCd);
					//设置该数据创建时间为当前时间
					menu.setCreateDt(new Date());
					//接着进行菜单资源数据的插入
					menuMapper.insert(menu);
				}
				
					//然后获取角色菜单表实例
					MenuRole menuRole = DBInstanceUtil.getMenuAndRoleTable(roleId, menuId);
					//设置当前数据创建时间
					menuRole.setCreateDt(new Date());
					//进行插入数据
					insertMenuRole(menuRole);
			}
			
			//字符串去重处理，并返回结果
			return CommonUtil.onlyString(sb.toString())+",批量插入角色和菜单数据成功!";
		}
			return sb.toString();		
	}
	
	/**
	 * @Title: insertRoleAndPrivilegeResData 
	 * @Description: 通过Excel向数据库插入角色和资源数据
	 * @param @param excelDataRoleAndPrivilegeRess
	 * @param @return   
	 * @return String
	 */
	private String insertRoleAndPrivilegeResData(List<ExcelDataRoleAndPrivilegeRes> excelDataRoleAndPrivilegeRess,String userName){
		//创建返回对象
		StringBuffer sb = new StringBuffer();
		//如果参数不为空
		if(CommonUtil.isNotNull(excelDataRoleAndPrivilegeRess)){
			//获取每一个角色权限资源数据
			for(ExcelDataRoleAndPrivilegeRes excelDataRoleAndPrivilegeRes : excelDataRoleAndPrivilegeRess){
				//获取每一个权限资源实例
				PrivilegeRes privilegeRes = DBInstanceUtil.getPrivilegeRes(excelDataRoleAndPrivilegeRes);
				//设置创建人
				privilegeRes.setCreateUserId(userName);
				//因为插入数据的每一条肯定都是一个平台，所以用权限资源中的系统平台名就可以查到所属系统平台CD
				privilegeRes.setSystemPlatformCd(this.systemPlatFromCd);
				//在这里做一个关于资源类型的处理
				privilegeRes.setResTypeCd(getPrivilegeResType(privilegeRes.getResTypeCd()));
				//处理权限资源中所属菜单资源id
				privilegeRes.setMenuId(getDBMenu(privilegeRes.getMenuId()
						,systemPlatFromCd).getMenuId());
				
				//通过该资源名称查询数据库中该数据是否已存在
				PrivilegeRes dbPrivilegeRes = this.getDBPrivilegeRes(privilegeRes.getResName(),systemPlatFromCd);
				
				//默认权限资源id为空
				String privilegeId = null;
				
				//通过查询到数据库中存在该资源
				if(CommonUtil.isNotNull(dbPrivilegeRes)){
					//那么id则为数据库库中的id
					privilegeId = dbPrivilegeRes.getPrivilegeResId();
					//做出回应
					sb.append(privilegeRes.getResName()+"数据库中已存在，不再进行添加操作,");
				}else{
					//否则说明，这条数据为新数据，那么id则为Excel导入新数据的Id
					privilegeId = privilegeRes.getPrivilegeResId();
					//设置该数据创建时间为当前时间
					privilegeRes.setCreateDate(new Date());
					//接着插入数据到权限资源表中
					privilegeResMapper.insert(privilegeRes);
					
				}
				
				//通过角色权限资源数据中的角色名去数据库中查询该数据
				Role role = this.getDBRole(excelDataRoleAndPrivilegeRes.getRoleName(),systemPlatFromCd);
				//如果角色数据不为空
				if(CommonUtil.isNotNull(role)){
					//获取角色权限资源实例
					RolePrivilegeRes rolePrivilegeRes = DBInstanceUtil
								.getRoleAndPrivilegeResTable(role.getRoleId(), privilegeId);
					//设置当前数据创建时间
					rolePrivilegeRes.setCreateDt(new Date());
					//将数据插入中间表
					insertRolePrivilegeRes(rolePrivilegeRes);
				}else{
					//否则说明这个角色是输入错的
					sb.append("角色和资源表中"+excelDataRoleAndPrivilegeRes.getRoleName()+"输入错误，此条数据不进行插入，请重新输入,");
					continue;
				}
			}
			//对数据进行去重操作,并返回结果
			return CommonUtil.onlyString(sb.toString())+",批量插入角色和资源数据成功!";
		}
			return sb.toString();
	}
	
	/**
	 * @Title: insert 
	 * @Description: 通过Excel向数据库插入用户组用户和角色数据
	 * @param @param excelDataUserAndRoleAndGroups
	 * @param @return   
	 * @return String
	 */
	private String insertUserAndRoleAndGroupData(List<ExcelDataUserAndRoleAndGroup> excelDataUserAndRoleAndGroups,String userName){
		//创建返回对象
		StringBuffer sb = new StringBuffer();
		//如果参数不为空
		if(CommonUtil.isNotNull(excelDataUserAndRoleAndGroups)){
			//获取每一个用户，用户组，角色数据
			for(ExcelDataUserAndRoleAndGroup excelDataUserAndRoleAndGroup : excelDataUserAndRoleAndGroups){
				//获取每一个用户组的实例
				GroupDo group = DBInstanceUtil.getUserGroup(excelDataUserAndRoleAndGroup);
				//设置创建人
				group.setCreateUserid(userName);
				//通过用户组名称，拿到数据库中该数据
				GroupDo dbGroup = this.getDBGroup(group.getGroupName());
				//默认组id为空
				String groupId = null ;
				
				//如果查到的数据库用户组不为空
				if(CommonUtil.isNotNull(dbGroup)){
					//那么赋值用户组id为数据库中存在数据的组id
					groupId = dbGroup.getGroupId();
					//做出回应
					sb.append(group.getGroupName()+"数据库中已存在，不再进行添加操作,");
				}else{
					//否则的话，说明数据库中没有该组，那么设置用户组id为Excel表格录入的用户组信息所产生的的新id
					groupId = group.getGroupId();
					//如果有上级用户组
					if(CommonUtil.isNotNull(group.getParentGroupId())){
						//通过上级用户组名称查询上级用户组，如果不为空
						if(CommonUtil.isNotNull(this.getDBGroup(group.getParentGroupId()))){
							//得到上级用户组的id
							String parentId = this.getDBGroup(group.getParentGroupId()).getGroupId();
							//放入这条数据中
							group.setParentGroupId(parentId);
						}else{
							//做出回应
							sb.append(group.getGroupName()+"的上级用户组在Excel中填写错误，不再进行添加操作,");
							continue ;
						}
					}
					
					//设置该数据创建时间为当前时间
					group.setCreateDt(new Date());
					//并且插入数据库中
					userGroupMapper.insertSelective(group);
					
				}
				
				//通过角色名查询该角色信息
				Role role = getDBRole(excelDataUserAndRoleAndGroup.getRoleName(),systemPlatFromCd);
				//如果返回值不为空
				if(CommonUtil.isNotNull(role)){
					//获取用户组角色关联表实例
					GroupRole groupRole = DBInstanceUtil.getGroupAndRoleTable(role.getRoleId(), groupId);
					//设置当前创建时间
					groupRole.setCreateDt(new Date());
					//那么插入用户组与角色关联表
					insertGroupRole(groupRole);
				}else{
					//否则说明这个角色是输入错的
					sb.append("用户组与用户角色关联表中"+excelDataUserAndRoleAndGroup.getRoleName()+"输入错误，此条数据不进行插入，请重新输入,");
					continue ;
				}
				
				//接着获取用户表实例
				SystemUser user = DBInstanceUtil.getSystemUser(excelDataUserAndRoleAndGroup);
				//设置创建人
				user.setCreateUserId(userName);
				//如果填写的身份证号不为空
				if(CommonUtil.isNotNUll(user.getIdCard())){
					//通过身份证号查询成员表中，如果不存在改数据
					if(CommonUtil.isNull(getMember(user.getIdCard()))){
						//那么获取成员表实例
						MemBer memBer = DBInstanceUtil.getMember(excelDataUserAndRoleAndGroup);
						//将数据插入成员表中
						memBerMapper.insert(memBer);
					}else{
						//否则说明存在该数据，那么进行提示
						sb.append(user.getUserName()+"的证件号码，数据库中已存在，不再进行添加操作,");
					}
				}
				
				//通过用户用户名，数据库中查询该用户的数据
				UserDo dbUser = this.getDBSystemUser(user.getUserName());
				
				//默认userId为空
				String userId = null;
				
				//如果查询到的数据库中存在该用户
				if(CommonUtil.isNotNull(dbUser)){
					//设置userid为数据库中的数据id
					userId = dbUser.getSystemUserId();
				}else{
					//否则说明数据库中不存在该数据，那么设置userid为Excel导入数据新生成的id
					userId = user.getSystemUserId();
					//设置该数据创建时间为当前时间
					user.setCreateDt(new Date());
					//并且插入这条数据到用户表
					systemUserMapper.insertSelective(user);
				}
				
				//得到用户角色实例表model
				UserRoleInstance userRoleInstance = DBInstanceUtil
									.getUserRoleInstanceTable(userId, groupId);
				
				//通过用户id，用户组id,查询用户角色实例表的id
				String instId = this.getDBUserRoleInstance(userId,groupId);
				//如果数据库中已经存在
				if(CommonUtil.isNotNull(instId)){
					//将查询出的用户角色实例表的id赋值给需要修改的用户角色实例
					userRoleInstance.setInstId(instId);
					//那么修改这条数据
					userRoleInstanceMapper.updateByPrimaryKeySelective(userRoleInstance);
				}else{
					//设置用户角色实例表当前数据的id
					userRoleInstance.setInstId(CommonUtil.getUUID());
					//设置该数据创建时间为当前时间
					userRoleInstance.setCreateDt(new Date());
					//并且直接插入数据到用户角色实例表
					userRoleInstanceMapper.insert(userRoleInstance);	
				}
			}
			//对数据进行去重操作,并返回结果
			return CommonUtil.onlyString(sb.toString())+",批量插入用户组与用户和角色数据成功!";
		}
			return sb.toString();
	}
	
	/**通过系统名查询到该系统平台的CD*/
	private String getSystemPlatform(String systemPlatName){
		SystemPlatformExample example = new SystemPlatformExample();
		example.createCriteria().andSystemPlatformNameEqualTo(systemPlatName);
		if(	systemPlatformMapper.selectByExample(example).isEmpty()){
				return null;
		}
		return 	systemPlatformMapper
				.selectByExample(example).get(0).getSystemPlatformCd();
	}

	/**通过角色名称和所属系统平台找到数据库唯一角色信息*/
	private Role getDBRole(String roleName,String systemPlatformCd){
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleNameEqualTo(roleName)
					.andSystemPlatformCdEqualTo(systemPlatformCd);
		if(roleMapper.selectByExample(example).isEmpty()){
			return null ;
		}else
		return roleMapper.selectByExample(example).get(0);
	}
	
	/**通过菜单名和所属系统平台找到数据库唯一菜单信息*/
	private Menu getDBMenu(String menuName,String systemPlatFromCd){
		MenuExample example = new MenuExample();
		example.createCriteria()
			.andMenuNameEqualTo(menuName)
				.andSystemPlatformCdEqualTo(systemPlatFromCd);
		if(menuMapper.selectByExample(example).isEmpty()){
			return null;
		}
		return menuMapper.selectByExample(example).get(0);
	}
	
	/**插入角色菜单中间表数据，防止数据冗余*/
	@Transactional
	private void insertMenuRole(MenuRole menuRole){
		MenuRoleExample example = new MenuRoleExample();
		example.createCriteria().andMenuIdEqualTo(menuRole.getMenuId())
			.andRoleIdEqualTo(menuRole.getRoleId());
		//先删除表中的数据
		menuRoleMapper.deleteByExample(example);
		//那么进行数据的插入
		menuRoleMapper.insert(menuRole);
	}
	
	/**通过权限资源名称，查询数据库中这个资源*/
	private PrivilegeRes getDBPrivilegeRes(String resName,String systemPlatFromCd){
		PrivilegeResExample example = new PrivilegeResExample();
		example.createCriteria().andResNameEqualTo(resName).andSystemPlatformCdEqualTo(systemPlatFromCd);
		if( privilegeResMapper.selectByExample(example).isEmpty()){
			return null;
		}
		return privilegeResMapper.selectByExample(example).get(0);
	}
	
	/**插入角色权限资源中间表数据，防止数据的冗余*/
	@Transactional
	private void insertRolePrivilegeRes(RolePrivilegeRes rolePrivilegeRes){
		RolePrivilegeResExample example = new RolePrivilegeResExample();
			example.createCriteria().andRoleIdEqualTo(rolePrivilegeRes.getRoleId())
				.andPrivilegeResIdEqualTo(rolePrivilegeRes.getPrivilegeResId());
		//先执行删除的操作
		rolePrivilegeResMapper.deleteByExample(example);
		//在执行添加的操作
		rolePrivilegeResMapper.insert(rolePrivilegeRes);
	}
	
	/**通过用户组名称，查询数据库中这个用户组*/
	private GroupDo getDBGroup(String groupName){
		GroupDo group = groupDao.selectGroupByGroupName(groupName);
		if(group != null){
			return group;
		}
		return null;
	}
	
	/**插入用户组角色中间表数据，防止数据冗余*/
	@Transactional
	private void insertGroupRole(GroupRole groupRole){
		GroupRoleExample example = new GroupRoleExample();
		example.createCriteria()
				.andGroupIdEqualTo(groupRole.getGroupId())
						.andRoleIdEqualTo(groupRole.getRoleId());
		//先删除表中的数据
		GroupRoleMapper.deleteByExample(example);
		//那么进行数据的插入
		GroupRoleMapper.insert(groupRole);
	}
	
	/**通过用户名称，查询数据库中该用户数据*/
	private UserDo getDBSystemUser(String userName){
		UserDo user = sysUserDao.selectUserByIdCard(userName);
		if(user != null){
			return user;
		}
		return null;
	}
	
	/**通过用户id和用户组id查询数据库中用户实例表id */
	private String getDBUserRoleInstance(String userId,String groupId){
		
		//创建用户角色实例Examle对象
		UserRoleInstanceExample userRoleInstanceExample = new UserRoleInstanceExample();
		//设置条件
		userRoleInstanceExample.createCriteria()
				.andSystemUserIdEqualTo(userId).andGroupIdEqualTo(groupId);
		
		//进行查询
		List<UserRoleInstance> userRoleInstances = 
					userRoleInstanceMapper.selectByExample(userRoleInstanceExample);
		
		//如果查询结果不为空
		if(CommonUtil.isNotNull(userRoleInstances)){
			return userRoleInstances.get(0).getInstId();
		}
		
		return null;
	}
	
	/**通过用户身份证号查询member表，获取实例*/
	private MemBer getMember(String userIdCard){
		MemBerExample example = new MemBerExample();
		example.createCriteria().andMemberIdEqualTo(userIdCard);
		
		if(memBerMapper.selectByExample(example).isEmpty()){
			return null;
		}
		return memBerMapper.selectByExample(example).get(0);
	}
	
	/**通过权限资源类型名称，获取对应权限资源CD*/
	private String getPrivilegeResType(String resTypeName){
		PrivilegeResTypeExample example = new PrivilegeResTypeExample();
		example.createCriteria().andResTypeNameEqualTo(resTypeName);
		if(privilegeResTypeMapper.selectByExample(example).isEmpty()){
			return null;
		}else{
			return privilegeResTypeMapper
					.selectByExample(example).get(0).getResTypeCd();
		}
	}

}
