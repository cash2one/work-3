package com.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.Role;
import com.oa.mapper.RoleMapper;
import com.oa.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public int deleteByPrimaryKey(Long roleId) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Role selectByPrimaryKey(Long roleId) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> selectRoleListByPage(Map<String, Object> paramMap) {
		paramMap.put("startIndex",Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize",Integer.parseInt(paramMap.get("pageSize")+""));

		List<Role> roles = roleMapper.selectRoleListByPage(paramMap);
		long total = roleMapper.selectRoleCount(paramMap);
		Map<String, Object> resMap = new HashMap<String,Object>();
		resMap.put("rolesList", roles);
		resMap.put("total", total);
		
		return resMap;
	}

	@Override
	public List<Role> selectAllRoleList() {
		// TODO Auto-generated method stub
		return roleMapper.selectAllRoleList();
	}

}
