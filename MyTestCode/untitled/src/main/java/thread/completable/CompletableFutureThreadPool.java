package thread.completable;


import common.Print;

import java.util.concurrent.*;

/**
 * use thread pool in the async task
 */
public class CompletableFutureThreadPool {

    public static void main(String[] args) {
        ExecutorService poolExecutor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            Print.tco("in cf1.supplyAsync() using thread pool");
            return 100;
        }, poolExecutor);
        cf1.whenCompleteAsync((a, t) -> {
            Print.tco("in cf1.whenCompleteAsync(), the value is: " + a);
        }, poolExecutor);

        try {
            cf1.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Print.tco("shutting down thread pool...");
        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Print.tco("main thread ending...");
    }


}
