package com.trust.privilege.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.trust.privilege.dao.PrivilegeResMapper;
import com.trust.privilege.dao.SystemConfigMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.SystemConfigComplexMapper;
import com.trust.privilege.dao.complexMapper.SystemPlatformComplexMapper;
import com.trust.privilege.facade.model.complex.QuerySystemPlatformVO;
import com.trust.privilege.model.PrivilegeResExample;
import com.trust.privilege.model.SystemConfig;
import com.trust.privilege.model.SystemConfigExample;
import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.model.SystemPlatformExample;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.service.SystemPlatFormService;
import com.trust.privilege.util.CommonUtil;
/**
 * @ClassName: SystemServiceImpl 
 * @Description: 系统管理Service实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月10日 上午11:14:02
 */
@Transactional
public class SystemPlatFormServiceImpl implements SystemPlatFormService {
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**依赖注入系统操作Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**依赖注入资源Mapper*/
	@Resource
	private PrivilegeResMapper privilegeResMapper;
	
	/**依赖注入系统配置Mapper*/
	@Resource
	private SystemConfigMapper systemConfigMapper;
	
	/**注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**依賴注入系統dao*/
	@Resource
	private SystemPlatformComplexMapper systemPlatformComplexMapper;
	
	/**依赖注入系统配置dao*/
	@Resource
	private SystemConfigComplexMapper systemConfigComplexMapper;
	
	/**
	 * @Title: insertSystem 
	 * @Description: 通过条件，查询系统列表
	 * @param @param systemPlatFormName,userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> selectSystemPlatform(QuerySystemPlatformVO querySystemPlatformVO) {
		//用于存放数据
		List<Map<String,Object>> systemList=new ArrayList<Map<String,Object>>();
		//创建查询条件容体
		Map<String,Object> queryMap = new HashMap<String,Object>();
		
		//获取用户名
		String userName = querySystemPlatformVO.getUserName();
		
		//设置查询条件
		queryMap.put("systemPlatformName",querySystemPlatformVO.getSystemPlatformName());
		queryMap.put("stateCd",querySystemPlatformVO.getStateCd());
				
		//如果是超级管理员
		if(roleService.valdiateSuper(userName)){
			//那么获取所有的系统列表
			systemList = systemPlatformComplexMapper.selectSystemPlatformByCreateUserId(queryMap);
		}else{
			//否则的话，设置当前操作人
			queryMap.put("userName",userName);
			//获取当前操作人可以访问的系统列表
			systemList = systemPlatformComplexMapper.selectSystemPlatform(queryMap);
		}
		//设置返回数据
		queryMap.clear();
		queryMap.put("list", systemList);
			
		return queryMap;
	}
	
	/**
	 * @Title: getAllSystem 
	 * @Description: 查询数据库中的系统列表，多条件查询日志时使用
	 * @param @param systemPlatform
	 * @param @return   
	 * @return List<SystemPlatform>
	 */
	@Override
	public List<SystemPlatform> getAllSystem() {
		//默认查询所有状态正常的系统
		String stateCD = "1";
		//如果查询到数据
		if(CommonUtil.isNotNull( getSystem(null,stateCD) )){
			return getSystem(null,stateCD);
		}
		return null ;
	}
	
