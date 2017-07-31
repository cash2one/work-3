package com.oa.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.Menu;
import com.oa.entity.RoleMenuRel;
import com.oa.mapper.RoleMenuRelMapper;
import com.oa.service.RoleMenuRelService;
@Service

public class RoleMenuRelServiceImpl implements RoleMenuRelService {
	@Resource
	private RoleMenuRelMapper roleMenuRelMapper;

	@Override
	public int deleteByPrimaryKey(Long roleMenuRel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RoleMenuRel record) {
		// TODO Auto-generated method stub
		return roleMenuRelMapper.insert(record);
	}

	@Override
	public int insertSelective(RoleMenuRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoleMenuRel selectByPrimaryKey(Long roleMenuRel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RoleMenuRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RoleMenuRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoleMenuRel> selectMenuRoleRelByMenuId(long menuId) {
		// TODO Auto-generated method stub
		return roleMenuRelMapper.selectMenuRoleRelByMenuId(menuId);
	}

	
}
