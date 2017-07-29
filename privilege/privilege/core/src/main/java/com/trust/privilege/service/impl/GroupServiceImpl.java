package com.trust.privilege.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.trust.privilege.dao.GroupRoleMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.SystemUserMapper;
import com.trust.privilege.dao.UserGroupMapper;
import com.trust.privilege.dao.UserRoleInstanceMapper;
import com.trust.privilege.dao.complexMapper.GroupDao;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.RoleComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.dao.complexMapper.SystemPlatformComplexMapper;
import com.trust.privilege.dao.complexMapper.UserRoleInstanceDao;
import com.trust.privilege.facade.model.GroupQueryVo;
import com.trust.privilege.model.GroupRole;
import com.trust.privilege.model.GroupRoleExample;
import com.trust.privilege.model.UserGroup;
import com.trust.privilege.model.UserGroupExample;
import com.trust.privilege.model.UserRoleInstance;
import com.trust.privilege.model.complex.GroupDo;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.service.GroupService;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.util.CommonUtil;
/**
 * @ClassName: GroupRestFacade 
 * @Description: 用户组模块Service接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月20日 下午7:53:17
 */
@Transactional
public class GroupServiceImpl implements GroupService {
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**依赖注入用户组dao*/
	@Resource
	private GroupDao groupDao;
	
	/**依赖注入角色dao*/
	@Resource
	private RoleComplexMapper roleComplexMapper;
	
	/**依赖注入系统Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**依赖注入用户Mapper*/
	@Resource
	private SystemUserMapper systemUserMapper;
	
	/**依赖注入用户组Mapper*/
	@Resource
	private UserGroupMapper userGroupMapper;
	
	/**依赖注入用户角色Mapper*/
	@Resource
	private UserRoleInstanceMapper userRoleInstanceMapper;
	
	/**依赖注入用户角色Mapper*/
	@Resource
	private GroupRoleMapper groupRoleMapper;
	
	/**依赖注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**依赖注入用户dao*/
	@Resource
	private SysUserDao sysUserDao;

	/**依赖注入用户角色实例dao*/
	@Resource
	private UserRoleInstanceDao userRoleInstanceDao;
	
	/**依赖注入系统dao*/
	@Resource
	private SystemPlatformComplexMapper systemPlatformComplexMapper;
	
