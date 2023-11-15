package thread.juccontainers;


import thread.threadbasic.BasicRunable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC page 321
 *
 * TODO: Test if CopyOnWriteArrayList can get all changes from 2 threads???? YES!!!
 *
 * TODO: Output is: The final CopyOnWriteList is [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *
 */
public class CopyOnWriteArrayListMultiThread {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger count = new AtomicInteger(0);
        List<Integer> cowList = new CopyOnWriteArrayList();
        Runnable target = () -> {
            cowList.add(count.getAndIncrement());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.submit(target);
        }

        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("The final CopyOnWriteList is " + cowList);
    }



}
