package com.trust.privilege.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trust.privilege.constants.OCSConstants;
import com.trust.privilege.model.Menu;
import com.trust.privilege.model.SystemUser;
import com.trust.privilege.model.complex.MenuDo;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

/***
 * @ClassName: CommonUtil 
 * @Description:  提供各种常用功能,和校验方法
 * @author zyn161616@163.com
 * @version 创建时间：2016年12月21日 下午3:36:12
 */
public class CommonUtil {
	/**创建日志记录对象*/
	private static final Logger logger = 
			LoggerFactory.getLogger(CommonUtil.class.getName());
	
	/**
	 * @Title: sub 
	 * @Description: 去重字符串
	 * @param @param str   
	 * @return void
	 */
	public static String onlyString(String str){
		String result = null ;
		//如果参数不为空
		if(CommonUtil.isNotNull(str)){
			//将字符串转换数组
	        String[] st =str.split(","); 
	        //创建集合
	        List<String> list= new ArrayList<>();
	        //循环这个数组
	        for(int i = 0 ; i < st.length ; i++){
	        	//如果集合中不包含这个字符串
	        	if(! list.contains(st[i])){
	        		//那么加入集合
	        		list.add(st[i]);
	        	}
	        }
	        //处理下
	        String listStr = list.toString();
	        result = listStr.substring(1,listStr.length()-1);
		}
			return result; 
	}
	
	/**
	 * @Title: isNotNUll 
	 * @Description: 校验传入的对象非空
	 * @param @param agr0
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public static boolean isNotNUll(Object ...agr0 ){
		for (Object s : agr0) {
			if(isNull(s)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @Title: isNotNull 
	 * @Description: 校验传入参数非空
	 * @param @param obj
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public final  static boolean isNotNull(Object  obj){
        if (obj == null)  
            return false;  
        if(obj instanceof Integer){
        	return (Integer)obj!=0;
        }
        if(obj instanceof Long){
        	return (Long)obj!=0;
        }
        if(obj instanceof String){
        	return !"".equals(obj.toString().trim());
        }
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() > 0;  
  
        if (obj instanceof Collection)  
            return !((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return !((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return false;  
            }  
        }  
        return true;  
    }
	
	/**
	 * @Title: getUUID 
	 * @Description: 获取UUID
	 * @param @return   
	 * @return String   
	 * @throws
	 */
	public static String getUUID (){
		UUID uuid= UUID.randomUUID(); 
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * @Title: isNull 
	 * @Description: 校验参数为空
	 * @param @param obj
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final  static boolean isNull(Object obj){
		return !isNotNull(obj);
	}

	/**
	 * @Title: isUrl 
	 * @Description: 校验URL,格式http://www.*.com/*
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isUrl(String str) {
		return match(str, "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
	}

	/**
	 * @Title: isPwd 
	 * @Description: 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isPwd(String str) {
		return match(str, "^[a-zA-Z]\\w{6,12}$");
	}

	/**
	 * @Title: stringCheck 
	 * @Description: 验证字符串，只能包含中文、英文、数字、下划线等字符。
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean stringCheck(String str) {
		return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$");
	}

	/**
	 * @Title: emaiCheck 
	 * @Description: 验证邮箱
	 * @param @param str
	 * @param @return   
	 * @return boolean
	 */
	public final static boolean emailCheck(String str){
		return match(str, "^[A-Za-z0-9_@.\u4e00-\u9fa5]{4,20}$");
	}
	
	/**
	 * @Title: phoneNumberCheck 
	 * @Description: 验证手机号
	 * @param @param str
	 * @param @return   
	 * @return boolean
	 */
	public final static boolean phoneNumberCheck(String str){
		return match(str , "^[1][3578][0-9]{9}$");
	}
	
	/**
	 * @Title: isInteger 
	 * @Description: 匹配非负整数（正整数+0）
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isInteger(String str) {
		return match(str, "^[+]?\\d+$");
	}

	/**
	 * @Title: isNumeric 
	 * @Description: 判断数值类型，包括整数和浮点数
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isNumeric(String str) {
		if (isFloat(str) || isInteger(str))
			return true;
		return false;
	}

	/**
	 * @Title: isDigits 
	 * @Description: 只能输入数字
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isDigits(String str) {
		return match(str, "^[0-9]*$");
	}

	/**
	 * @Title: isFloat 
	 * @Description: 校验正浮点数
	 * @param @param str
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isFloat(String str) {
		return match(str, "^[-\\+]?\\d+(\\.\\d+)?$");
	}

	/**
	 * @Title: isIdCardNo 
	 * @Description: 身份证号码验证
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isIdCardNo(String text) {
		return match(text, "^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");
	}

	/**
	 * @Title: isZipCode 
	 * @Description: 校验邮政编码
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isZipCode(String text) {
		return match(text, "^[0-9]{6}$");
	}

	/**
	 * @Title: isRightfulString 
	 * @Description: 判断是否为合法字符(a-zA-Z0-9-_)
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isRightfulString(String text) {
		return match(text, "^[A-Za-z0-9_-]+$");
	}

	/**
	 * @Title: isEnglish 
	 * @Description: 判断英文字符(a-zA-Z)
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isEnglish(String text) {
		return match(text, "^[A-Za-z]+$");
	}

	/**
	 * @Title: isChineseChar 
	 * @Description: 判断中文字符(包括汉字和符号)
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isChineseChar(String text) {
		return match(text, "^[\u0391-\uFFE5]+$");
	}

	/**
	 * @Title: isChinese 
	 * @Description: 匹配汉字
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public final static boolean isChinese(String text) {
		return match(text, "^[\u4e00-\u9fa5]+$");
	}

	/**
	 * @Title: isContainsSpecialChar 
	 * @Description: 是否包含中英文特殊字符，除英文"-_"字符外
	 * @param @param text
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	public static boolean isContainsSpecialChar(String text) {
		if (StringUtils.isBlank(text))
			return false;
		String[] chars = { "[", "`", "~", "!", "@", "#", "$", "%", "^", "&",
				"*", "(", ")", "+", "=", "|", "{", "}", "'", ":", ";", "'",
				",", "[", "]", ".", "<", ">", "/", "?", "~", "！", "@", "#",
				"￥", "%", "…", "&", "*", "（", "）", "—", "+", "|", "{", "}",
				"【", "】", "‘", "；", "：", "”", "“", "’", "。", "，", "、", "？", "]" };
		for (String ch : chars) {
			if (text.contains(ch))
				return true;
		}
		return false;
	}

	/**
	 * @Title: stringFilter 
	 * @Description: 过滤中英文特殊字符，除英文"-_"字符外
	 * @param @param text
	 * @param @return   
	 * @return String   
	 * @throws
	 */
	public static String stringFilter(String text) {
		String regExpr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regExpr);
		Matcher m = p.matcher(text);
		return m.replaceAll("").trim();
	}

