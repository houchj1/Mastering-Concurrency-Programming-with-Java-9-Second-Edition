package thread.threadbasic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableFutureTask {

    static AtomicInteger accum = new AtomicInteger(1);
    static class CallableTestTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("in callable task, accumulate ");
            accum.incrementAndGet();
            return accum.get();
        }
    }

    public static void main(String[] args) {
        CallableTestTask callableTask = new CallableTestTask();
        FutureTask<Integer> futureTask = new FutureTask(callableTask);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println("the value from future task is " + futureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        /**
         * NOTE: can share the same Callable, but not the same FutureTask
         * because if FutureTask is returned by FutureTask.get(),
         * then the return value is fixed
         */
        FutureTask<Integer> futureTask2 = new FutureTask<>(callableTask);
        Thread thread2 = new Thread(futureTask2);
        thread2.start();
        try {
            System.out.println("the value from future task is " + futureTask2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
