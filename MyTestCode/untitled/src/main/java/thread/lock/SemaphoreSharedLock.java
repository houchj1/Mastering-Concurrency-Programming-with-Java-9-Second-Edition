package thread.lock;

import common.DateUtil;
import common.Print;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC page 270, shows how to use Semaphore as a shared lock
 *  only 2 threads are getting the permits at the same time
 *
 * [线程0]: 15:52:31, 受理中......，服务号:1
 * [线程4]: 15:52:31, 受理中......，服务号:2
 * [线程8]: 15:52:32, 受理中......，服务号:3
 * [线程7]: 15:52:32, 受理中......，服务号:4
 * [线程3]: 15:52:33, 受理中......，服务号:5
 * [线程5]: 15:52:33, 受理中......，服务号:6
 * [线程9]: 15:52:34, 受理中......，服务号:7
 * [线程1]: 15:52:34, 受理中......，服务号:8
 * [线程2]: 15:52:35, 受理中......，服务号:9
 * [线程6]: 15:52:35, 受理中......，服务号:10
 *
 */
public class SemaphoreSharedLock {

    public static void main(String[] args) throws InterruptedException {

        final int USER_TOTAL = 10;
        final int PERMIT_TOTAL = 2;
        final CountDownLatch latch = new CountDownLatch(USER_TOTAL);

        final Semaphore semaphore = new Semaphore(PERMIT_TOTAL);

        AtomicInteger index = new AtomicInteger(0);

        Runnable r = () -> {

            try {
                semaphore.acquire();
                Print.tco(DateUtil.getNowTime() +  ", 受理中......，服务号:" + index.incrementAndGet());

                Thread.sleep(1000);

                semaphore.release(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
        };

        Thread[] threads = new Thread[USER_TOTAL];
        for (int i = 0; i < USER_TOTAL; i++) {
            threads[i] = new Thread(r, "线程" + i);
        }
        for (int i = 0; i < USER_TOTAL; i++) {
            threads[i].start();
        }

        latch.await();

    }


}
