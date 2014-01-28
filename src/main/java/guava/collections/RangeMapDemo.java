package guava.collections;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.Map;

/**
 * Created by wujiabin on 14-1-28.
 */
public class RangeMapDemo {
    public static void main(String[] args) {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
        System.out.println(rangeMap);
        System.out.println(rangeMap.get(5));

        // iteration
        for (Map.Entry<Range<Integer>, String> e : rangeMap.asMapOfRanges().entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }
    }
}
