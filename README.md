##一、主要内容
- 1.Lambda表达式
- 2.函数式接口
- 3.方法引用和构造器引用
- 4.Stream API
- 5.接口中的默认方法与静态方法
- 6.新时间日期API
- 7.其他新特性

### 1.1 Lambda表达式
> 注：不支持Lambda表达式可参考https://blog.csdn.net/weixin_39800144/article/details/78500449?locationNum=8&fps=1进行设置。

```java
 //Lambda 表达式
    @Test
    public void test6(){
        List<Employee> list = filterEmployee(emps, (e) -> e.getAge() <= 35);
        list.forEach(System.out::println);

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() >= 5000);
        list2.forEach(System.out::println);
    }
```
### 1.2 Stream API
```java
    //Stream API
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
```

