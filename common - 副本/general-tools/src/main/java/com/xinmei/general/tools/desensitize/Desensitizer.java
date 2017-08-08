package com.xinmei.general.tools.desensitize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: com.xinmei.general.tools.desensitize.Desensitizer
 * @Description: 脱敏注解,用于标识那些字段需要进行脱敏处理
 * @author gdw
 * @date 2017年6月7日 下午4:27:26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitizer {
	/**
	 * @Title: value
	 * @Description: 指定该字段使用哪个脱敏编码/解码器
	 * @return Class<? extends DataDesensitizeCodec<?>> 脱敏编码/解码器
	 */
	Class<? extends DataDesensitizeCodec<?>> value();
}
