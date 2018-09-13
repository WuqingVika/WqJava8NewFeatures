package com.wq.java8._7datetime;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by qwu on 2018/8/10.
 */
public class TestSimpleDateFormat {
    //解决线程安全问题 传统的实现
    public static void main(String[] args) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task=new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return MyDateFormatThreadLocal.convert("20180810");//sdf.parse("20180810");
            }
        };
        ExecutorService pool= Executors.newFixedThreadPool(10);
        List<Future<Date>> futures=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(task));
        }
        for (Future<Date> m:futures) {
            try {
                System.out.println(m.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    @Test
    public void testNewApi(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyyMMdd");//.ISO_LOCAL_DATE;
        Callable<LocalDate> task=new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
              return  LocalDate.parse("20180810",dtf); // return MyDateFormatThreadLocal.convert("20180810");//sdf.parse("20180810");
            }
        };
        ExecutorService pool= Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> futures=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(task));
        }
        for (Future<LocalDate> m:futures) {
            try {
                System.out.println(m.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }
}
