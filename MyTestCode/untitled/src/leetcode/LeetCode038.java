package leetcode;

/**
 * count and say
 * The count and say sequence is the sequence of integers
 * beginning as follows: 1, 11, 21, 1211,
 */
public class LeetCode038 {

    public static String countAndSay(int n) {
        if (n <= 0) return "";
        String str = "1";
        for (int i = 1; i <= n; i++) {
            int count = 0;
            char pre = '.';
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == pre || pre == '.') {
                    count++;
                } else {
                    sb.append(count+Character.toString(pre));
                    count = 1;
                }
                pre = str.charAt(idx);
            }
            sb.append(count + Character.toString(pre));
            str = sb.toString();
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
    }
}
