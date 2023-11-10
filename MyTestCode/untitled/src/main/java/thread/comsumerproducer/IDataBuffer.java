package thread.comsumerproducer;

public interface IDataBuffer<T> {

    public void add(T element) throws Exception;

    public T fetch() throws Exception;

}
