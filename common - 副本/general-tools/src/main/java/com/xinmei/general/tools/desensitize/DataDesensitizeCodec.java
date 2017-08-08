package com.xinmei.general.tools.desensitize;

/**
 * @ClassName: .com.xinmei.general.tools.desensitize.DataDesensitizeCodec<T>
 * @Description: 数据脱敏编码/解码器
 * @author gdw
 * @date 2017年6月7日 上午10:11:44
 */
public interface DataDesensitizeCodec<T> {
	/**
	 * @Title: encode
	 * @Description: 将敏感数据编码
	 * @param sensitiveData 敏感数据
	 * @return T 编码数据
	 */
	T encode(T sensitiveData);
	
	/**
	 * @Title: decode
	 * @Description: 将敏感数据的编码数据还原编码
	 * @param encodeData 编码数据
	 * @return T 敏感数据
	 */
	T decode(T encodeData);
}