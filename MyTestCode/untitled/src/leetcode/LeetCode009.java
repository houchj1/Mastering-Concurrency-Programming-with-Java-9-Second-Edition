package leetcode;

public class LeetCode009 {

    public static boolean isPalindrome(int x) {

        if (x < 0) return false;

        int div = 1, num = x;
        while (num / div >= 10) {
            div *= 10;
        }

        while (num != 0) {
            int left = num / div;
            int right = num % 10;

            if (left != right) return false;

            num = (num - left * div) / 10;
            div /= 100;

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));

        System.out.println(isPalindrome(123));

        System.out.println(isPalindrome(1));

        System.out.println(isPalindrome(-1));

        System.out.println(isPalindrome(-121));
    }

}
