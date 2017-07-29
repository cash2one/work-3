package com.trust.privilege.service;

import java.util.List;
import java.util.Map;

import com.trust.privilege.model.Menu;
import com.trust.privilege.model.complex.MenuDo;
import com.trust.privilege.model.complex.QueryMenu;
/**
 * @ClassName: MenuService 
 * @Description: 菜单Service层接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月7日 下午5:05:11
 */
public interface MenuService {
	
	/**
	 * @Title: insertMenu 
	 * @Description: 添加菜单信息
	 * @param @param menu
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> insertMenu(Menu menu);
	
	/**
	 * @Title: deleteMenu 
	 * @Description: 删除菜单信息
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> deleteMenu(String menuId,String userName);
	
	/**
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAllParentMenus(QueryMenu queryMenu);
	
	/**
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menu
	 * @param @return   
	 * @return Map<String,Object>
	 */
	public Map<String,Object> updateMenu(Menu menu);
	
	/**
	 * @Title: getAllMenus 
	 * @Description: 根据系统CD获取该用户在此系统的所有菜单
	 * @param @param queryMenu
	 * @param @return   
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAllMenus(QueryMenu queryMenu);
	
	/**
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据菜单Id得到该菜单信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return Menu
	 */
	public Menu getMenuByPrimaryKey(String menuId);
	
	/**
	 * @Title: selectSysMenu 
	 * @Description: 系统初始化的时候,加载左侧菜单
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	public List<MenuDo> selectSysMenu(String userName,String systemId);
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色信息
	 * @param @param menuId
	 * @param @return   
	 * @return String
	 */	
	public String getRolesByMenuId(String menuId);
	
	/**
	 * @Title: getMenu 
	 * @Description: 通过条件获取该菜单信息
	 * @param @param menu
	 * @param @return   
	 * @return 
	 */
	public List<Menu> getDBMenu(Map<String,Object> queryMap);

}
