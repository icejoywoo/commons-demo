package guava.primitives;

import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-1-31
 * Time: 22:55
 * Guava Primitives Utilities Demo.
 */
public class PrimitivesUtilitiesDemo {
    public static void main(String[] args) {
        /**
         * Primitive Type	 Guava Utilities (all in com.google.common.primitives)
         byte	Bytes, SignedBytes, UnsignedBytes
         short	Shorts
         int	Ints, UnsignedInteger, UnsignedInts
         long	Longs, UnsignedLong, UnsignedLongs
         float	Floats
         double	Doubles
         char	Chars
         boolean	Booleans
         */
        System.out.println(Ints.compare(1, 4));

        try {
            System.out.println(Ints.checkedCast(123456779999999l));
        } catch (IllegalArgumentException e) {
            // e.printStackTrace();
        }
        System.out.println(Ints.saturatedCast(123456779999999l));

        // Byte conversion methods
        System.out.println(Arrays.toString(Ints.toByteArray(1234)));
        byte[] bytes = Ints.toByteArray(1234);
        System.out.println(Ints.fromByteArray(bytes));

        System.out.println(UnsignedInts.parseUnsignedInt("1234"));
        System.out.println(UnsignedInts.parseUnsignedInt("1234", 8));
    }
}
