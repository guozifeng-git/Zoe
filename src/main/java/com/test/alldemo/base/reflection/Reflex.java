package com.test.alldemo.base.reflection;

import com.test.alldemo.entity.seckill.UserDO;

/**
 * 获取Class对象的三种方式
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 *
 * @author admin
 */
public class Reflex {
    public static void main(String[] args) {
        UserDO userDO = new UserDO();
        Class<? extends UserDO> aClass = userDO.getClass();
        System.out.println(aClass);

        Class<UserDO> userDOClass = UserDO.class;
        System.out.println(userDOClass);

        try {
            Class<?> aClass1 = Class.forName("com.test.alldemo.entity.seckill.UserDO");
            System.out.println(aClass1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
