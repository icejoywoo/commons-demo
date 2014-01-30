package guava.strings;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 14:30
 * Guava splitter demo.
 */
public class SplitterDemo {
    public static void main(String[] args) {
        System.out.println(Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux"));
        System.out.println(Splitter.on(',').omitEmptyStrings().split("foo,bar,,   qux"));
        System.out.println(Splitter.on(',').split("foo,bar,,   qux"));

        System.out.println(Splitter.onPattern("(\\[|\\]\\W\\[|\\])").omitEmptyStrings().split("[a = 1] [b = 2]"));

        System.out.println(Splitter.fixedLength(3).split("123456789"));

        System.out.println(Splitter.fixedLength(3).limit(2).split("123456789"));

        System.out.println(Splitter.fixedLength(4).limit(2).trimResults(CharMatcher.is('_')).split("123_456789"));
    }
}
