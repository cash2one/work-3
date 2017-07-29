package com.trust.privilege.endpoint.response;
/***
 * 将实际返回数据封装成json,然后向前台返回json数据
 * @author Administrator
 *
 */
public class RestSampleFacadeResp<T> extends AbstractFacadeResp {

	/***
	 * response的返回对象数据
	 */
	private T data;

	/**继承父类抽象方法*/ 	
	public RestSampleFacadeResp(boolean success) {
		super(success);
	}
	
	/**有参构造器*/
	public RestSampleFacadeResp( T data,boolean success) {
		super(success);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
