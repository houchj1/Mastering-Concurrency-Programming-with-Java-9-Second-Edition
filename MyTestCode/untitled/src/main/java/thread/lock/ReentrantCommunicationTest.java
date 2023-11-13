package thread.lock;


import common.Print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * JUC page 242.
 *
 * TODO: shows how 2 threads communicate with each other using ReentrantLock.newCondition()
 *
 */
public class ReentrantCommunicationTest {

    static Lock lock = new ReentrantLock();
    static private Condition condition = lock.newCondition();

    static class WaitTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                Print.tco("我是等待方");
                condition.await();
                Print.tco("收到通知，等待方继续执行");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class NotifyTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                Print.tco("我是通知方");
                condition.signal();
                Print.tco("发出通知了， But the Lock is not yet released");
            } catch (Exception e) {
                Print.tco("");
            } finally {
                lock.unlock();    // TODO: 这个非常重要，不然 WaitTarget will never get chance to execute
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new WaitTarget(), "WaitThread");
        waitThread.start();

        Thread.sleep(1000);

        Thread notifyThread = new Thread(new NotifyTarget(), "NotifyThread");
        notifyThread.start();
    }

}
