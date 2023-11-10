package thread.completable;

import common.Print;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSimpleTest {

    public static void main(String[] args) {

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            Print.tco("in cf1.supplyAsync(), this should run in another thread");
            return 1;
        }).whenComplete((a, throwable) -> {
            Print.tco("in cf1.whenComplete(), the same thread as cf1.supplyAsync()");
        }).whenCompleteAsync((a, throwable) -> {
            Print.tco("in cf1.whenCompleteAsync(), can be in another thread");
        });

        try {
            Print.tco("calling cf1.get(), the value is " + cf1.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            Print.tco("in cf2 runAsync");
        });
        try {
            cf2.get();  // as no return value, just for sync with main thread;

            Print.tco("cf2 returned");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
