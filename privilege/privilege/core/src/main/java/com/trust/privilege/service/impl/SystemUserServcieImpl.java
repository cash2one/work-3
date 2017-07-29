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

import com.trust.privilege.dao.MemBerMapper;
import com.trust.privilege.dao.SystemUserMapper;
import com.trust.privilege.dao.UserRoleInstanceMapper;
import com.trust.privilege.dao.complexMapper.GroupDao;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.MenuComplexMapper;
import com.trust.privilege.dao.complexMapper.RoleComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.dao.complexMapper.UserRoleInstanceDao;
import com.trust.privilege.facade.model.SysUserModel;
import com.trust.privilege.facade.model.SysUserQuaryVo;
import com.trust.privilege.model.MemBer;
import com.trust.privilege.model.MemBerExample;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.model.SystemUserExample;
import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.UserRoleInstanceExample;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.model.complex.MenuDo;
import com.trust.privilege.model.complex.UserDo;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.service.SystemUserServcie;
import com.trust.privilege.util.CommonUtil;

/**
 * @ClassName: SystemUserRestFacade 
 * @Description: 用户模块Service接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月17日 下午2:33:09
 */
@Transactional
public class SystemUserServcieImpl implements SystemUserServcie {
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**依赖注入用户Mapper*/
	@Resource
	private SystemUserMapper systemUserMapper;
	
	/**依赖注入成员Mapper*/
	@Resource
	private MemBerMapper memberMapper;
	
	/**依赖注入角色Mapper*/
	@Resource
	private RoleComplexMapper roleComplexMapper;
	
	/**依赖注入用户角色实例Mapper*/
	@Resource
	private UserRoleInstanceMapper userRoleInstanceMapper;
	
	/**依赖注入用户Mapper*/
	@Resource
	private SystemUserMapper userMapper;
	
	/**依赖注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**依赖注入用户角色实例dao*/
	@Resource
	private UserRoleInstanceDao userRoleInstanceDao;
	
	/**依赖注入用户dao*/
	@Resource
	private SysUserDao sysUserDao;
	
	/**依赖注入用户组dao*/
	@Resource
	private GroupDao groupDao;
	
	/**依赖注入菜单dao*/
	@Resource
	private MenuComplexMapper menuDao;
	
