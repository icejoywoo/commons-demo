package guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-22
 * Time: 23:30
 * Guava Hash Bloom Filter demo.
 */
public class BloomFilterDemo {
    static class Person {
        Person(int id, String firstName, String lastName, int birthYear) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthYear = birthYear;
        }

        int id;
        String firstName;
        String lastName;
        int birthYear;
    }

    public static void main(String[] args) {
        Funnel<Person> personFunnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink primitiveSink) {
                primitiveSink
                        .putInt(person.id)
                        .putString(person.firstName, Charsets.UTF_8)
                        .putString(person.lastName, Charsets.UTF_8)
                        .putInt(person.birthYear);
            }
        };
        // init bloom filter, you can justify expectedInsertions to see that non_existed's result might be true
        // create(Funnel funnel, int expectedInsertions, double falsePositiveProbability)
        BloomFilter<Person> friends = BloomFilter.create(personFunnel, 100, 0.01);

        // init a pile of friends
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 100; ++i) {
            persons.add(new Person(i, "p" + i, "l" + i, i + 3));
        }

        // put to filter
        for (Person p : persons) {
            friends.put(p);
        }

        Person non_existed = new Person(1, "p1", "l3", 5);
        Person existed = new Person(1, "p1", "l1", 4);

        System.out.println(friends.mightContain(non_existed));
        System.out.println(friends.mightContain(existed));
    }
}
