package thread.threadbasic;

/**
 * run the code, then > jps
 *                    > stack [java process number]
 */
public class JoinDemoTimedWaiting {

    public static final int SLEEP_GAP = 5000;
    public static final int MAX_TURN = 50;

    static class SleepThread extends Thread {
        static int threadSequenceNo = 1;
        public SleepThread() {
            super("sleepThread-" + threadSequenceNo);
            threadSequenceNo++;
        }
        public void run() {
            int i = 0;
            while (i < MAX_TURN) {
                try {
                    System.out.println(getName() + " going to sleep");
                    Thread.sleep(SLEEP_GAP);      // in jstack showing TIMED_WAITING (sleeping)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(getName() + " interrupted by InterruptedException");
                    return;
                }
            }
            System.out.println(getName() + " finishing execution");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new SleepThread();
        System.out.println("start thread1");
        thread1.start();
        try {
            thread1.join();  // in jstack: java.lang.Thread.State: WAITING (on object monitor)
                            //  at java.lang.Object.wait(java.base@11.0.18/Native Method)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new SleepThread();
        System.out.println("start thread2");
        thread2.start();
        try {
            thread2.join(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread exit");
    }


}
