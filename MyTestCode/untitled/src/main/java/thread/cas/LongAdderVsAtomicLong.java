package thread.cas;

import common.Print;

import java.awt.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * JUC page 181.
 * compare the performance of LongAdder and AtomicLong in multi thread add scenario
 */
public class LongAdderVsAtomicLong {
    static final int TURNS = 100000000;
    final static int TASK_AMOUNT = 10;

    public static void testAtomicLong() {
        ExecutorService executorService = Executors.newFixedThreadPool(18);
        AtomicLong atomicLong = new AtomicLong(0);
        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < TASK_AMOUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        atomicLong.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float time = (System.currentTimeMillis() - start) / 1000f;
        Print.tco("AtomicLong运行时长：" + time);
        Print.tco("AtomicLong累加结果为：" + atomicLong.get());
    }

    public static void testLongAdder() {
        ExecutorService executorService = Executors.newFixedThreadPool(18);
        LongAdder longAdder = new LongAdder();
        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();

        for (int i = 0; i < TASK_AMOUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        longAdder.add(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float time = (System.currentTimeMillis() - start) / 1000f;
        Print.tco("LongAdder运行时长：" + time);
        Print.tco("LongAdder累加结果为：" + longAdder.longValue());
    }
    public static void main(String[] args) {
        testAtomicLong();

        testLongAdder();

    }
}
