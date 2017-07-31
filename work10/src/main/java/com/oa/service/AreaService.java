package com.oa.service;

import java.util.List;
import java.util.Map;

public interface AreaService {
	
	public    List<Map<String, Object>>  selectAreaByParentId(String parentId);

}
