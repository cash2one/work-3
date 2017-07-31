package com.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.Org;
import com.oa.mapper.OrgMapper;
import com.oa.service.OrgService;
@Service
public class OrgServiceImpl implements OrgService {
	
	@Resource
	private OrgMapper orgMapper;

	@Override
	public int deleteByPrimaryKey(Long orgId) {
		// TODO Auto-generated method stub
		return orgMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public int insert(Map<String, Object> paramMap){
		// TODO Auto-generated method stub
		return orgMapper.insert(paramMap);
	}

	@Override
	public int insertSelective(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Org selectByPrimaryKey(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Org record) {
		// TODO Auto-generated method stub
		return orgMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Org record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> selectOrgList(Map<String, Object> paramMap) {
		//起始记录  每页显示多少条做处理
		
		paramMap.put("startIndex",Integer.parseInt((paramMap.get("startIndex")+"")));
		paramMap.put("pageSize",Integer.parseInt((paramMap.get("pageSize")+"")));
		//求出所有数据
		List<Org> list = orgMapper.selectOrgByPage(paramMap);
		//总的记录数
		long total = orgMapper.selectTotalCount(paramMap);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgList", list);
		map.put("total", total);
		return map;
	}

	@Override
	public List<Org> selectOrgListByParentId(long parentId) {
		
		return orgMapper.selectOrgListByParentId(parentId);
	}
	
	public long selectOrgId(){
		return orgMapper.selectOrgId();//查询id的最大值
	}

	@Override
	public Org selectOrgAndParentByOrgId(long orgId) {
		// TODO Auto-generated method stub
		return orgMapper.selectOrgAndParentByOrgId(orgId);
	}
	 public int selectOrgIdByOrgName(String orgName){
		return orgMapper.selectOrgIdByOrgName(orgName); 
	 }

	@Override
	public Map<String, Object> selectRoleOrgList(Map<String, Object> paramMap) {
		paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
		paramMap.put("roleId", Integer.parseInt(paramMap.get("roleId")+""));

		List<Org> list = orgMapper.selectRoleOrgList(paramMap);
		int total = orgMapper.selectRoleOrgListCount(paramMap);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgList", list);
		map.put("total", total);
		return map;
	}

}
