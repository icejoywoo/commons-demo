package guava.basic;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Objects.*;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-25
 * Time: 16:17
 * Guava Ordering demo.
 */
public class OrderingDemo {

    static class Foo {
        Foo(String sortedBy, int notSortedBy) {
            this.sortedBy = sortedBy;
            this.notSortedBy = notSortedBy;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("sorted", this.sortedBy)
                    .add("notSortedBy", this.notSortedBy)
                    .toString();
        }

        public String sortedBy;
        public int notSortedBy;
    }

    public static void main(String[] args) {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(java.lang.String left, java.lang.String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        List<String> l1 = Arrays.asList("abc", "a", "bcd");
        System.out.println(byLengthOrdering.isOrdered(l1));
        // sort
        System.out.println(byLengthOrdering.sortedCopy(l1));

        List<String> l2 = byLengthOrdering.sortedCopy(l1);
        // in reverse order
        System.out.println(byLengthOrdering.reverse().isOrdered(l2));
        System.out.println(byLengthOrdering.reverse().sortedCopy(l2));

        List<String> l3 = Arrays.asList(null, "a", "ab", "bcd");
        System.out.println(byLengthOrdering.nullsFirst().sortedCopy(l3));
        System.out.println(byLengthOrdering.nullsLast().sortedCopy(l3));
        // k greatest
        System.out.println(byLengthOrdering.nullsLast().greatestOf(l3, 2));
        // min
        System.out.println(byLengthOrdering.min("a", "b", "0", "A"));
        System.out.println(byLengthOrdering.min(l2));

        // onResultOf Function
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
        List<Foo> l4 = Arrays.asList(new Foo("a", 1), new Foo("c", 2), new Foo("b", 3));
        System.out.println(ordering.sortedCopy(l4));

        // nature
        Ordering<Integer> nature = Ordering.natural();
        System.out.println(nature.sortedCopy(Arrays.asList(4, 5, 1, 2, 0)));

        // toString
        Ordering<Object> toStringOrdering = Ordering.usingToString();
        System.out.println(toStringOrdering.sortedCopy(Arrays.asList(new Object(), new Object())));
    }
}
