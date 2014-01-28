package guava.collections;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wujiabin on 14-1-28.
 */
public class ClassToInstanceMapDemo {
    public static void main(String[] args) {
        // ClassToInstanceMap<B> implements Map<Class<? extends B>, B>
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        // putInstance getInstance
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.putInstance(Double.class, Double.valueOf(0));
        System.out.println(numberDefaults);

        // iteration
        for (Map.Entry<Class<? extends Number>, Number> e: numberDefaults.entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }

        // a map init method
        System.out.println(new HashMap() {{
            put("a", 1);
            put("b", 2);
        }});

        ClassToInstanceMap<Map> mapDefaults = ImmutableClassToInstanceMap.<Map>builder()
                .put(HashMap.class, new HashMap() {{
                    put("a", 1);
                    put("b", 2);
                }})
                .put(LinkedHashMap.class, new LinkedHashMap(){{
                    put("a", 1);
                    put("b", 2);
                }}).build();
        System.out.println(mapDefaults);
    }
}
