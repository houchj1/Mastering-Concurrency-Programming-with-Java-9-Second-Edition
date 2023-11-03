package leetcode.common;

import java.util.Arrays;

public class MyUtility {

    public static void printNums(int[][] nums) {
        for (int[] numrow : nums) {
            System.out.print("[");
            StringBuilder sb = new StringBuilder();
            Arrays.stream(numrow).forEach( num -> sb.append("" + num + ","));
            sb.delete(sb.length() - 1, sb.length());
            System.out.print(sb.toString());
            System.out.println("]");
        }
    }

    public static void printNums1(int[] nums) {
        System.out.print("[");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(nums).forEach( num -> sb.append("" + num + ","));
        sb.delete(sb.length() - 1, sb.length());
        System.out.print(sb.toString());
        System.out.println("]");
    }
}
