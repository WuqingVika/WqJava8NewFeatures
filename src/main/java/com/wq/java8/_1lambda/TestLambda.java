package com.wq.java8._1lambda;

import org.junit.Test;

import java.util.*;

/**
 * 1.1 Lambda表达式入门篇
 * Created by qwu on 2018/5/28.
 */
public class TestLambda {
    //1.原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> a=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);//就这么一句有用的
            }
        };
        TreeSet<Integer> ts=new TreeSet<>(a);
    }

    //现在的 Lambda 表达式
    @Test
    public void test2(){
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);
        System.out.println(ts);
    }

    //2.1获取当前
    //需求：获取公司中年龄小于 35 的员工信息
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );
    public List<Employee> filterEmployeeAge(List<Employee> emps){
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if(emp.getAge() <= 35){
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void test3(){
        List<Employee> list = filterEmployeeAge(emps);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
    //2.2需求：获取公司中工资大于 5000 的员工信息
    public List<Employee> filterEmployeeSalary(List<Employee> emps){
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if(emp.getSalary() >= 5000){
                list.add(emp);
            }
        }
        return list;
    }
    //优化方式一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();

        for (Employee employee : emps) {
            if(mp.test(employee)){
                list.add(employee);
            }
        }

        return list;
    }
    @Test
    public void test4(){
        List<Employee> list = filterEmployee(emps, new FilterEmployeeForAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("------------------------------------------");
        List<Employee> list2 = filterEmployee(emps, new FilterEmployeeForSalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }
    //优化方式二：匿名内部类 原来每次搞个实现类 现在搞成这个
    @Test
    public void test5(){
        List<Employee> list = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee t) {
                return t.getId() <= 103;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
    //FIXME 优化方式三：Lambda 表达式
    @Test
    public void test6(){
        List<Employee> list = filterEmployee(emps, (e) -> e.getAge() <= 35);
        list.forEach(System.out::println);

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() >= 5000);
        list2.forEach(System.out::println);
    }

    //优化方式四：Stream API
    @Test
    public void test7(){
        emps.stream()
                .filter((e) -> e.getAge() <= 35)
                .forEach(System.out::println);

        System.out.println("----------------------------------------------");

        emps.stream()
                .map(Employee::getName)
                .limit(3)
                .sorted()
                .forEach(System.out::println);
    }
}
