package guava.math;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: icejoywoo
 * Date: 14-2-4
 * Time: 10:30
 * Guava Math demo.
 */
public class MathDemo {
    public static void main(String[] args) {
        int logFloor = LongMath.log2(20, RoundingMode.FLOOR);
        System.out.println(logFloor);

        try {
            int mustNotOverflow = IntMath.checkedMultiply(9999999, 9999999); // 超过会抛异常
            System.out.println(mustNotOverflow);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        long quotient = LongMath.divide(9l, 3, RoundingMode.UNNECESSARY); // fail fast on non-multiple of 3 不整除会抛异常
        System.out.println(quotient);

        BigInteger nearestInteger = DoubleMath.roundToBigInteger(100.03, RoundingMode.HALF_EVEN);
        System.out.println(nearestInteger);

        BigInteger sideLength = BigIntegerMath.sqrt(new BigInteger("1009", 10), RoundingMode.CEILING);
        System.out.println(sideLength);

        // gcd mod pow etc. LongMath & BigIntegerMath
        System.out.println(IntMath.gcd(21, 14));
        System.out.println(IntMath.mod(-5, 3));
        System.out.println(IntMath.pow(2, 10));
        System.out.println(IntMath.isPowerOfTwo(20));
        System.out.println(IntMath.factorial(2)); // 阶乘
        System.out.println(IntMath.binomial(20, 10)); // 二项式系数

        // DoubleMath
        System.out.println(DoubleMath.isMathematicalInteger(20.01));
        System.out.println(DoubleMath.roundToInt(20.3, RoundingMode.HALF_EVEN));
        System.out.println(DoubleMath.roundToLong(100.6, RoundingMode.CEILING));
        System.out.println(DoubleMath.roundToBigInteger(123.456, RoundingMode.CEILING));
        System.out.println(DoubleMath.log2(23.4));
    }
}
