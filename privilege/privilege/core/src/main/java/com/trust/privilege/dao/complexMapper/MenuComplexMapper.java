package com.trust.privilege.dao.complexMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.trust.privilege.model.complex.MenuDo;
/**
 * @ClassName: MenuComplexMapper 
 * @Description: 菜单模块dao层
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月21日 上午10:45:07
 */
public interface MenuComplexMapper {
	
	/**
	 * @Title: getAllMenus 
	 * @Description: 根据条件查询菜单
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getAllMenus(Map<String, Object> map);
	
	/**
	 * @Title: getAllMenusBySuperAdmin 
	 * @Description: 超级管理员身份查询所有的菜单
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getAllMenusBySuperAdmin(Map<String, Object> map);
	
	/**
	 * @Title: selectSysMenu 
	 * @Description: 根据用户名，系统CD，查询菜单 
	 * @param @param map
	 * @param @return   
	 * @return List<MenuDo>
	 */
	public List<MenuDo> selectSysMenu(Map<String, String> map);
	
	/**
	 * @Title: allMenuBySys 
	 * @Description: 根据系统CD查询菜单
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuDo>
	 */
	public List<MenuDo> allMenuBySys(String systemId);
	
	/**
	 * @Title: getAllParentMenusBySuperAdmin 
	 * @Description: 超级管理员查询父级菜单
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getAllParentMenusBySuperAdmin(Map<String, Object> map);
	
	/**
	 * @Title: getAllParentMenus 
	 * @Description: 非超管查询父级菜单
	 * @param @param map
	 * @param @return   
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getAllParentMenus(Map<String, Object> map);
	
	/**
	 * @Title: selectSysMenuByUserName 
	 * @Description: 通过用户名和系统CD获取菜单，用于portal首页数据渲染
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuDo>
	 */
	public List<MenuDo> selectSysMenuByUserName(@Param("userName")String userName,@Param("systemId")String systemId);
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 根据菜单Id得到拥有此菜单的角色
	 * @param @param menuId
	 * @param @return   
	 * @return List<String>
	 */
	public List<String> getRolesByMenuId(@Param("menuId") String menuId);
	
}