	/**
	 * @Title: htmlFilter 
	 * @Description: 过滤html代码
	 * @param @param inputString
	 * @param @return   
	 * @return String   
	 * @throws
	 */
	public static String htmlFilter(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}

	/**
	 * @Title: match 
	 * @Description: 正则表达式匹配
	 * @param @param text
	 * @param @param reg
	 * @param @return   
	 * @return boolean   
	 * @throws
	 */
	private final static boolean match(String text, String reg) {
		if (StringUtils.isBlank(text) || StringUtils.isBlank(reg))
			return false;
		return Pattern.compile(reg).matcher(text).matches();
	}

	
	/***
	 * @Title: getOCSClient 
	 * @Description: 获取OCS对象
	 * @param @return   
	 * @return MemcachedClient
	 */
	public static MemcachedClient getCache(){
		//声明缓存对象
		MemcachedClient cache = null ;
		
		//安全说明
		AuthDescriptor ab = new AuthDescriptor(new String[]{"PLAIN"},
				new PlainCallbackHandler(OCSConstants.userName,OCSConstants.passwoerd));
		
		//创建这个缓存对象
		try {
			cache = new MemcachedClient(
				new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY)
					.setAuthDescriptor(ab).build(),
					AddrUtil.getAddresses(OCSConstants.host+":"+OCSConstants.port)
				);
		} catch (IOException e) {
			//日志记录错误
			logger.info("获取Memcached对象出错！",e);
		}
			
