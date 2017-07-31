package com.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.RoleOrgRel;
import com.oa.mapper.RoleOrgRelMapper;
import com.oa.service.RoleOrgRelService;
@Service
public class RoleOrgRelServlceImpl  implements RoleOrgRelService{
	
	@Resource
	private RoleOrgRelMapper roleOrgRelMapper;

	@Override
	public int deleteByPrimaryKey(Long roleOrgRelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoleOrgRel selectByPrimaryKey(Long roleOrgRelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RoleOrgRel record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectRoleIdByOrgId(long orgId) {
		// TODO Auto-generated method stub
		return roleOrgRelMapper.selectRoleIdByOrgId(orgId);
	}

	@Override
	public int deleteRoleOrgRel(int orgId) {
		// TODO Auto-generated method stub
		return roleOrgRelMapper.deleteRoleOrgRel(orgId);
	}

}
