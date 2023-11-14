package thread.lock;

import common.Print;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC page 264
 */
public class InterruptLock {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Runnable r = () -> IncrementData.lockInterruptibilityAndIncrease(lock);

        Thread t1 = new Thread(r, "thread-1");
        Thread t2 = new Thread(r, "thread-2");

        t1.start();
        t2.start();

        Thread.sleep(100);
        Print.tco("等待100毫秒，中断两个线程");
        t1.interrupt();
        t2.interrupt();

        Thread.sleep(2000);
    }


}
