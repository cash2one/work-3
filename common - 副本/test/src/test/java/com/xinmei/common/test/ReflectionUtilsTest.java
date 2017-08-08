package com.xinmei.common.test;

import com.xinmei.common.basic.tools.ReflectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Toby on 2017/1/10.
 */
public class ReflectionUtilsTest {


    @Test
    public void testSetFieldValue(){
        User user=new User();
        ReflectionUtils.setFieldValue(user,"name","name");
    }

    @Test
    public void testSetFieldValueError(){
        User user=new User();
        try {
            ReflectionUtils.setFieldValue(user, "name", "name1");
        }catch (Exception e){

        }

    }

    @Test
    public void testGetAccessibleField(){
        User user=new User();
        user.setName("name");
        ReflectionUtils.getAccessibleField(user,"name");
    }

    @Test
    public void testGetAccessibleFieldError(){
        User user=new User();
        user.setName("name");
        try {
            ReflectionUtils.getAccessibleField(user, "name1");
        }catch (Exception e){

        }
    }

    @Test
    public void testGetAccessibleMethod(){
        User user=new User();
        ReflectionUtils.getAccessibleMethod(user,"test");
    }

    @Test
    public void tesGetAccessibleMethodByNamet(){
        User user=new User();
        ReflectionUtils.getAccessibleMethodByName(user,"test");
    }

    @Test
    public void testGetFieldValue(){
        User user=new User();
        user.setName("name");
        ReflectionUtils.getFieldValue(user,"name");
    }

    @Test
    public void testGetFieldValueError(){
        User user=new User();
        user.setName("name");
        try {
            ReflectionUtils.getFieldValue(user, "name1");
        }catch (Exception e){

        }
    }


    @Test
    public void testGetSuperClassGenricType(){
        ReflectionUtils.getSuperClassGenricType(User.class);
    }

    @Test
    public void testGetSuperClassGenricTypeByIndex(){
        ReflectionUtils.getSuperClassGenricType(User.class,0);
    }

    @Test
    public void testGetUserClass(){
        User user=new User();
        ReflectionUtils.getUserClass(user);
    }

    @Test
    public void testInvokeGetter(){
        User user=new User();
        ReflectionUtils.invokeGetter(user,"name");
    }

    @Test
    public void testInvokeMethod(){
        User user=new User();
        ReflectionUtils.invokeMethod(user,"test",null,null);
    }

    @Test
    public void testInvokeMethodError(){
        User user=new User();
        try {
            ReflectionUtils.invokeMethod(user, "test1", null, null);
        }catch (Exception e){

        }
    }

    @Test
    public void testInvokeMethodByName(){
        User user=new User();
        ReflectionUtils.invokeMethodByName(user,"test",null);
    }

    @Test
    public void testInvokeMethodByNameError(){
        User user=new User();
        try {
            ReflectionUtils.invokeMethodByName(user, "test1", null);
        }catch (Exception e){

        }
    }


    @Test
    public void testInvokeSetter(){
        User user=new User();
        ReflectionUtils.invokeSetter(user,"name","name");
    }


    @Test
    public void testMakeAccessibleMethod() throws NoSuchMethodException {
        Method method=User.class.getMethod("test");
        ReflectionUtils.makeAccessible(method);
    }

    private class User{
        private String name;

        public void test(){
            System.out.println("invoke");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
