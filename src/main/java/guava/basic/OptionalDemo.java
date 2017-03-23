package guava.basic;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-25
 * Time: 15:46
 * Guava Optional Demo.
 */
public class OptionalDemo {
    public static void main(String[] args) {
        // null 自身的歧义性, 可能表示一个null的object, 也可以是不存在的意思, Optional明确了这种含义

        // Optional<Integer> possible = Optional.of(5);
        Optional<Integer> possible = Optional.fromNullable(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());

        // absent
        // Optional<Integer> absent = Optional.absent();
        Optional<Integer> absent = Optional.fromNullable(null);
        System.out.println(absent.isPresent());
        System.out.println(absent.orNull()); // absent use .orNull instead of .get or .or(Obj)

        //
        System.out.println(Objects.firstNonNull(null, "abc"));
        try {
            System.out.println(Objects.<Object>firstNonNull(null, null));
        } catch (NullPointerException e) {
        }
        System.out.println(Strings.emptyToNull(""));
    }
}
