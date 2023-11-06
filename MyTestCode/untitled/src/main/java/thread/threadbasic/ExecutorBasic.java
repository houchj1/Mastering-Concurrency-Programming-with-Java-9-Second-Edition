package thread.threadbasic;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * execute thread using Executor, ExecutorService
 */
public class ExecutorBasic {

    static class MyTask implements Runnable {

        private String name;
        private CountDownLatch countDownLatch;

        public MyTask(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("The task name is: " + this.name);
            try {
                Thread.sleep(1000);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MyCallableTask implements Callable<Integer> {

        static AtomicInteger accum = new AtomicInteger(1);
        @Override
        public Integer call() throws Exception {
            System.out.println("in MyCallableTask...");
            return accum.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int factor = 1;
        int numThreads = factor *
                (Runtime.getRuntime().availableProcessors());

        CountDownLatch endController = new CountDownLatch(10);


        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < 10; i++) {
            executor.submit(new MyTask("Task " + i, endController));
            Future<Integer> future =  executor.submit(new MyCallableTask());
            System.out.println("The result from callable task is --> " + future.get());
        }

        executor.shutdown();
        endController.await();

        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("main thread is done");
    }

}
