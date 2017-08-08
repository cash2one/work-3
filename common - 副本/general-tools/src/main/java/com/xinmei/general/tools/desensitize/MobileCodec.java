package com.xinmei.general.tools.desensitize;
/**
 * @ClassName: com.xinmei.general.tools.desensitize.MobileCodec
 * @Description: 手机号脱敏
 * @author gdw
 * @date 2017年6月7日 上午10:16:39
 */
public class MobileCodec implements DataDesensitizeCodec<String>{
	public static final MobileCodec INSTANCE = new MobileCodec();
	
	@Override
	public String encode(String mobile) {
		StringBuilder encodeMobile = new StringBuilder(mobile.length());
		encodeMobile.append(getPrefix(mobile));
		encodeMobile.append(DataDesensitizeStringCodec.INSTANCE.encode(getDesensitize(mobile)));
		return encodeMobile.toString();
	}

	@Override
	public String decode(String encodeMobile) {
		StringBuilder mobile = new StringBuilder(encodeMobile.length());
		mobile.append(getPrefix(encodeMobile));
		mobile.append(DataDesensitizeStringCodec.INSTANCE.decode(getDesensitize(encodeMobile)));
		return mobile.toString();
	}
	
	private static String getPrefix(String mobile){
		return mobile.substring(0, DESENSITIZE_INDEX);
	}
	
	private static String getDesensitize(String mobile){
		return mobile.substring(DESENSITIZE_INDEX);
	}
	
	/**
	 * 手机号中脱敏部分的下标
	 */
	private static final int DESENSITIZE_INDEX = 3;
	
	/**
	 * @Title: desensitize
	 * @Description: 将手机号脱敏
	 * @param mobile 手机号
	 * @return String 脱敏后的手机号
	 */
	public static String desensitize(String mobile) {
		return INSTANCE.encode(mobile);
	}

	/**
	 * @Title: reduce
	 * @Description: 将脱敏手机号还原
	 * @param mobile 手机号
	 * @return String 还原后的手机号
	 */
	public static String reduce(String mobile) {
		return INSTANCE.decode(mobile);
	}
}