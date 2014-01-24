package guava.caches;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-24
 * Time: 22:06
 * A simple person for cache demo.
 */
public class Person {
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

    private String name;
    private int age;
}
