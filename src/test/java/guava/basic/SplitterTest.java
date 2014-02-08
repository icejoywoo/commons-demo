package guava.basic;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by wujiabin on 14-2-8.
 */
public class SplitterTest {
    @Test
    public void testSplitterBasic() {
        // splitter is immutable on creation
        String testString = "Monday,Tuesday,,Thursday,Friday,,";
        // String.split 会截掉后面的几个空字符串
        assertThat(Arrays.asList(testString.split(",")),
                is(Arrays.asList("Monday", "Tuesday", "", "Thursday", "Friday")));
        assertThat(Lists.newArrayList(Splitter.on(',').split(testString)),
                is(Arrays.asList("Monday", "Tuesday", "", "Thursday", "Friday", "", "")));
    }

    @Test
    public void testSplitterTrimResults() {
        String testString = "Monday ,   Tuesday,,Thursday,Friday,,";
        // trimResults
        assertThat(Lists.newArrayList(Splitter.on(',').split(testString)),
                is(Arrays.asList("Monday ", "   Tuesday", "", "Thursday", "Friday", "", "")));
        assertThat(Lists.newArrayList(Splitter.on(',').trimResults().split(testString)),
                is(Arrays.asList("Monday", "Tuesday", "", "Thursday", "Friday", "", "")));
    }

    @Test
    public void testMapSplitter() {
        // 不能处理null
        String testString = "New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys#Washington D.C=Redskins";
        Splitter.MapSplitter mapSplitter = Splitter.on('#').withKeyValueSeparator('=');
        Map<String, String> expectedMap = new HashMap<String, String>() {{
            put("Washington D.C", "Redskins");
            put("New York City", "Giants");
            put("Philadelphia", "Eagles");
            put("Dallas", "Cowboys");
        }};
        assertThat(mapSplitter.split(testString), equalTo(expectedMap));
    }
}
