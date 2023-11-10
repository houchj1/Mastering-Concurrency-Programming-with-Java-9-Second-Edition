package thread.completable;


import common.Print;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * demos thenApply(), thenAccept(), thenRun(), thenCompose()
 * to serialize the task
 */
public class CompletableFutureSerialExecute {

    public static void main(String[] args) {

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            Print.tco("in first cf1.supplyAsync() returns 100");
            return 100;
        }).thenApplyAsync((a) -> {
            Print.tco("in second cf1.thenApply() got value: " + a);
            int secondVal = a + 100;
            Print.tco("in second cf1.thenApply() return value: " + secondVal);
            return secondVal;
        });

        try {
            int lastGetVal = cf1.get();
            Print.tco("cf1.get() returned val: " + lastGetVal);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Void> cf2 = CompletableFuture.supplyAsync(()-> {
            Print.tco("in cf2.supplyAsync()");
            return 1000;
        }).thenAccept((a) -> {
            Print.tco("in second cf2.thenAccept() the value is consumed, value: " + a);
        });

        try {
            cf2.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
