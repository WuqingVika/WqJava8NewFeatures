package com.wq.java8._4forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 并行流与串行流
 * Created by qwu on 2018/8/9.
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {
    private long start;
    private long end;
    public static final long THRESHOLD=10000;

    public ForkJoinCalculate(long start,long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long length = end - start;
        if(length<=THRESHOLD){
            //到了临界值
            long sum=0;
            for (long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            long middle=(start+end)/2;
            ForkJoinCalculate left=new ForkJoinCalculate(start,middle);
            left.fork();//拆分子任务，同时压入线程队列
            ForkJoinCalculate right=new ForkJoinCalculate(middle+1,end);
            right.fork();
            //合并
            return left.join() + right.join();
        }
    }
}
