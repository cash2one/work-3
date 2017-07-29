package com.trust.privilege.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.trust.privilege.dao.MenuMapper;
import com.trust.privilege.dao.MenuRoleMapper;
import com.trust.privilege.dao.PrivilegeResMapper;
import com.trust.privilege.dao.SystemPlatformMapper;
import com.trust.privilege.dao.complexMapper.LogMapper;
import com.trust.privilege.dao.complexMapper.MenuComplexMapper;
import com.trust.privilege.dao.complexMapper.SysUserDao;
import com.trust.privilege.model.Menu;
import com.trust.privilege.model.MenuExample;
import com.trust.privilege.model.MenuRole;
import com.trust.privilege.model.MenuRoleExample;
import com.trust.privilege.model.PrivilegeResExample;
import com.trust.privilege.model.complex.LogDetail;
import com.trust.privilege.model.complex.MenuDo;
import com.trust.privilege.model.complex.QueryMenu;
import com.trust.privilege.service.MenuService;
import com.trust.privilege.service.RoleService;
import com.trust.privilege.util.CommonUtil;
/**
 * @ClassName: MenuServiceImpl 
 * @Description: 菜单管理Service层实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月7日 下午5:05:31
 */
@Transactional
public class MenuServiceImpl implements MenuService{
	
	/**依赖注入角色Service*/
	@Resource
	private RoleService roleService;
	
	/**依赖注入系统操作Mapper*/
	@Resource
	private SystemPlatformMapper systemPlatformMapper;
	
	/**注入日志Mapper*/
	@Resource
	private LogMapper logMapper;
	
	/**依赖注入菜单Mapper*/
	@Resource
	private MenuMapper menuMapper;
	
	/**注入菜单角色Mapper*/
	@Resource
	private MenuRoleMapper menuRoleMapper;
	
	/**依赖注入资源Mapper*/
	@Resource
	private PrivilegeResMapper privilegeResMapper;
	
	/**注入菜单dao*/
	@Resource
	private MenuComplexMapper menuComplexMapper;
	
	/**注入用户dao层*/
	@Resource
	private SysUserDao sysUserDao;
	
