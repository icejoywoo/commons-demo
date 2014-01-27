package guava.collections;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * Created by wujiabin on 14-1-27.
 */
public class ImmutableSetDemo {
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

    public static void main(String[] args) {

        final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");
    }
}
