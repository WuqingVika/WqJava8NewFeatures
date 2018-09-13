package com.wq.java8._2exec;

import com.wq.java8._1lambda.Employee;
import org.junit.Test;

import java.util.*;

/**
 * Created by qwu on 2018/7/9.
 */
public class WqExecLambda {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "2张三", 28, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "1王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void testSort(){
        Collections.sort(emps,(x,y)->{
            if(x.getAge()==y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(),y.getAge());
            }
        });
        emps.stream().forEach(System.out::println);
    }

    public String getUpperString(String s, MyFunction myFunction){
        return myFunction.getValue(s);
    }

    @Test
    public void testUpperString(){
        String s=new String("wuqingVika");
        String upperString = getUpperString(s, x -> x.toUpperCase());
        System.out.println(upperString);
    }

    public void getOperateResult(Long x,Long y,MyFunction2<Long,Long> myFunction2){
        System.out.println(myFunction2.getValue(x, y));
    }

    @Test
    public void testOperate(){
       getOperateResult(1L,2L,(x,y)->x+y);
    }
}
