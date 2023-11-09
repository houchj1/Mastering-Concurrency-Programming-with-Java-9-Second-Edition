package thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    public static void main(String[] args) {

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync( () -> {
            return 1;
        });

        try {
            System.out.println("the ret from cf is " + cf.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            System.out.println("running in Completable future, current thread is " + Thread.currentThread().getName());
        });
        try {
            cf2.get();  // as no return value, just for sync with main thread;

            System.out.println("execute in main thread after cf2, current thread is " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
