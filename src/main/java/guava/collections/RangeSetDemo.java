package guava.collections;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * Created by wujiabin on 14-1-28.
 */
public class RangeSetDemo {
    public static void main(String[] args) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        System.out.println(rangeSet);

        // complement
        System.out.println(rangeSet.complement());
        System.out.println(rangeSet.contains(40));
        System.out.println(rangeSet.span());

        // iteration
        for (Range<Integer> e : rangeSet.asRanges()) {
            System.out.println(e + " " + e.contains(1));
        }
    }
}