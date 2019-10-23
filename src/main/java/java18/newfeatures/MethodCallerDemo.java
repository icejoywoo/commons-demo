package java18.newfeatures;

import java.util.ArrayList;
import java.util.List;

public class MethodCallerDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
