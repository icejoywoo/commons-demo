package guava.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by wujiabin on 14-1-28.
 */
public class MultimapDemo {
    public static void main(String[] args) {
        // Multimap Is Not A Map, not a Map<K, Collection<V>>
        Multimap<String, Integer> map = ArrayListMultimap.create();
        map.put("a", 1);
        map.put("a", 2);
        map.put("b", 3);
        List<Integer> values = ImmutableList.of(3, 4, 5);
        map.putAll("c", values);
        System.out.println(map);

        System.out.println(map.asMap());

        for (Map.Entry<String, Collection<Integer>> e : map.asMap().entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }

        for (Map.Entry<String, Integer> e : map.entries()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }
        // distinct keys' size
        System.out.println(map.keySet().size());

        System.out.println(map.keys());
        System.out.println(map.values());
    }
}
