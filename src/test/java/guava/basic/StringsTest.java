package guava.basic;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by wujiabin on 14-2-8.
 */
public class StringsTest {
    @Test
    public void testAscii() {
        assertThat(Ascii.isLowerCase('c'), is(true));
        assertThat(Ascii.isLowerCase('D'), is(false));

        assertThat(Ascii.equalsIgnoreCase("tEst", "TeSt"), is(true));
        assertThat(Ascii.equalsIgnoreCase("tEst1", "TeSt"), is(false));

        assertThat(Ascii.toLowerCase("tESt"), equalTo("test"));
        assertThat(Ascii.toUpperCase("test"), equalTo("TEST"));
    }

    @Test
    public void testCharsets() {
        List<Byte> list = Lists.newArrayList();
        for (Byte i : "abcxzy".getBytes(Charsets.UTF_8)) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println(Arrays.asList("abcxzy".getBytes(Charsets.UTF_8)));
    }

    @Test
    public void testStrings() {
        assertThat(Strings.padEnd("foo", 6, 'x'), equalTo("fooxxx"));

        assertThat(Strings.nullToEmpty(null), equalTo(""));
        assertThat(Strings.emptyToNull(""), equalTo(null));

        assertThat(Strings.isNullOrEmpty(""), is(true));
        assertThat(Strings.isNullOrEmpty(null), is(true));
    }

    @Test
    public void testCharMatcher() {
        String stringWithLineBreaks = "a\tb\n";
        assertThat(CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLineBreaks, " "), equalTo("a b "));

        String tabsAndSpaces = "String    with    spaces     and     tabs";
        assertThat(CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' '), is("String with spaces and tabs"));

        // trim
        String tabsAndSpaces1 = "   String    with    spaces     and     tabs";
        assertThat(CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces, ' '), is("String with spaces and tabs"));
    }
}
