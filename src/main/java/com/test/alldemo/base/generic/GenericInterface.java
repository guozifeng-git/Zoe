package com.test.alldemo.base.generic;

/**
 * @author admin
 */
public interface GenericInterface<T> {
    /**
     * 范型接口
     * @return 放回范型
     */
    T getValue();
}

class Test implements GenericInterface<Integer>{

    @Override
    public Integer getValue() {
        return null;
    }
}

class Test2 implements GenericInterface<String>{

    @Override
    public String getValue() {
        return null;
    }
}