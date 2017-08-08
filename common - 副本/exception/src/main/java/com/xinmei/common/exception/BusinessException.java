package com.xinmei.common.exception;

import com.xinmei.common.exception.code.ExcepitonCodeEnum;
import com.xinmei.common.metadata.dto.MetaDataCodeValue;
import com.xinmei.common.metadata.util.MetaDataUtil;

/**
* @ClassName: BusinessException
* @Description: 用于业务异常抛出
* @author jason.zhang
* @date 2016年11月15日 下午3:40:30
*
*/ 
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private static String mdErrorCode = "T_SYS_ERROR_CD";
	
	/**
	* @Fields errorMsg : 业务异常提示信息
	*/ 
	private String errorMsg = "";
	/**
	* @Fields errorCode : 业务异常码
	*/ 
	private String errorCode = "";
	/**
	* @Fields errorName : 业务异常名称
	*/ 
	private String errorName = "";
	
	/**
	* @Fields errorDesc : 业务异常描述
	*/ 
	private String errorDesc = "";
	
	/**
	* <p>Title: 业务异常</p>
	* <p>Description: 创建包含错误码的业务异常</p>
	* @param errorCode 业务异常码
	*/ 
	public BusinessException(String errorCode){
		this.errorCode = errorCode;
	}
	
	/**
	* @Title: getExceptionByMetadata
	* @Description: 根据错误码创建标准业务异常
	* @param @param errorCode 错误码
	* @param @return    
	* @return BusinessException    业务异常
	* @throws
	*/ 
	public static BusinessException getExceptionByMetadata(String errorCode){
		BusinessException be = new BusinessException(errorCode);
		MetaDataCodeValue mdcv = MetaDataUtil.getCodeValueByENAndENName(mdErrorCode, errorCode);
		if(mdcv != null){
			be.setErrorMsg(mdcv.getCodeValueDesc());
			be.setErrorName(mdcv.getCodeValueCNName());
		}else{
			be.setErrorMsg(ExcepitonCodeEnum.UNKNOWERROR.getErrorMsg());
			be.setErrorName(ExcepitonCodeEnum.UNKNOWERROR.getErrorName());
		}
		
		return be;
	}
	
	/**
	* @Title: getExceptionByMetadata
	* @Description: 根据错误码，错误描述创建标准业务异常
	* @param @param errorCode 错误码
	* @param @param errorDesc 错误描述
	* @param @return    设定文件
	* @return BusinessException    返回类型
	* @throws
	*/ 
	public static BusinessException getExceptionByMetadata(String errorCode,String errorDesc){
		BusinessException be = new BusinessException(errorCode);
		MetaDataCodeValue mdcv = MetaDataUtil.getCodeValueByENAndENName(mdErrorCode, errorCode);
		if(mdcv != null){
			be.setErrorMsg(mdcv.getCodeValueDesc());
			be.setErrorName(mdcv.getCodeValueCNName());
		}else{
			// 当查不到标准异常时，如果包含errorDesc,直接赋值给msg
			be.setErrorMsg(errorDesc);
			be.setErrorName(ExcepitonCodeEnum.UNKNOWERROR.getErrorName());
		}
		
		be.setErrorDesc(errorDesc);
		return be;
	}
	
	/**
	* @Title: getErrorCode
	* @Description: 获取业务异常码
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	public String getErrorCode(){
		
		return errorCode;
	}
	
	/**
	* @Title: getErrorMsg
	* @Description:  获取业务异常提示信息
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	public String getErrorMsg(){
		
		return errorMsg;
	}
	
	/**
	* @Title: getErrorName
	* @Description: 获取业务异常名称
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	public String getErrorName(){
		return errorName;
	}
	/**
	* @Title: setErrorMsg
	* @Description: 设置业务异常提示信息
	* @param @param errorMsg    设定文件
	* @return void    返回类型
	* @throws
	*/ 
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	* @Title: setErrorCode
	* @Description: 设置业务异常码
	* @param @param errorCode    设定文件
	* @return void    返回类型
	* @throws
	*/ 
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	* @Title: setErrorName
	* @Description: 设置业务异常名称
	* @param @param errorName    设定文件
	* @return void    返回类型
	* @throws
	*/ 
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	/**
	* @Title: getErrorDesc
	* @Description: 获取错误描述
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	*/ 
	public String getErrorDesc() {
		return errorDesc;
	}

	/**
	* @Title: setErrorDesc
	* @Description: 设置错误描述
	* @param @param errorDesc    设定文件
	* @return void    返回类型
	* @throws
	*/ 
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}


}
