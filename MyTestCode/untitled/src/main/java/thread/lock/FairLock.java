package thread.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC page 263, demos the scenario of fair lock
 */
public class FairLock {

    /**
     * [Thread-1]: -- 开始抢占锁
     * [Thread-0]: -- 开始抢占锁
     * [Thread-1]: ^^ 抢到了锁
     * [Thread-2]: -- 开始抢占锁
     * [Thread-2]: ^^ 抢到了锁
     * [Thread-3]: -- 开始抢占锁
     * [Thread-0]: ^^ 抢到了锁
     * [Thread-3]: ^^ 抢到了锁
     * @throws InterruptedException
     */
    static void testUnFairLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Runnable r = () -> {IncrementData.lockAndFastIncrease(lock);};

        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(r, "Thread-"+i);
        }
        for (int i = 0; i < 4; i++) {
            threads[i].start();
        }
        Thread.sleep(2000);
    }

    /**
     * [Thread-0]: -- 开始抢占锁
     * [Thread-0]: ^^ 抢到了锁
     * [Thread-3]: -- 开始抢占锁
     * [Thread-3]: ^^ 抢到了锁
     * [Thread-1]: -- 开始抢占锁
     * [Thread-1]: ^^ 抢到了锁
     * [Thread-2]: -- 开始抢占锁
     * [Thread-2]: ^^ 抢到了锁
     * @throws InterruptedException
     */
    static void testFairLock() throws InterruptedException {
        Lock lock = new ReentrantLock(true);
        Runnable r = () -> {IncrementData.lockAndFastIncrease(lock);};

        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(r, "Thread-"+i);
        }
        for (int i = 0; i < 4; i++) {
            threads[i].start();
        }
        Thread.sleep(2000);
    }
    public static void main(String[] args) throws InterruptedException {
        testUnFairLock();

        System.out.println("=================== Fair Lock below =========");
        testFairLock();
    }

}
