package thread.waitnotify;


import common.Print;
import thread.comsumerproducer.Consumer;
import thread.comsumerproducer.IDataBuffer;
import thread.comsumerproducer.NotSafePetStore;
import thread.comsumerproducer.Producer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JUC page 156, using Object.wait(), Object.notify() to communicate the Producer and Consumer.
 *
 * 注意 wait(), notify()使用的固定搭配
 *
 * NOTE: improved the performance a lot, as compared to Synchronized version (all actions are serial on one monitor)
 * Why? because if the Store(Buffer) is not full, the producer is not put to wait
 *      and also if the Store is not empty, the consumer is not put to wait
 *      Producer action and Consumer action are parallel except full/empty condition happens.
 *
 * But, there are still Blocking cases when the store is full/empty, the Threads are Blocked in these cases.
 *      not maximal performance.
 */
public class WaitNotifyPetStore extends NotSafePetStore {
    public static final int MAX_AMOUNT = 10;
    static class WaitNotifyDataBuffer<T> implements IDataBuffer<T> {
        private List<T> dataList = new LinkedList<>();
        private int amount = 0;

        // 3 monitors used to control the producer and consumer
        private final Object LOCK_OBJECT = new Object();
        private final Object NOT_FULL = new Object();
        private final Object NOT_EMPTY = new Object();

        public void add(T element) throws Exception {
            while (amount > MAX_AMOUNT) {
                synchronized (NOT_FULL) {
                    Print.tco("队列已经满了");
                    NOT_FULL.wait();
                }
            }
            synchronized (LOCK_OBJECT) {
                dataList.add(element);
                amount++;
            }
            synchronized (NOT_EMPTY) {
                NOT_EMPTY.notify();
            }
        }
        public T fetch() throws Exception {
            while (amount <= 0) {
                synchronized (NOT_EMPTY) {
                    Print.tco("队列已经空了");
                    NOT_EMPTY.wait();
                }
            }
            T element = null;
            synchronized (LOCK_OBJECT) {
                element = dataList.remove(0);
                amount--;
            }
            synchronized (NOT_FULL) {
                NOT_FULL.notifyAll();
            }
            return element;
        }

    }

    public static void main(String[] args) {
        WaitNotifyPetStore.dataBuffer = new WaitNotifyDataBuffer();

        final int THREAD_TOTAL = 20;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_TOTAL);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Producer(produceAction, 500));
            pool.submit(new Consumer(consumeAction, 1500));
        }

    }


}
