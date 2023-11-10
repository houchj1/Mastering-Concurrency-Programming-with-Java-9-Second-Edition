package thread.comsumerproducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * this demos the producer-consumer scenario using synchronized key word on the data buffer class
 *
 * the drawback is that the as the synchronization is on the single SynchronizedDataBuffer instance
 * all producer and consumer actions are serialized (not actually synchronized)
 * and throughput is low.
 * 
 * Actually Producer and Consumer are not necessarily serialized.
 *
 */
public class SynchronizedPetStore extends NotSafePetStore{

    public static void main(String[] args) {

        SynchronizedPetStore.dataBuffer = new SynchronizedDataBuffer();

        final int THREAD_TOTAL = 20;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_TOTAL);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Producer(produceAction, 500));
            pool.submit(new Consumer(consumeAction, 1500));
        }
    }

}
