package guava.primitives;

import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-31
 * Time: 22:48
 * Guava Primitive array utilities.
 */
public class ArrayUtilitiesDemo {
    public static void main(String[] args) {
        // array utilities
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8, 9);
        System.out.println(list);
        System.out.println(list.contains(1) + "\t" + list.indexOf(3) + "\t" + list.lastIndexOf(8));
        Integer[] array = (Integer[]) list.toArray();
        System.out.println(array);

        // max & min
        System.out.println(Collections.min(list) + "\t" + Collections.max(list));

        // 拼接array
        System.out.println(Iterables.concat(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(6, 7, 8, 9, 0)));


    }
}
