package com.xinmei.general.tools.desensitize;

/**
 * @ClassName: com.xinmei.general.tools.desensitize.CertiCodeCodec
 * @Description: 证件号脱敏编码/解码器
 * @author gdw
 * @date 2017年6月7日 上午10:16:39
 */
public class CertiCodeCodec implements DataDesensitizeCodec<String>{
	public static final CertiCodeCodec INSTANCE = new CertiCodeCodec();
	
	@Override
	public String encode(String sensitiveData) {
		return DataDesensitizeStringCodec.INSTANCE.encode(sensitiveData);
	}

	@Override
	public String decode(String encodeData) {
		return DataDesensitizeStringCodec.INSTANCE.decode(encodeData);
	}
	
	/**
	 * @Title: desensitize
	 * @Description: 将证件号脱敏
	 * @param certiCode 证件号
	 * @return String 脱敏后的证件号
	 */
	public static String desensitize(String certiCode) {
		return INSTANCE.encode(certiCode);
	}

	/**
	 * @Title: reduce
	 * @Description: 将脱敏证件号还原
	 * @param certiCode 证件号
	 * @return String 还原后的证件号
	 */
	public static String reduce(String certiCode) {
		return INSTANCE.decode(certiCode);
	}
}