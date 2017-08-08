package com.xinmei.common.basic.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: com.xinmei.common.basic.annotation.BizDesc
 * @Description: 标记在某一方法上用于描述该方法的业务含义
 * @Author xbzhu
 * @Date 2017年03月11日 11:21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BizDesc {


    public String desc() default "";

    public boolean notice() default true;

    public String author() default "";

}
