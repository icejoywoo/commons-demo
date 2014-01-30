package guava.functional;

import com.google.common.base.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-30
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate p = new Predicate() {

            @Override
            public boolean apply(java.lang.Object input) {
                return false;
            }
        };
    }
}
