package thread.threadbasic;

public class BlockThreadStatusDemo {


    public static synchronized void testFun() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(Thread.currentThread().getName() + " in synchronized method");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class BlockThreadStatusDemo1 extends Thread {

        static int threadNo = 1;
        public BlockThreadStatusDemo1() {
            super("BlockThreadStatusDemo-" + threadNo);
            threadNo++;
        }

        @Override
        public void run() {
            testFun();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new BlockThreadStatusDemo1();
        thread1.start();

        /**
         * thread2 showing in jstack: java.lang.Thread.State: BLOCKED (on object monitor)
         */
        Thread thread2 = new BlockThreadStatusDemo1();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
