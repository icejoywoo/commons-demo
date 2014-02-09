package java17.newfeatures;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-9
 * Time: 16:46
 * jdk1.7 new feature: Generic Type Derive.
 */
public class GenericDerive {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>(); // must be "new HashMap<String, Integer>()" before jdk 1.7
        scores.put("John", 100);
        scores.put("Sam", 78);
        System.out.println(scores);
    }
}
