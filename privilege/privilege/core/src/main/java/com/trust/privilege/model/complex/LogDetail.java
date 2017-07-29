package com.trust.privilege.model.complex;

import java.util.Date;
/***
 * @ClassName: LogDetail 
 * @Description: 日志model
 * @author zyn161616@163.com
 * @version 创建时间：2017年3月6日 下午4:07:04
 */
public class LogDetail {
	//日志id
	private String logId;
	//类型id
    private String typeId;
    //操作人
    private String userName;
    //资源名称
    private String resourceName;
    //系统名称
    private String systemPlatformName;
    //操作类型
    private String method;
    //创建时间
    private Date createTime;
    //描述
    private String message;
    /**后台分页条件*/
    private Integer page;
    private Integer pageSize;
	private Integer count;
	private Integer totalPage;
	
	/**根据时间段查询日志*/
	/**起始时间*/
	private String startDate;
	/**结束时间*/
	private String endDate;

	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	

	public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getSystemPlatformName() {
		return systemPlatformName;
	}

	public void setSystemPlatformName(String systemPlatformName) {
		this.systemPlatformName = systemPlatformName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public LogDetail(String logId, String typeId, String userName, String resourceName, String systemPlatformName,
			String method, Date createTime, String message) {
		super();
		this.logId = logId;
		this.typeId = typeId;
		this.userName = userName;
		this.resourceName = resourceName;
		this.systemPlatformName = systemPlatformName;
		this.method = method;
		this.createTime = createTime;
		this.message = message;
	}

	public LogDetail() {
		super();
	}
	
	
}