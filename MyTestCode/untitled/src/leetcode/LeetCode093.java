package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Restore IP Address
 * 	 valid IP address consists of exactly four integers separated by single dots.
 * 	 Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * •	For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
 *      but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s.
 */
public class LeetCode093 {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;
        if (s.length() <= 0 || s.length() > 12) return res;





    }

}
