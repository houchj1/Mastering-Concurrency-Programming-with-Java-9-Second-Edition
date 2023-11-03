package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Pow(x, n)
 */
public class LeetCode050Test {

    public static double myPow(double num, int power) {
        if (power == 0 || num == 1) return 1;
        if (power == 1) return num;

        //avoid minus MIN_VALUE, as -MIN_VALUE will overflow
        if (power < 0) return 1 / (num * myPow(num, -(power + 1)));

        double res = 1;
        while (power > 1) {
            if (power % 2 == 1) {
                res *= num;
            }
            num = num * num;
            power /= 2;
        }
        res *= num;
        return res;
    }

    @Test
    public void test1() {
        double ret = LeetCode050Test.myPow(1.2, 2);
        Assert.assertEquals(1.44, ret, 0);

        ret = LeetCode050Test.myPow(1.2, 3);
        Assert.assertEquals(1.728, ret, 0);

    }

}
