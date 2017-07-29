package com.trust.privilege.facade;

import java.util.List;
import java.util.Map;

import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
/**
 * @ClassName: MenuServerFacade 
 * @Description: 菜单模块facade接口
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月7日 下午5:01:05
 */
public interface MenuServerFacade {
	
	/**
	 * @Title: insertMenu 
	 * @Description: 新增菜单信息
	 * @param @param menu
	 * @param @return   
	 * @return Map<String, Object>
	 */
	Map<String, Object> insertMenu(MenuVO menu);
	
	/**
	 * @Title: deleteMenu 
	 * @Description: 删除菜单信息
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	Map<String, Object> deleteMenu(String menuId,String userName);
	
	/**
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return Map<String,Object>
	 */
	Map<String, Object> getAllParentMenus(QueryMenuVO queryMenuVO);
	
	/**
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	Map<String, Object> updateMenu(MenuVO menuVO);
	
	/**
	 * @Title: getAllMenus 
	 * @Description: 根据系统CD获取该用户在此系统的所有菜单
	 * @param @param queryMenu
	 * @param @return   
	 * @return Map<String, Object>
	 */
	Map<String, Object> getAllMenus(QueryMenuVO queryMenuVO);
	
	/**
	 * @Title: selectSysMenu 
	 * @Description: 系统初始化的时候,加载左侧菜单
	 * @param @param userName
	 * @param @param systemId
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	List<MenuResVO> selectSysMenu(String userName,String systemId);
	
	/**
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据系统CD得到该菜单信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return MenuVO
	 */
	MenuVO getMenuByPrimaryKey(String menuId);
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色信息
	 * @param @param menuId
	 * @param @return   
	 * @return String
	 */
	String getRolesByMenuId(String menuId);
	
}
