package guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 18:58
 * Guava Function Demo
 */
public class FunctionDemo {

    static class Test {
        public Test(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public String name;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 2);
        }};

        // Returns a function which performs a map lookup.
        Function f = Functions.forMap(map);
        System.out.println(f.apply("a") + "\t" + f.apply("b"));

        // identity, return object self
        Function id = Functions.identity();
        System.out.println(id.apply("abc"));
        System.out.println(id.apply(new Test("abcsss")));

        // constant
        Function constant = Functions.constant("ConstantName");
        System.out.println(constant.apply("abc"));

        // compose
    }
}
