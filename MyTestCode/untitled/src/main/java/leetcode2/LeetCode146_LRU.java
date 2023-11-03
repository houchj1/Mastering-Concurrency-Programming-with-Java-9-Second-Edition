package leetcode2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LRU Cache
 *
 */
public class LeetCode146_LRU {

    private int capacity;
    private LinkedList<Integer> list = new LinkedList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    private LeetCode146_LRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            list.remove(Integer.valueOf(key));
            list.offer(key);
            return map.get(key);
        } else {
            return -1;
        }
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(Integer.valueOf(key));
        } else {
            if (capacity <= map.size()) {
                Integer lastKey = list.removeLast();
                map.remove(lastKey);
            }
        }
        list.offer(key);
        map.put(key, value);
    }

}
