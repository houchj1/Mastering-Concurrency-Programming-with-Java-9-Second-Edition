package thread.comsumerproducer;

import common.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * common consumer class
 */
public class Consumer implements Runnable{

    public static final int CONSUME_GAP = 100;

    static final AtomicInteger TURN = new AtomicInteger(0);
    static final AtomicInteger CONSUMER_NO = new AtomicInteger(1);

    String name;
    Callable action = null;
    int gap = CONSUME_GAP;
    public Consumer(Callable action, int gap) {
        this.action = action;
        this.gap = gap;
        name = "消费者-" +CONSUMER_NO.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {

            TURN.incrementAndGet();
            try {
                Object out = action.call();
                if (null != out) {
                    Print.tco("Turn " + TURN.get() + " consume: " + out);
                }
                Thread.sleep(gap);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
