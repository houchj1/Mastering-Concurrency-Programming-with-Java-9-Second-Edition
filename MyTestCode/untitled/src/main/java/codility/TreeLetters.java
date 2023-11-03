package codility;

public class TreeLetters {

    public static String solution(int A, int B) {
        if (A == 0 && B==0) return "";
        if (A==0) {
            return produceString('b', B);
        }
        if (B==0) {
            return produceString('a', A);
        }
        int a = A, b = A;
        StringBuilder sb = new StringBuilder();
        while (a != 0 && b != 0) {
            if (A >= B) sb.append("ab");
            else sb.append("ba");
            a--;
            b--;
        }
        if (A > B) {
            sb.append('a');
            a--;
        }
        if (A < B) {
            sb.append('b');
            b--;
        }
        if (b ==0 && a != 0) {
            insertLeftChar(sb, 'a', a);
        } else if (a == 0 && b != 0) {
            insertLeftChar(sb, 'b', b);
        }
        return sb.toString();
    }

    public static void insertLeftChar(StringBuilder sb, char aOrB, int count) {
        String s = sb.toString();
        int i = 0;
        while (count > 0) {
            if (i == s.length() || s.charAt(i) == aOrB) {
                sb.insert(i, aOrB);
                count--;
                i += 3;
                if (count == 0) break;
            }
            i++;
            s = sb.toString();
            if (i > s.length()) i = 0;
        }
    }
    private static String produceString(char aOrB, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(aOrB);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(solution(5, 3));  // not correct
        System.out.println(solution(3, 3));
        System.out.println(solution(1, 4));  // not correct
    }

}
