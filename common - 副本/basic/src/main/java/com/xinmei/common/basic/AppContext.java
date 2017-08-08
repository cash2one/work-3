package com.xinmei.common.basic;

/**
 * @ClassName: com.xinmei.common.basic.AppContext
 * @Description: 应用全局容器，用于持有当前登录用户、唯一请求id等
 * @Author xbzhu
 * @Date 2017年02月24日 16:59
 */
public class AppContext {

    //访问渠道 rest
    public static final String REQUEST_CHANNEL_REST="channel.rest";
    //访问渠道 mobile
    public static final String REQUEST_CHANNEL_MOBILE="channel.mobile";

    //全局用户持有容器，用户登录id
    private static ThreadLocal<String> userHolder=new ThreadLocal<String>();

    //用户姓名
    private static ThreadLocal<String> userNameHolder=new ThreadLocal<String>();

    //全局唯一请求id
    private static ThreadLocal<String> requestIdHolder=new ThreadLocal<String>();

    //全局请求报文
    private static ThreadLocal<String> requestMsgHolder=new ThreadLocal<String>();

    //访问渠道
    private static ThreadLocal<String> channelHolder=new ThreadLocal<String>();


    //当前线程用户持有
    private static ThreadLocal userObjHolder=new ThreadLocal();

    //
    private static ThreadLocal<String> restIPHolder=new ThreadLocal<String>();



    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:07
     * Desc: 将当前请求用户登录id放入当前线程
     * @param loginUserNo
     */
    public static void setUser(String loginUserNo){
        userHolder.set(loginUserNo);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:07
     * Desc: 取出当前请求用户登录id
     * @return
     */
    public static String getUser(){
        return userHolder.get();
    }


    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:07
     * Desc: 将当前请求用户姓名放入当前线程
     * @param userName
     */
    public static void setUserName(String userName){
        userNameHolder.set(userName);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:07
     * Desc: 取出当前请求用户姓名
     * @return
     */
    public static String getUserName(){
        return userNameHolder.get();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:08
     * Desc: 将唯一请求号放入当前线程
     * @param requestId
     */
    public static void setRequestId(String requestId){
        requestIdHolder.set(requestId);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:08
     * Desc: 从当前线程取出当前请求号
     * @return
     */
    public static String getRequestId(){
        return requestIdHolder.get();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/1 19:24
     * Desc: 将请求报文放入全局容器
     * @param requestMsg 请求报文
     */
    public static void setRequestMsg(String requestMsg){
        requestMsgHolder.set(requestMsg);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/1 19:43
     * Desc: 从全局容器中获取请求报文
     * @return
     */
    public static String getRequestMsg(){
        return requestMsgHolder.get();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/4 16:48
     * Desc: 放入请求渠道
     * @param channel
     */
    public static void setChannel(String channel){
        channelHolder.set(channel);
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/4 16:51
     * Desc: 获取当前访问渠道
     * @return
     */
    public static String getChannel(){
        return channelHolder.get();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/11 10:51
     * Desc: 获取当前请求的用户信息，包括用户id、用户名称
     * @return
     */
    public static String getCurrentUserInfo(){
        StringBuilder buffer=new StringBuilder();
        buffer.append("用户：");
        buffer.append(getUserName()==null?"未知用户":getUserName());
        buffer.append(",登录ID:").append(getUser()==null?"未知ID":getUser());
        return buffer.toString();
    }

    public static void setUserObj(Object userObj){
        userObjHolder.set(userObj);
    }


    public static <T> T getUserObject(Class<T> clazz){
        return (T) userObjHolder.get();
    }


    public static void setRestIP(String ip){
        restIPHolder.set(ip);
    }

    public static String getRestIP(){
        return restIPHolder.get();
    }

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/2/24 17:09
     * Desc: 释放当前容器中信息
     */
    public static void releaseContext(){
        userHolder.remove();
        requestIdHolder.remove();
        requestMsgHolder.remove();
        channelHolder.remove();
        userNameHolder.remove();
        userObjHolder.remove();
        restIPHolder.remove();
    }

}
