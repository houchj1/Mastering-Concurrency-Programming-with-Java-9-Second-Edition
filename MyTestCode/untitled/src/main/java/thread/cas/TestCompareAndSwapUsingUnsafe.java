package thread.cas;

import common.JvmUtil;
import common.Print;
import common.ThreadUtil;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * JUC page 164. demo how to use Unsafe class to do the compare and swap. Not Using Atomicxxxx class.
 */
public class TestCompareAndSwapUsingUnsafe {

    private final static int THREAD_COUNT = 10;

    static class OptimisticLockingPlus {
        private volatile int value;
        private final static Unsafe unsafe = JvmUtil.getUnsafe();
        public final static AtomicLong failure = new AtomicLong(0);
        private static final long valueOffset;

        static {
            try {
                valueOffset = unsafe.objectFieldOffset(OptimisticLockingPlus.class.getDeclaredField("value"));
                Print.tco("valueOffset = " + valueOffset);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        public final boolean unSafeCompareAndSet(int oldValue, int newValue) {
            return unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue);
        }

        public void selfPlus() {
            int oldValue = this.value;
            int i = 0;
            while (!unSafeCompareAndSet(oldValue, oldValue + 1)) {
                oldValue = this.value;
                i++;
                if (i > 1) {
                    failure.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) {
        final OptimisticLockingPlus cas = new OptimisticLockingPlus();
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            ThreadUtil.getMixedTargetThreadPool().submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    cas.selfPlus();;
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Print.tco("the sum after selfPlus():" + cas.value);
        Print.tco("failure times: " + OptimisticLockingPlus.failure.get());
    }

}
