package com.oa.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.mapper.AreaMapper;
import com.oa.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Resource
	private AreaMapper areaMapper;

	@Override
	public List<Map<String, Object>> selectAreaByParentId(String parentId) {
		
		
		return areaMapper.selectAreaByParentId(parentId);
	}

}
