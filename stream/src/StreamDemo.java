import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author Administrator
 * 参考 https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html
 */
public class StreamDemo {
    public static void main(String[] args) {
        // 流构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // 转换大写
        List<String> wordList = new LinkedList<>();
        wordList.add("first");
        wordList.add("second");
        List<String> wordUpperList = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
        wordUpperList.stream().forEach(System.out::println);

        // 平方数
        System.out.println("square");
        List<Integer> numList = Arrays.asList(1, 2, 3);
        List<Integer> squareList = numList.stream().map(n -> n*n).collect(Collectors.toList());
        squareList.stream().forEach(System.out::println);

        // 合并stream
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap(childList -> childList.stream());
        outputStream.forEach(System.out::println);

        // 过滤
        System.out.println("filter.");
        Integer[] integers = {1, 2, 3, 4};
        Integer[] oddIntegers = Arrays.stream(integers).filter(n ->n%2==0).toArray(Integer[]::new);
        Arrays.stream(oddIntegers).forEach(System.out::println);

        // peek
        System.out.println("peek");
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        // Optional
        System.out.println("optional");
        Optional.ofNullable(null).ifPresent(System.out::println);
        Optional.ofNullable("test").ifPresent(System.out::println);

        // reduce
        System.out.println("reduce");
        System.out.println(Stream.of("a", "b", "c").filter(a -> a.compareTo("b") >= 0).reduce("", String::concat));
        System.out.println(Stream.of(1.1, 2.2, 3.3).reduce(Double.MAX_VALUE, Double::min));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(0, Integer::sum));

        // skip
        System.out.println("skip");
        Stream.of("1", "2", "3", "4").skip(1).limit(2).forEach(System.out::println);

        // sort
        System.out.println("sort");
        Stream.of("1", "2", "3", "4").skip(1).limit(2).sorted((n1, n2) -> n1.compareTo(n2)).forEach(System.out::println);
        Stream.of("1", "2", "3", "4").skip(1).limit(2).sorted((n1, n2) -> n2.compareTo(n1)).forEach(System.out::println);

        // max
        System.out.println("max length " + Stream.of("1", "12", "123").mapToInt(String::length).max().getAsInt());

        // 统计单词
        String line = "hello your Hello world.";
        Stream.of(line.split(" ")).map(String::toLowerCase).distinct().sorted().forEach(System.out::println);

        // match
        System.out.println("match");
        Integer[] age = {22, 25, 28};
        System.out.println(Arrays.stream(age).anyMatch(n -> n> 25));
        System.out.println(Arrays.stream(age).allMatch(n -> n> 12));
        System.out.println(Arrays.stream(age).noneMatch(n -> n> 88));

        // generate
        System.out.println("generate");
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
        System.out.println();

        // group by
        System.out.println("Group by");
        List<Person> personList = new LinkedList<>();
        Person p1 = new Person(22, "n1");
        Person p2 = new Person(22, "n2");
        Person p3 = new Person(23, "n3");
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        Map<Integer, List<Person>> ageMap = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(ageMap.get(22).size());
    }
}
