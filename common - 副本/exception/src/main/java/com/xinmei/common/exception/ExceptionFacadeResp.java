package com.xinmei.common.exception;

import com.xinmei.common.basic.response.AbstractFacadeResp;

/**
 * 响应数据封装
 * <p/>
 * Created by jason.zhang on 16/11/11.
 */
public class ExceptionFacadeResp extends AbstractFacadeResp {
	
	private String errorDesc;
	
	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public ExceptionFacadeResp(String errorcode,String errormsg) {
		super(false);
		this.setErrorCode(errorcode);
		this.setResultMsg(errormsg);
	}
	
	public ExceptionFacadeResp(String errorcode,String errormsg,String errordesc) {
		super(false);
		this.setErrorCode(errorcode);
		this.setResultMsg(errormsg);
		this.setErrorDesc(errordesc);
	}

}
