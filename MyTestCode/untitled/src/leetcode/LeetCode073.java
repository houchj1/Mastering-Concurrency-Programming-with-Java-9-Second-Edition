package leetcode;

import leetcode.common.MyUtility;
import org.junit.Test;

/**
 * Set Matrix Zeroes
 * given a m x n matrix, if an element is 0,
 * set its entire row and column to 0. Do it in place.
 * TODO: there is bug, see the case, fix it
 */
public class LeetCode073 {

    public static void setZeros(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        boolean firstRowZero = false;
        boolean firstColZero = false;
        //initial check first row and col, otherwise will be taint
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // go over the matrix, set first col/row to indicate there is a 0
        for (int x = 1; x < matrix[0].length; x++) {
            for (int y = 1; y < matrix.length; y++) {
                if (matrix[y][x] == 0) {
                    matrix[y][0] = 0;
                    matrix[0][x] = 0;
                }
            }
        }

        //set the whole column to 0 if the first row meets 0
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        //set the whole row to 0 if the first col meet 0
        for (int j = 1; j < matrix.length; j++) {
            if (matrix[j][0] == 0) {
                for (int i = 1; i < matrix[0].length; i++) {
                    matrix[j][i] = 0;
                }
            }
        }

        // check original first row/col status
        if (firstRowZero) for (int j = 0; j < matrix[0].length; j++) matrix[0][j] = 0;
        if (firstColZero) for (int i = 0; i > matrix.length; i++) matrix[i][0] = 0;
    }

    @Test
    public void test1() {
//        int[][] matrix = new int[][] {
//                {1,2,3},
//                {4,0,6},
//                {7,8,9}
//        };
//        LeetCode072.setZeros(matrix);
//        MyUtility.printNums(matrix);
//
//        matrix = new int[][] {
//                {1,0,3},
//                {4,5,6},
//                {7,8,9}
//        };
//        LeetCode072.setZeros(matrix);
//        MyUtility.printNums(matrix);

        int[][] matrix2 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {0, 8, 9}
        };
        LeetCode073.setZeros(matrix2);
        MyUtility.printNums(matrix2);
    }

}