	/**
	 * @Title: insertSystem 
	 * @Description: 新增系统信息
	 * @param @param systemPlatform
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertSystem(SystemPlatform systemPlatform) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取系统名称
		String systemName = systemPlatform.getSystemPlatformName();
		//获取系统CD
		String systemCD = systemPlatform.getSystemPlatformCd();
		
		//如果系统CD或者系统名称为空
		if(CommonUtil.isNull(systemCD) || CommonUtil.isNull(systemName)){
			reuseMap.put("fail", "系统CD或者系统名称不可为空");
			//返回
			return reuseMap;
		}
		
		//通过系统CD得到该系统信息
		SystemPlatform DBsystemPlatform = systemPlatformMapper.selectByPrimaryKey(systemCD);
		
		//如果数据不为空
		if(CommonUtil.isNotNull(DBsystemPlatform) && CommonUtil.isNotNull(systemName)){
			//用来存储错误数据
			StringBuffer sb = new StringBuffer();
			//查到数据，说明系统CD肯定存在
			sb.append( "系统CD已存在,");
			
			//如果系统名字已存在
			if(DBsystemPlatform.getSystemPlatformName().equals(systemName)){
				sb.append("系统名称已存在,");
			}
			
			//如果存在错误信息
			if(CommonUtil.isNotNull(sb.toString())){
				//加上一句
				reuseMap.put("fail", sb.append("请重新填写!").toString());
				//返回
				return reuseMap;
			}
		}
		
		//否则的话，设置创建时间
		systemPlatform.setCreateDt(new Date());
		//进行系统信息的插入
		systemPlatformMapper.insertSelective(systemPlatform);
		
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"系统", systemPlatform.getCreateUserId(), systemName, systemName, 
					"insert", new Date(), "增加["+systemName+"]系统");
		
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);
		
		
		reuseMap.put("success","系统信息插入成功！");
		return reuseMap;
	}
	
	/**
	 * @Title: updateSystem 
	 * @Description: 修改系统信息
	 * @param @param systemPlatform
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateSystem(SystemPlatform systemPlatform) {
		//设置返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取系统CD
		String systemPlatfromCD = systemPlatform.getSystemPlatformCd();
		//获取系统名称
		String systemPlatformName = systemPlatform.getSystemPlatformName();
		//获取系统状态
		String stateCD = systemPlatform.getStateCd();
		
		//如果系统CD或者系统名称为空
		if(CommonUtil.isNull(systemPlatfromCD) || CommonUtil.isNull(systemPlatformName)){
			reuseMap.put("fail", "系统CD或者系统名称不可为空");
			//返回
			return reuseMap;
		}				
		
		//如果当前操作是使系统失效
		if(CommonUtil.isNotNull(stateCD) && stateCD.equals("0")){
			//那么查询该系统的下的所有角色
			List<String> roleIds = systemPlatformComplexMapper
					.getRoleIdsBySystemPlatformCd(systemPlatform.getSystemPlatformCd());
			
			//如果系统下边有角色
			if(CommonUtil.isNotNull(roleIds)){
				//使角色失效
				systemPlatformComplexMapper.updateStateCdOfRoleIds(roleIds,stateCD);
				//角色失效后查询资源表和菜单表，对其系统下的资源和菜单进行失效
				systemPlatformComplexMapper.updateMenuBySys(stateCD, systemPlatform.getSystemPlatformCd());
				systemPlatformComplexMapper.updateResBySys(stateCD, systemPlatform.getSystemPlatformCd());
			}
		}
		
		//如果当前系统名不为空
		if(CommonUtil.isNotNull(systemPlatformName)){
			//通过系统名和状态查询该数据
			List<SystemPlatform> SystemPlatforms = getSystem(systemPlatformName,stateCD);
			//如果数据不为空
			if(CommonUtil.isNotNull(SystemPlatforms)){
				//说明数据已存在，那么直接报错
				reuseMap.put("fail", "系统名称重复，请重新填写");
				return reuseMap;
			}
		}else{
			systemPlatformName = systemPlatformMapper.
					selectByPrimaryKey(systemPlatfromCD).getSystemPlatformName();
		}
		
		//接着修改系统表，使该数据失效
		systemPlatformMapper.updateByPrimaryKeySelective(systemPlatform);
		
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"系统", systemPlatform.getCreateUserId(), systemPlatformName, systemPlatformName, 
					"update", new Date(), "修改["+systemPlatformName+"]系统");
		
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);				
		//设置返回数据
		reuseMap.put("success","修改系统信息成功");
		return reuseMap ;
	}
	
	/**
	 * @Title: deleteSystem 
	 * @Description: 根据系统CD删除该系统数据
	 * @param @param systemPlatform
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> deleteSystem(String systemPlatformCd,String userName) {
		//设置返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果传入系统cd不为空
		if(CommonUtil.isNotNull(systemPlatformCd)){
			//如果当前系统有菜单或者资源或者角色
			if(systemPlatformComplexMapper.selectMenuInSys(systemPlatformCd) > 0 || 
					systemPlatformComplexMapper.selectPrivilegeInSys(systemPlatformCd) > 0 ||
							systemPlatformComplexMapper.selectRoleInSys(systemPlatformCd) > 0 ){
				//返回信息
				reuseMap.put("fail", "系统下有资源，不可删除");
				return reuseMap;
			}
			//通过系统CD得到该系统名称
			String systemPlatformName = systemPlatformMapper
						.selectByPrimaryKey(systemPlatformCd).getSystemPlatformName();
			//否则通过系统CD进行删除该数据,如果成功的话
			systemPlatformMapper.deleteByPrimaryKey(systemPlatformCd);
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"系统", userName, systemPlatformName, systemPlatformName, 
						"delete", new Date(), "删除["+systemPlatformName+"]系统");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);
			
			//设置返回信息
			reuseMap.put("success", "删除系统成功"); 
		}
			return reuseMap;
	}
	
	/**
	 * @Title: getSystem 
	 * @Description: 通过条件获取系统数据
	 * @param @param systemName
	 * @param @param stateCD
	 * @param @return   
	 * @return List<SystemPlatform>
	 */
	public List<SystemPlatform> getSystem(String systemName,String stateCD){
		//创建Examle对象
		SystemPlatformExample systemExample = new SystemPlatformExample();
		//如果系统名称不为空
		if(CommonUtil.isNotNull(systemName)){
			//设置条件
			systemExample.createCriteria().andSystemPlatformNameEqualTo(systemName);
		}
		//如果状态不为空
		if(CommonUtil.isNotNull(stateCD)){
			//设置条件
			systemExample.createCriteria().andStateCdEqualTo(stateCD);
		}
		//如果查询结果不为空
		if(CommonUtil.isNotNull(systemPlatformMapper.selectByExample(systemExample))){
			//那么返回结果
			return systemPlatformMapper.selectByExample(systemExample);
		}
		return null;
	}
	
