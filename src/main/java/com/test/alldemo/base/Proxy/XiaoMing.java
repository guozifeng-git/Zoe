package com.test.alldemo.base.Proxy;

import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Service
public class XiaoMing implements Person {
    @Override
    public void sing(String name) {
        System.out.println("小明唱" + name);
    }

    @Override
    public void dance(String name) {
        System.out.println("小明跳" + name);
    }
}
