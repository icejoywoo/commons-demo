package guava.basic;

import com.google.common.base.Joiner;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;

/**
 * Created by wujiabin on 14-2-7.
 */
public class JoinerTest {

    // joiner is immutable on creation
    private Joiner joiner = null;
    private Joiner old = null;

    @BeforeTest
    public void beforeTest() {
        joiner = Joiner.on(',');
    }

    @AfterTest
    public void afterTest() {
        joiner = null;
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
    }

    @Test
    public void testJoinerBasic() {
        StringBuilder builder = new StringBuilder();
        assertThat(joiner.appendTo(builder, "a", "b", "c").toString(), equalTo("a,b,c"));
        old = joiner;
    }

    @Test
    public void testJoinerWithNull() {
        assertThat(joiner.skipNulls().join("a", "b", "c", null), equalTo("a,b,c"));
        assertThat(joiner.useForNull("no value").join("a", "b", "c", null), equalTo("a,b,c,no value"));
        assertThat(joiner, is(old));
        assertThat(joiner, equalTo(old));
    }

    private Object createPerson(final String name) {
        return new Object() {
            @Override
            public String toString() {
                return name;
            }
        };
    }

    @Test
    public void testJoinerOnObjectsList() {
        StringBuilder builder = new StringBuilder();
        joiner.appendTo(builder, createPerson("a"), createPerson("b"), createPerson("c"));
        // allOf
        assertThat(builder.toString(), allOf(equalTo("a,b,c"), startsWith("a,b,c")));
        // anyOf
        assertThat(builder.toString(), anyOf(equalTo("a,b,c"), startsWith("a,b,c")));
    }

    @Test
    public void testJoinerAppendToAppendable() throws IOException {
        System.out.println(System.getProperty("java.io.tmpdir"));
        try (FileWriter fileWriter = new FileWriter(new File(System.getProperty("java.io.tmpdir"), "temp.txt"))) {
            joiner.appendTo(fileWriter, createPerson("a"), createPerson("b"), createPerson("c"));
        }
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void testJoinerNoNullHandler() {
        Joiner.on("#").join("foo",null,"bar");
        fail("Should not get here");
    }

    @Test
    public void testMapJoinerBasic() {
        Joiner.MapJoiner mapJoiner = Joiner.on('#').withKeyValueSeparator("=").useForNull("NULL");
        Map<String, String> map = new HashMap<String, String>() {{
            put("Washington D.C", "Redskins");
            put("New York City", "Giants");
            put("Philadelphia", "Eagles");
            put("Dallas", "Cowboys");
            put(null, null);
        }};
        assertThat(mapJoiner.join(map),
                is("NULL=NULL#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys#Washington D.C=Redskins"));
    }

}
