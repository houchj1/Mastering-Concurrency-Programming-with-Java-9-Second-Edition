package leetcode;

/**
 * Both dividend and divisor will be 32-bit signed integers.
 * the divisor will never be 0
 * Assume we are dealing with an environment which could only store
 * integers within the 32bit signed integer range:[-2^31, 2^31 -1].
 * For the purpose of this problem, assume your function returns 2^31 -1
 * when the division result overflows.
 */
public class LeetCode029 {

    static int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) return Integer.MAX_VALUE;
            else if (divisor == 1) return Integer.MIN_VALUE;
        }

        long divid = (long) dividend;
        long divs = (long) divisor;
        int sign = 1;
        if (divid < 0) {
            divid = -divid;
            sign = -sign;
        }
        if (divs < 0) {
            divs = -divs;
            sign = -sign;
        }
        int res = 0;
        while (divid > divs) {
            int shift = 0;
            while (divid >= divs << shift) {
                shift++;
            }
            res += (1 << (shift - 1));
            divid -= divs << (shift - 1);
        }
        return sign * res;
    }

    public static void main(String[] args) {
        System.out.println(divide(2, 3));
        System.out.println(divide(3, 2));
        System.out.println(divide(25, 6));
    }
}
