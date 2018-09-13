package com.wq.java8._3streamapi;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qwu on 2018/7/26.
 */
public class TestStreamAPI2 {
    @Test
    public void test1(){
        List<String> list= Arrays.asList("bb","aa","ccc");
        list.stream().map((x)-> x.toUpperCase()).forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);
    }

}
