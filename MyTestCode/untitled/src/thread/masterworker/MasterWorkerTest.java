package thread.masterworker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MasterWorkerTest {

    static class SimpleTask extends Task<Integer> {
        @Override
        protected Integer doExecute() {
            System.out.println("task " + getId() + " is done ");
            return getId();
        }
    }

    public static void main(String[] args) {
        Master<SimpleTask, Integer> master = new Master<>(4);

        ScheduledExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(1);
        threadPoolExecutor.scheduleAtFixedRate(() -> {
            master.submit(new SimpleTask());
        }, 0, 2, TimeUnit.SECONDS);

        threadPoolExecutor.scheduleAtFixedRate(() -> master.printResult(), 5, 5, TimeUnit.SECONDS);

    }

}
