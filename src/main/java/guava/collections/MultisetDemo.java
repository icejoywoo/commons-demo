package guava.collections;

import com.google.common.collect.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-28
 * Time: 0:13
 * Guava new type collection multiset.
 * There are two main ways of looking at this:
 *      This is like an ArrayList<E> without an ordering constraint: ordering does not matter.
 *      This is like a Map<E, Integer>, with elements and counts.
 */
public class MultisetDemo {
    public static void main(String[] args) {
        // HashMultiset TreeMultiset LinkedHashMultiset ConcurrentHashMultiset ImmutableMultiset
        Multiset<String> multiset = HashMultiset.create(); // unsorted
        for (int i = 0; i < 10; ++i) {
            multiset.add(Integer.toString(i), 3);
        }
        System.out.println(multiset + "\t" + multiset.size());
        multiset.remove("3", 3);
        System.out.println(multiset);

        // iterate distinct set
        for (String e : multiset.elementSet()) {
            System.out.print(e + " " + multiset.count(e) + ",");
        }
        System.out.println();
        // iterate multiset
        for (Multiset.Entry<String> e : multiset.entrySet()) {
            System.out.print(e + " " + e.getCount() + ",");
        }

        Multiset<String> ms2 = LinkedHashMultiset.create(); // unsorted, in insert order
        for (int i = 0; i < 10; ++i) {
            ms2.add(Integer.toString(i), 3);
        }
        System.out.println(ms2);

        Multiset<String> ms3 = ImmutableMultiset.of("b", "b", "a", "c", "d", "a");
        System.out.println(ms3);

        SortedMultiset<Integer> sortedMultiset = TreeMultiset.create();
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            sortedMultiset.add(100 * i, random.nextInt(10));
        }
        System.out.println(sortedMultiset);
        SortedMultiset<Integer> slice = sortedMultiset.subMultiset(0, BoundType.CLOSED, 500, BoundType.OPEN);
        System.out.println(slice.size() + "\t" + slice);
    }
}
