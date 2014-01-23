package guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.*;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-22
 * Time: 22:54
 * Guava Hash demo.
 */
public class HashDemo {
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
        Funnel<Person> funnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink primitiveSink) {
                primitiveSink
                        .putInt(person.id)
                        .putString(person.firstName, Charsets.UTF_8)
                        .putString(person.lastName, Charsets.UTF_8)
                        .putInt(person.birthYear);
            }
        };

        Person person = new Person(1, "John", "Smith", 1);
        HashFunction hf = Hashing.sha512(); // md5()	murmur3_128()	murmur3_32()	sha1()  sha256()	sha512()	goodFastHash(int bits)
        HashCode hc = hf.newHasher()
                .putLong(123)
                .putString("test", Charsets.UTF_8)
                .putObject(person, funnel)
                .hash();
        System.out.println(hc.asInt());
        System.out.println(hc.asLong());
        System.out.println(hc.asBytes());
    }
}