	/**
	 * @Title: insertMenu 
	 * @Description: 新增菜单信息
	 * @param @param menuVO
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> insertMenu(Menu menuDo) {
		//设置复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取菜单名称
		String menuName = menuDo.getMenuName();
		//获取当前操作人
		String userName = menuDo.getCreateUserId();
		//获取当前系统CD
		String systemPlatformCD = menuDo.getSystemPlatformCd();
		
		//如果菜单名称为空
		if(CommonUtil.isNull(menuName)){
			//返回错误数据
			reuseMap.put("fail", "菜单名称不可以为空");
			return reuseMap;
		}
		
		//如果上级菜单为空
		if(CommonUtil.isNull(menuDo.getParentId())){
			//那么社会上级菜单为当前系统CD
			menuDo.setParentId(systemPlatformCD);
		}
		
		//进行校验
		String errorStr = validateQuery(menuDo);
		//如果返回结果不为空
		if(CommonUtil.isNotNull(errorStr)){
			//返回错误数据
			reuseMap.put("fail", errorStr);
			return reuseMap;
		}
		
		//如果校验没问题，那么设置参数菜单id
		menuDo.setMenuId(CommonUtil.getUUID());
		//设置新建日期
		menuDo.setCreateDt(new Date());
		
		//如果插入菜单信息成功
		menuMapper.insertSelective(menuDo);
		
		//通过当前操作人获取其所拥有的角色
		List<Map<String,Object>> roles = sysUserDao.
					selectRoleByUserName(menuDo.getCreateUserId(), systemPlatformCD);
		
		//获取每一个角色
		for (Map<String, Object> role : roles) {
			//获取角色Id
			String roleId = (String) role.get("roleId");
			//将菜单与当前角色进行绑定
			menuRoleMapper.insert(new MenuRole(menuDo.getMenuId(),roleId , "1", new Date(), "1"));
		}
		
		//通过系统CD得到系统名
		String systemPlatformName = systemPlatformMapper.
					selectByPrimaryKey(systemPlatformCD).getSystemPlatformName();
		
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"菜单", userName, menuDo.getMenuName(),systemPlatformName , 
					"insert", new Date(), "新增["+menuDo.getMenuName()+"]菜单");
		
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);
		
		reuseMap.put("success", "添加菜单成功");
		
		return reuseMap ;
	}	
	
	/**
	 * @Title: deleteMenu 
	 * @Description: 删除菜单信息,删除菜单要判断是否有用户使用这个菜单，如果使用这个菜单那就不能删除
	 * @param @param systemPlatformCD
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return String
	 */
	@Override
	public Map<String, Object> deleteMenu(String menuId,String userName) {
		//设置复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(menuId) && CommonUtil.isNotNull(userName) ){
			//存储返回信息
			StringBuffer sb = new StringBuffer();
			
			//通过菜单id获取该菜单信息
			Menu DBmenu =  menuMapper.selectByPrimaryKey(menuId);
			
			//声明系统CD
			String systemPlatformCD = null ;
			
			//如果不为空
			if(CommonUtil.isNotNull(DBmenu)){
				//获取系统CD
				systemPlatformCD = DBmenu.getSystemPlatformCd();
			}
			
			//创建Examle对象
			MenuRoleExample menuExample = new MenuRoleExample();
			//设置查询条件
			menuExample.createCriteria().andMenuIdEqualTo(menuId);
			//查询菜单角色中间表，如果此菜单下有角色
			if(menuRoleMapper.countByExample(menuExample) > 0){
				//返回错误信息
				sb.append("该菜单下有角色，");
			}
			
			//创建角色资源的Example
			PrivilegeResExample privilegeResExample = new PrivilegeResExample();
			//设置查询条件
			privilegeResExample.createCriteria().andMenuIdEqualTo(menuId);
			//如果菜单下有资源，那么是不允许删除的
			if(privilegeResMapper.countByExample(privilegeResExample) > 0){
				//返回错误信息
				sb.append("该菜单下有资源，");
			}
			
			//设置查询条件
			reuseMap.put("parentId",menuId);
			reuseMap.put("systemPlatformCd",systemPlatformCD);
			//如果该菜单下有子菜单
			if(CommonUtil.isNotNull(getDBMenu(reuseMap))){
				//返回错误信息
				sb.append("该菜单下有子菜单，");
			}
			
			//清下
			reuseMap.clear();
			
			//如果错误信息存在
			if(CommonUtil.isNotNull(sb.toString())){
				reuseMap.put("fail", sb.append("不可删除,请先删除菜单下的关联数据！").toString());
				//直接返回结果
				return reuseMap;
			}
		
			//通过系统CD得到该系统名称
			String systemPlatformName = systemPlatformMapper
							.selectByPrimaryKey(systemPlatformCD).getSystemPlatformName();
			//如果菜单删除成功
			menuMapper.deleteByPrimaryKey(menuId);
			
			//创建log对象
			LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
					"菜单", userName, DBmenu.getMenuName(), systemPlatformName, 
						"delete", new Date(), "删除["+DBmenu.getMenuName()+"]菜单");
			
			//向数据库插入日志数据
			logMapper.insertLog(logDetail);
			