	/**
	 * @Title: selectUser 
	 * @Description: 按条件查询用户信息
	 * @param @param quaryVo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
 	public Map<String, Object> selectUser(SysUserQuaryVo queryVo) {
		//创建复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//用于存放结果
		List<UserDo> resultList = new ArrayList<>();
		
		//获取当前操作人
		String userName = queryVo.getUserName();
		//设置查询条件
		reuseMap.put("queryUserName", queryVo.getQueryUserName());
		reuseMap.put("userName", userName);
		
		
		//如果不是超级管理员
		if(!roleService.valdiateSuper(userName)){
			//用于存放用户Id
			List<String> userNames = new ArrayList<>();
			//查询所有用户				
			List<SystemUser> users = getUserByQuery(null, null);
			//根据操作人姓名查询子用户并将子用户添加到用户id集合中
			CommonUtil.getUserChilds(users, userName, userNames);
			//将操作人添加到用户集合中
			userNames.add(userName);
			//设置用户名集合
			reuseMap.put("list", userNames);
			
			resultList =sysUserDao.selectUser(reuseMap);
		}else{
			//超级管理员
			resultList =sysUserDao.selectUserBySuperAdmin(reuseMap);
		}
		//存放返回数据
		reuseMap.put("resultList", resultList);
		//返回结果
		return reuseMap;
	}
	
	/**
	 * @Title: insertUser 
	 * @Description: 新增用户信息
	 * @param @param sysUserModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertUser(UserDo sysUserDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(sysUserDo.getUserName())){
			//获取用户名
			String userName = sysUserDo.getUserName();
			//获取用户组Id
			String groupId = sysUserDo.getGroupId();
			//校验参数
			String errorStr = validateQuery(sysUserDo);
			
			//如果参数不为空
			if(CommonUtil.isNotNull(errorStr)){
				//加上一句
				reuseMap.put("fail", errorStr);
				//返回错误信息
				return reuseMap;
			}
			
			//设置用户Id
			sysUserDo.setSystemUserId(CommonUtil.getUUID());
			//设置创建时间
			sysUserDo.setCreateDt(new Date());
			//插入该用户信息
			systemUserMapper.insertSelective(sysUserDo);
			
			//向用户角色实例表插入数据
			insertUserRoleInstance(groupId, sysUserDo.getSystemUserId());
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"用户", sysUserDo.getCreateUserId(), userName, null, 
						"insert", new Date(), "新增["+userName+"]用户");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);			
			
			reuseMap.put("success","新增用户信息成功");
		}
			return reuseMap ;
		
	}
		
	/**
	 * @Title: deleteUserById 
	 * @Description: 根据用户Id删除该用户信息
	 * @param @param sysUserId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteUserById(String sysUserId, String userName) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		//如果参数不为空
		if(CommonUtil.isNotNull(sysUserId)){
			//创建用户角色实例Example对象
			UserRoleInstanceExample example1 = new UserRoleInstanceExample();
			//设置条件
			example1.createCriteria().andSystemUserIdEqualTo(sysUserId);
			//获取该数据库中用户名
			String resourceName = systemUserMapper.selectByPrimaryKey(sysUserId).getUserName();
			
			//通过用户ID删除中间表中有此用户关联的数据
			userRoleInstanceMapper.deleteByExample(example1);
			
			//通过用户Id删除该用户信息
			systemUserMapper.deleteByPrimaryKey(sysUserId);
			
			//删除成功，将此操作记录插入数据库日志表中
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), "用户", userName, resourceName, 
					null, "delete", new Date(), "删除["+resourceName+"]用户");
			
			//插入日志表数据
			logMapper.insertLog(logDetail);
			//设置返回信息
			reuseMap.put("success","删除该用户成功");
		}
			return reuseMap;
	}
	
	/**
	 * @Title: selectUpdDis 
	 * @Description: 修改用户时查询所需的数据（预修改）
	 * @param @param userId
	 * @param @param userName
	 * @param @return  
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectUpdDis(String userId,String userName) {
		//创建复用Map
		Map<String,Object> reuseMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(userId)){
			//用来存储可操作角色集合
			List<Map<String, Object>> roles = new ArrayList<>();
			//用来存储用户可操作的角色id
			Set<String> userRoleIds = new HashSet<>();
			//用来存储用户所属用户组
			Set<String> userGroups = new HashSet<>();
			
			//通过用户ID得到用户名
			String dbUserName = systemUserMapper.selectByPrimaryKey(userId).getUserName();
			
			//通过用户ID得到该用户详情
			List<Map<String,Object>> userGroupDetails = sysUserDao.selectUserDetail(userId);
			
			//循环取值
			for (Map<String, Object> userGroupDetail : userGroupDetails) {
				//获取每个角色
				userRoleIds.add((String)userGroupDetail.get("Role_Id"));
				//获取每个用户组
				userGroups.add((String)userGroupDetail.get("groupId"));
			}
			
			//如果该用户不是超级管理员
			if(!roleService.valdiateSuper(userName)){
				//设置条件
				reuseMap.put("userName", dbUserName);
				//通过用户名得到该用户组中的角色
				roles = roleComplexMapper.selectRoleByUserName(reuseMap);
			}else{
				roles = roleComplexMapper.selectOptRoleBySuperAdmin(null);
			}
			//清下
			reuseMap.clear();
			//用户基本信息
			reuseMap.put("userInfo", selectUserDetail(userId).get(0));
			//用户可操作角色
			reuseMap.put("roles", roles);
			//用户所属用户组
			reuseMap.put("userGroups", userGroups);
			//用户所属角色
			reuseMap.put("userRoles", userRoleIds);
		}
		return reuseMap;
	}
	
	/**
	 * @Title: updateUser 
	 * @Description: 修改用户信息
	 * @param @param systemUserDo
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateUser(UserDo systemUserDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取用户Id
		String userId = systemUserDo.getSystemUserId();
		//如果参数不为空
		if(CommonUtil.isNotNull(userId)){
			//获取用户名
			String userName = systemUserDo.getUserName();
			//获取用户组id
			String groupId =  systemUserDo.getGroupId();
			
			//校验参数
			String errorStr = validateQuery(systemUserDo);
			
			//如果存在错误信息
			if(CommonUtil.isNotNull(errorStr)){
				//设置返回信息
				reuseMap.put("fail",errorStr);
				//返回错误信息
				return reuseMap;
			}
			
			//修改用户基本信息
			systemUserMapper.updateByPrimaryKeySelective(systemUserDo);
			//删除用户角色实例表中之前关于此用户的相关数据
			UserRoleInstanceExample example = new UserRoleInstanceExample();
			example.createCriteria().andSystemUserIdEqualTo(userId);
			userRoleInstanceMapper.deleteByExample(example);
			
			//然后插入修改之后的用户组数据
			insertUserRoleInstance(groupId,userId);
			
			//如果用户名为空
			if(CommonUtil.isNull(userName)){
				userName = getUserByQuery(userName,null).get(0).getUserName();
			}
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"用户", systemUserDo.getCreateUserId(), userName, null, 
						"update", new Date(), "修改["+userName+"]用户");		
					
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);		
			
			reuseMap.put("success","修改用户信息成功");
		}
		return reuseMap;
	}
	
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过id获取该用户详情
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectUserDetail(String userId) {
		//如果参数不为空
		if(CommonUtil.isNotNull(userId)){
			return sysUserDao.selectUserDetail(userId);
		}
		return null;
	}

	/**
	 * @Title: validateQuery 
	 * @Description: 校验参数
	 * @param @param userDo
	 * @param @return   
	 * @return String
	 */
	private String validateQuery(UserDo userDo){
		//用来存放错误信息
		StringBuffer sb = new StringBuffer();
		
		//获取用户名
		String userName = userDo.getUserName();
		//获取角色Id
		String roleId = userDo.getRoleId();
		//获取用户组Id
		String groupId = userDo.getGroupId();
		//获取身份证
		String idCard = userDo.getIdCard();
		//获取证件类型
		String memberType= userDo.getMemberType();		
		
		//通过用户名获取该用户信息
		List<SystemUser> dbUser = getUserByQuery(userName,null);
		
		//通过用户名获取该用户数据
		if(CommonUtil.isNotNull(userName) && CommonUtil.isNotNull(dbUser)){
			//既然查到数据，说明用户名重复了
			sb.append("该用户名已经存在,");
		}		
		
		//如果用户组或者角色为空
		if(CommonUtil.isNull(groupId) || CommonUtil.isNull(roleId)){
			sb.append("用户组或者角色为空,");
		}		
		
		//如果填写的身份证号为空
		if(CommonUtil.isNull(idCard)){
			sb.append("证件号码不可为空,");
		}
		
		//如果数据库中证件号不存在
		if(CommonUtil.isNull(getMemberByQuery(idCard,null))){
			sb.append("所填证件号码不存在,");
		}
		
		//如果证件类型与数据库中的证件类型不符
		if(CommonUtil.isNull(getMemberByQuery(null,memberType))){
			sb.append("证件号码与证件类型不匹配或不存在，联系管理员,");
		}	
		
		//如果存在错误信息
		if(CommonUtil.isNotNull(sb.toString())){
			//返回错误信息
			sb.append("请重新填写!");
		}		
		return sb.toString() ;
	}
	
