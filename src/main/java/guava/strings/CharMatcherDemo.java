package guava.strings;

import com.google.common.base.CharMatcher;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 14:58
 * Guava CharMatcher.
 */
public class CharMatcherDemo {
    public static void main(String[] args) {
        String string = "abc123ABC \t\u0001";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); // remove control characters
        System.out.println(noControl);

        String theDigits = CharMatcher.DIGIT.retainFrom(string); // only the digits
        System.out.println(theDigits);

        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(spaced);

        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); // star out all digits
        System.out.println(noDigits);
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
        // eliminate all characters that aren't digits or lowercase
        System.out.println(lowerAndDigit);

        System.out.println(CharMatcher.anyOf("a1A").retainFrom("abcdefghijklmnABCDEFGHIJKLMN1234567890"));
        System.out.println(CharMatcher.anyOf("a1A").removeFrom("abcdefghijklmnABCDEFGHIJKLMN1234567890"));
        System.out.println(CharMatcher.JAVA_LOWER_CASE.or(CharMatcher.is('_')).retainFrom("123ABCasdf_csdfSDF12adsf"));
        System.out.println(CharMatcher.inRange('a', 'h').and(CharMatcher.ANY).retainFrom("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(CharMatcher.inRange('a', 'h').retainFrom("abcdefghijklmnopqrstuvwxyz"));
        System.out.println(CharMatcher.inRange('a', 'h').collapseFrom("abcdefghijklmnopqrstuvwxyz", '_'));
        System.out.println(CharMatcher.JAVA_LETTER.trimFrom("abcdefgh12312312312ijklmnopqrstuvwxyz"));
    }
}
