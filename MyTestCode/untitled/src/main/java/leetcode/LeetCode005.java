package leetcode;


/**
 * DP solution. TODO: do it latter.
 *
 * Longest palindrome.
 * "abcbd" , the longest palindrome is "bcb"
 */
public class LeetCode005 {

    public static String longestPalindrome(String s) {

        if (s == null || s.length() == 0) return s;
        int len = s.length();

        char[] sChar = s.toCharArray();
        int start = 0;
        int length = 1;
        boolean[][] isPalindrome = new boolean[len][len];
        //init
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len - 1; i++) {
            if (sChar[i] == sChar[i + 1]) {
                isPalindrome[i][i + 1] = true;
                start = i;
                length = 2;
            }
        }
        for (int i = 3; i <= len; i++) {
            for (int j = 0; j + i + 1 < len; j++) {
                if (sChar[j] == sChar[j+i-1] && isPalindrome[j+1][j+i-2] == true) {
                    isPalindrome[j][j+i-1] = true;
                    start = j;
                    length = i;
                }


            }
        }
        return s.substring(start, start + length);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }
}
