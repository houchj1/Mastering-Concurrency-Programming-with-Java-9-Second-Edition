package codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Tesla
 *
 */
public class MinStone {

    /**
     *
     * @param L  the total length of the bridge
     * @param stones  the index when it has stones
     * @param distances distance range when you can jump
     * @return return minimum jump on the stones that you can pass the bridge
     */
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
