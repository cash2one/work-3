package com.trust.privilege.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.trust.privilege.dao.GroupRoleMapper;
import com.trust.privilege.dao.MenuRoleMapper;
import com.trust.privilege.dao.RoleMapper;
import com.trust.privilege.dao.RolePrivilegeResMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.UserRoleInstanceMapper;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.RoleComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.model.GroupRoleExample;
import com.trust.privilege.model.MenuRoleExample;
import com.trust.privilege.model.Role;
import com.trust.privilege.model.RoleExample;
import com.trust.privilege.model.RolePrivilegeRes;
import com.trust.privilege.model.RolePrivilegeResExample;
import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.UserRoleInstanceExample;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.model.complex.QueryRole;
import com.trust.privilege.model.complex.RoleDo;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.util.CommonUtil;
/**
 * @ClassName: RoleServiceImpl 
 * @Description: 角色Service接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 上午10:28:40
 */
@Transactional
public class RoleServiceImpl implements RoleService{
	
	/**依赖注入角色Mapper*/
	@Resource
	private RoleMapper roleMapper;
	
	/**依赖注入系统Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**依赖注入用户组角色Mapper*/
	@Resource
	private GroupRoleMapper groupRoleMapper;
	
	/**依赖注入菜单角色Mapper*/
	@Resource
	private MenuRoleMapper menuRoleMapper;
	
	/**依赖注入角色资源Mapper*/
	@Resource
	private RolePrivilegeResMapper rolePrivilegeResMapper;
	
	/**依赖注入角色用户Mapper*/
	@Resource
	private UserRoleInstanceMapper userRoleInstanceMapper;
	
	/**依赖注入角色dao*/
	@Resource
	private RoleComplexMapper roleComplexMapper;
	
	/**依赖注入用户dao*/
	@Resource
	private SysUserDao sysUserDao;
	
	/**依赖注入日志dao*/
	@Resource
	private LogMapper logMapper;
	
	/**
	 * @Title: validateSuperByUserName 
	 * @Description: 判断用户是否拥有超级管理员
	 * @param @param userName
	 * @param @return   
	 * @return boolean
	 */	
	@Override
	public boolean valdiateSuper(String userName) {
		//如果参数不为空
		if(CommonUtil.isNotNull(userName)){
			//创建map
			Map<String, Object> reuseMap = new HashMap<>();
			//加入条件
			reuseMap.put("userName", userName);
				
			//通过用户名和系统CD获取该系统下的该用户所拥有的角色
			Set<String> roleNames = this.selectRole4UserName(reuseMap);
			
			//循环获取到的角色名
			for (String roleName : roleNames) {
				//如果该用户拥有超级管理员角色
				if(roleName.contains("超级管理员")){
					return true ;
				}
			}
		}
		return false ;
	}
	
