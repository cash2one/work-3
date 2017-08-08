package com.xinmei.common.exception.code;

/**
* @ClassName: ExcepitonCodeEnum
* @Description: 系统内异常代码枚举，不存在与元数据中
* @author jason.zhang
* @date 2016年11月15日 下午3:49:22
*
*/ 
public enum ExcepitonCodeEnum {
	UNKNOWERROR("UNKNOWERROR","未知错误","请稍等，系统努力加载中..."),
	RUNTIMEERROR("RUNTIMEERROR","运行时错误","请稍等，系统努力加载中...");

	private String errorMsg;
	private String errorCode;
	private String errorName;

	private ExcepitonCodeEnum(String code, String codename,String codemsg) {
		this.errorCode = code;
		this.errorMsg = codemsg;
		this.errorName = codename;
	}

	public String getErrorCode() {

		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getErrorName() {
		return errorName;
	}


}