package leetcode;

import leetcode.common.Interval;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Intervals
 * given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary). You may assume that the intervals were
 * initially sorted according to their start times
 * given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9]
 * 	some corner cases,
 * 1.	the new interval overlaps with old internal
 * 2.	new interval in the middle, but no overlaps with the others
 * 3.	new interval in the beginning, no overlaps with others
 */
public class LeetCode057Test {

    public static List<Interval> insert(List<Interval> intList, Interval newInt) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        int n = intList.size();
        int nStart = newInt.start;
        int nEnd = newInt.end;

        while (i < n && intList.get(i).end < newInt.start) {
            res.add(intList.get(i));
            i++;
        }
        if (i == n) { // until the last there is no overlap
            res.add(newInt);
            return res;
        }

        nStart = Math.min(intList.get(i).start, newInt.start);
        while (i < n && intList.get(i).start <= newInt.end) {
            nEnd = Math.max(newInt.end, intList.get(i).end);
            i++;
        }
        res.add(new Interval(nStart, nEnd));
        while (i < n) {
            res.add(intList.get(i));
            i++;
        }
        return res;
    }

    @Test
    public void test1() {
        List<Interval> intervals = Interval.fromArray(new int[][]{{1,3}, {6,9}});
        List<Interval> res = LeetCode057Test.insert(intervals, new Interval(2, 5));
        Interval.printIntervals(res);

    }

}
