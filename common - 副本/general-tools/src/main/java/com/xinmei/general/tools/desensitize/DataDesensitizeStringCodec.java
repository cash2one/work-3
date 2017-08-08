package com.xinmei.general.tools.desensitize;

/**
 * @ClassName: com.xinmei.general.tools.desensitize.DataDesensitizeStringCodec
 * @Description: 字符串编码/解码器,目前只支持{@code DataDesensitizeCharset}中有的字符
 * @author gdw
 * @date 2017年6月7日 下午3:41:11
 */
public class DataDesensitizeStringCodec implements DataDesensitizeCodec<String>{
	public static final DataDesensitizeStringCodec INSTANCE = new DataDesensitizeStringCodec();
	
	@Override
	public String encode(String sensitiveData) {
		int[] codeArray = DataDesensitizeCharset.str2Code(sensitiveData);
		
		// 算法:
		// 	   字符串(长度为n)中的前n-1个字符与最后一个字符亦或
		// 	   最后一个字符再与第一个字符亦或(即将最后一个字符设为第一个字符)
		int lastIndex = codeArray.length - 1;
		for(int i = 0; i < lastIndex; i ++){
			codeArray[i] = codeArray[i] ^ codeArray[lastIndex];
		}
		codeArray[lastIndex] = codeArray[lastIndex] ^ codeArray[0];
		
		return DataDesensitizeCharset.code2Str(codeArray);
	}
	
	@Override
	public String decode(String encodeData) {
		int[] codeArray = DataDesensitizeCharset.str2Code(encodeData);
		
		// 算法:与encode相反
		int lastIndex = codeArray.length - 1;
		codeArray[lastIndex] = codeArray[lastIndex] ^ codeArray[0];
		for(int i = 0; i < lastIndex; i ++){
			codeArray[i] = codeArray[i] ^ codeArray[lastIndex];
		}
		
		return DataDesensitizeCharset.code2Str(codeArray);
	}
}
