package com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.RoleUserRel;
import com.oa.mapper.RoleUserRelMapper;
import com.oa.service.RoleUserRelService;
@Service
public class RoleUserRelServiceImpl implements RoleUserRelService {
	@Resource
	private RoleUserRelMapper roleUserMapper;

	@Override
	public int deleteByPrimaryKey(Long roleUserRelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoleUserRel selectByPrimaryKey(Long roleUserRelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RoleUserRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectRoleIdByUserId(long userId) {
		// TODO Auto-generated method stub
		return roleUserMapper.selectRoleIdByUserId(userId);
	}

	@Override
	public List<RoleUserRel> selectUserByRoleId(long roleId) {
	
		return roleUserMapper.selectUserByRoleId(roleId);
	}

}
