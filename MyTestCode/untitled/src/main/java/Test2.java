import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test2 {


    public int solution(int L, int[] stones, int[] distances) {
        // write your code in Java 11
        Arrays.sort(stones);
        Set<Integer> stonesSet = new HashSet<>();
        Arrays.stream(stones).forEach(stone -> stonesSet.add(stone));
        return helper(L, 0, 0, 0, distances, stonesSet);
    }
    private int helper(int L, int index, int step,
                       int minStones, int[] distances, Set<Integer> stones) {
        if (index >= L) return minStones;
        else {
            int min = minStones;
            for (int i = distances[0]; i <= distances[1]; i++) {
                int currStone = minStones;
                if (stones.contains(index + i))
                    currStone++;
                int stone = helper(L, index + i, step, currStone, distances, stones);
                min = Math.min(stone, min);
            }
            return min;
        }
    }


}
