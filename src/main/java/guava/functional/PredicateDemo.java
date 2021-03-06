package guava.functional;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 18:59
 * Guava predicate demo.
 */
public class PredicateDemo {
    public static void main(String[] args) {
        boolean result = Iterables.all(Arrays.asList(11, 12), new Predicate<Integer>() {

            @Override
            public boolean apply(Integer input) {
                return input > 10;
            }
        });
        System.out.println(result);
    }
}
