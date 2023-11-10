package thread.comsumerproducer;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JUC page 118. demonstrate the using of a non-safe buffer using LinkedList
 */
public class NotSafePetStore {

    protected static IDataBuffer<IGoods> dataBuffer = new NotSafeDataBuffer<>();

    protected static Callable<IGoods> produceAction = () -> {
        IGoods goods = Goods.produceOne();
        try {
            dataBuffer.add(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    };

    protected static Callable<IGoods> consumeAction = () -> {
        IGoods goods = null;

        try {
            goods = dataBuffer.fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    };

    public static void main(String[] args) {
        final int THREAD_TOTAL = 20;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_TOTAL);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Producer(produceAction, 500));
            pool.submit(new Consumer(consumeAction, 1500));
        }

    }
}
