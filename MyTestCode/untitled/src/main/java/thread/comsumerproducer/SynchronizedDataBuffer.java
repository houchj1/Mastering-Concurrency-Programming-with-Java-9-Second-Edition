package thread.comsumerproducer;

public class SynchronizedDataBuffer<T> extends NotSafeDataBuffer<T> {

    @Override
    public synchronized void add(T element) throws Exception {
        super.add(element);
    }

    @Override
    public synchronized T fetch() throws Exception {
        return super.fetch();
    }
}