	/**
	 * @Title: getAllRoles 
	 * @Description: 根据条件查询角色信息
	 * @param @param queryRoleVO
	 * @param @param user
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllRoles(QueryRole queryRole) {
		//创建复用map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//存放查询到的角色
		List<Map<String,Object>> roles = new ArrayList<>();
		
		//获取用户名
		String userName= queryRole.getUserName();
		
		//获取多个系统CD
		String systemPlatFormCDs = queryRole.getSystemPlatformCd();
				
		//如果系统CD不为空
		if(CommonUtil.isNotNull(systemPlatFormCDs)){
			//将字符串转换成集合
			List<String> systemCDs = CommonUtil.splitStrToList(systemPlatFormCDs);
			//设置查询的系统CD集合，用于查询用户拥有的角色
			reuseMap.put("sysCdlist", systemCDs);
		}
		
		//设置用户名
		reuseMap.put("userName", userName);
		//设置查询的角色名
		reuseMap.put("roleName",queryRole.getRoleName());
		
		//设置查询的系统名称
		reuseMap.put("systemPlatformName", queryRole.getSystemPlatformName());
		
		//通过用户名校验如果该用户为超级管理员
		if(valdiateSuper(userName)){
			//超级管理员拥有最大权限
			roles = roleComplexMapper.getAllRolesByCreateUserId(reuseMap);
		}else{
			//如果不是超级管理员，只可以看见激活状态下的角色
			reuseMap.put("stateCd", "1");
			//获取该用户所属用户组中拥有的角色
			roles = roleComplexMapper.getAllRoles(reuseMap);
			//获取该用户创建的角色
			roles.addAll(roleComplexMapper.selectUserCreateRoleByUserName(reuseMap));
		}
		
		//清下
		reuseMap.clear();
		//放入返回数据
		reuseMap.put("list",roles);
		//返回结果
		return reuseMap;
	}

	/**
	 * @Title: insertRole 
	 * @Description: 新增角色信息  
	 * @param @param role
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertRole(RoleDo role) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		//获取角色名
		String roleName = role.getRoleName();
		//获取角色状态
		String stateCd = role.getStateCd();
		//获取系统CD
		String systemPlatfromCD = role.getSystemPlatformCd();
		//获取新建角色时为该角色分配的菜单id
		String menuIds = role.getMenuIds();
		//如果角色名不为空
		if(CommonUtil.isNotNull(roleName)){
			//通过角色名称和系统CD查询该数据不为空
			if(CommonUtil.isNotNull(getRoleByExample(roleName, systemPlatfromCD))){
				//说明名称肯定存在了，设置返回信息
				reuseMap.put("fail", "所属系统下角色名称重复,请重复填写");
				//直接返回
				return reuseMap;
			}
			
			//如果该角色不存在，那么设置角色id
			String roleId = CommonUtil.getUUID();	
			//设置主键
			role.setRoleId(roleId);
			//设置创建时间
			role.setCreateDt(new Date());
			//如果插入角色成功
			roleMapper.insertSelective(role);
					
			//如果创建角色的时候为该角色分配了菜单和资源
			if(CommonUtil.isNotNull(menuIds)){
				//那么为角色插入菜单
				roleComplexMapper.addManyMenuToRole(
							CommonUtil.splitStrToList(menuIds), roleId, stateCd);
				//如果选择菜单下的资源
				if(CommonUtil.isNotNull(role.getResMap())){
					//为角色插入多个资源及操作类型
					insertRoleRes(role.getResMap(), roleId,stateCd);
				}
			}
						
			//通过创建人姓名获取该ID
			String userId = sysUserDao.selectUserIdByUserName( role.getCreateUserId());
			//角色与创建人做关联
			insertUserRoleInstance(userId, roleId, stateCd);
			
			//通过系统CD获取该系统名称
			String systemPlatformName = systemPlatformMapper.
							selectByPrimaryKey(systemPlatfromCD).getSystemPlatformName();
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"角色", role.getCreateUserId(), roleName, systemPlatformName, 
						"insert", new Date(), "新增["+roleName+"]角色");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);	
			
			//设置返回信息
			reuseMap.put("success","新增角色成功");
		}
		return reuseMap;
	}
	
	/**
	 * @Title: deleteRoleByRoleId 
	 * @Description: 通过角色id删除角色数据
	 * @param @param roleId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteRoleByRoleId(String roleId,String userName) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//通过角色Id得到该角色数据
		Role DBRole = getRoleByRoleId(roleId);
		//如果查询到数据
		if(CommonUtil.isNotNull(DBRole)){
			//创建角色菜单Examle对象
			MenuRoleExample menuRoleExample = new MenuRoleExample();
			//设置条件
			menuRoleExample.createCriteria().andRoleIdEqualTo(roleId);
			//如果角色下有菜单
			if(menuRoleMapper.countByExample(menuRoleExample) > 0 ){
				reuseMap.put("fail", "角色下有菜单,不可删除");
				return reuseMap;
			}
			//创建角色资源Example对象
			RolePrivilegeResExample privilegeRoleExample = new RolePrivilegeResExample();
			//设置条件
			privilegeRoleExample.createCriteria().andRoleIdEqualTo(roleId);
			//如果角色下有资源
			if(rolePrivilegeResMapper.countByExample(privilegeRoleExample) > 0){
				reuseMap.put("fail", "角色下有资源,不可删除");
				return reuseMap;
			}
			
			//通过系统CD得到该系统名
			String systemPlatformName =systemPlatformMapper.selectByPrimaryKey(
									DBRole.getSystemPlatformCd()).getSystemPlatformName();
			
			//获取角色用户组Example对象
			GroupRoleExample groupRoleExample = new GroupRoleExample();
			//设置条件
			groupRoleExample.createCriteria().andRoleIdEqualTo(roleId);
			//在删除角色的时候，要将角色用户组的关系先删除
			groupRoleMapper.deleteByExample(groupRoleExample);
			
			//创建用户角色实例表Example
			UserRoleInstanceExample userRoleInstanceExample = new UserRoleInstanceExample();
			//设置条件
			userRoleInstanceExample.createCriteria().andRoleIdEqualTo(roleId);
			//删除数据
			userRoleInstanceMapper.deleteByExample(userRoleInstanceExample);
			
			//在删除角色前，进行日志记录
			LogDetail logDetail = new LogDetail(
					CommonUtil.getUUID(), "角色", userName, DBRole.getRoleName(), 
							systemPlatformName, "delete", new Date(), "删除了["+DBRole.getRoleName()+"]角色");
			
			//删除角色
			roleMapper.deleteByPrimaryKey(roleId);
			//插入日志信息
			logMapper.insertLog(logDetail);
			//设置返回信息
			reuseMap.put("success", "删除角色成功");
		}
			return reuseMap;
	}

	/**
	 * @Title: getAllResourceByRoleId 
	 * @Description: 获取当前角色下的所有菜单和页面资源(预修改角色)
	 * @param @param roleId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllResourceByRoleId(String roleId) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(roleId)){
			//用来存储菜单id
			List<String> menuIds = new ArrayList<String>();
			
			//根据角色id得到该角色拥有的菜单
			List<Map<String,Object>> menuMaps = roleComplexMapper.getAllMemusByRoleId(roleId);
			//根据角色id获取该角色拥有的资源
			List<Map<String,Object>> privileges = roleComplexMapper.getAllPrivilegeResByRoleId(roleId);
			
			//循环菜单Map
			for (Map<String, Object> menuMap : menuMaps) {
				//获取每个菜单的id
				menuIds.add((String)menuMap.get("menuId"));
			}
			//通过角色id获取该对象
			Role dbRole = getRoleByRoleId(roleId);
			
			//设置返回Map
			reuseMap.put("roleName",dbRole.getRoleName());
			reuseMap.put("roleDesc",dbRole.getRoleDesc());
			reuseMap.put("stateCd",dbRole.getStateCd());
			reuseMap.put("memuIdsList",menuIds);
			reuseMap.put("resIdsList",privileges);
		}
		
		//返回数据
		return reuseMap;
	}
	
	/**
	 * @Title: updateRole 
	 * @Description: 修改角色
	 * @param @param roleDo
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateRole(RoleDo roleDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取角色id
		String roleId = roleDo.getRoleId();
		//如果角色ID不为空
		if(CommonUtil.isNotNull(roleId)){
			//获取角色名称
			String roleName = roleDo.getRoleName();
			//获取该角色的菜单
			String menuIds = roleDo.getMenuIds();
			//获取该角色的资源
			Set<String> resMap = roleDo.getResMap();			
			//获取状态
			String stateCD = roleDo.getStateCd();
			//通过主键获取该角色数据
			Role dbRole = roleMapper.selectByPrimaryKey(roleId);
			//获取系统CD
			String systemPlatformCD = dbRole.getSystemPlatformCd();
			//默认状态为正常
			if(CommonUtil.isNull(stateCD)){
				roleDo.setStateCd("1");
			}
			
			//如果角色名不为空
			if(CommonUtil.isNotNull(roleName)){
				//通过角色名称和系统CD查询该数据不为空
				if(CommonUtil.isNotNull(getRoleByExample(roleName, systemPlatformCD))){
					//说明名称肯定存在了，设置返回信息
					reuseMap.put("fail", "角色名称已存在,请重复填写");
					//直接返回
					return reuseMap;
				}
			}
			//通过系统CD得到该系统名称
			String systemPlatformName = systemPlatformMapper.
							selectByPrimaryKey(systemPlatformCD).getSystemPlatformName();
			
			//因为是可以多菜单选择的，那么数量就可能发生变化，所以修改角色之前，先将角色和菜单做下解绑
			deleteMenuRoleData(roleId);
			//接着与资源做下解绑
			deleteRolePrivilegeResData(roleId);
			
			//接下来为该角色重新添加菜单
			if(CommonUtil.isNotNull(menuIds)){
				roleComplexMapper.addManyMenuToRole( CommonUtil.splitStrToList(menuIds), roleId, roleDo.getStateCd());
			}
			
			//为该角色重新添加资源,以及类型
			if(CommonUtil.isNotNull(resMap)){							
				insertRoleRes(resMap, roleId,stateCD);
			}
			
			//修改角色,设置修改时间
			roleDo.setUpdateDt(new Date());
			//如果修改成功
			roleMapper.updateByPrimaryKeySelective(roleDo);
			
			//如果角色名为空
			if(CommonUtil.isNotNull(dbRole)){
				roleName = dbRole.getRoleName();
			}
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"角色", roleDo.getCreateUserId(), roleName, systemPlatformName, 
						"update", new Date(), "修改["+roleName+"]角色");		
					
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);					
			
			reuseMap.put("success","修改角色成功");
		}
		return reuseMap;
	}
	
	/**
	 * @Title: selectRole4UserName 
	 * @Description:通过用户名和系统CD获取该用户在该系统所拥有的所有角色
	 * @param @param queryMap
	 * @param @return   
	 * @return Set<String>
	 */
	@Override
	public Set<String> selectRole4UserName(Map<String,Object> queryMap) {
		Set<String> roleNames = new HashSet<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(queryMap)){
			//获取该用户在本系统中拥有的用户组中的角色
			List<Map<String,Object>> roles = roleComplexMapper.selectRoleByUserName(queryMap);
			//获取该用户创建的角色,并加入集合中
			roles.addAll(roleComplexMapper.selectUserCreateRoleByUserName(queryMap));
			//获取每一个roleName
			for (Map<String, Object> role : roles) {
				roleNames.add((String)role.get("roleName"));
			}	
		}
		
