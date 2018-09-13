package com.wq.java8._7datetime;

import org.junit.Test;

import java.time.*;

/**
 * Created by qwu on 2018/8/10.
 */
public class TestLocalDatetime {
    @Test
    public void test(){//人能读懂的时间
        LocalDate localDate=LocalDate.now();//2018-08-10
        System.out.println(localDate);

        LocalDateTime localDateTime=LocalDateTime.now();// 2018-08-10T15:22:25.119
        System.out.println(localDateTime);

        //自定义时间
        LocalDateTime localDateTime1= LocalDateTime.of(2018, 8, 10, 21, 10, 57);
        System.out.println(localDateTime1);
        //加减天数
        LocalDateTime ldt=LocalDateTime.now();//2018-08-10T16:14:10.466
        LocalDateTime ldtNew = ldt.plusDays(1).plusYears(1);//加一年零一天
        System.out.println(ldtNew);//2019-08-11T16:14:10.466

        LocalDateTime ldtNew2 = ldt.minusMonths(2);//减两个月
        System.out.println(ldtNew2);//2018-06-10T16:14:10.466
        //获取年月日时分秒
        System.out.println("=====================获取年月日时分秒========================");
        System.out.println(ldt.getYear());//年：
        System.out.println(ldt.getMonthValue());//月
        System.out.println(ldt.getDayOfMonth());//日
        System.out.println(ldt.getHour());//时
        System.out.println(ldt.getMinute());//分
        System.out.println(ldt.getSecond());//秒
    }

    @Test
    public void test2(){//计算机读的时间 时间戮 以Unix元年 1970年1月1日00：00：00到某个时间之间的毫秒值
        Instant instant=Instant.now();
        System.out.println(instant);//2018-08-10T16:44:35.101当前是默认获取的是UTC时区 标准的2018-08-10T08:44:35.101Z
        //那么怎么解决这种偏移呢？
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2018-08-10T16:51:37.942+08:00
        //时间戮
        System.out.println(instant.toEpochMilli());
        //test
        Instant instant2 = Instant.ofEpochSecond(3600);//在1970-01-01 00：00：00上加3600秒=1小时   ->1970-01-01T01:00:00Z
        System.out.println(instant2);
    }

    //计算两个时间之间的间隔 duration
    //计算两个日期之前的间隔 period
    @Test
    public void Test3(){
        String sWorkNos=",1,3";
        System.out.println(sWorkNos.substring(1));
    }
}
