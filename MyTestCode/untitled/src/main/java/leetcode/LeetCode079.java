package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Word Search
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 */
public class LeetCode079 {

    public static boolean exist(char[][] board, String word) {
        if (board == null) return false;

        boolean[][] used = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (existHelper(board, used, word.toCharArray(), 0, 0, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean existHelper(char[][] board, boolean[][] used, char[] word,
                                      int idx, int col, int row) {
        //found the word
        if (idx == word.length) return true;
        // DFS reaches the edges, return false
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;

        if (used[row][col] == true || board[row][col] != word[idx])
            return false;

        used[row][col] = true;
        //right side
        boolean exist = existHelper(board, used, word, idx + 1, col + 1, row);
        if (exist) return true;
        //left
        exist = existHelper(board, used, word, idx + 1, col - 1, row);
        if (exist) return true;
        //up
        exist = existHelper(board, used, word, idx + 1, col, row - 1);
        if (exist) return true;
        //down
        exist = existHelper(board, used, word, idx + 1, col, row + 1);
        if (exist) return true;

        used[row][col] = false;
        return false;
    }

    @Test
    public void test1() {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean exist = LeetCode079.exist(board, "ABCCED");
        Assert.assertEquals(true, exist);

        exist = LeetCode079.exist(board, "ABCCEE");
        Assert.assertEquals(true, exist);

        exist = LeetCode079.exist(board, "ABCCEF");
        Assert.assertEquals(false, exist);
    }
}
