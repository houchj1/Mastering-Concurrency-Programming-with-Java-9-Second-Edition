import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    public int solution(int L, int[] stones, int[] distances) {
        // write your code in Java 11
//        int distStart = distances[0];
//        int disEnd = distances[1];
//        int step = 0;
//        int stoneCount = 0;
//        int index = 0;
        Arrays.sort(stones);
        return helper(L, 0, 0, 0, distances, stones);
    }
    private int helper(int L, int index, int step,
                           int minStones, int[] distances, int[] stones) {
        if (index >= L) return minStones;
        else {
            int min = minStones;
            for (int i = distances[0]; i <= distances[1]; i++) {
                int stone = helper(L, index + i, step, minStones, distances, stones);
                min = Math.min(stone, min);
            }
            return min;
        }
    }


    public static void main(String[] args) {

    }
}
