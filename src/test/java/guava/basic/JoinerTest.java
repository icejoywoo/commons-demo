package guava.basic;

import com.google.common.base.Joiner;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by wujiabin on 14-2-7.
 */
public class JoinerTest {

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
        assertThat(builder.toString(), equalTo("a,b,c"));
        assertThat(builder.toString(), startsWith("a,b,c"));
    }
}
