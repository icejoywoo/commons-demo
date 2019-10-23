package java18.newfeatures;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        System.out.println("strings:");
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings.stream().filter(w -> !w.isEmpty()).map(String::toUpperCase).forEach(System.out::println);

        System.out.println("random:");
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        System.out.println("squared:");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        System.out.println("list: " + numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList()));
        numbers.stream().map( i -> i*i).distinct().forEach(System.out::println);

        System.out.println("count:");
        // 获取空字符串的数量
        System.out.println(strings.stream().filter(String::isEmpty).count());

        System.out.println(strings.parallelStream().filter(String::isEmpty).count());

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        System.out.println("IntSummaryStatistics:");
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
