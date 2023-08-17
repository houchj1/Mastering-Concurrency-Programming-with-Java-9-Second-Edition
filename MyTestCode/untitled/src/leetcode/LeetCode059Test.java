package leetcode;

import leetcode.common.MyUtility;
import org.junit.Test;

/**
 * Spiral Matrix II
 * Given a positive integer n, generate an n x n matrix
 * filled with elements from 1 to n2 in spiral order.
 */
public class LeetCode059Test {

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, left = 0;
        int right = n - 1, bottom = n -1;
        int seq = 1;
        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) matrix[top][i] = seq++;
            for (int i = top; i < bottom; i++) matrix[i][right] = seq++;
            for (int i = right; i > left; i--) matrix[bottom][i] = seq++;
            for (int i = bottom; i > top; i--) matrix[i][left] = seq++;

            left++;
            top++;
            right--;
            bottom--;
        }
        if (left == right) {
            for(int i = top; i <= bottom; i++) matrix[i][left] = seq++;
        } else if (bottom == top) {
            for (int i = left; i < right; i++) matrix[top][i] = seq++;
        }
        return matrix;
    }

    @Test
    public void test1() {
        int[][] matrix = LeetCode059Test.generateMatrix(3);
        MyUtility.printNums(matrix);

        matrix = LeetCode059Test.generateMatrix(4);
        MyUtility.printNums(matrix);
    }

}
