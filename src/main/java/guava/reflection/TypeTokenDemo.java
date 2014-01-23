package guava.reflection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-23
 * Time: 22:02
 * Get a generic type in runtime using TypeToken.
 */
public class TypeTokenDemo {
    static class Util {
        static <K, V> TypeToken<Map<K, V>> incorrectMapToken() {
            return new TypeToken<Map<K, V>>() {
            };
        }

        static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
            return new TypeToken<Map<K, V>>() {
            }
                    .where(new TypeParameter<K>() {
                    }, keyToken)
                    .where(new TypeParameter<V>() {
                    }, valueToken);
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // jvm 没有泛型, 编译完之后泛型被擦除
        // Due to type erasure, you can't pass around generic Class objects at runtime
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
        // returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>

        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);

        System.out.println(stringTok.getType() + " " + stringTok.getRawType());

        System.out.println(Util.<String, BigInteger>incorrectMapToken());
        // just prints out "java.util.Map<K, V>"
        System.out.println(Util.mapToken(stringTok, intTok));

        abstract class IKnowMyType<T> {
            TypeToken<T> type = new TypeToken<T>(getClass()) {
            };
        }
        System.out.println(new IKnowMyType<String>() {}.type); // returns a correct TypeToken<String>

        TypeToken<Function<Integer, String>> funToken = new TypeToken<Function<Integer, String>>() {};
        System.out.println(funToken);
        TypeToken<?> funResultToken = funToken.resolveType(Function.class.getTypeParameters()[1]);
        // returns a TypeToken<String>
        System.out.println(funResultToken);

        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {};
        TypeToken<?> entrySetToken = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
        // returns a TypeToken<Set<Map.Entry<String, Integer>>>
        System.out.println(entrySetToken);

        Invokable<List<String>, ?> invokable = new TypeToken<List<String>>() {}.method(List.class.getMethod("get", int.class));
        System.out.println(invokable.getReturnType()); // String.class
    }
}
