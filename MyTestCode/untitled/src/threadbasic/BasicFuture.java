package threadbasic;

import java.util.concurrent.*;

public class BasicFuture {

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": in callable task");

            return "value returned from callable task";
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main task begins");

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(new MyTask());

        String ret = null;
        try {
            ret = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + ", Future.isDone() is " + future.isDone());
        System.out.println(Thread.currentThread().getName() + ", the value returned from task is " + ret);
        System.out.println(Thread.currentThread().getName() + ", task1 ends");

        // show how to cancel tasks
        Future<String> cancelledFuture = executorService.submit(new MyTask());
        cancelledFuture.cancel(true);
        System.out.println(Thread.currentThread().getName() + ", task 2 the isCancelled" + cancelledFuture.isCancelled());

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("task 2 finished, main thread exits");


    }

}