		//返回这个对象
		return cache ;
	}
	
	/**
	 * @Title: generate 
	 * @Description: 密码加密工具
	 * @param @param s
	 * @param @return   
	 * @return String
	 */
	public final static String generateMD5(String s) {
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**递归查询菜单子菜单
	 * @param menuList
	 * @param pid
	 * @return
	 */
	public static List<MenuDo> getSubMenu(List<MenuDo> menuList,String pid){
		 List<MenuDo> vlist=new ArrayList<>();
			for(MenuDo m:menuList){
			if(pid!=null&& m.getParentId()!=null&&m.getParentId().equals(pid)){
				MenuDo menu=new MenuDo();
				menu.setMenuId(m.getMenuId());
				menu.setParentId(pid);
				menu.setMenuName(m.getMenuName());
				menu.setUrl(m.getUrl());
				menu.setPrivileges(m.getPrivileges());
				menu.setStateType(m.getStateType());
				menu.setShowOrder(m.getShowOrder());
				List<MenuDo> list=m.getChilds();
				if(list==null){
					list=new ArrayList<>();
				}
				list=getSubMenu(menuList, m.getMenuId());
				menu.setChilds(list);
				vlist.add(menu);
			}
			
			}
			return vlist;
	 }

	/**
	 * @Title: getChild 
	 * @Description: 获取当前菜单下的所有子菜单
	 * @param @param menuList
	 * @param @param parenId
	 * @param @param vlist
	 * @param @return   
	 * @return List<String>
	 */
	public static List<String> getChildMenu(List<Menu> menus,String parenId,List<String> result ){
	    //循换菜单集合 
	    for(Menu menu : menus){
	    	//如果此菜单有上级菜单,并且是传入参数
	    	if(CommonUtil.isNotNull(menu.getParentId()) && menu.getParentId().equals(parenId)){
	    		//那么加入这个菜单的id
	    		result.add(menu.getMenuId());
	    		getChildMenu(menus, menu.getMenuId(), result);
	    	}
	    }
	    //返回这个集合
	    return result;
	}
	
	/**
	 * @Title: getChilds 
	 * @Description: 通过创建人，获取该创建人下的所有子用户
	 * @param @param userDos
	 * @param @param creatName
	 * @param @param result
	 * @param @return   
	 * @return List<String>
	 */
	public static List<String> getUserChilds(List<SystemUser> users,String creatName,List<String> result ){
		//创建存储用户组Id集合
		List<String> temList = new ArrayList<>();
		
		//循环所有用户
		for (SystemUser user : users) {
			//获取当前用户名
			String userName = user.getUserName();
			//获取当前用户创建人
			String createUserName = user.getCreateUserId();
			//如果该用户的创建人不为空,并且是当前操作人
			if(CommonUtil.isNotNull(createUserName) && createUserName.equals(creatName)){
				temList.add(userName);
				//当前用户为创建人，递归他创建的所有用户，并加入集合
				getUserChilds(users, userName, result);
			}	
		}
		//将结果放入集合中
		result.addAll(temList);
		//最后将集合返回
		return result;
	}
	
	/**
	 * @Title: getChildGroup 
	 * @Description: 获取当前用户组下的所有用户组
	 * @param @param groups
	 * @param @param parentGroupId
	 * @param @param result
	 * @param @return   
	 * @return List<String>
	 */
	public static List<String> getChildGroup(List<Map<String,Object>> groups , String parentGroupId , List<String> result){
	
		//创建存储用户组Id集合
		List<String> temList = new ArrayList<>();
		//循环所有用户组
		for (Map<String,Object> group : groups) {
			//获取当前用户组Id
			String groupId = (String) group.get("groupId");
			//获取当前用户组的父用户组Id
			String userGroupParentId = (String) group.get("pId");
			
			//如果上级用户组Id不为空，并且为当前参数
			if(CommonUtil.isNotNull(userGroupParentId) && parentGroupId.equals(userGroupParentId)){
				temList.add(groupId);
				getChildGroup(groups, groupId, result);
			}
		}
		result.addAll(temList);
		
		return result ;
	}

	/**
	 * @Title: splitStrToList 
	 * @Description: 将字符串转换成集合
	 * @param @param ids
	 * @param @return   
	 * @return List<String>
	 */
	public static List<String> splitStrToList(String ids){
		//创建返回对象
		List<String> list = new ArrayList<String>();
		//如果参数不为空
		if(CommonUtil.isNotNull(ids)){
			//如果存在","
			if(ids.indexOf(",") > 0){
				String [] strs = ids.split(",");
				for(String str : strs){
					list.add(str);
				}
			}else{
				list.add(ids);
			}
		}
			return list;
	}
	
	 
	
}
