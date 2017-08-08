package com.xinmei.general.tools.desensitize;
import java.util.Arrays;

/**
 * @ClassName: DataDesensitizeCharset
 * @Description: 用于脱敏的字符集
 * @author gdw
 * @date 2017年6月7日 下午3:20:42
 */
public class DataDesensitizeCharset {
	/**
	 * 字符 ==> 编码
	 */
	private static int[] char2Code;
	
	/**
	 * 编码 ==> 字符
	 */
	private static char[] code2Char;

	/**
	 * 字符集
	 */
	private static int INVALID_CODE = -1;
	
	static{
		String charset = charset();
		initChar2Code(charset);
		initCode2Char(charset);
	}

	private static void initChar2Code(String charset) {
		char[] charArray = charset.toCharArray();
		char2Code = createChar2CodeArray(charArray);
		for(int i = 0; i < charArray.length; i ++){
			char2Code[charArray[i]] = i;
		}
	}
	
	private static void initCode2Char(String charset) {
		code2Char = charset.toCharArray();
	}

	// 字符集中目前包含的字符[0-9],[a-z],[A-Z],!,@
	private static String charset(){
		StringBuilder sb = new StringBuilder();
		// 数字
		for(int i = 0; i <= 9; i ++){
			sb.append(String.valueOf(i));
		}
		// 大写字母
		for(int i = 'A'; i <= 'Z'; i ++){
			sb.append((char)i);
		}
		// 小写字母
		for(int i = 'a'; i <= 'z'; i ++){
			sb.append((char)i);
		}
		// !
		sb.append('!');
		// @
		sb.append('@');
		return sb.toString();
	}
	
	private static int[] createChar2CodeArray(char[] charArray) {
		int maxChar = maxChar(charArray);
		int[] char2Code = new int[maxChar + 1];
		Arrays.fill(char2Code, INVALID_CODE);
		return char2Code;
	}

	private static char maxChar(char[] charArray) {
		char maxChar = charArray[0];
		for(int i = 1; i < charArray.length; i ++){
			if(maxChar < charArray[i]){
				maxChar = charArray[i]; 
			}
		}
		return maxChar;
	}
	
	public static char[] code2Char(int[] codeArray) {
		char[] charArray = new char[codeArray.length];
		for(int i = 0; i < codeArray.length; i ++){
			charArray[i] = code2Char[codeArray[i]];
		}
		return charArray;
	}
	
	public static String code2Str(int[] codeArray) {
		return new String(code2Char(codeArray));
	}
	
	public static int[] str2Code(String str) {
		return char2Code(str.toCharArray());
	}

	public static int[] char2Code(char[] charArray) {
		int[] codeArray = new int[charArray.length];
		for(int i = 0; i < charArray.length; i ++){
			codeArray[i] = char2Code(charArray[i]);
		}
		return codeArray;
	}
	
	public static int char2Code(char ch) {
		int code = char2Code[ch];
		if(code == INVALID_CODE){
			throw new IllegalArgumentException("char " + ch + " do not in DataDesensitizeCharset");
		}
		return code;
	}
}
