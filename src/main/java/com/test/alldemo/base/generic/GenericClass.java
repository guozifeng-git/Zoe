package com.test.alldemo.base.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author admin
 * 常用的通配符有T,E,K,V分别表示类型、元素、键、值，当然这并不是硬性规定，而是大家形成的一种通识。
 */
@Data
@AllArgsConstructor
public class GenericClass<T> {
    private T value;

    public static void main(String[] args) {
        GenericClass<Integer> integerNode = new GenericClass<Integer>(100);
        System.out.println(integerNode.getValue());
        GenericClass<String> stringNode = new GenericClass<String>("test");
        System.out.println(stringNode.getValue());

    }
}