		//返回
		return roleNames ;
	}
	
	/**
	 * @Title: getRoleByRoleId 
	 * @Description: 根据主键查询该角色信息
	 * @param @param roleId
	 * @param @return   
	 * @return Role
	 */
	@Override
	public Role getRoleByRoleId(String roleId) {
		//如果参数不为空
		if(CommonUtil.isNotNull(roleId)){
			return roleMapper.selectByPrimaryKey(roleId);
		}
		return null;
	}
	
	/**
	 * @Title: deleteMenuRoleData 
	 * @Description: 删除角色与菜单关系
	 * @param @param roleId
	 * @param @return   
	 * @return boolean
	 */
	private boolean deleteMenuRoleData(String roleId){
		//创建菜单角色Example
		MenuRoleExample menuRoelExample = new MenuRoleExample();
		//设置条件
		menuRoelExample.createCriteria().andRoleIdEqualTo(roleId);
		//如果删除成功
		if(menuRoleMapper.deleteByExample(menuRoelExample) > 0){
			return true ;
		}
		return false;
	}
	
	/**
	 * @Title: deleteRolePrivilegeResData 
	 * @Description: 删除角色与资源关系
	 * @param @param roleId
	 * @param @return   
	 * @return boolean
	 */
	private boolean deleteRolePrivilegeResData(String roleId){
		//创建角色资源Example
		RolePrivilegeResExample rolePrivilegeResExample = new RolePrivilegeResExample();
		//设置条件
		rolePrivilegeResExample.createCriteria().andRoleIdEqualTo(roleId);
		//如果删除成功
		if(rolePrivilegeResMapper.deleteByExample(rolePrivilegeResExample) > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: getRoleByExample 
	 * @Description: 通过条件获取数据库中的角色数据
	 * @param @param roleName
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return List<Role>
	 */
	public List<Role> getRoleByExample(String roleName,String systemPlatformCD){
		//创建Examle
		RoleExample roleExample = new RoleExample();
		//如果角色名为空
		if(CommonUtil.isNotNull(roleName)){
			roleExample.createCriteria().andRoleNameEqualTo(roleName);
		}
		//如果系统CD不为空
		if(CommonUtil.isNotNull(systemPlatformCD)){
			roleExample.createCriteria().andSystemPlatformCdEqualTo(systemPlatformCD);
		}
		
		//进行查询
		return roleMapper.selectByExample(roleExample);
	}
	
	/**
	 * @Title: insertUserRoleInstance 
	 * @Description: 插入用户角色实例
	 * @param @param userId
	 * @param @param roleId
	 * @param @param stateCd
	 * @param @return   
	 * @return boolean
	 */
	private boolean insertUserRoleInstance(String userId,String roleId,String stateCd){
		//创建用户角色表实例
		UserRoleInstance userRoleInstance1 = new UserRoleInstance();  
		//设置id
		userRoleInstance1.setInstId(CommonUtil.getUUID());
		//设置用户id
		userRoleInstance1.setSystemUserId(userId);
		//设置角色id
		userRoleInstance1.setRoleId(roleId);
		//设置状态
		userRoleInstance1.setStateCd(stateCd);
		//设置创建时间
		userRoleInstance1.setCreateDt(new Date());
		//如果添加成功
		if(userRoleInstanceMapper.insertSelective(userRoleInstance1) > 0){
			return true ;
		}
		return false;
	}
	
	/**
	 * @Title: insertRoleRes 
	 * @Description: 为角色插入多个资源及操作类型
	 * @param @param privilegeRes
	 * @param @param roleId
	 * @param @return   
	 * @return boolean
	 */
	private boolean insertRoleRes(Set<String> privilegeRess,String roleId,String stateCd){
		//设置默认返回值
		boolean result = false ;
		//如果参数不为空
		if(CommonUtil.isNotNull(privilegeRess) && CommonUtil.isNotNull(roleId)){
			//循环取值
			for(String privilegeRes : privilegeRess){
				//如果资源不为空，并且传值正确
				if(CommonUtil.isNotNull(privilegeRes)){
					//做下处理，前台传递 Map，为了key不重复，所以是资源Id+资源类型
					String privilegeResourceId = privilegeRes.substring(0, privilegeRes.length()-1);
					String stateType = privilegeRes.substring(privilegeRes.length()-1);
					//为了应付资源类型未选择
					String undefined = privilegeRes.substring(privilegeRes.length()-9);
					
					//前台无法做逻辑，后台这边处理下，防范于用户点击新增资源而没有进行填写其值
					if(privilegeResourceId.equals("undefined")){
						continue;
					}
					//如果资源类型没选择
					if("undefined".equals(undefined)){
						//那么类型默认为可见
						stateType = "1";
						//资源id处理下
						privilegeResourceId = privilegeRes.replace("undefined", "");
					}
					
					//创建资源对象
					RolePrivilegeRes rolePrivilegeRes = new RolePrivilegeRes();
					//设置创建时间
					rolePrivilegeRes.setCreateDt(new Date());
					//设置角色id
					rolePrivilegeRes.setRoleId(roleId);
					//设置资源id
					rolePrivilegeRes.setPrivilegeResId(privilegeResourceId);
					//设置状态
					rolePrivilegeRes.setStateCd(stateCd);
					//设置资源类型
					rolePrivilegeRes.setStateType(stateType);
					//插入数据
					rolePrivilegeResMapper.insertSelective(rolePrivilegeRes);
					result = true;
				}
			}
		}
			return result ;
	}
	
}
