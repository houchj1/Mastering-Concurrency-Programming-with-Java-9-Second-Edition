package codility;

public class MinPerimeterRectangle {

    public static int solution(int N) {
        int i = 1;
        int maxFactor = 0;
        while (i * i < N) {
            if (N % i == 0) {
                maxFactor = i;
            }
            i++;
        }
        if (i * i == N) {
            return 4 * i;
        } else {
            return 2 * (maxFactor + N / maxFactor);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(36));
        System.out.println(solution(10));
    }
}
