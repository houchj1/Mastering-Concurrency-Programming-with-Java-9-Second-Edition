package thread.comsumerproducer;

import common.Print;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Common producer and consumer classes
 */
public class Producer implements Runnable {

    public static final int PRODUCE_GAP = 200;
    static final AtomicInteger TURN = new AtomicInteger(0);
    static final AtomicInteger PRODUCER_NO = new AtomicInteger(0);

    String name = null;
    Callable action = null;

    int gap = PRODUCE_GAP;

    public Producer(Callable action, int gap) {
        this.action = action;
        this.gap = gap;
        name = "生产者-" + PRODUCER_NO.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object obj = action.call();
                if (null != obj) {
                    Print.tco("Turn " + TURN.get() + ", produces: " + obj);
                }
                Thread.sleep(gap);

                TURN.incrementAndGet();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
