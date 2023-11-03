package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Letter combination of A phone number buttons
 * DFS/BFS can all solve this
 */
public class LeetCode017 {

    public static List<String> letterCombination(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});

        helper("", 0, digits, res, map);

        return res;
    }

    static void helper(String curr, int currIdx, String digits, List<String> res, Map<Character, char[]> map) {
        if (currIdx == digits.length()) {
            res.add(curr);
        } else {
            char c = digits.charAt(currIdx);
            if (map.containsKey(c)) {

                for (char ch: map.get(c)) {
                    helper(curr +ch, currIdx + 1, digits, res, map);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> res = letterCombination("23");
        res.stream().forEach(System.out::println);
    }


}
