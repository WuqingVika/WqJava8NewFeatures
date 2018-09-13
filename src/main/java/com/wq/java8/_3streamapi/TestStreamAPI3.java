package com.wq.java8._3streamapi;

import com.wq.java8._1lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * Created by qwu on 2018/7/27.
 * 查找与匹配
 * 归约
 * 收集
 */
public class TestStreamAPI3 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 100.00),
            new Employee(101, "张三", 18, 200.00),
            new Employee(103, "王五", 28, 100.00),
            new Employee(104, "赵六", 8, 150.00),
            new Employee(104, "赵六", 28, 200.00),
            new Employee(105, "田七", 38, 150.00)
    );

    @Test
    public void test1() {//查找与匹配
        //排序
        emps.stream().sorted(Comparator.comparingDouble(Employee::getSalary))
                .forEach(System.out::println);
        System.out.println("-------------------------------1---------------------");
        //找到最有钱的员工
        Optional<Employee> one = emps.stream()//.sorted((x,y)->Double.compare(x.getSalary(),y.getSalary()))
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(one.get());
        System.out.println("-------------------------------2---------------------");
        //找到最穷的员工的工资是多少
        Optional<Double> min = emps.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min.get());
        System.out.println("-------------------------------3---------------------");
        //个数
        long count = emps.stream().filter((x) -> x.getSalary() > 50).count();
        System.out.println(count);
    }

    @Test
    public void test2() {//归约
        System.out.println("----------------------（1）测试归约---------------------");
        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer a = 1;
        Integer sum = list.stream().reduce(a, (x, y) -> x + y);
       /* Optional<Integer> reduce = list.stream().reduce((x, y) -> x + y);
        System.out.println(reduce.get());*/
        //先把a作为x,再从集合中取出第一个元素1作为y，
        // 把它们相加的结果再作为x,再从集合中取出第2个元素作为y依次类推相加的结果就是sum.
        System.out.println(sum);
        System.out.println("----------------------（2）测试员工工资总和---------------------");
        Optional<Double> salarys = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        //为什么这里返回的是Optional？因为员工的工资为空，而上面第一个例子中有个起始值a所以它不为空
        System.out.println(salarys.get());
    }

    @Test
    public void test3() {//收集
        System.out.println("----------------------（1）测试收集---------------------");
        //放到list中
        List<String> collect = emps.stream().map(Employee::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("----------------------（2）测试收集set去重---------------------");
        //放到set中 去下重
        Set<String> namesDistinct = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        namesDistinct.forEach(System.out::println);
        //想放哪就放哪
        System.out.println("----------------------（3）测试收集hashset---------------------");
        HashSet<String> hashNames = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashNames.forEach(System.out::println);
        System.out.println("----------------------（4）测试收集 获取总数和平均值 ---------------------");

        //总数
        Long num = emps.stream().collect(Collectors.counting());
        System.out.println(num);
        //平均值
        Double averageSalary = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averageSalary);
        //工资总和
        Double sumSalary = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sumSalary);
        //获取最大工资的员工
        Optional<Employee> maxEmployee = emps.stream().collect(Collectors.maxBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        System.out.println(maxEmployee.get());
        //获取最小工资值
        Optional<Double> minSalary = emps.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println(minSalary.get());

    }

    @Test
    public void test3_1() {//组函数
        DoubleSummaryStatistics collect = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getAverage());//平均值
        System.out.println(collect.getSum());//员工工资总数
        System.out.println(collect.getCount());//员工数
        System.out.println(collect.getMax());//最大工资数
        System.out.println(collect.getMin());//最小工资数
    }

    @Test
    public void test4() {
        //收集--分组
        Map<String, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getName));
        // map没有foreach
        //System.out.println(collect);

        //收集--多级分组
        Map<String, Map<Integer, List<Employee>>> collect1 = emps.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy(Employee::getAge)));
        System.out.println(collect1);
        Map<String, Map<String, List<Employee>>> collect2 = emps.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy((x) -> {
            if (x.getAge() < 18) {
                return "少年";
            } else {
                return "成年";
            }
        })));
        System.out.println(collect2);
    }

    @Test
    public void test5() {
        //收集--分区
        Map<Boolean, List<Employee>> collect = emps.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 120.0));
        //满足工资大于120的分一个区，其他的分在另一区
        System.out.println(collect);
    }

    @Test
    public void test6(){
        String collect = emps.stream().map(Employee::getName)
                // .distinct()//去重
                // .collect(Collectors.joining());
                //.collect(Collectors.joining("->"));//加上符号  输出   李四->张三->王五->赵六->赵六->田七
                .collect(Collectors.joining("->","(",")"));//加上首尾 输出 (李四->张三->王五->赵六->赵六->田七)
        System.out.println(collect);
    }
}
