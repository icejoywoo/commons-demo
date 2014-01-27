package guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;

import java.util.Collection;
import java.util.Set;

/**
 * Created by wujiabin on 14-1-27.
 */
public class ImmutableSetDemo {

    public final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    static class Bar {
        String name;
    }

    static class Foo {
        Set<Bar> bars;

        // Making immutable copies of objects is a good defensive programming technique.
        Foo(Set<Bar> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy!
        }
    }

    static void doSomething(Collection<String> collection) {
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(collection);
    }

    public static void main(String[] args) {
        ImmutableSet<String> foobar = ImmutableSet.of("a", "b", "c");
        System.out.println(foobar.toString());

        ImmutableList<String> l = foobar.asList();
        System.out.println(l);

        doSomething(foobar);
        // iterator
        for (String e : foobar) {
            System.out.println(e);
        }

        for (UnmodifiableIterator<String> iter = foobar.iterator();
             iter.hasNext(); ) {
            String e = iter.next();
            System.out.println(e);
        }
    }
}
