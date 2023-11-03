package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * start from top left corner
 * Like Rotate image
 */
public class LeetCode054Test {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, left = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) res.add(matrix[top][i]);
            for (int i = top; i < bottom; i++) res.add(matrix[i][right]);
            for (int i = right; i > left; i--) res.add(matrix[bottom][i]);
            for (int i = bottom; i > top; i--) res.add(matrix[i][left]);
            left++;
            top++;
            right--;
            bottom--;
        }
        // make sure check left == right first, otherwise it will be error.
        if (left == right) {
            for (int i = top; i <= bottom; i++) res.add(matrix[i][left]);
        } else if (top == bottom) {
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
        }
        return res;
    }

    @Test
    public void test1() {
        int[][] matrix = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> res = LeetCode054Test.spiralOrder(matrix);
        System.out.println(res);

        matrix = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        res = LeetCode054Test.spiralOrder(matrix);
        System.out.println(res);
    }


}
