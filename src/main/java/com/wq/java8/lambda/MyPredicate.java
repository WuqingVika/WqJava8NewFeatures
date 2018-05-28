package com.wq.java8.lambda;

/**
 * Created by qwu on 2018/5/28.
 */
@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);

}