package com.wq.java8._5optional;

import com.wq.java8._1lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by qwu on 2018/8/9.
 */
public class TestOptional {

    @Test
    public void test1(){
        Optional<Employee> optional = Optional.ofNullable(null);//new Employee()
        //Optional.of(null)直接报错
        if(optional.isPresent()){
            //代表有值
            System.out.println(optional.get());
        }else{
            System.out.println("Optional.ofNullable(null)会导致employee中没有get()方法哦");
        }

        Optional<Employee> optional2 = Optional.ofNullable(null);
        Employee employee=optional2.orElse(new Employee("wq",24));//orElse没值就给它new一个Employee对象
        //System.out.println(employee);

        Employee employee3=optional2.orElseGet(()->  new Employee());//跟上一个比优点在于：它能接受函数式接口
       // System.out.println(employee3);

        Optional<Employee> wq = Optional.ofNullable(new Employee("wq", 1));
        /*Optional<String> name = wq.map((x) -> x.getName());
        System.out.println(name.get());*/
        Optional<String> s = wq.flatMap((x) -> Optional.of(x.getName()));
        System.out.println(s.get());
    }

    @Test
    public void test2(){
        Optional<Goddess> goddess=Optional.ofNullable(new Goddess("hebe"));
        Optional<NewMan> optional = Optional.ofNullable(new NewMan(goddess));//null 则输出wuqingvika
        System.out.println(getGoddessName(optional));
    }

    /**
     * 获取一个男人心中的女神名字
     * @param newMan
     * @return
     */
    public String getGoddessName(Optional<NewMan> newMan){
        return newMan.orElse(new NewMan())
                .getGoddess()
                .orElse(new Goddess("wuqingvika"))
                .getName();
    }
}