	/**
	 * @Title: getUserByQuery 
	 * @Description: 通过条件获取该用户信息(Mybatis逆向工程)
	 * @param @param userName
	 * @param @param password
	 * @param @return   
	 * @return List<SystemUser>
	 */
	public List<SystemUser> getUserByQuery(String userName,String password){
		//创建Example对象
		SystemUserExample userExample = new SystemUserExample();
		//如果用户名不为空
		if(CommonUtil.isNotNull(userName)){
			//如果密码不为空
			if(CommonUtil.isNotNull(password)){
				//设置条件
				userExample.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password);
			}else{
				//设置条件
				userExample.createCriteria().andUserNameEqualTo(userName);
			}
		}
		//进行查询，并返回
		return systemUserMapper.selectByExample(userExample);
	}
	
	/**
	 * @Title: getMemberByQuery 
	 * @Description: 通过条件获取成员表信息(Mybatis逆向工程)
	 * @param @param idCard
	 * @param @param memberType
	 * @param @return   
	 * @return List<MemBer>
	 */
	private List<MemBer> getMemberByQuery(String idCard,String memberType){
		//创建Example对象
		MemBerExample memberExample = new MemBerExample();
		//如果证件号不为空
		if(CommonUtil.isNotNull(idCard)){
			//设置条件
			memberExample.createCriteria().andMemberIdEqualTo(idCard);
		}
		//如果成员类型不为空
		if(CommonUtil.isNotNull(memberType)){
			//设置条件
			memberExample.createCriteria().andMemberTypeEqualTo(memberType);
		}
		
		//进行查询，并返回
		return memberMapper.selectByExample(memberExample);
	}
	
	/**
	 * @Title: insertUserRoleInstance 
	 * @Description: 插入用户角色实例表数据
	 * @param @param groupId
	 * @param @param userId   
	 * @return void
	 */
	private void insertUserRoleInstance(String groupId,String userId){
		//创建用户角色实例model
		UserRoleInstance userRoleInstance = new UserRoleInstance();
		//设置主键
		userRoleInstance.setInstId(CommonUtil.getUUID());
		//设置用户组Id
		userRoleInstance.setGroupId(groupId);
		//默认状态为激活状态
		userRoleInstance.setStateCd("1");
		//设置用户Id
		userRoleInstance.setSystemUserId(userId);
		//设置创建时间
		userRoleInstance.setCreateDt(new Date());
		//在用户角色实例表中插入数据
		userRoleInstanceMapper.insertSelective(userRoleInstance);
	}

