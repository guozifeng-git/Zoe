package com.test.alldemo.base.Proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib
 * @author admin
 */
@AllArgsConstructor
@Data
public class ProxyFactory implements MethodInterceptor {

    private Object target;

    // 给目标对象创建代理对象
    public Object getProxyInstance(){
        //1. 工具类
        Enhancer en = new Enhancer();
        //2. 设置父类
        en.setSuperclass(target.getClass());
        //3. 设置回调函数
        en.setCallback(this);
        //4. 创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务.....");

        // 执行目标对象的方法
        Object returnValue = method.invoke(target, objects);

        System.out.println("提交事务.....");

        return returnValue;
    }
}
