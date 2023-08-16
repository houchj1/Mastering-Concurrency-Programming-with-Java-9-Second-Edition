package leetcode;

import leetcode.common.MyUtility;
import org.junit.Test;

/**
 * Rotate image
 * you are given nxn 2D matrix representing an image.
 * Rotate the image by 90 degree (clockwise).
 * space complexity should be O(1)
 *
 */
public class LeetCode048Test {

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int top = 0, left = 0;
        int right = matrix.length - 1;
        int bottom = matrix.length - 1;

        int n = matrix.length;
        while (n > 1) {
            for (int i =0; i < n - 1; i++) {
                int tmp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bottom - i][left];
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = tmp;
            }
            top++;
            left++;
            right--;
            bottom--;
            n = n - 2;
        }

    }

    @Test
    public void test1() {
        int[][] nums = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        LeetCode048Test.rotate(nums);
        MyUtility.printNums(nums);

        nums = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12},{13,14,15,16}};
        LeetCode048Test.rotate(nums);
        MyUtility.printNums(nums);

        LeetCode048Test.rotate(nums);
        MyUtility.printNums(nums);
    }

}
