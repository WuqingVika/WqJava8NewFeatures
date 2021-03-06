package com.wq.java8._1lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 1.2 Lambda表达式语法
 * Created by qwu on 2018/5/28.
 * /*
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 						    箭头操作符将 Lambda 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 *     （不然没办法区别实现哪个方法）
 */
public class TestLambda2 {
    @Test
    public void test1(){//无参数，无返回值
        int num = 0;//jdk 1.7 前，必须是 final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);//但是num实际还是一个final只不过省略了而已
            }
        };

        r.run();

        System.out.println("-------------------------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
        String a=new String("东区电信局");
        a=a.replace("电信局","");
        System.out.println(a);
    }

    @Test
    public void test2(){//一个参数 无返回值
        Consumer<Long> con = (x) -> {System.out.println(x+6);};
        con.accept(2L);
        Consumer<String> con2 = x -> System.out.println(x+1);
        con2.accept("111");
    }

    @Test
    public void test3(){//有两个以上的参数，有返回值 并且 Lambda 体中有多条语句
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        System.out.println(com.compare(4,5));

        Comparator<Integer> com2 = (x, y) -> 3;//{return 3}
        System.out.println(com2.compare(4,5));
    }

    @Test
    public void test4(){//若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
        Comparator<Integer> com = (x, y) ->  Integer.compare(x, y);
        System.out.println(com.compare(4, 3));
    }

    @Test
    public void test5(){
 		String[] strs={"aa","bb","CC"};
 		//strs = {"aaa", "bbb", "ccc"};//类型推断  不能拆开写 这样写是错的

        List<String> list = new ArrayList<>();//类型推断 <>里面可以不写

        show(new HashMap<>());//类型推断
    }

    public void show(Map<String, Integer> map){

    }

    //需求：对一个数进行运算
    @Test
    public void test6(){
       Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200, (y) -> y + 200));
    }

    public Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }
}
