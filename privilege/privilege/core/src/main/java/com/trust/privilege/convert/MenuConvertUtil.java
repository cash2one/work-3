package com.trust.privilege.convert;

import java.util.ArrayList;
import java.util.List;

import com.trust.privilege.facade.model.MenuVO;
import com.trust.privilege.facade.model.PrivilegeResVO;
import com.trust.privilege.facade.model.complex.MenuResVO;
import com.trust.privilege.facade.model.complex.QueryMenuVO;
import com.trust.privilege.model.Menu;
import com.trust.privilege.model.complex.MenuDo;
import com.trust.privilege.model.complex.PrivilegeResDo;
import com.trust.privilege.model.complex.QueryMenu;
import com.trust.privilege.util.CommonUtil;
import com.trust.privilege.util.JavaBeanUtil;
/**
 * @ClassName: MenuConvertUtil 
 * @Description: 菜单模块实例转换
 * @author zyn161616@163.com
 * @version 创建时间：2017年4月28日 下午3:22:51
 */
public class MenuConvertUtil {
	
	/**
	 * @Title: convertMenuDoToModel 
	 * @Description: 后台Do转换前台Vo
	 * @param @param menu
	 * @param @return   
	 * @return MenuVO
	 */
	public static MenuVO convertMenuDoToModel(Menu menu){
		//如果参数不为空
		if(CommonUtil.isNotNull(menu)){
			//创建返回对象
			MenuVO menuVO = new MenuVO();
			//进行赋值
			JavaBeanUtil.copyBean(menu,menuVO);
			//返回
			return menuVO;
		}
			return null;
	}
	
	/**
	 * @Title: convertModelToDo 
	 * @Description: 前台Vo转后台Do
	 * @param @param menuVo
	 * @param @return   
	 * @return Menu
	 */
	public static Menu convertModelToDo(MenuVO menuVo){
		//如果参数不为空
		if (CommonUtil.isNotNull(menuVo)) {
			//创建返回对象
			Menu menu = new Menu();
			//进行赋值
			JavaBeanUtil.copyBean(menuVo, menu);
			//返回
			return menu;
		}
			return null;
	}
	
	/**
	 * @Title: menuDoListToMenuVoList 
	 * @Description: 后台菜单集合转前台菜单集合
	 * @param @param menuDoList
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	public static List<MenuResVO> menuDoListToMenuVoList(List<MenuDo> menuDoList){
		//如果参数不为空
		if (CommonUtil.isNotNull(menuDoList)) {
			//创建返回对象
			List<MenuResVO> resultList=new ArrayList<>();
			//循环取值
			for (MenuDo menuDo : menuDoList) {
				//进行类型转换并且加入集合中
				resultList.add(menuDoToMenuVo(menuDo));
			}
			return resultList;
		}
			return null;
	}
	
	public static MenuResVO menuDoToMenuVo(MenuDo menuDo){
		MenuResVO vo=new MenuResVO();
		List<MenuResVO> vos=new ArrayList<MenuResVO>();
		if(menuDo.getChilds()!=null){
			for(MenuDo md:menuDo.getChilds()){
				MenuResVO vo1=menuDoToMenuVo(md);
				vos.add(vo1);
			}
		}
		vo.setMenus(vos);
		vo.setMenuId(menuDo.getMenuId());
		vo.setMenuName(menuDo.getMenuName());
		vo.setStateType(menuDo.getStateType());
		vo.setUrl(menuDo.getUrl());
		List<PrivilegeResVO> resVos=new ArrayList<PrivilegeResVO>();
		if(menuDo.getPrivileges()!=null){
			for(PrivilegeResDo res:menuDo.getPrivileges()){
				PrivilegeResVO resVo =PrivilegeConvertUtil.privilegeResToPrivilegeResVo(res);
				resVos.add(resVo);
			}
		}
		vo.setList(resVos);
		return vo;
	};
	
	/**
	 * @Title: menuDoToMenuVo 
	 * @Description: 后台do集合转前台vo集合
	 * @param @param menuDo
	 * @param @return   
	 * @return List<MenuResVO>
	 */
	public static List<MenuResVO> menuDoToMenuVo(List<MenuDo> menuDos){
		//如果参数不为空
		if (CommonUtil.isNotNull(menuDos)) {
			//创建返回集合
			List<MenuResVO> resultList = new ArrayList<>();
			//循环转换
			for (MenuDo menuDo : menuDos) {
				resultList.add(menuDoToMenuVo(menuDo));
			}
			return resultList;
		}
			return null;
	}
	
	/**
	 * @Title: queryMenuVOConvert 
	 * @Description: 前台查询Vo转后台查询Model
	 * @param @param queryMenuVO
	 * @param @return   
	 * @return QueryMenu
	 */
	public static QueryMenu queryMenuVOConvert(QueryMenuVO queryMenuVO) {
		//如果参数不为空
		if(CommonUtil.isNotNull(queryMenuVO)){
			//创建返回对象
			QueryMenu queryMenu=new QueryMenu();
			//进行赋值
			JavaBeanUtil.copyBean(queryMenuVO, queryMenu);
			//返回
			return queryMenu;
		}
			return null;
	}
	
}
