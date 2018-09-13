package com.wq.java8._6myinterface;

/**
 * java8中允许有默认方法 用default来修饰
 * !!!!!接口默认方法 类优先原则
 *      什么意思呢 如果有个类A继承类B（B中含有getName（）方法），又实现 了接口MyInterface （此接口也含有getName()方法）
 *      此时优先类B
 *  !!!!!如果子类实现了两个接口，且那两个接口都有getName()方法 此时子类必须实现getName()方法。
 * Created by qwu on 2018/8/10.
 */
public interface MyInterface {

   default String getName(){
        return "wuqing";
    }

    public static void show(){
        System.out.println("接口中的静态方法 show show show！！！");
    }
}
