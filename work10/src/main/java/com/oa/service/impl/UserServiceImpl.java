package com.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.User;
import com.oa.mapper.UserMapper;
import com.oa.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	//注入dao
	@Resource
	private UserMapper userMapper;

	public int deleteByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User selectByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userMapper.login(user);
	}

	@Override
	public List<User> selectUserListByOrgId(long orgId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserListByOrgId(orgId);
	}

	@Override
	public Map<String, Object> selectProvinceNameAndCount() {
		List<Map<String, Object>> list = userMapper.selectProvinceNameAndCount();
		//省份的名字，人数
		List list2 = new ArrayList();
		for (Map<String, Object> object : list) {
			Map<String, Object> map = object;//一个map  对应sql查询出来的每一行
			list2.add(map.get("name"));
		}
		//list: 对应的就是echarts的pie图的 option.series[0].data
		//list2: 对应的就是echarts的pie图的 option.legend.data
		//北京市 上海  广东省
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("series0Data", list);
		resMap.put("legendData", list2);
		return resMap;
	}

	@Override
	public Map<String, Object> selectProviceAndSex() {
		List<Map<String, Object>> list = userMapper.selectProviceAndSex();
		List legendData = new ArrayList();
		List  boyData = new ArrayList();
		List  girlData = new ArrayList();
		List  securytData = new ArrayList();
		for (Map<String, Object> map : list) {
			Object privinceName = map.get("name"); 
			if (privinceName==null||"".equals(privinceName)) {
				privinceName = "其他";
			}
			legendData.add(privinceName);
			
			Object boyCount = map.get("boy");
			boyData.add(boyCount);
			girlData.add(map.get("girl"));
			securytData.add(map.get("securty"));	
		}
		
		Map<String, Object> resMap = new HashMap<String,Object>();
		resMap.put("legendData", legendData);
		resMap.put("boyData", boyData);
		resMap.put("girlData", girlData);
		resMap.put("securytData", securytData);
		return resMap;
	}

	@Override
	public Map<String, Object> selectUserListByPage(Map<String, Object> paramMap) {
		//转化请求参数
		paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
		
		List<User> list = userMapper.selectUserListByPage(paramMap);
		long total = userMapper.selectUserCount(paramMap);
		
		 Map<String, Object>  resMap = new HashMap<String,Object>();
		resMap.put("userList", list);
		resMap.put("total", total);
		return  resMap;
	}

	@Override
	public List<Map<String, Object>> exportUsers() {
		// TODO Auto-generated method stub
		return userMapper.exportUsers();
	}

	@Override
	public int InsertUsers(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return userMapper.importUsers(list);
	}

	@Override
	public Map<String, Object> selectRoleUserList(Map<String, Object> paramMap) {
		//转化请求参数
		paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
		
		List<User> list = userMapper.selectRoleUserList(paramMap);
		int total = userMapper.selectRoleUserListCount(paramMap);
		
		 Map<String, Object>  resMap = new HashMap<String,Object>();
		resMap.put("userList", list);
		resMap.put("total", total);
		
		return resMap;
					
	}

}
