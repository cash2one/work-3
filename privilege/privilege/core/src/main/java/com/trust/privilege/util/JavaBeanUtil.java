package com.trust.privilege.util;


import org.springframework.cglib.beans.BeanCopier;

public class JavaBeanUtil {
	/**
	 * 这个方法只适用于名字不同成员变量相同的两个类的赋值,将o1赋值给o2,适用于将视图模型快速复制给javabean
	 * 
	 * @param o1
	 *            有值的对象
	 * @param o2
	 *            待被赋值的对象
	 */
	public static void copyBean(Object o1, Object o2) {
		BeanCopier beanCopier = BeanCopier.create(o1.getClass(), o2.getClass(),
				false);
		beanCopier.copy(o1, o2, null);

	}
}