	/**
	 * @Title: selectGroup 
	 * @Description: 根据条件查询用户组
	 * @param @param vo
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectGroup(GroupQueryVo groupQuaryVo) {
		//创建返回对象
		Map<String,Object> reuseMap = new HashMap<>();
		//获取用户名
		String userName = groupQuaryVo.getUserName();
		//设置状态
		reuseMap.put("state", groupQuaryVo.getState());
		//设置用户组名
		reuseMap.put("groupName", groupQuaryVo.getGroupName());
		reuseMap.put("userName", userName);
		//声明结果集
		List<Map<String, Object>> resultList = null ;

		//如果不是超级管理员
		if(!roleService.valdiateSuper(userName)){
			//通过当前操作人，获取该拥有的用户组
			resultList = groupDao.selectGroupByUserName(reuseMap);
		}else{
			//超级管理员,那么查询所有用户组
			resultList = groupDao.selectUserGroupBySuperAdmin(reuseMap);
		}
			
		//清下
		reuseMap.clear();
		//设置返回结果结果
		reuseMap.put("resultList", resultList);
		//返回结果
		return reuseMap;
	}

	/**
	 * @Title: selectGroupDetail 
	 * @Description: 查看用户组详情
	 * @param @param groupid
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectGroupDetail(String groupId) {
		//创建返回Map
		Map<String,Object> resultMap = new HashMap<>();
		
		//创建承载Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取用户组所拥有的用户
		List<String> users = groupDao.selectUserInGroup(groupId);
		//用来承载用户组所拥有的角色
		List<String> groupRoleNames = new ArrayList<>();
		
		//通过用户组Id查询该用户组详情
		List<Map<String,Object>> groupDetails = groupDao.selectGroupDetail(groupId);
		//如果查询结果不为空
		if(CommonUtil.isNotNull(groupDetails)){
			//获取该用户组信息
			reuseMap = groupDetails.get(0);
			//得到这些用户组所拥有的角色
			for (Map<String, Object> groupDetail : groupDetails) {
				//获取这些用户组的每一个角色
				groupRoleNames.add((String)groupDetail.get("Role_Name"));
			}
		}
		
		//设置返回数据
		resultMap.put("detail", reuseMap);
		resultMap.put("groupRoles", groupRoleNames);
		resultMap.put("users", users);
		
		return resultMap;
	}
	
	/**
	 * @Title: selectOptGroup 
	 * @Description: 查询用户可选用户组								
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectOptGroup(String userName) {
		//创建返回值
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		Map<String, Object> reuseMap = new HashMap<>();
		//设置状态
		reuseMap.put("state", "1");
		reuseMap.put("userName", userName);
		
		//如果该用户不拥有超级管理员角色
		if(!roleService.valdiateSuper(userName)){
			//获取所有用户
			resultList = groupDao.selectGroupByUserName(reuseMap);
		}else{
			resultList = groupDao.selectOptGroupBySuperAdmin();
		}
			return resultList;
	}
	
	/**
	 * @Title: selectSys 
	 * @Description: 查询用户组所拥有系统
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectSys(String userName) {
		//创建返回集合
		List<Map<String, Object>> resultList = new ArrayList<>();
		//如果当前操作人为超级管理员
		if (roleService.valdiateSuper(userName)) {
			//超级管理员得到所有的系统列表
			resultList = systemPlatformComplexMapper.selectAllSys();
		} else {
			//否则通过该用户名查询所拥有的系统
			resultList = systemPlatformComplexMapper.selectSysByUser(userName,null);
		}
			return resultList;
	}
	
	/**
	 * @Title: selectRoleFromGroup 
	 * @Description: 查询用户组拥有的角色
	 * @param @param groupId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> selectRoleFromGroup(String groupId) {
		//创建复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		//用来承载角色ID
		List<String> roleIds = new ArrayList<>();
		//通过用户组Id查询角色
		List<Map<String,Object>> roles = groupDao.selectRoleFromGroup(groupId);
		//获取到每一个角色id
		for (Map<String, Object> role : roles) {
			roleIds.add((String)role.get("roleId"));
		}
		
		reuseMap.put("result", roles);
		reuseMap.put("roleStr", roleIds);
		
		return reuseMap;
	}
	
	/**
	 * @Title: insertGroup 
	 * @Description: 添加用户组
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> insertGroup(GroupDo groupDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//用来存储上级用户组的角色
		List<Map<String, Object>> parentGroupRoles = new ArrayList<>();
		
		//用来存储所有角色id
		Set<String> groupRoleIds = new HashSet<>();
		
		//用来承载错误信息
		StringBuffer sb = new StringBuffer();
				
		//获取用户组名称
		String groupName = groupDo.getGroupName();
		
		//如果用户组为空
		if(CommonUtil.isNull(groupName)){
			//那么直接返回错误信息
			reuseMap.put("fail","用户组名为空,请重新填写！");
			//返回
			return reuseMap;
		}
		
		//获取上级用户组
		String parentGroupId = groupDo.getParentGroupId();
		
		//设置条件
		reuseMap.put("groupName", groupName);
		//通过用户组名称查询该用户组信息
		List<UserGroup> groups = getUserGroupByQuery(reuseMap);
		//用完清下
		reuseMap.clear();
		//将选择角色加入到角色集合中
		groupRoleIds.addAll(CommonUtil.splitStrToList( groupDo.getRoleId()));
		
		//如果用户组名查询结果不为空，说明已经存在了
		if(CommonUtil.isNotNull(groups) && CommonUtil.isNotNull(groupName)){
			sb.append("用户组名已存在,");
		}
		//如果存在错误信息
		if(CommonUtil.isNotNull(sb.toString())){
			reuseMap.put("fail", sb.append("请重新填写！").toString());
			//返回
			return reuseMap;
		}
		
		//如果上级用户组id不为空
		if(CommonUtil.isNotNull(parentGroupId)){
			parentGroupRoles = groupDao.selectRoleFromGroup(parentGroupId);
			//循环取值
			for (Map<String,Object> parentGroupRole : parentGroupRoles) {
				//得到没一个角色id
				groupRoleIds.add((String) parentGroupRole.get("roleId"));
			}
		}
		
		//设置用户组ID
		groupDo.setGroupId(CommonUtil.getUUID());
		//设置创建时间
		groupDo.setCreateDt(new Date());   
		
		//新增一个用户组
		userGroupMapper.insertSelective(groupDo);
		//给用户组添加角色
		addRoleToGroup(groupDo.getGroupId(), groupRoleIds);
		
		//通过操作人姓名得到Id
		String userId = sysUserDao.selectUserIdByUserName(groupDo.getCreateUserid());
		
		//将新创建的用户组与当前操作人建立联系
		insertRoleUserInstanceData(groupDo.getGroupId(), userId);
		
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"用户组", groupDo.getCreateUserid(), groupName, null, 
					"insert", new Date(), "新增["+groupName+"]用户组");
		
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);	
		
		//设置返回值
		reuseMap.put("success", "添加用户组成功!");
		
		return reuseMap ;
	}
	
	
	/**
	 * @Title: deleteGroup 
	 * @Description: 删除用户组
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteGroup(String groupId,String userName) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果用户组Id为空
		if(CommonUtil.isNull(groupId)){
			reuseMap.put("fail", "用户组Id不可为空");
			//返回数据
			return reuseMap;
		}
		
		//通过用户id查询数据库中的用户组信息
		UserGroup dbGroup = userGroupMapper.selectByPrimaryKey(groupId);
		
		//如果用户组下存在用户
		if(userRoleInstanceDao.countUserInGroup(groupId) > 0){
			reuseMap.put("fail", "用户组下存在用户,不可删除");
			//返回数据
			return reuseMap;
		}
		// 删除用户组所有的角色
		deleteRoleAndGroupData(groupId, null);
		//删除该用户组信息
		userGroupMapper.deleteByPrimaryKey(groupId);
		
		//创建日志对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), "用户组", userName,
				dbGroup.getGroupName(),null, "delete", new Date(), "删除["+dbGroup.getGroupName()+"]用户组");
		// 插入日志信息
		logMapper.insertLog(logDetail);
		reuseMap.put("success","删除用户组成功");
		return reuseMap;
	}
	
	
	/**
	 * @Title: groupUpdDis 
	 * @Description: 修改用户组时需要的数据(预修改)						
	 * @param @param groupId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> groupUpdDis(String groupId, String userName) {
		//创建复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果用户组Id为空
		if(CommonUtil.isNull(groupId)){
			reuseMap.put("fail", "用户组Id不可为空");
			//返回数据
			return reuseMap;
		}
		
		//用来存储用户组所拥有的角色
		List<String> groupRoles = new ArrayList<>();
		//用来存储用户组所拥有的系统CD
		List<String> groupSystemCDs = new ArrayList<>();
		//用来存储用户可操作的角色
		List<Map<String, Object>> roles= new ArrayList<>();
		//声明操作人员可操作的系统列表
		List<Map<String, Object>> systemPlatforms = new ArrayList<>() ;
		
		//通过用户名获取当前操作人可操作用户组列表				
		List<Map<String, Object>> groups = selectOptGroup(userName);
		
		//通过前台传值用户组Id获取用户组详情
		List<Map<String,Object>> dbGroupDetails = groupDao.selectGroupDetail(groupId);
		
		//通过前台传值用户组Id获取用户组所拥有的系统
		List<Map<String, Object>> dbGroupSystems = systemPlatformComplexMapper.selectSysByGroup(groupId);
				
		
		//获取该用户组所拥有的角色，并加入集合
		for(Map<String,Object> dbGroupDetail : dbGroupDetails){
			groupRoles.add((String) dbGroupDetail.get("Role_Id"));
		}
		
		
		//获取这些用户所所拥有的系统CD
		for(Map<String,Object> groupSystem : dbGroupSystems){
			groupSystemCDs.add((String)groupSystem.get("systemId"));
		}
		
		
		//验证进行操作的用户不是超级管理员
		if(!roleService.valdiateSuper(userName)){
			//设置条件
			reuseMap.put("userName", userName);
			reuseMap.put("stateCd", "1");
			
			//那么通过用户名和用户组ID获取获取可操作的系统
			systemPlatforms = systemPlatformComplexMapper.selectSysByUser(userName,groupId);
			
			//查询当前操作人可操作的角色
			roles = roleComplexMapper.selectRoleByUserName(reuseMap);
			
			//用完清下
			reuseMap.clear();
			
		}else{
			//否则执行超级管理员操作,那么拥有权限获取所有的系统
			systemPlatforms = systemPlatformComplexMapper.selectAllSys();
			//获取所有角色
			roles = roleComplexMapper.selectOptRoleBySuperAdmin(null);
			//获取所有课用户组列表
			groups = groupDao.selectOptGroupBySuperAdmin();
		}
		
		//用来存储当前用户组下的所有子用户组的id
		List<String> childGroupIds = new ArrayList<>();
		//通过当前的组ID获取下边所有子组的id，这里来做递归的
		CommonUtil.getChildGroup(groups, groupId, childGroupIds);
		//把自己也加进去
		childGroupIds.add(groupId);
		
		//循环当前用户拥有用户组
		Iterator<Map<String, Object>> userGroup = groups.iterator();
		//迭代用户组列表
		while (userGroup.hasNext()) {
			Map<String, Object> map = userGroup.next();
			//获取每一个组Id
			String userGroupId = (String) map.get("groupId");
			for (String subId : childGroupIds) {
				// 如果用户组id与所传用户组id以及其子用户组id相同，则把该用户组从用户组列表中去除
				if (userGroupId.equals(subId)) {
					userGroup.remove();
				}
			}
		}
		
		//用户可操作用户组
		reuseMap.put("groups", groups);
		//已选中的系统CD
		reuseMap.put("groupSys", groupSystemCDs);
		//操作人可操作系统列表
		reuseMap.put("systems", systemPlatforms);
		//用户组基本信息
		reuseMap.put("detail", dbGroupDetails.get(0));
		//上级用户组结果集，继承角色
		reuseMap.put("pGroupRole", getParentGroupDetail(groupId));
		//操作人可操作角色列表
		reuseMap.put("roles", roles);
		//用户组所属角色框，选中的角色
		reuseMap.put("groupRoles", groupRoles);
		
		//返回结果
		return reuseMap;
	}
	
	
	/**
	 * @Title: updateGroup 
	 * @Description: 修改用户组信息
	 * @param @param groupModel
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateGroup(GroupDo groupDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//用来承载错误信息
		StringBuffer sb = new StringBuffer();
				
		//获取用户组Id
		String groupId = groupDo.getGroupId();
		//如果用户组Id为空
		if(CommonUtil.isNull(groupId)){
			reuseMap.put("fail", "用户组Id不可为空");
			//返回数据
			return reuseMap;
		}
		//获取用户组名
		String groupName = groupDo.getGroupName();
		//获取上级用户组Id
		String groupParentId = groupDo.getParentGroupId();
		
		//用于存储上级用户组角色实例
		List<Map<String, Object>> parentGroupRoles = new ArrayList<>();
		//用于存储所有的角色Id
		Set<String> roleIds = new HashSet<>();
		//设置条件
		reuseMap.put("groupName", groupName);
		//通过用户组名称查询该用户组信息
		List<UserGroup> groups = getUserGroupByQuery(reuseMap);
		//用完清下
		reuseMap.clear();
		//获取所分配的角色,转换成集合
		List<String> roleIdList = CommonUtil.splitStrToList(groupDo.getRoleId());
		
		//如果用户组名查询结果不为空，说明已经存在了
		if(CommonUtil.isNotNull(groups) && CommonUtil.isNotNull(groupName)){
			sb.append("用户组名已存在,");
		}
		
		//如果存在错误信息
		if(CommonUtil.isNotNull(sb.toString())){
			//加一句
			reuseMap.put("fail", sb.append("请重新填写！").toString());
			//返回
			return reuseMap;
		}

		//如果存在上级用户组
		if(CommonUtil.isNotNull(groupParentId)){
			//那么通过上级用户组Id获取该用户组所拥有的角色
			parentGroupRoles = groupDao.selectRoleFromGroup(groupParentId);
			//获取每一个角色id
			for (Map<String,Object> parentGroupRole : parentGroupRoles) {
				//将用户组的id放进去
				roleIds.add((String)parentGroupRole.get("roleId"));
			}
		}
		
		//将分配的id放入集合中
		roleIds.addAll(roleIdList);
		
		//设置更新时间
		groupDo.setUpdateDt(new Date());
		//先更新用户组基本信息
		userGroupMapper.updateByPrimaryKeySelective(groupDo);
		
		//将用户组角色中间表中，关于此用户组的数据全部删除
		deleteRoleAndGroupData(groupId, null);
		
		//然后将用户组和角色关联信息插入中间表
		addRoleToGroup(groupId, roleIds);
		
		//如果用户组名为空
		if(CommonUtil.isNull(groupName)){
			groupName = userGroupMapper.selectByPrimaryKey(groupId).getGroupName();
		}
		
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"用户组", groupDo.getCreateUserid(), groupName, null, 
					"update", new Date(), "修改["+groupName+"]用户组");				
		
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);	

		reuseMap.put("success", "修改用户组成功！");
		return reuseMap ;
	}

	/**
	 * @Title: selectRoleBySys 
	 * @Description: 新建用户组时，通过所属系统CD获取该系统的角色
	 * @param @param systemId
	 * @param @param userName
	 * @param @return   
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> selectRoleBySys(String systemIds,String userName) {
		//创建返回集合
		List<Map<String,Object>> resultList = new ArrayList<>();
		
		//将所选系统转换成集合
		List<String> systemPlatformCDs = CommonUtil.splitStrToList(systemIds);
		
		//循环系统
		for (String systemPlatformCD : systemPlatformCDs) {
			//如果是超级管理员
			if(roleService.valdiateSuper(userName)){
				//如果查询结果不为空
				if(CommonUtil.isNotNull(roleComplexMapper.selectOptRoleBySuperAdmin(systemPlatformCD))){
					resultList.addAll(roleComplexMapper.selectOptRoleBySuperAdmin(systemPlatformCD));
				}
			}else{
				resultList.addAll(groupDao.selectRoleBySys(systemPlatformCD, userName));
			}
		}
		return resultList;
	}
	
	/**
	 * @Title: getUserGroupByEquery 
	 * @Description: 通过条件查询用户组信息
	 * @param @param groupName
	 * @param @return   
	 * @return List<UserGroup>
	 */
	public List<UserGroup> getUserGroupByQuery(Map<String,Object> queryMap){
		//获取用户组名
		String groupName = (String) queryMap.get("groupName");
		
		//创建Example对象
		UserGroupExample example = new UserGroupExample();
		//如果用户组名不为空
		if(CommonUtil.isNotNull(groupName)){
			//设置条件
			example.createCriteria().andGroupNameEqualTo(groupName);
		}
		//进行查询，并返回
		return userGroupMapper.selectByExample(example);
	}
	
