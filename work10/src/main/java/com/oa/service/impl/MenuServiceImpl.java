package com.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.entity.Menu;
import com.oa.mapper.MenuMapper;
import com.oa.service.MenuService;
@Service
//@Transactional
public class MenuServiceImpl implements MenuService {
	@Resource
	MenuMapper meneMapper;

	@Override
	public int deleteByPrimaryKey(Long menuId) {
		// TODO Auto-generated method stub
		return meneMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(Menu record) {
		
		
		int 	i = meneMapper.insert(record);
		System.out.println(i/0);
		
		
		return i;
	}

	@Override
	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Menu selectByPrimaryKey(Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		// TODO Auto-generated method stub
		return meneMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Menu> selectMenuByRoleId(List roleIdList) {
		// TODO Auto-generated method stub
		return meneMapper.selectMenuByRoleId(roleIdList);
	}

	@Override
	public Map<String, Object> selectMenuListByPage(Map<String, Object> paramMap) {
		//startIndex  pageSize
		paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
		
		List<Menu> menus = meneMapper.selectMenuListByPage(paramMap);
		long total = meneMapper.selectTotalCount(paramMap);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("menuList", menus);
		resMap.put("total", total);
		
		return resMap;
	}

	@Override
	public  List<Menu> selectDirByParentId(long parentId) {
		// TODO Auto-generated method stub
		return meneMapper.selectDirByParentId(parentId);
	}

	@Override
	public Menu selectMenuAndParenMenuByMenuId(long menuId) {
		// TODO Auto-generated method stub
		return meneMapper.selectMenuAndParenMenuByMenuId(menuId);
	}

	@Override
	public Map<String, Object> selectRoleMenuList(Map<String, Object> paramMap) {
		//startIndex  pageSize
				paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
				paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
				
				List<Menu> menus = meneMapper.selectRoleMenuList(paramMap);
				long total = meneMapper.selectRoleMenuListCount(paramMap);
				
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("menuList", menus);
				resMap.put("total", total);
				
				return resMap;
			}

	@Override
	public List<Menu> selectMenuByParentId(long menuParentId) {
		// TODO Auto-generated method stub
		return meneMapper.selectMenuByParentId(menuParentId);
	}


}
