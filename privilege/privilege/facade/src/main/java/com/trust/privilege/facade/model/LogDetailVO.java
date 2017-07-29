package com.trust.privilege.facade.model;

import java.util.Date;

public class LogDetailVO {
	
	private String logId;
	/**操作的资源类型*/
    private String typeId;
    /**操作的用户名称*/
    private String userName;
    /**操作的资源名称*/
    private String resourceName;
    /**操作的系统名称，用于多条件查询*/
	private String systemPlatformName;
	/**操作类型如（增删改）*/
    private String method;

    private Date createTime;

    private String message;
    
    private Integer page;
    
	private Integer totalPage;
   
	private Integer pageSize;
	
	private Integer count;
	
	private String startDate;
	
	private String endDate;

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

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

	public Integer getPageSize() {
		return pageSize;
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

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getResourceName() {
			return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getSystemPlatformName() {
		return systemPlatformName;
	}

	public void setSystemPlatformName(String systemPlatformName) {
		this.systemPlatformName = systemPlatformName;
	}

	@Override
	public String toString() {
		return "LogDetailVO [logId=" + logId + ", typeId=" + typeId + ", userName=" + userName + ", resourceName="
				+ resourceName + ", systemPlatformName=" + systemPlatformName + ", method=" + method + ", createTime="
				+ createTime + ", message=" + message + ", page=" + page + ", totalPage=" + totalPage + ", pageSize="
				+ pageSize + ", count=" + count + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	public LogDetailVO() {
		super();
	}
	
}