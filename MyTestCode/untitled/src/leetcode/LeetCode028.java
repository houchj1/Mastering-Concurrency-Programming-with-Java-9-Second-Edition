package leetcode;

/**
 * implement strStr
 * don’t need to write the KMP. Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * if needle is empty string, just return 0. This is consistent of java’s indexOf().
 * the time complexity is O(m * n)
 */
public class LeetCode028 {

    static int strStr(String haystack, String needle) {

        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() == 0) return -1;

        int m = needle.length();
        int n = haystack.length();

        for (int i = 0; i < n - m + 1; i++) {
            for (int j = 0; j < m; j++) {

                if (haystack.charAt(i + j) != needle.charAt(j))
                    break;
                if (j == m - 1) return i;

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("abcddesdfl", "dde"));
        System.out.println(strStr("abcddesdfl", ""));
        System.out.println(strStr("", "dde"));

        System.out.println(strStr("abcdefghijklmn", "mn"));
    }

}
