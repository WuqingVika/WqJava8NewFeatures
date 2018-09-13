package com.wq.java8._6myinterface;

import org.junit.Test;

/**
 * Created by qwu on 2018/8/10.
 */
public class TestMyInterface {
    @Test
    public void testMyInterface(){
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());
        MyInterface.show();
    }
}
