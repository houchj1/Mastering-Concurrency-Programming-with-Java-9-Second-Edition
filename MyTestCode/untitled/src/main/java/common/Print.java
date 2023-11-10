package common;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Print {

    public static void tco(String msg) {
        System.out.printf("[%s]: %s\n", Thread.currentThread().getName(), msg);
    }

    public static void consoleInput() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Press Enter to continue...");
            input.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
