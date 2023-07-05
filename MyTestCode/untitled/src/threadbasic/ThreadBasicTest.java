package threadbasic;

public class ThreadBasicTest {

    public static void main(String[] args) {

        MyTask task1 = new MyTask();

        Thread t1 = new Thread(task1);
        t1.setName("T1");
        Thread t2 = new Thread(task1);
        t2.setName("T2");

        System.out.println("starting child threads....");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("waiting for 2 child threads to complete.");
    }

    static class MyTask implements Runnable {

        public void MyTask() {

        }

        @Override
        public void run() {
            System.out.println("From Thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
