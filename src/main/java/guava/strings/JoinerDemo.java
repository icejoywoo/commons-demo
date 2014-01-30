package guava.strings;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 13:31
 * Guava Strings Joiner.
 */
public class JoinerDemo {
    public static void main(String[] args) {
        // joiner instances are always immutable. The joiner configuration methods will always return a new Joiner
        Joiner joiner = Joiner.on(';').skipNulls(); // skip null
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));

        try {
            System.out.println(Joiner.on(',').join("Harry", null, "Ron", "Hermione")); // raise NullPointerException
        } catch(NullPointerException e) {
            System.out.println(e);
        }

        System.out.println(Joiner.on(',').useForNull("NULL").join("Harry", null, "Ron", "Hermione")); // null instead of "NULL"

        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7))); // returns "1,5,7"
    }
}
