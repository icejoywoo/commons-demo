package guava.caches;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-24
 * Time: 22:05
 * A guava cache demo in size-based eviction.
 */
public class SizeBasedEvictionCacheDemo {
    public static void main(String[] args) {
        Cache<String, Person> persons = CacheBuilder.newBuilder().weakValues()
                .maximumWeight(100) // 设置最大权重
                .weigher(new Weigher<String, Person>() {
                    @Override
                    public int weigh(String key, Person value) {
                        return 1; // 计算权重
                    }
                })
                .build();
        try {
            Person p = persons.get("John", new Callable<Person>() {
                @Override
                public Person call() throws Exception {
                    return new Person("Amen");
                }
            });
            System.out.println(p);
            p.setAge(10);
            System.out.println(p);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
