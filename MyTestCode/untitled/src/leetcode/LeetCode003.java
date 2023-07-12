package leetcode;

/**
 * longest sub string without repeating chars
 */
public class LeetCode003 {

    public static int lengthOfLongestSubString(String s) {
        if (s == null || s.length() == 0) return 0;

        int left = 0, right = 0;

        int n = s.length();

        boolean[] used = new boolean[128];

        int max = 0;
        while (right < n) {
            if (used[s.charAt(right)] == false) {
                used[s.charAt(right)] = true;
                right++;
            } else {
                max = Math.max(max, right - left);
                while (left < right && s.charAt(right) != s.charAt(left)) {
                    used[s.charAt(left)] = false;
                    left++;
                }
                left++;
                right++;

            }
        }
        max = Math.max(max, right - left);
        return max;
    }

    public static void main(String[] args) {

        int len = lengthOfLongestSubString("abcdedfg");
        System.out.println(len);


        len = lengthOfLongestSubString("abcdedfghi");
        System.out.println(len);


    }


}
