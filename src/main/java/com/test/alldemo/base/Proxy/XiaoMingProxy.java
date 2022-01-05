package com.test.alldemo.base.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author admin
 */
public class XiaoMingProxy {

    //代理只是一个中介，实际干活的还是小明，于是需要在代理类上维护小明这个变量
    XiaoMing xiaoMing = new XiaoMing();


    //返回代理对象
    public Person getProxy() {

        /**
         * 参数一：代理类的类加载器
         * 参数二：被代理对象的接口
         * 参数三：InvocationHandler实现类
         */
        return (Person)Proxy.newProxyInstance(XiaoMingProxy.class.getClassLoader(), xiaoMing.getClass().getInterfaces(), new InvocationHandler() {

            /**
             * proxy : 把代理对象自己传递进来
             * method：把代理对象当前调用的方法传递进来
             * args:把方法参数传递进来
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //如果别人想要让小明唱歌
                if ("sing".equals(method.getName())) {

                    System.out.println("给1000万来再唱");

                    //实际上唱歌的还是小明
                    method.invoke(xiaoMing, args);
                }
                return null;
            }
        });

    }
}
