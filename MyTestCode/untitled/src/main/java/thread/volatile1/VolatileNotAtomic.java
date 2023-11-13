package thread.volatile1;

import common.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JUC page 232 VolatileDemo.java
 * demos that volatile variable is not atomic
 * TODO: NOTE: also shows how lambda accesses local variable, class property variable
 *
 */
public class VolatileNotAtomic {

    private volatile long value;
    private volatile static long valueUsedInStaticMethod;

    void testVolatileLong() {
        final int TASK_AMOUNT = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final int TURNS = 10000;
        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();

        // following will report error, as volatile is not allowed to be defined as local variable
        // volatile long value2;
        long value2 = 0;

        for (int i = 0; i < TASK_AMOUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        /**
                         * 也就是说Lambda可以使用当前类的成员变量，没有任何特殊限制， 遵循非lambda的访问规则。
                         * 但是本地（本方法内部定义的）变量就会提示：
                         *      Variable used in lambda expression should be final or effectively final
                         */
                        value++;
                        valueUsedInStaticMethod++;
                        // error: Variable used in lambda expression should be final or effectively final
                        // value2++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float time = System.currentTimeMillis() - start;
        Print.tco("Run time cost:" + time);
        Print.tco("Amount added result is: " + value);
        Print.tco("less than expected value: " + (TURNS * TASK_AMOUNT - value));

        executorService.shutdown();
    }

    static void testVolatileLongStatic() {
        final int TASK_AMOUNT = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final int TURNS = 10000;
        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);
        long start = System.currentTimeMillis();

        // following will report error, as volatile is not allowed to be defined as local variable
        // volatile long value2;
        long value2 = 0;

        for (int i = 0; i < TASK_AMOUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < TURNS; j++) {
                        // static method can only static class property variables
                        valueUsedInStaticMethod++;
                        // error: Variable used in lambda expression should be final or effectively final
                        // value2++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float time = System.currentTimeMillis() - start;
        Print.tco("Run time cost:" + time);
        Print.tco("Amount added result is: " + valueUsedInStaticMethod);
        Print.tco("less than expected value: " + (TURNS * TASK_AMOUNT - valueUsedInStaticMethod));

        executorService.shutdown();
    }
    public static void main(String[] args) {
        testVolatileLongStatic();
    }
}
