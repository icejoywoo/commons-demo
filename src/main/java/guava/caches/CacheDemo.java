package guava.caches;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-24
 * Time: 21:06
 * A guava caches demo in timed eviction.
 */
public class CacheDemo {

    public static void main(String[] args) {
        LoadingCache<String, Person> persons = CacheBuilder.newBuilder() // weakKeys, weakValues, softValues
                .maximumSize(100)
                .expireAfterAccess(1, TimeUnit.SECONDS) // expireAfterWrite
                .recordStats()
                .removalListener(new RemovalListener<String, Person>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Person> notification) {
                        // System.out.println(notification.getValue());
                    }
                })
                .build(new CacheLoader<String, Person>() { // atomic "get-if-absent-compute" semantics
                    @Override
                    public Person load(String key) throws Exception {
                        return new Person(key);
                    }
                });

        try {
            // if cached, return; otherwise create, cache and return
            System.out.println(persons.get("Amen", new Callable<Person>() {
                @Override
                public Person call() throws Exception {
                    return new Person("Amen");
                }
            }));
            System.out.println(persons.get("Amen"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Person p = persons.get("John");
            System.out.println(p);
            p.setAge(10);
            System.out.println(p);
            System.out.println(persons.get("John"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1000; ++i) {
            String name = "John" + i;
            persons.put(name, new Person(name));
        }

        try {
            // get multi-keys
            ImmutableMap<String, Person> map = persons.getAll(Arrays.asList("John", "John0"));
            for (Map.Entry<String, Person> e : map.entrySet()) {
                System.out.println(e.getKey() + "\t" + e.getValue());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // get all keys
        System.out.println("get all keys");
        ConcurrentMap<String, Person> map = persons.asMap();
        System.out.println("Size: " + map.size());
        for (Map.Entry<String, Person> e : map.entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }

        // stats
        CacheStats stats = persons.stats();
        System.out.println(stats);
        System.out.println(stats.hitCount());
        System.out.println(stats.hitRate());
        System.out.println(stats.averageLoadPenalty());


        persons.cleanUp();
        // explicit removal
        persons.invalidate("John");
        persons.invalidateAll();
    }
}
