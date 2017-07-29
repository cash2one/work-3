package com.trust.privilege.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.trust.privilege.convert.MenuConvertUtil;
import com.trust.privilege.facade.MenuServerFacade;
import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
import com.trust.privilege.service.MenuService;
/**
 * @ClassName: MenuServerFacade 
 * @Description: 菜单模块facade接口实现
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月7日 下午5:01:05
 */
public class MenuServerFacadeImpl implements MenuServerFacade{
	
	/**依赖注入菜单Service*/
	@Resource
	private MenuService menuService;
	
	/**
	 * @Title: insertMenu 
	 * @Description: 新增菜单信息
	 * @param @param menuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> insertMenu(MenuVO menuVO) {
		return menuService.insertMenu(MenuConvertUtil.convertModelToDo(menuVO));
	}

	/**
	 * @Title: deleteMenu 
	 * @Description: 删除菜单信息
	 * @param @param menuId
	 * @param @param userName
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object>  deleteMenu(String menuId,String userName) {
		return menuService.deleteMenu(menuId,userName);
	}

	/**
	 * @Title: getAllParentMenus 
	 * @Description: 修改菜单时查询菜单的父级菜单列表的接口
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getAllParentMenus(QueryMenuVO queryMenuVO) {
		return menuService.getAllParentMenus(
				MenuConvertUtil.queryMenuVOConvert(queryMenuVO));
	}

	/**
	 * @Title: updateMenu 
	 * @Description: 修改菜单信息
	 * @param @param menuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> updateMenu(MenuVO menuVO) {
		return menuService.updateMenu(MenuConvertUtil.convertModelToDo(menuVO));
	}

	/**
	 * @Title: getAllMenus 
	 * @Description: 根据系统CD获取该用户在此系统的所有菜单
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getAllMenus(QueryMenuVO queryMenuVO) {
		return menuService.getAllMenus(
				MenuConvertUtil.queryMenuVOConvert(queryMenuVO));
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
	public  List<MenuResVO> selectSysMenu(String userName, String systemId) {
		return MenuConvertUtil.menuDoListToMenuVoList(
					menuService.selectSysMenu(userName, systemId));
	}
	
	/**
	 * @Title: getMenuByPrimaryKey 
	 * @Description: 根据系统CD得到该菜单信息(系统上及菜单获取showOrder)
	 * @param @param menuId
	 * @param @return   
	 * @return MenuVO
	 */
	@Override
	public MenuVO getMenuByPrimaryKey(String menuId) {
		return MenuConvertUtil.convertMenuDoToModel(menuService.getMenuByPrimaryKey(menuId));
	}
	
	/**
	 * @Title: getRolesByMenuId 
	 * @Description: 查询该菜单信息时，获取菜单关联的角色信息
	 * @param @param menuId
	 * @param @return   
	 * @return String
	 */
	@Override
	public String getRolesByMenuId(String menuId) {
		return menuService.getRolesByMenuId(menuId);
	}
}
