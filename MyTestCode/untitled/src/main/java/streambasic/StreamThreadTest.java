package streambasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamThreadTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        list.stream().forEach(a -> {
            System.out.println("normal stream executed in thread: " + Thread.currentThread().getName());
        });

        /**
         * parallel stream executed in thread: ForkJoinPool.commonPool-worker-7, val = 1
         * parallel stream executed in thread: main, val = 3                   <-  Why in main thread???? work stealing?
         * parallel stream executed in thread: ForkJoinPool.commonPool-worker-5, val = 2
         * parallel stream executed in thread: ForkJoinPool.commonPool-worker-3, val = 4
         */
        list.stream().parallel().forEach(a -> {
            System.out.println("parallel stream executed in thread: " + Thread.currentThread().getName() + ", val = " + a);
        });



    }


}
