package thread.comsumerproducer;

import common.Print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * the one will report error
 * @param <T>
 */
public class NotSafeDataBuffer<T> {

    public static final int MAX_AMOUNT = 10;
    private List<T> dataList = new LinkedList<>();
    private AtomicInteger amount = new AtomicInteger(0);
    public void add(T element) throws Exception {
        if (amount.get() > MAX_AMOUNT) {
            Print.tco("data buffer is full");
            return;
        }
        dataList.add(element);
        Print.tco(element + ".");
        amount.incrementAndGet();
        if (amount.get() != dataList.size()) {
            throw new Exception(amount + " != " + dataList.size());
        }
    }

    public T fetch() throws Exception {
        if (amount.get() <=0) {
            Print.tco("data buffer is full");
            return null;
        }
        T element = dataList.remove(0);
        Print.tco("removing: " + element);
        amount.decrementAndGet();
        if (amount.get() != dataList.size()) {
            throw new Exception(amount.get() + " != " + dataList.size());
        }
        return element;
    }

}
