package com.wq.java8._4forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by qwu on 2018/8/9.
 */
public class TestForkJoin {
    public static void main(String[] args) {
        Instant startIns=Instant.now();
        long start=System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask=new ForkJoinCalculate(0,100000000L);//适合数据大的。
        Long invoke = forkJoinPool.invoke(forkJoinTask);
        long end=System.currentTimeMillis();
        Instant endIns=Instant.now();
        System.out.println("经过:"+(end-start)+"毫秒的计算，得到结果为:"+invoke);
        System.out.println("经过:"+(Duration.between(startIns,endIns).toMillis())+"毫秒的计算，得到结果为:"+invoke);
    }

    /**
     * 并行流
     */
    @Test
    public void test2(){
        Instant startIns=Instant.now();
        long reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel()//并行流 120ms
                //.sequential()//串行流  104ms
                .reduce(0, Long::sum);
        Instant endIns=Instant.now();
        System.out.println("经过:"+(Duration.between(startIns,endIns).toMillis())+"毫秒的计算，得到结果为:"+reduce);

    }
}
