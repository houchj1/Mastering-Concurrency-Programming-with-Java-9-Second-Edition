package common;

public class Print {

    public static void tco(String msg) {
        System.out.printf("[%s]: %s\n", Thread.currentThread().getName(), msg);
    }
}
