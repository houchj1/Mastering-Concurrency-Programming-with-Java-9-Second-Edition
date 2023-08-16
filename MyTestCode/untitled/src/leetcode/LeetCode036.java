package leetcode;

/**
 * Valid Sudoku
 * Empty cells are filled with the character ‘.’.
 * From large view Each row/column cannot have same number.
 * From 3x3 view cannot have same number.
 */
public class LeetCode036 {

    static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return false;
        //check each row
        for (int row = 0; row < 9; row++) {
            boolean[] taken = new boolean[9];
            for (int i = 0; i < 9; i++) {
                char c = board[row][i];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num] == true) return false;
                    else taken[num] = true;
                }
            }
        }
        //check column wise
        for (int col = 0; col < 9; col++) {
            boolean[] taken = new boolean[9];
            for (int i = 0; i < 9; i++) {
                char c =board[i][col];
                if (c != '.') {
                    int num = c - '1';
                    if (taken[num] == true) return false;
                    else taken[num] = true;
                }
            }
        }
        for (int box = 0; box < 9; box++) {
            boolean[] taken = new boolean[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + 3 * (box / 3)][col + 3 * (box % 3)];
                    if (c != '.') {
                        int num = c - '1';
                        if (taken[num] == true) return false;
                        else taken[num] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}
        };
        System.out.println(isValidSudoku(board));

        board = new char[][]{
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}
        };
        System.out.println(isValidSudoku(board));
    }
}