	//-----------------------------------------------------------------
	
	/**
	 * @Title: getAllOffenUseOption 
	 * @Description: 通过用户名查询该用户常用功能列表（portal门户）
	 * @param @param systemName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> getAllOffenUseOption(String userName) {
		//用来承载返回Map
		Map<String, Object> resultMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(userName)){
			//用来承载系统列表
			List<SystemConfig> systems = new ArrayList<>();
			
			//通过用户名得到该用户常用功能列表
			List<SystemConfig> systemConfigs = systemConfigComplexMapper.getAllOffenUserOption(userName);
			
			//遍历
			for (SystemConfig systemConfig : systemConfigs) {
				//获取资源类型
				String resourceType = systemConfig.getResourceType();
				//如果遍历到的常用功能类型为系统
				if(CommonUtil.isNotNull(resourceType) && resourceType.equals("system")){
					//添加到系统集合中
					systems.add(systemConfig);
				}
			}
			//设置返回数据
			resultMap.put("oftenSystemlist", systems);
		}
		return resultMap;
	}
	
	/**
	 * @Title: insertOffenUseOption 
	 * @Description: 插入系统常用功能
	 * @param @param systemConfigVO
	 * @param @return   
	 * @return String
	 */
	@Override
	public String insertOffenUseOption(SystemConfig systemConfig) {
		//设置默认返回值
		String result = "插入常用功能失败";
		
		//获取资源名称
		String resourceName = systemConfig.getResourceName();
		//获取用户名
		String userName = systemConfig.getUserName();
		
		if(CommonUtil.isNull(resourceName) && CommonUtil.isNull(userName)){
			return "资源名称不可为空";
		}
		
		//如果常用功能数据Id为空
		if(CommonUtil.isNull(systemConfig.getSystemConfigId())){
			//那么设置主键
			systemConfig.setSystemConfigId(CommonUtil.getUUID());
		}
		
		//创建系统配置Example对象
		SystemConfigExample systemConfigExample = new SystemConfigExample();
		
		//设置条件
		systemConfigExample.createCriteria()
				.andResourceNameEqualTo(systemConfig.getResourceName())
							.andUserNameEqualTo(systemConfig.getUserName());
		//如果资源名称已存在
		if(systemConfigMapper.countByExample(systemConfigExample) > 0){
			//说明资源名称重复
			return "名称不可重复";
		}
		
		//创建资源Example对象
		PrivilegeResExample privilegeResExample = new PrivilegeResExample();
		//设置条件
		privilegeResExample.createCriteria().andPrivilegeResIdEqualTo(systemConfig.getPrivilegeResId());
		//如果资源存在
		if(privilegeResMapper.countByExample(privilegeResExample) > 0){
			//插入系统配置数据
			systemConfigMapper.insertSelective(systemConfig);
			result =  "插入常用功能成功";
		}
		return result ;
	}
	
	/**
	 * @Title: deleteOffenUseOption 
	 * @Description: 通过常用系统功能的ID删除系统常用功能
	 * @param @param systemConfigId
	 * @param @return   
	 * @return String
	 */
	@Override
	public String deleteOffenUseOption(String systemConfigId) {
		//如果参数不为空
		if(CommonUtil.isNotNull(systemConfigId)){
			//如果删除成功
			if(systemConfigMapper.deleteByPrimaryKey(systemConfigId) > 0){
				return "删除常用功能成功";
			}
		}
		return null;
	}
	
}
