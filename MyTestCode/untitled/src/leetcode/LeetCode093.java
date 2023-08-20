package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Restore IP Address
 * valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * •	For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
 * but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s.
 *
 * TODO: understand it and add my own solution
 */
public class LeetCode093 {

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;
        if (s.length() <= 0 || s.length() > 12) return res;

        restoreHelper(res, s, "", 0);
        return res;
    }

    private static void restoreHelper(List<String> res, String s, String current, int field) {
        // field: 0,1,2,3,4 means all done
        if (field == 4 && s.length() == 0)
            res.add(current.substring(1)); // remove prefixed "."
        else if (field == 4 || s.length() == 0) return; // not a valid conbination
        else {
            restoreHelper(res, s.substring(1), current + "." + s.substring(0, 1),
                    field + 1);
            if (s.charAt(0) != 0 && s.length() > 1) {
                restoreHelper(res, s.substring(2), current + "." + s.substring(0, 2),
                        field + 1);
                if (s.length() > 2 && Integer.valueOf(s.substring(0, 3)) <= 255)
                    restoreHelper(res, s.substring(3),
                            current + "." + s.substring(0, 3), field + 1);

            }
        }
    }

    @Test
    public void test1() {
        List<String> res = LeetCode093.restoreIpAddresses("012201");
        System.out.println(res);
    }
}
