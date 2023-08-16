package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * given 2 non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2. Note:
 * 1.	the length of both num1 and num2 is < 110.
 * 2.	both num1 and num2 contains only digits 0 – 9.
 * 3.	both num1 and num2 does not contain any leading zero
 * 4.	you must not use and build-in BigInteger library or convert the inputs to integer.
 */
public class LeetCode043Test {

    public static String multuply(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return "0";
        if (num2 == null || num2.length() == 0) return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int posLow = i + j + 1;
                int posHigh = i + j;
                mul += result[posLow];
                result[posLow] = mul % 10;
                result[posHigh] += mul / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            if (!(sb.length() == 0 && res == 0))
                sb.append(res);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }


    @Test
    public void test1() {
        String ret = LeetCode043Test.multuply("21", "2");
        Assert.assertEquals("42", ret);

        ret = LeetCode043Test.multuply("21", "11");
        Assert.assertEquals("231", ret);

        ret = LeetCode043Test.multuply("99", "99");
        Assert.assertEquals("9801", ret);
    }

}
