package leetcode.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interval {

    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Interval() {}

    public static List<Interval> fromArray(int[][] intervals) {
        List<Interval> res = new ArrayList<>();
        Arrays.stream(intervals)
                .forEach( inter -> res.add(new Interval(inter[0], inter[1])));
        return res;
    }

    public static void printIntervals(List<Interval> intervals) {
        StringBuilder sb = new StringBuilder("[");
        intervals.stream()
                .forEach(inter -> sb.append("[" + inter.start+"," + inter.end+ "]"));
        sb.append("]");
        System.out.println(sb.toString());
    }
}
