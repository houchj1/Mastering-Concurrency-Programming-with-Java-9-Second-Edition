package thread.completable;

import common.Print;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureException {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            Print.tco("in cf1.supplyAsync()");
            return 100;
        }).whenCompleteAsync((a, throwable) -> {
            Print.tco("in cf1.whenCompleteAsync() exception object is " + throwable);
        });

        cf1.get();

        CompletableFuture<Object> cf2 = CompletableFuture.supplyAsync(() -> {
            Print.tco("in cf2.supplyAsync()");
            throw new RuntimeException("exception happens in supplyAsync()");
        }).whenCompleteAsync((a, throwable) -> {
            Print.tco("in cf2.whenCompleteAsync() exception object is " + throwable);
        });

        try {
            cf2.get();
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } catch (ExecutionException e) {
            Print.tco(e.getMessage());
//            e.printStackTrace();
        }

        CompletableFuture<Object> cf3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("mock runtime exception in cf3");
        });
        cf3.exceptionally((throwable) -> {
            Print.tco("in cf3.exceptionally()");
            return "mock value returned from exception handler";   // will not get this value
        });
        try {
            Object ret = cf3.get();
            Print.tco("cf3.get() returned: " + ret);
        } catch (Exception e) {
            Print.tco(" catching exception for cf3.get(): " + e.getMessage());
        }

        // user handle()
        CompletableFuture<Object> cf4 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("mock runtime exception in cf4");
        });
        cf4.handle((a, t) -> {
            Print.tco("in cf4.handle()");
            return "default value for cf4 while exception";    // also will not get this value
        });
        try {
            Object ret = cf4.get();
            Print.tco("cf4.get() returned: " + ret);
        } catch (Exception e) {
            Print.tco(" catching exception for cf4.get(): " + e.getMessage());
        }

        Print.tco("main thread ending...");
    }


}
