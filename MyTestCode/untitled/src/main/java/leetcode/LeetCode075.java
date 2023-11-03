package leetcode;

import leetcode.common.MyUtility;
import org.junit.Test;

/**
 * Sort Colors
 * given an array with n objects colored as red, white, or blue.
 * sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white, blue.
 * Here we use 0, 1, 2 to represent the color red, white, blue.
 * another easier solution is that, go through the colors and count numbers for 0, 1, 2.
 * and the output it sequentially as 0, 0, ..(0’s count number), 1, ,1
 */
public class LeetCode075 {

    public static void sortColors(int[] colors) {
        if (colors == null || colors.length <= 1) return;
        int colFirst = 0;
        while (colFirst < colors.length && colors[colFirst] == 0) colFirst++;
        int colLast = colors.length - 1;
        while (colLast >= 0 && colors[colLast] == 2) colLast--;

        int index = colFirst;
        while (index <= colLast) {
            if (colors[index] == 1) index++;
            else if (colors[index] == 0) {
                switchColor(colors, colFirst, index);
                colFirst++;
                index++;
            } else if (colors[index] == 2) {
                switchColor(colors, colLast, index);
                colLast--;
            }
        }
    }

    public static void sortColors2(int[] colors) {
        if (colors == null || colors.length <= 1) return;
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < colors.length; i++) {
            switch (colors[i]) {
                case 0: count0++; break;
                case 1: count1++; break;
                case 2: count2++; break;
            }
        }
        int index = 0;
        while (count0 > 0) {
            colors[index++] = 0;
            count0--;
        }
        while (count1 > 0) {
            colors[index++] = 1;
            count1--;
        }
        while (count2 > 0) {
            colors[index++] = 2;
            count2--;
        }
    }

    public static void switchColor(int[] colors, int i, int j) {
        int tmp = colors[i];
        colors[i] = colors[j];
        colors[j] = tmp;
    }

    @Test
    public void test1() {
        int[] colors = new int[]{1, 1, 1, 0, 0, 2, 2, 2};
        LeetCode075.sortColors(colors);
        MyUtility.printNums1(colors);

        colors = new int[]{1, 1, 1, 0, 0, 2, 2, 2};
        LeetCode075.sortColors2(colors);
        MyUtility.printNums1(colors);

    }
}
