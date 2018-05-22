import javax.rmi.CORBA.Util;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by @author wangzhenya on 2018/5/22
 * 参考:http://blog.oneapm.com/apm-tech/226.html
 */
public class LambdaDemo {
    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list)  {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void main(String[] args) {
        //旧方法:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("老写法");
            }
        }).start();

        // 函数式接口
        new Thread(
                () -> System.out.println("Lambda表达式作为参数")
        ).start();

        System.out.println("遍历");
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        integerList.forEach(n -> System.out.print(n + " "));
        System.out.println();
        // 双冒号变成lambad表达式
        integerList.forEach(System.out::println);

        System.out.println("Print all numbers:");
        evaluate(integerList, (n)->true);

        System.out.println("Print even numbers:");
        evaluate(integerList, (n)-> n%2 == 0 );

        System.out.println("Print greater than 3");
        Predicate<Integer> isGreaterThan3 = number -> number > 3;
        integerList.stream().filter(isGreaterThan3).forEach(System.out::println);


    }
}
