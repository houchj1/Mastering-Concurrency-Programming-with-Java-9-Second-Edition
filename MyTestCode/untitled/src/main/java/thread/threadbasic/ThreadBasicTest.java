package thread.threadbasic;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Nick
 * wait for 2 threads termination using Thread.join()
 *
 */
public class ThreadBasicTest {

    public static void main(String[] args) {

        Random random = new Random();
        IntStream stream = random.ints(10);
        int[] data = stream.toArray();

        MyTask task1 = new MyTask(data, 0, 5);
        MyTask task2 = new MyTask(data, 5, 10);

        Thread t1 = new Thread(task1);
        t1.setName("T1");
        Thread t2 = new Thread(task1);
        t2.setName("T2");

        System.out.println("starting child threads....");

        t1.start();
        t2.start();

        System.out.println("waiting for 2 child threads to complete.");

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("2 child threads are all completed.");
    }

    static class MyTask implements Runnable {

        private int start;
        private int end;
        private int[] data;

        public MyTask(int[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("From Thread " + Thread.currentThread().getName());
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i + ", ");
            }

        }
    }


}
