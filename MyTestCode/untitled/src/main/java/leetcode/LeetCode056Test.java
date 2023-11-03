package leetcode;

import leetcode.common.Interval;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merge Intervals
 * given a collection of intervals, merge all overlapping intervals.
 * For example [1, 3],[2, 6],[8, 10],[15,18]  -> [1,6],[8, 10],[15,18]
 */
public class LeetCode056Test {

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return res;

        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int i = 0;
        while (i < intervals.size()) {
            int st = start[i];
            while (i < intervals.size() - 1 && start[i+1] <= end[i])
                i++;
            int en = end[i];
            Interval in = new Interval(st, en);
            res.add(in);
            i++;
        }
        return res;
    }

    @Test
    public void test1() {
        List<Interval> intervals = Interval.fromArray(
                new int[][] {
                        {1,3},{2,6},{8,10},{15,18}
                });
        List<Interval> res = LeetCode056Test.merge(intervals);

        Interval.printIntervals(res);


    }
}
