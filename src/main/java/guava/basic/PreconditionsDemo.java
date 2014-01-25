package guava.basic;

import static com.google.common.base.Preconditions.*;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-25
 * Time: 16:03
 * Guava preconditions demo.
 */
public class PreconditionsDemo {

    public static void getFromContext(boolean flag, Object context) {
        checkArgument(flag, "flag must be true. [flag = %s]", flag);
        checkNotNull(context, "context cannot be null. [context = %s]", context);
    }

    public static void main(String[] args) {
        getFromContext(true, "abc");
        try{
            getFromContext(false, "abc");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try{
            getFromContext(true, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
