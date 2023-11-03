package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Sqrt(x)
 * 	Given a non-negative integer x, compute and return the square root of x.
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 * Note: You are not allowed to use any built-in exponent function or operator
 *
 */
public class LeetCode069 {

    public static int mySqrt(int n) {
        if (n <=0) return 0;
        if (n == 1) return 1;
        int num = n;
        num = num / 2;
        while (num > n / num) {
            num = num / 2;
        }
        while (num + 1 <= n / (num + 1)) {
            num++;
        }
        return num;
    }

    @Test
    public void test1() {
        int ret = LeetCode069.mySqrt(4);
        Assert.assertEquals(ret, 2);

        ret = LeetCode069.mySqrt(5);
        Assert.assertEquals(ret, 2);

        ret = LeetCode069.mySqrt(8);
        Assert.assertEquals(ret, 2);

        int ret2 = LeetCode069.mySqrt(9);
        Assert.assertEquals(ret2, 3);
    }


}