	/**
	 * @Title: getParentGroupDetail 
	 * @Description: 通过当前用户组ID获取上级用户组信息
	 * @param @param groupId
	 * @param @return   
	 * @return Map<String,Object>
	 */
	private Map<String, Object> getParentGroupDetail(String groupId){
		//创建返回Map
		Map<String, Object> resultMap = new HashMap<>();
		
		//用来存储上级用户组角色Id的集合
		List<String> parentGroupRoleIds = new ArrayList<>();
		
		//通过用户组id,获取该用户组详情
		Map<String,Object> groupDetail = (Map<String, Object>) groupDao.selectGroupDetail(groupId).get(0);
		//获取该用户组的上级用户组Id
		String parentGroupId = (String) groupDetail.get("pid");
		
		//通过上级用户组Id获取上级用户组所拥有的角色
		List<Map<String,Object>> parentGroupRoles = groupDao.selectRoleFromGroup(parentGroupId);
		//遍历上级用户组角色
		for (Map<String,Object> parentGroupRole : parentGroupRoles) {
			//获取到每一个角色Id
			parentGroupRoleIds.add((String)parentGroupRole.get("roleId"));
		}
		//将上级用户组所拥有的角色Id加入结果集中
		resultMap.put("result", parentGroupRoles);
		//将上级用户组所拥有的角色放入结果集中
		resultMap.put("roleStr", parentGroupRoleIds);
		
		//返回结果
		return resultMap;				
	}
	
