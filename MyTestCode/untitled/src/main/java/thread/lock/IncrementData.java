package thread.lock;

import java.util.concurrent.locks.Lock;

/**
 * JUC page 238.
 * the command class used in lock scenarios by pass in different types of locks
 */
public class IncrementData {

    static int sum = 0;

    public static void lockAndFastIncrease(Lock lock) {
        //TODO: 注意这个模板写法， lock（）方法在try 外面调用，而不是里面
        lock.lock();
        try {
            sum++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
