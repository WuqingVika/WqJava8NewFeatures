package com.wq.java8.lambda;

/**
 * Created by qwu on 2018/5/28.
 */
public class FilterEmployeeForAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee t) {
        return t.getAge() <= 35;
    }

}