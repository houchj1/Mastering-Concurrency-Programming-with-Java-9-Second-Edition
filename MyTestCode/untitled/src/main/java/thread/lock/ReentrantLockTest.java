package thread.lock;

import common.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC page 237
 */
public class ReentrantLockTest {

    final static int TURNS = 1000;
    final static int THREADS = 10;

    public static void testReentrantLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        Lock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(THREADS);
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREADS; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        IncrementData.lockAndFastIncrease(lock);
                    }
                    Print.tco("Current Thread Sum is completed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            latch.countDown();
        }

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        float time = (System.currentTimeMillis() - start) / 1000f;
        Print.tco("Total Cost time: " + time);
        Print.tco("The Sum result is: " + IncrementData.sum);

        executorService.shutdown();
    }

    public static void main(String[] args) {
        testReentrantLock();
    }
}
