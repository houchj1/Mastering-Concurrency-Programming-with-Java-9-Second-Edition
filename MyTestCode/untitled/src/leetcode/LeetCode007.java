package leetcode;

public class LeetCode007 {

    public static int reverseInteger(int x) {
        int rev = 0;

        while (x != 0) {
            int newrev = rev * 10 + x % 10;
            if ((newrev - x % 10)/10 != rev) return 0; //overflow

            rev = newrev;
            x /= 10;
        }

        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverseInteger(123));

        System.out.println(reverseInteger(1));

        System.out.println(reverseInteger(0));

        System.out.println(reverseInteger(-123));

        System.out.println(reverseInteger(-1));

    }

}
