package thread.cas;


import common.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * test the basic usage of AtomicInteger
 */
public class AtomicTest {

    public static void basicAtomicTest() {
        final AtomicInteger count = new AtomicInteger(0);
        final CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    count.getAndIncrement();
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

        Print.tco("the sum is " + count.get());
    }

    /**
     * testing 2 threads update the AtomicStampedReference with the same stamp, one should fail
     */
    public static void testAtomicStampReference() {
        CountDownLatch latch = new CountDownLatch(2);
        AtomicStampedReference<Integer> atomicStampedRef =
                new AtomicStampedReference<>(1, 0);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            boolean success = false;
            int stamp = atomicStampedRef.getStamp();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            success = atomicStampedRef.compareAndSet(1, 10, stamp, stamp + 1);

            Print.tco("after sleep 500, cas 1: success=" + success +
                    " value=" + atomicStampedRef.getReference() +
                    " stamp=" + atomicStampedRef.getStamp());
            latch.countDown();
        });

        executorService.submit(() -> {
            boolean success = false;
            int stamp = atomicStampedRef.getStamp();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            success = atomicStampedRef.compareAndSet(1, 20, stamp, stamp + 1);

            Print.tco("after sleep 1000, cas 1: success=" + success +
                    " value=" + atomicStampedRef.getReference() +
                    " stamp=" + atomicStampedRef.getStamp());
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

    }


    public static void main(String[] args) {

        basicAtomicTest();

        testAtomicStampReference();

    }


}
