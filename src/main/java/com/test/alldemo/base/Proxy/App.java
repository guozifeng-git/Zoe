package com.test.alldemo.base.Proxy;

import com.test.alldemo.entity.Transactional.User1DO;

/**
 * @author admin
 */
public class App {
    public static void main(String[] args) {

        XiaoMing userDao = new XiaoMing();

        XiaoMing factory = (XiaoMing) new ProxyFactory(userDao).getProxyInstance();

        factory.sing("xxx");
    }
}
