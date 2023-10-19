package thread.threadbasic;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws InterruptedException {
        int factor = 1;
        int numThreads = factor *
                (Runtime.getRuntime().availableProcessors());

        CountDownLatch endController = new CountDownLatch(10);


        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < 10; i++) {
            executor.submit(new MyTask("Task " + i, endController));
        }

        executor.shutdown();
        endController.await();

        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("main thread is done");
    }

}
