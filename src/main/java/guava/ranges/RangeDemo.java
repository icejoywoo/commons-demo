package guava.ranges;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-1
 * Time: 16:07
 * Guava Ranges demo.
 */
public class RangeDemo {
    public static void main(String[] args) {
        List<Double> scores = Arrays.asList(0d, 1d, 2d, 3d, 4d, 5d);
        Iterable<Double> belowMedianScores = Iterables.filter(scores, Range.lessThan(3d));
        System.out.println(belowMedianScores);
        Range<Integer> validGrades = Range.closed(1, 12);
        for (int grade : ContiguousSet.create(validGrades, DiscreteDomain.integers())) {
            System.out.print(grade + " ");
        }
        System.out.println();

        Range<Integer> closedRange = Range.closed(1, 10);
        System.out.println(closedRange.contains(5) + "\t" + closedRange.containsAll(Ints.asList(1, 2, 3, 4)));
        System.out.println(closedRange.encloses(Range.closed(2, 5)));

        System.out.println(Range.lessThan(3d));
        System.out.println(Range.all());
        System.out.println(Range.downTo(4, BoundType.OPEN)); // allows you to decide whether or not you want to include 4
        System.out.println(Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN)); // another way of writing Range.closedOpen(1, 4)

        Range.closedOpen(4, 4).isEmpty(); // returns true
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 5).isEmpty(); // Range.open throws IllegalArgumentException

        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN
    }
}
