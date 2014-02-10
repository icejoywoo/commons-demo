package guava.collect;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.io.Files;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

/**
 * Created by wujiabin on 14-2-10.
 */
public class BuilderTest {
    @Test
    public void testCollectionsBasicBuilder() {
        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
        map.put("test", Maps.<Long, List<String>>newHashMap());
        System.out.println(map);
        List<String> list = Lists.newArrayList("a", "b");
        assertThat(list, hasItems("a", "b"));
        Set<String> set = Sets.newHashSet("a", "a", "b");
        assertThat(set, hasItems("a", "b"));
        assertThat("a", isIn(set));
    }

    @Test
    public void testImmutableCollectionsBasic() {
        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
        assertThat(list, hasItems("a", "b", "c", "d"));
        Map<String, String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
        Map<String, String> expected = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
        assertThat(map.entrySet(), equalTo(expected.entrySet()));
        assertThat(map.entrySet(), everyItem(isIn(expected.entrySet())));
    }

    @Test
    public void testReadFile() {
        File file = new File(this.getClass().getResource("/test.txt").getFile());
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charset.forName("GB18030"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);
    }

    @Test
    public void testMultimapSlice() {
        List<Map<String, String>> listOfMaps = Lists.newArrayList();
        listOfMaps.add(ImmutableMap.of("type", "blog", "id", "292", "author", "john"));
        listOfMaps.add(ImmutableMap.of("type", "blog", "id", "293", "author", "sam"));
        listOfMaps.add(ImmutableMap.of("type", "news", "id", "294", "author", "benjamin"));
        listOfMaps.add(ImmutableMap.of("type", "news", "id", "295", "author", "james"));
        Multimap<String, Map<String, String>> partitionedMap = Multimaps.index(
                listOfMaps,
                new Function<Map<String, String>, String>() {
                    public String apply(final Map<String, String> from) {
                        return from.get("type");
                    }
                });
        System.out.println(partitionedMap);
    }
}
