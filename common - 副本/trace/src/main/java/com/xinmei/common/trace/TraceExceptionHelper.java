package com.xinmei.common.trace;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: com.xinmei.common.trace.TraceExceptionHelper
 * @Description: trace埋点对于自定义异常绕过处理、提供业务异常持有容器
 * @Author xbzhu
 * @Date 2017年03月21日 21:57
 */
public class TraceExceptionHelper {

    //自定义异常通过完整类名注册到容器
    private Set<String> bizException =new HashSet<String>();

    /**
     * Author: xbzhu
     * Mail: toby.zhu@trustlife.com
     * Date: 2017/3/21 22:06
     * Desc: 判断一个异常是否需要过滤的业务异常
     * @param exception
     * @return
     */
    public boolean isBizException(Exception exception){
        return bizException.contains(exception.getClass().getName());
    }


    public void setBizException(Set<String> bizException) {
        this.bizException = bizException;
    }
}
