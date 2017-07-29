package com.trust.privilege.test.facade;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.trust.privilege.facade.MenuServerFacade;
import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
import com.trust.privilege.model.Menu;
import com.trust.privilege.service.MenuService;
import com.trust.privilege.test.base.AbstractTestBase;
/**
 * @ClassName: MenuServerFacadeTest 
 * @Description: 菜单facade单测
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月21日 下午8:31:06
 */
@SuppressWarnings("unchecked")
public class MenuServerFacadeTest extends AbstractTestBase{
	
	/**IOC容器获取menuServerFacade实例*/
	private MenuServerFacade menuServerFacade = 
				(MenuServerFacade) this.applicationContext.getBean("menuServerFacade");
	
	/**IOC容器获取menuService实例*/
	private MenuService menuService = 
		   			(MenuService) this.applicationContext.getBean("menuService");
	
	/**全局变量，查询Map*/
	private Map<String, Object> queryMap = new HashMap<>();
	
	/**
	 * @Title: getAllMenusTestIsSuper 
	 * @Description: 单测,根据系统CD获取该用户在此系统的所有菜单(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllMenusTestIsSuper(){
		//创建菜单对象
		QueryMenuVO queryMenuVO = new QueryMenuVO();
		//设置当前操作人
		queryMenuVO.setUserName("admin");
		//设置系统CD
		queryMenuVO.setSystemPlatformCd("1");
		//进行查询
		Map<String,Object> resultMap = menuServerFacade.getAllMenus(queryMenuVO);
		//获取该系统下菜单
		List<Map<String,Object>> menus = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false,menus.isEmpty());
	}
	
	/**
	 * @Title: getAllMenusTestIsTestUser 
	 * @Description: 单测,根据系统CD获取该用户在此系统的所有菜单(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void getAllMenusTestIsTestUser(){
		//创建菜单对象
		QueryMenuVO queryMenuVO = new QueryMenuVO();
		//设置当前操作人
		queryMenuVO.setUserName("testUser");
		//设置系统CD
		queryMenuVO.setSystemPlatformCd("1");
		//进行查询
		Map<String,Object> resultMap = menuServerFacade.getAllMenus(queryMenuVO);
		//获取该系统下菜单
		List<Map<String,Object>> menus = (List<Map<String, Object>>) resultMap.get("list");
		//断言
		assertEquals(false,menus.isEmpty());
	}

	/**
	 * @Title: selectSysMenuTesQueryIsNull 
	 * @Description: 单测,系统初始化的时候,加载左侧菜单(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysMenuTesQueryIsNull(){
		//进行查询
		List<MenuResVO> menus = menuServerFacade.selectSysMenu(null, null);
		//断言
		assertEquals(true, menus.isEmpty());
	}
	
	/**
	 * @Title: selectSysMenuTestIsSuper 
	 * @Description: 单测,系统初始化的时候,加载左侧菜单(超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysMenuTestIsSuper(){
		//查询
		List<MenuResVO> menus = menuServerFacade.selectSysMenu("admin", "4");
		//断言
		assertEquals(false, menus.isEmpty());
	}
	
	/**
	 * @Title: selectSysMenuTest 
	 * @Description: 单测,系统初始化的时候,加载左侧菜单(非超管)
	 * @param    
	 * @return void
	 */
	@Test
	public void selectSysMenuTest(){
		//查询
		List<MenuResVO> menus = menuServerFacade.selectSysMenu("testUser", "4");
		//断言
		assertEquals(false, menus.isEmpty());
	}

	/**
	 * @Title: insertMenuTestQueryIsNull 
	 * @Description: 单测,新增菜单信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertMenuTestQueryIsNull(){
		//创建菜单Model
		MenuVO menu = new MenuVO();
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.insertMenu(menu);
		//断言
		assertEquals("菜单名称不可以为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertMenuTestQuery 
	 * @Description: 单测,新增菜单信息(菜单名重复)
	 * @param    
	 * @return void
	 */
	@Test
	public void insertMenuTestQuery(){
		//创建菜单Model
		MenuVO menu = new MenuVO();
		//设置菜单名称
		menu.setMenuName("菜单管理");
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.insertMenu(menu);
		//断言
		assertEquals("当前系统下菜单名称重复,请重新填写!", resultMap.get("fail"));
	}
	