	/**
	 * @Title: addRoleToGroup 
	 * @Description: 给用户组添加角色
	 * @param @param groupId
	 * @param @param roleIds
	 * @param @return   
	 * @return boolean
	 */
	private void addRoleToGroup(String groupId, Set<String> roleIds) {
		//循环取值
		for (String roleId : roleIds) {
			//创建用户组角色model
			GroupRole groupRole = new GroupRole();
			//设置用户组Id
			groupRole.setGroupId(groupId);
			//设置角色Id
			groupRole.setRoleId(roleId);
			//设置创建时间
			groupRole.setCreateDt(new Date());
			//设置状态
			groupRole.setStateCd("1");
			//插入数据库
			groupRoleMapper.insertSelective(groupRole);
		}
	}		

	/**
	 * @Title: deleteRoleAndGroupData 
	 * @Description: 删除用户组角色中间表数据
	 * @param @param groupId
	 * @param @param roleId
	 * @param @return   
	 * @return 
	 */
	private void deleteRoleAndGroupData(String groupId, String roleId){
		//创建Example对象
		GroupRoleExample example = new GroupRoleExample();
		//如果角色Id不为空
		if(CommonUtil.isNotNull(roleId)){
			//设置条件
			example.createCriteria().andRoleIdEqualTo(roleId);
		}
		//如果用户组不为空
		if(CommonUtil.isNotNull(groupId)){
			//设置条件
			example.createCriteria().andGroupIdEqualTo(groupId);
		}
		//删除数据
		groupRoleMapper.deleteByExample(example);
	}
	
	/**
	 * @Title: insertRoleUserInstanceData 
	 * @Description: 新增用户角色实例表数据
	 * @param @param groupId
	 * @param @param userId   
	 * @return void
	 */
	private void insertRoleUserInstanceData(String groupId,String userId){
		//创建用户角色实例表model
		UserRoleInstance userRoleInstance = new UserRoleInstance();
		//设置主键
		userRoleInstance.setInstId(CommonUtil.getUUID());
		//设置用户组Id
		userRoleInstance.setGroupId(groupId);
		//设置用户Id
		userRoleInstance.setSystemUserId(userId);
		//设置状态CD
		userRoleInstance.setStateCd("1");
		//设置创建时间
		userRoleInstance.setCreateDt(new Date());
		//插入数据
		userRoleInstanceMapper.insertSelective(userRoleInstance);			
	}
	
}
