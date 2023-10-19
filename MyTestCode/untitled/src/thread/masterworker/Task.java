package thread.masterworker;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


public class Task<R> {

    static AtomicInteger index = new AtomicInteger(1);
    public Consumer<Task<R>> resultAction;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    private int workerId;

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    R result = null;

    public Task() {
        this.id = index.getAndIncrement();
    }

    public void execute() {
        this.result = this.doExecute();
        resultAction.accept(this);
    }
    protected R doExecute() {
        return null;
    }
}