//-----------------------------------------------------------------
	/**
	 * @Title: getInitMsg 
	 * @Description: 通过条件取门户首页初始化数据（将系统配置成资源，用作portal页面）
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */	
	@Override
	public List<MenuDo> getInitMsg(String userName, String systemId) {
		//创建返回集合
		List<MenuDo> menus = new ArrayList<>();
		//如果参数不为空
		if(CommonUtil.isNotNull(systemId)){
			//通过当前操作人和系统获取该系统拥有的菜单
			//如果查询结果不为空
			if(CommonUtil.isNotNull(menuDao.selectSysMenuByUserName(userName, systemId))){
				menus =  menuDao.selectSysMenuByUserName(userName, systemId);
			}
		}
		return menus;
	}		

	/**
	 * @Title: getUserInfo 
	 * @Description: portal页面，左侧获取个人信息的接口
	 * @param @return   
	 * @return UserDo
	 */
	@Override
	public UserDo getUserInfo(String userName) {
		//创建返回对象
		UserDo user = new UserDo();
		//如果参数不为空
		if(CommonUtil.isNotNull(userName)){
			//通过用户名获取该用户基本信息
			List<UserDo> users = sysUserDao.selectUserInfo(userName);
			//如果查询不为空
			if(CommonUtil.isNotNull(users)){
				user = users.get(0);
			}
		}
			return user;
	}
	
	/**
	 * @Title: modifyPassword 
	 * @Description: portal页面根据用户名修改密码
	 * @param @param sysUserModel
	 * @param @return   
	 * @return String
	 */
	@Override
	public String modifyPassword(SysUserModel sysUserModel ){
		//设置默认信息
		String result = "修改密码失败！";
		//获取用户名
		String userName = sysUserModel.getUserName();
		//获取原密码
		String oldPassowrd =  CommonUtil.generateMD5(sysUserModel.getPassword());
		//获取新密码
		String newPassword = CommonUtil.generateMD5(sysUserModel.getNewPassword());
		
		//如果该数据存在于数据库
		if(CommonUtil.isNotNull(getUserByQuery(userName,oldPassowrd))){
			//加密新密码,并修改该用户信息,如果成功
			if(sysUserDao.modifyPassWord(userName, newPassword) > 0){
				result = "修改密码成功！";
			}
		}
		return result ;
	}
			
}
