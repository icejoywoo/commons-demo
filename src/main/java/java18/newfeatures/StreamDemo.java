package java18.newfeatures;

import java.util.Arrays;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings.stream().filter(w -> !w.isEmpty()).map(String::toUpperCase).forEach(System.out::println);
    }
}
