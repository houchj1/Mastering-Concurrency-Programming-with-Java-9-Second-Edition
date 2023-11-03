package codility;

public class CountFactors {

    public static int countFactors(int N) {
        int i =1;
        int ret = 0;
        while (i * i < N) {
            if (N % i == 0) ret += 2;
            i++;
        }
        if (i * i == N) ret++;
        return ret;
    }

    public static void main(String[] args) {

        System.out.println(countFactors(24));
        System.out.println(countFactors(27));
        System.out.println(countFactors(29));
    }
}
