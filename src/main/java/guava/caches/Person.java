package guava.caches;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-24
 * Time: 22:06
 * A simple person for cache demo.
 */
public class Person implements Comparable<Person> {
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.age);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.name, that.name)
                .compare(this.age, that.age)
                .result();
    }

    private String name;
    private int age;

    public static void main(String[] args) {
        Person p1 = new Person("John");
        Person p2 = new Person("John");
        assert(p1.equals(p2));
        p2.setAge(5);
        assert(!p1.equals(p2));
    }
}
