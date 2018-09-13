package com.wq.java8._1lambda;

/**
 * 只包含一个抽象方法的接口 称为函数式接口
 * Created by qwu on 2018/5/28.
 */
@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);

}