	/**
	 * @Title: insertMenuTest 
	 * @Description: 单测,新增菜单信息
	 * @param    
	 * @return void
	 */
	@Test
	public void insertMenuTest(){
		//创建菜单Model
		MenuVO menu = new MenuVO();
		//设置当前操作人
		menu.setCreateUserId("admin");
		//设置菜单名称
		menu.setMenuName("testMenu");
		//设置系统CD
		menu.setSystemPlatformCd("1");
		//设置状态
		menu.setStateCd("1");
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.insertMenu(menu);
		//断言
		assertEquals("添加菜单成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: getMenuByPrimaryKeyTestQueryIsNull 
	 * @Description: 单测,根据菜单Id得到该菜单信息(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getMenuByPrimaryKeyTestQueryIsNull(){
		//进行查询并且断言
		assertEquals(false, menuServerFacade.getMenuByPrimaryKey(null) != null );
	}
	
	/**
	 * @Title: getMenuByprimaryKeyTest 
	 * @Description: 单测,根据菜单Id得到该菜单信息(系统上及菜单获取showOrder)
	 * @param    
	 * @return void
	 */
	@Test
	public void getMenuByprimaryKeyTest(){
		queryMap.put("menuName","testMenu");
		queryMap.put("stateCd","1");
		//进行查询
		Menu menus = menuService.getDBMenu(queryMap).get(0);
		//进行查询并且断言
		assertEquals(true, menuServerFacade.getMenuByPrimaryKey(menus.getMenuId()) != null );			
	}
	
	/**
	 * @Title: getRolesByMenuIdTestQueryIsNull 
	 * @Description: 单测,查询该菜单信息时，获取菜单关联的角色(参数为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void getRolesByMenuIdTestQueryIsNull(){
		//进行查询,并断言
		assertEquals(false, menuServerFacade.getRolesByMenuId(null) != null);
	}
	
	/**
	 * @Title: getRolesByMenuIdTest 
	 * @Description: 单测,查询该菜单信息时，获取菜单关联的角色
	 * @param    
	 * @return void
	 */
	@Test
	public void getRolesByMenuIdTest(){
		//设置查询条件
		queryMap.put("menuName","testMenu");
		queryMap.put("stateCd","1");
		//进行查询
		Menu menus = menuService.getDBMenu(queryMap).get(0);
		//进行查询,并断言
		assertEquals(true, menuServerFacade.getRolesByMenuId(menus.getMenuId()) != null);
	}
	
	/**
	 * @Title: updateMenuTestQueryIsNull 
	 * @Description: 单测,修改菜单信息(菜单Id参数为空)
	 * @param    
	 * @return void
	 */					
	@Test
	public void updateMenuTestQueryIsNull(){
		//创建Model
		MenuVO menuVO = new MenuVO();
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.updateMenu(menuVO);
		//断言
		assertEquals("菜单Id不可为空", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updateMenuTestQuery 
	 * @Description: 单测,修改菜单信息(菜单名重复)
	 * @param    
	 * @return void
	 */
	@Test
	public void updateMenuTestQuery(){
		//设置查询条件
		queryMap.put("menuName","testMenu");
		queryMap.put("stateCd","1");
		//进行查询
		Menu menus = menuService.getDBMenu(queryMap).get(0);		
		//创建Model
		MenuVO menuVO = new MenuVO();
		//设置菜单Id
		menuVO.setMenuId(menus.getMenuId());
		//设置菜单名称
		menuVO.setMenuName("testMenu");
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.updateMenu(menuVO);
		//断言
		assertEquals("当前系统下菜单名称重复,请重新填写!", resultMap.get("fail"));
	}
	
	/**
	 * @Title: updateMenuTest 
	 * @Description: 单测,修改菜单信息
	 * @param    
	 * @return void
	 */
	@Test
	public void updateMenuTest(){
		//设置查询条件
		queryMap.put("menuName","testMenu");
		queryMap.put("stateCd","1");
		//进行查询
		Menu menus = menuService.getDBMenu(queryMap).get(0);
		//创建Model
		MenuVO menuVO = new MenuVO();
		//设置菜单Id
		menuVO.setMenuId(menus.getMenuId());
		//设置系统CD
		menuVO.setSystemPlatformCd("1");
		//设置菜单名称
		menuVO.setMenuName("testMenu002");
		//调用方法
		Map<String,Object> resultMap = menuServerFacade.updateMenu(menuVO);
		//断言
		assertEquals("修改菜单信息成功", resultMap.get("success"));
	}
	
	/**
	 * @Title: deleteMenuTestMenuIdIsNull 
	 * @Description: 单测, 删除菜单信息(菜单Id为空)
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteMenuTestMenuIdIsNull(){
		//删除，并断言
		assertEquals(true, menuServerFacade.deleteMenu(null, "admin").isEmpty());
	}
	
	/**
	 * @Title: deleteMenuTest 
	 * @Description: 单测, 删除菜单信息
	 * @param    
	 * @return void
	 */
	@Test
	public void deleteMenuTest(){
		//设置查询条件
		queryMap.put("menuName","testMenu001");
		queryMap.put("stateCd","1");
		//进行查询
		Menu menus = menuService.getDBMenu(queryMap).get(0);
		//删除，并断言
		assertEquals("该菜单下有角色，不可删除,请先删除菜单下的关联数据！", menuServerFacade.deleteMenu(menus.getMenuId(), "admin").get("fail"));
	}
	
}
