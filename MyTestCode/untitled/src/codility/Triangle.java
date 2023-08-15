package codility;

import java.util.Arrays;

public class Triangle {

    public static int solution(int[] A) {
        if (A== null || A.length < 3) return 0;
        Arrays.sort(A);

        for (int j = A.length - 1; j >= 2; j--) {
            int first = A[j];
            int nextTwo = A[j -1] + A[j -2];
            if (nextTwo < 0) {
                if (A[j-1] > first - A[j-2]) return 1;
            } else if (nextTwo > first) return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {3,2,1}));
        System.out.println(solution(new int[] {3,2,2}));
    }
}
