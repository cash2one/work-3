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

import com.trust.privilege.dao.PrivilegeResMapper;
import com.trust.privilege.dao.PrivilegeResTypeMapper;
import com.trust.privilege.dao.RolePrivilegeResMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.MenuComplexMapper;
import com.trust.privilege.dao.complexMapper.PrivilegeComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.model.PrivilegeRes;
import com.trust.privilege.model.PrivilegeResExample;
import com.trust.privilege.model.PrivilegeResType;
import com.trust.privilege.model.PrivilegeResTypeExample;
import com.trust.privilege.model.RolePrivilegeRes;
import com.trust.privilege.model.RolePrivilegeResExample;
import com.trust.privilege.model.SystemPlatform;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.model.complex.PrivilegeResDo;
import com.trust.privilege.model.complex.QueryPrivilege;
import com.trust.privilege.service.PrivilegeService;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.util.CommonUtil;

/**
 * @ClassName: PrivilegeService 
 * @Description: 页面资源Service接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月14日 下午3:58:45
 */
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**依赖注入权限资源dao*/
	@Resource
	private PrivilegeComplexMapper privilegeComplexMapper;
	
	/**依赖注入菜单dao*/
	@Resource
	private MenuComplexMapper menuComplexMapper;

	/**依赖注入用户dao*/
	@Resource
	private SysUserDao sysUserDao;
	
	/**依赖注入资源类型Mapper*/
	@Resource
	private PrivilegeResTypeMapper privilegeResTypeMapper;
	
	/**依赖注入系统Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**依赖注入资源Mapper*/
	@Resource
	private PrivilegeResMapper privilegeResMapper;
			
	/**依赖注入角色资源Mapper*/
	@Resource
	private RolePrivilegeResMapper rolePrivilegeResMapper;
	
	/**依赖注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**
	 * @Title: selectSysPrivilege 
	 * @Description: 通过用户名和菜单id，来查询该用户所拥有的权限资源
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> selectSysPrivilege(String menuId, String userName) {
		//创建复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(menuId)){
			//用来承载当前操作人所拥有的该菜单下的资源
			List<Map<String,Object>> privilegeResources = new ArrayList<>();
			//用来承载资源标识的集合
			List<Map<String, Object>> aMark = new ArrayList<Map<String, Object>>();
			
			//设置查询条件
			reuseMap.put("userName", userName);
			reuseMap.put("menuId", menuId);
			//默认查找可见的资源
			reuseMap.put("stateCd", "1");
			//根据条件查询当前用户所拥有该菜单下的资源
			privilegeResources =  privilegeComplexMapper.selectSysPrivilege(reuseMap);
			
			//清下
			reuseMap.clear();

			//循环当前操作人拥有的资源
			for (Map<String, Object> privilegeResource : privilegeResources) {
				//获取该资源的标识
				String privilegeMark = (String) privilegeResource.get("Mark");
				
				//如果标识不为空
				if(CommonUtil.isNotNull(privilegeMark)){
					//将当前资源标识状态返回
					reuseMap.put(privilegeMark, false);
				}
				
			}
			reuseMap.put("btn", aMark);
			reuseMap.put("aMark", aMark);
		}
		return reuseMap;
		
//-----------------------------------------------------------------		
	}
	
	/**
	 * @Title: getAllpriveleges 
	 * @Description: 获取系统资源列表
	 * @param @param queryPrivilege
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> getAllpriveleges(QueryPrivilege queryPrivilege) {
		//创建复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		//用来承载返回的资源列表
		List<Map<String, Object>> privilegeRess = new ArrayList<Map<String, Object>>();
		
		//获取用户名
		String userName = queryPrivilege.getUserName();
		
		//设置用户名
		reuseMap.put("userName", userName);
		//设置资源名称
		reuseMap.put("resName", queryPrivilege.getResName());
		//设置所属菜单
		reuseMap.put("menuName", queryPrivilege.getMenuName());
		//设置系统名称
		reuseMap.put("systemPlatformName", queryPrivilege.getSystemPlatformName());
		
		//通过用户名判断此用户如果是超级管理员
		if(roleService.valdiateSuper(userName)){
			privilegeRess = privilegeComplexMapper.getAllPrivilegeBySuperAdmin(reuseMap);
		}else{
			privilegeRess = privilegeComplexMapper.getAllPrivilege(reuseMap);
		}
		
		//清下
		reuseMap.clear();
		//设置返回数据
		reuseMap.put("list", privilegeRess);
		
		return reuseMap;
	}
	
	/**
	 * @Title: insertPrivilege 
	 * @Description: 新增资源信息
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> insertPrivilege(PrivilegeResDo privilegeDo) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取当前操作人
		String userName = privilegeDo.getCreateUserId();
		//获取系统CD
		String systemPlatformCD = privilegeDo.getSystemPlatformCd();
		//获取资源名称
		String resName = privilegeDo.getResName();
		//获取该资源的url
		String reqUrl = privilegeDo.getUrl();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(resName) && CommonUtil.isNotNull(reqUrl)){
			//用来承载错误信息
			StringBuffer sb = new StringBuffer();
			
			//通过系统CD和资源名称查询该资源不为空
			if(CommonUtil.isNotNull(getPrivilege(resName, null,systemPlatformCD))){
				//那么系统名称肯定重复了，设置错误信息
				sb.append("当前系统下资源名称重复,");
			}
			//通过系统CD和url查询该资源不为空
			if(CommonUtil.isNotNull(getPrivilege(null, reqUrl, systemPlatformCD))){
				//加入错误信息
				sb.append("当前资源所属的系统下已存在此url,");
			}
			
			//如果存在错误信息
			if(CommonUtil.isNotNull(sb.toString())){
				//再加一句
				reuseMap.put("fail", sb.append("请重新填写！").toString());
				//返回
				return reuseMap;
			}
			
			//新建资源，设置主键
			privilegeDo.setPrivilegeResId(CommonUtil.getUUID());
			//设置创建时间
			privilegeDo.setCreateDate(new Date());
			//如果插入资源数据成功
			privilegeResMapper.insertSelective(privilegeDo);
			
			//通过当前操作人获取其角色
			List<Map<String,Object>> roles = sysUserDao.selectRoleByUserName(userName, systemPlatformCD);
			
			//遍历取值
			for (Map<String, Object> role : roles) {
				//获取每个角色Id
				String roleId = (String) role.get("roleId");
				//向角色资源表插入数据
				rolePrivilegeResMapper.insert(new RolePrivilegeRes(roleId,
						privilegeDo.getPrivilegeResId(), "1", new Date(), "1"));
			}
			
			//通过系统CD获取该系统名称
			String systemPlatformName = systemPlatformMapper.
							selectByPrimaryKey(systemPlatformCD).getSystemPlatformName();
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"页面资源", userName , resName, systemPlatformName, 
						"insert", new Date(), "新增["+resName+"]页面资源");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);			
			
			reuseMap.put("success", "添加资源成功！");
		}
		
		return reuseMap;
	}

	/**
	 * @Title: deleteprivilegeRes 
	 * @Description: 删除一个资源
	 * @param @param privilegeResId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> deletePrivilege(String privilegeResId, String userName) {
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		//如果参数不为空
		if(CommonUtil.isNotNull(privilegeResId)){
			//通过资源id获取角色资源中间表数据，如果不为空，说明有角色在绑定该资源
			if(CommonUtil.isNotNull(getRolePrivilegeRes(privilegeResId,null))){
				reuseMap.put("fail", "已有角色使用这个资源，不可删除");
				return reuseMap;
			}
			
			//通过资源id，获取该资源数据
			PrivilegeRes dbPrivilegeRes = privilegeResMapper.selectByPrimaryKey(privilegeResId);
			
			//通过该资源系统CD获取该资源系统信息
			SystemPlatform systemPlatform = systemPlatformMapper.selectByPrimaryKey(dbPrivilegeRes.getSystemPlatformCd());
			
			//创建日志对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(),
					 "页面资源", userName, dbPrivilegeRes.getResName(), systemPlatform.getSystemPlatformName(),
									 "delete", new Date(), "删除了["+dbPrivilegeRes.getResName()+"]页面资源");
			//先删除该资源数据
			privilegeResMapper.deleteByPrimaryKey(privilegeResId);
			//在进行数据库日志信息插入
			logMapper.insertLog(logDetail);
			//设置返回信息
			reuseMap.put("success", "删除资源成功");
		}
		return reuseMap;
	}

	/**
	 * @Title: updatePrivilege 
	 * @Description: 修改资源信息
	 * @param @param Privilege
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> updatePrivilege(PrivilegeResDo privilegeDo ) {
		
		//创建返回Map
		Map<String, Object> reuseMap = new HashMap<>();
		//获取资源id
		String resId = privilegeDo.getPrivilegeResId();
		//获取资源名称
		String resName = privilegeDo.getResName();
		//获取该资源的url
		String reqUrl = privilegeDo.getUrl();
		//获取系统CD
		String systemPlatformCD = privilegeDo.getSystemPlatformCd();
		//如果参数不为空
		if(CommonUtil.isNotNull(resName) && CommonUtil.isNotNull(reqUrl)){
			//用来承载错误信息
			StringBuffer sb = new StringBuffer();
			
			//如果资源名称不为空
			if(CommonUtil.isNotNull(resName)){
				//通过资源名称和系统CD不为空
				if(CommonUtil.isNotNull(getPrivilege(resName, null, systemPlatformCD))){
					//记录错误信息
					sb.append("当前系统下已存在此资源名称,");
				}
			}		
			//如果url不为空
			if(CommonUtil.isNotNull(reqUrl)){
				//通过url和系统CD不为空
				if(CommonUtil.isNotNull(getPrivilege(null, reqUrl, systemPlatformCD))){
					sb.append("当前系统下已存在此请求Url,");
				}
			}
			
			//如果错误信息不为空
			if(CommonUtil.isNotNull(sb.toString())){
				//加一句
				reuseMap.put("fail", sb.append("请重新填写！").toString());
				//返回错误信息
				return reuseMap;
			}
			//如果资源名称为空
			if(CommonUtil.isNull(resName)){
				resName = privilegeResMapper.selectByPrimaryKey(resId).getResName();
			}
			
			//通过系统CD得到该系统名称
			String systemPlatformName = systemPlatformMapper.
						selectByPrimaryKey(systemPlatformCD).getSystemPlatformName();
			
			//如果修改数据成功
			privilegeResMapper.updateByPrimaryKeySelective(privilegeDo);
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"页面资源", privilegeDo.getCreateUserId(), resName, systemPlatformName, 
						"update", new Date(), "修改["+resName+"]页面资源");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);			
			
			//设置返回信息
			reuseMap.put("success","修改资源信息成功！");
		}
			return reuseMap;
		
	}
	
	/**
	 * @Title: getAllResType 
	 * @Description: 查询所有的页面资源类型
	 * @param @return   
	 * @return List<PrivilegeResTypeVO>
	 */
	@Override
	public List<PrivilegeResType> getAllResType() {
		PrivilegeResTypeExample example = new PrivilegeResTypeExample();
		return privilegeResTypeMapper.selectByExample(example);
	}
	
	/**
	 * @Title: getPrivilegeByMenuIds 
	 * @Description: 在新建角色时，根据多个菜单id查询页面有效资源
	 * @param @param privilegeResVO
	 * @param @return   
	 * @return List<PrivilegeRes>
	 */
	@Override
	public List<PrivilegeRes> getPrivilegeByMenuIds(PrivilegeResVO privilegeResVO) {
		//获取多个菜单id
		String menuIds = privilegeResVO.getMenuIds();
		//获取状态
		String stateCD = privilegeResVO.getStateCd();
		//先将字符串转换成集合
		List<String> menuIdList = CommonUtil.splitStrToList(menuIds);
		//查询并返回结果
		return privilegeComplexMapper.getPrivilegeByMenuIds(menuIdList, stateCD);
	}
	
	/**
	 * @Title: selectSysValidatePrivilege 
	 * @Description: 根据用户名，系统CD，查询需要拦截的url
	 * @param systemId
     * @param userName
	 * @param @return   
	 * @return Set<String>
	 */
	@Override
	public Set<String> selectSysValidatePrivilege(String systemId, String userName) {	
		//创建返回值
		Set<String> resultUrls = new HashSet<>();
		//如果参数不为空
		if(CommonUtil.isNotNull(systemId)){
			//通过系统CD获取该系统下的所有url
			List<String> allUrl = privilegeComplexMapper.selectAllUrlBySys(systemId);
			//获取该用户所拥有的url
			List<String> userUrls = privilegeComplexMapper.selectValidUrlByUserName(userName, systemId);
			//循环该系统下所有的url
			for (String url : allUrl) {
				//循环该用户所拥有的每个url
				for (String userUrl : userUrls) {
					//如果当前用户拥有的url不在系统下url中
					if(!url.equals(userUrl)){
						//那么则为该用户没有权限访问的url，加入集合中
						resultUrls.add(url);
					}
				}
			}	
		}
		
		//返回结果
		return resultUrls;
	}

	/**
	 * @Title: getPrivilege 
	 * @Description: 通过条件获取资源数据
	 * @param @param resName
	 * @param @param privilegeUrl
	 * @param @param systemPlatformCD
	 * @param @return   
	 * @return List<PrivilegeRes>
	 */
	public List<PrivilegeRes> getPrivilege(String resName,String privilegeUrl,String systemPlatformCD){
		//创建Example对象
		PrivilegeResExample resExample = new PrivilegeResExample();
		//如果资源名称不为空
		if(CommonUtil.isNotNull(resName)){
			resExample.createCriteria().andResNameEqualTo(resName);
		}
		//如果url不为空
		if(CommonUtil.isNotNull(privilegeUrl)){
			resExample.createCriteria().andUrlEqualTo(privilegeUrl);
		}
		//如果系统CD不为空
		if(CommonUtil.isNotNull(systemPlatformCD)){
			resExample.createCriteria().andSystemPlatformCdEqualTo(systemPlatformCD);
		}
		
		//返回查询结果
		return privilegeResMapper.selectByExample(resExample);
	}
	
	/**
	 * @Title: getRolePrivilegeRes 
	 * @Description: 通过条件查询角色资源中间表数据
	 * @param @param privilegeResId
	 * @param @return   
	 * @return List<?>
	 */
	private List<RolePrivilegeRes> getRolePrivilegeRes(String privilegeResId,String roleId){
		//创建Example对象
		RolePrivilegeResExample rprExample = new RolePrivilegeResExample();
		//如果资源id不为空
		if(CommonUtil.isNotNull(privilegeResId)){
			//设置条件
			rprExample.createCriteria().andPrivilegeResIdEqualTo(privilegeResId);
		}
		//如果角色id不为空
		if(CommonUtil.isNotNull(roleId)){
			//设置条件
			rprExample.createCriteria().andRoleIdEqualTo(roleId);
		}
		//返回查询结果
		return rolePrivilegeResMapper.selectByExample(rprExample);
	}
	// -----------------------------------------------------------------

	

	// 插入页面资源类型
//	@Override
//	public Integer insertResType(PrivilegeResType privilegeResType) {
//		// 插入页面资源类型UUID主键
//		String resTypCd = privilegeResType.getResTypeCd();
//		if (null == resTypCd) {
//			resTypCd = UUID.randomUUID().toString().replace("-", "");
//		}
//		privilegeResType.setResTypeCd(resTypCd);
//		int count = privilegeResTypeMapper.insertSelective(privilegeResType);
//		return count;
//	}

}
