package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Search a 2D matrix (2 个二分法) (****** MS interviewed)
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * •	Integers in each row are sorted from left to right.
 * •	The first integer of each row is greater than the last integer of the previous row
 */
public class LeetCode074 {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int startRow = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;

        int row = -1;
        while (startRow + 1 < endRow) {
            int mid = startRow + (endRow - startRow) / 2;
            // search in the last col first, as it's the largest for each row
            if (matrix[mid][endCol] < target) startRow = mid;
            else endRow = mid;
        }
        // which row's last col is larger than target, then this row got the target
        if (matrix[startRow][endCol] >= target) row = startRow;
        else if (matrix[endRow][endCol] >= target) row = endRow;
        else return false;// otherwise no target

        // search again in the target row, typical divide
        int start = 0;
        int end = endCol;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] < target) start = mid;
            else end = mid;
        }
        if (matrix[row][start] == target || matrix[row][end] == target) return true;
        else return false;
    }

    @Test
    public void test1() {
        int[][] matrix = new int[][] {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15}
        };
        boolean ret = LeetCode074.searchMatrix(matrix, 7);
        Assert.assertEquals(true, ret);

        ret = LeetCode074.searchMatrix(matrix, 16);
        Assert.assertEquals(false, ret);

        ret = LeetCode074.searchMatrix(matrix, 5);
        Assert.assertEquals(true, ret);

        ret = LeetCode074.searchMatrix(matrix, 6);
        Assert.assertEquals(true, ret);
    }


}
