package thread.synchronizedbasic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedBasic {

    static Integer count = 0;

    public synchronized static void staticTest1() {
        count++;
    }

    public static void staticTest12() {
        synchronized (SynchronizedBasic.class) {
            count++;
        }
    }

    public synchronized void staticTest2() {
        count++;
    }

    public void staticTest22() {
        synchronized (this) {
            count++;
        }
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            pool.submit( () -> {
               SynchronizedBasic.staticTest1();
            });
        }

        for (int i = 0; i < 100; i++) {
            pool.submit( () -> {
                SynchronizedBasic.staticTest12();
            });
        }
        // next 2 examples synchronized on 1 instance
        SynchronizedBasic s = new SynchronizedBasic();
        for (int i = 0; i < 100; i++) {
            pool.submit( () -> {
                s.staticTest2();
            });
        }

        for (int i = 0; i < 100; i++) {
            pool.submit( () -> {
                s.staticTest22();
            });
        }

        pool.shutdown();
        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("the self-plus result is: " + SynchronizedBasic.count);

    }

}
