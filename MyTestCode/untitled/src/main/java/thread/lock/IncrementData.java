package thread.lock;

import common.Print;

import java.util.concurrent.locks.Lock;

/**
 * JUC page 238.
 * the command class used in lock scenarios by pass in different types of locks
 */
public class IncrementData {

    static int sum = 0;

    public static void lockAndFastIncrease(Lock lock) {
        //TODO: 注意这个模板写法， lock（）方法在try 外面调用，而不是里面
        Print.tco("-- 开始抢占锁");
        lock.lock();
        try {
            Print.tco("^^ 抢到了锁");
            sum++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * demos the scenario of lock.lockInterruptibly();
     *
     * @param lock
     */
    static void lockInterruptibilityAndIncrease(Lock lock) {
        Print.tco("-- 开始抢占锁");
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            Print.tco("抢占被中断，抢锁失败");
            e.printStackTrace();
            return;
        }
        try {
            Print.tco("抢到锁了，同步执行1秒");
            Thread.sleep(1000);
            sum++;

            if (Thread.currentThread().isInterrupted()) {
                Print.tco("同步执行被中断");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