			reuseMap.put("success", "成功删除该菜单");
		}
			return reuseMap;
	}
	
	/**
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getAllParentMenus(QueryMenu queryMenu) {
		//创建复用Map
		Map<String,Object> reuseMap = new HashMap<String,Object>();
		
		//如果参数不为空
		if(CommonUtil.isNotNull(queryMenu)){
			//用来存储菜单集合
			List<Map<String,Object>> menus = new ArrayList<Map<String,Object>>();
			
			//获取当前菜单ID
			String menuId = queryMenu.getMenuId();
			//获取状态CD
			String stateCd = queryMenu.getStateCd();
			//获取系统CD
			String systemPlatformCD = queryMenu.getSystemPlatformCd();
			//获取用户名
			String userName = queryMenu.getUserName();
			
			//创建所有不在查询范围内id的容器
			List<String> notInIds = new ArrayList<>();
			
			//设置查询条件
			reuseMap.put("stateCd",stateCd);
			reuseMap.put("systemPlatformCD",systemPlatformCD);
			//获取所有的菜单
			List<Menu> DBMenus = getDBMenu(reuseMap);
			
			//获取当前菜单的上级菜单id
			String parentId = menuMapper.selectByPrimaryKey(menuId).getParentId();
			
			//循环所有菜单
			for (Menu DBMenu : DBMenus) {
				//获取菜单Id
				String dbMenuId = DBMenu.getMenuId();
				//如果当前菜单的上级菜单id，在菜单数据中查不到，那么肯定是系统CD了，在返回结果中直接返回null就可以了
				if(dbMenuId.equals(parentId)){
					//进行递归操作,获取该菜单下所有子菜单和子菜单的子菜单。。。
					notInIds = CommonUtil.getChildMenu(DBMenus, menuId, notInIds);
					
					//把自己也放进去
					notInIds.add(menuId);
					//设置不进行查询的菜单id
					reuseMap.put("menuIdList", notInIds);
					
					//如果该用户不是超级管理员角色
					if(!roleService.valdiateSuper(userName)){
						//设置用户名
						reuseMap.put("userName", userName);
						//进行查询
						menus = menuComplexMapper.getAllParentMenus(reuseMap);
					}else{
						//否则全部查询
						menus =menuComplexMapper.getAllParentMenusBySuperAdmin(reuseMap);
					}
				}
			}
				
			//清除下
			reuseMap.clear();
			reuseMap.put("list", menus);
		}
			return reuseMap;
	}
	
	/**
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menuVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> updateMenu(Menu menu) {
		//设置复用Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//获取系统CD
		String systemPlatformCd = menu.getSystemPlatformCd();
		//获取状态CD
		String stateCd = menu.getStateCd();
		//获取菜单Id
		String menuId = menu.getMenuId();
		//获取菜单名称
		String menuName = menu.getMenuName();
		
		//如果菜单Id为空
		if(CommonUtil.isNull(menuId)){
			//返回错误数据
			reuseMap.put("fail", "菜单Id不可为空");
			return reuseMap;
		}
		
		//如果是当前系统下第一级菜单，那么他的上级菜单默认是当前系统CD
		if(CommonUtil.isNull(menu.getParentId())){
			menu.setParentId(systemPlatformCd);
		}
		
		//对菜单名,url,展示顺序进行校验
		String errorStr = validateQuery(menu);
		//如果返回结果不为空
		if(CommonUtil.isNotNull(errorStr)){
			//返回错误数据
			reuseMap.put("fail", errorStr);
			return reuseMap;
		}
		
		//设置查询条件
		reuseMap.put("systemPlatformCd",systemPlatformCd );
		reuseMap.put("stateCd",stateCd );
		reuseMap.put("parentId",menuId );
		
		
		//如果当前菜单存在下级菜单
		List<Menu> menus = getDBMenu(reuseMap);
		
		//如果当前菜单下面有子菜单
		if(CommonUtil.isNotNull(menus)){
			//如果状态设置为失效
			if(menu.getStateCd().equals("0")){
				//返回错误数据
				reuseMap.put("fail", "当前菜单下有子菜单,不可失效!");
				//那么给出错误提示
				return reuseMap;
			}
			
			//循环这些子菜单
			for (Menu Menu : menus) {
				//如果该菜单进行系统转换，那么他下边的所有子菜单也要进行跟随
				Menu.setSystemPlatformCd(systemPlatformCd);
				//处理下
				String dbShowOrder = Menu.getShowOrder();
				String newShowOrder = dbShowOrder.substring(dbShowOrder.length()-2);
				//最终的展示顺序是该菜单展示顺序+DB中菜单最后两位展示顺序
				Menu.setShowOrder(menu.getShowOrder()+newShowOrder);
				//修改子菜单的展示顺序
				menuMapper.updateByPrimaryKey(Menu);
			}
		}
		//如果当前菜单名为空
		if(CommonUtil.isNull(menuName)){
			//那么获取数据库中菜单名
			menuName = getMenuByPrimaryKey(menuId).getMenuName();
		}
		
		//设置修改时间
		menu.setUpdateDt(new Date());
		//如果当前菜单下面没有子菜单，那么只更新自己的顺序即可
		menuMapper.updateByPrimaryKeySelective(menu);
		
		//通过系统CD得到系统名
		String systemPlatformName = systemPlatformMapper.
				selectByPrimaryKey(systemPlatformCd).getSystemPlatformName();		
				
		//创建log对象
		LogDetail logDetail = new LogDetail(CommonUtil.getUUID(), 
				"菜单", menu.getCreateUserId(), menuName,systemPlatformName , 
					"update", new Date(), "修改["+menuName+"]菜单");		
				
		//向数据库插入日志数据
		logMapper.insertLog(logDetail);		
		
		//返回成功信息
		reuseMap.put("success","修改菜单信息成功");
		
		return reuseMap;
	}
	
	/**
	 * @Title: getAllMenus 
	 * @Description: 根据系统CD获取该用户在此系统的所有菜单
	 * @param @param queryMenu
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getAllMenus(QueryMenu queryMenu) {
		//创建返回对象
		Map<String,Object> reuseMap = new HashMap<>();
		//用来存储菜单集合
		List<Map<String,Object>> menus = new ArrayList<Map<String,Object>>();
		//获取用户名
		String userName = queryMenu.getUserName();
		//获取系统CD,权限系统获取所有系统所有菜单，此CD为空
		String systemPlatformCD = queryMenu.getSystemPlatformCd();
			
			//设置查询条件
			reuseMap.put("userName", queryMenu.getUserName());
			reuseMap.put("systemPlatformCd", systemPlatformCD);
			reuseMap.put("stateCd", queryMenu.getStateCd());
			reuseMap.put("menuName", queryMenu.getMenuName());
			reuseMap.put("systemPlatformName", queryMenu.getSystemPlatformName());
			
			//通过用户名验证当前角色如果是超级管理员
			if(roleService.valdiateSuper(userName)){
				//那么将菜单全部查询
				menus = menuComplexMapper.getAllMenusBySuperAdmin(reuseMap);
			}else{
				menus = menuComplexMapper.getAllMenus(reuseMap);
			}
				
			//清除下
			reuseMap.clear();
			//设置返回值
			reuseMap.put("list", menus);
		return reuseMap;
	}
	
	/**
	 * @Title: selectSysMenu 
	 * @Description: 系统初始化的时候,加载左侧菜单
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	@Override
	public List<MenuDo> selectSysMenu(String userName, String systemPlatfromCD) {
		//创建返回对象
		List<MenuDo> resultList = new ArrayList<>();
		//如果参数为空
		if(CommonUtil.isNotNull(userName) && CommonUtil.isNotNull(systemPlatfromCD)){
			//如果该角色不是超级管理员
			if(!roleService.valdiateSuper(userName)){
				//创建查询map
				Map<String, String> queryMap = new HashMap<>();
				//设置条件
				queryMap.put("userName", userName);
				queryMap.put("systemPlatfromCD", systemPlatfromCD);
				//根据用户名，系统CD，查询菜单
				resultList=menuComplexMapper.selectSysMenu(queryMap);
				
			}else{
				//否则查询该系统下所有菜单
				resultList = menuComplexMapper.allMenuBySys(systemPlatfromCD);
			}
			//将菜单递归出层级结构
			resultList = CommonUtil.getSubMenu(resultList, systemPlatfromCD);
		}
		return resultList;
	}		
	
	/**
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据菜单Id得到该菜单信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return Menu
	 */
	@Override
	public Menu getMenuByPrimaryKey(String menuId) {
		//如果参数不为空
		if(CommonUtil.isNotNull(menuId)){
			return menuMapper.selectByPrimaryKey(menuId);
		}
		return null;
	}
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色(默认)
	 * @param @param menuId
	 * @param @return   	
	 * @return String
	 */	
	@Override
	public String getRolesByMenuId(String menuId) {
		//如果菜单Id不为空
		if(CommonUtil.isNotNull(menuId)){
			//通过菜单Id获取对应的角色
			List<String> roleNames = menuComplexMapper.getRolesByMenuId(menuId);
			//用来承载角色
			StringBuilder sb = new StringBuilder();
			//循环取值
			for (String roleName : roleNames) {
				sb.append(roleName).append(",");
			}
			return sb.toString().substring(0, sb.length()-1);
		}
		return null;
	}
	
	/**
	 * @Title: getMenu 
	 * @Description: 通过条件获取该菜单信息
	 * @param @param menu
	 * @param @return   
	 * @return 
	 */
	public List<Menu> getDBMenu(Map<String,Object> queryMap){
		//创建Example对象
		MenuExample menuExample = new MenuExample();
		//获取参数
		String menuName = (String) queryMap.get("menuName");
		String menuUrl = (String) queryMap.get("menuUrl");
		String parentId = (String) queryMap.get("parentId");
		String systemPlatformCd = (String) queryMap.get("systemPlatformCd");
		String stateCd = (String) queryMap.get("stateCd");
		
		//如果菜单名称不为空
		if(CommonUtil.isNotNull(menuName)){
			menuExample.createCriteria().andMenuNameEqualTo(menuName);
		}
		//如果url不为空
		if(CommonUtil.isNotNull(menuUrl)){
			menuExample.createCriteria().andUrlEqualTo(menuUrl);
		}
		//如果上级菜单id不为空
		if(CommonUtil.isNotNull(parentId)){
			menuExample.createCriteria().andParentIdEqualTo(parentId);
		}
		//如果系统CD不为空
		if(CommonUtil.isNotNull(systemPlatformCd)){
			menuExample.createCriteria().andSystemPlatformCdEqualTo(systemPlatformCd);
		}
		//如果状态不为空
		if(CommonUtil.isNotNull(stateCd)){
			menuExample.createCriteria().andStateCdEqualTo(stateCd);
		}
		//进行查询,并返回
		return menuMapper.selectByExample(menuExample);
	}
	
	/**
	 * @Title: validateQuery 
	 * @Description: 校验菜单参数
	 * @param @param queryMenu
	 * @param @return   
	 * @return String
	 */
	private String validateQuery(Menu queryMenu){
		//创建查询Map
		Map<String, Object> reuseMap = new HashMap<>();
		
		//用来承载返回字符串
		StringBuffer sb = new StringBuffer();
		
		//如果url不为空
		if(CommonUtil.isNotNull(queryMenu.getUrl())){
			reuseMap.put("menuUrl",queryMenu.getUrl());
			//如果查询结果不为空，说明url重复
			if(CommonUtil.isNotNull(getDBMenu(reuseMap))){
				sb.append("所选系统下,菜单Url重复,");
			}
		}
		
		//清下
		reuseMap.remove("menuUrl");
		//设置查询条件
		reuseMap.put("systemPlatformCd", queryMenu.getSystemPlatformCd());
		reuseMap.put("stateCd", queryMenu.getStateCd());
		//如果菜单名称不为空
		if(CommonUtil.isNotNull(queryMenu.getMenuName())){
			//设置查询条件
			reuseMap.put("menuName", queryMenu.getMenuName());
			//通过菜单名称和系统CD查询该数据(如果查询不到数据，则菜单名和url可以使用)
			if(CommonUtil.isNotNull(getDBMenu(reuseMap))){
				//那么菜单名称肯定重复
				sb.append("当前系统下菜单名称重复,");
			}
		}
		
		//清下
		reuseMap.remove("menuName");
		//设置父菜单id
		reuseMap.put("parentId", queryMenu.getParentId());
		reuseMap.put("showOrder", queryMenu.getShowOrder());
		
		//进行showOrder的校验
		if(!validateShowOrder(reuseMap)){
			sb.append("当前系统下展示顺序重复,");
		}
		
		//如果存在错误信息
		if(CommonUtil.isNotNull(sb.toString())){
			//加上一句
			sb.append("请重新填写!");
			//返回错误信息
			return 	sb.toString();
		}
			return null;
	}
	
	/**
	 * @Title: validateShowOrder 
	 * @Description: 验证菜单的排序是否合法
	 * @param @param menu
	 * @param @return   
	 * @return boolean
	 */
	private boolean validateShowOrder(Map<String,Object> queryMap){
		
		//设置默认返回值
		boolean result = true ;
		//如果存在上级菜单
		if(CommonUtil.isNotNull(queryMap.get("parentId"))){
			//通过上级菜单id查询该菜单的所有子菜单排序
			List<Menu> DBChildMenus = getDBMenu(queryMap);
			//如果子菜单不为空
			if(CommonUtil.isNotNull(DBChildMenus)){
				//循环取值
				for (Menu DBmenu : DBChildMenus) {
					//获取展示顺序
					String showOrder = (String) queryMap.get("showOrder");
					//如果当前设置的showOrder数据库中已存在
					if(CommonUtil.isNotNull(showOrder) && DBmenu.getShowOrder().equals(showOrder)){
						return false;
					}
				}
			}
		}
		return result;
	}
	
}
