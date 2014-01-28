package guava.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by wujiabin on 14-1-28.
 */
public class BiMapDemo {
    public static void main(String[] args) {
        BiMap<String, Integer> userId = HashBiMap.create();
        for (int i = 0; i < 10; ++i) {
            userId.put("key" + i, i);
        }
        System.out.println(userId);

        try {
            // throw an IllegalArgumentException if you attempt to map a key to an already-present value.
            userId.put("key1", 2);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        userId.forcePut("key1", 2); // replace key2=2 & key1=1 to key1=2
        System.out.println(userId);
    }
}
