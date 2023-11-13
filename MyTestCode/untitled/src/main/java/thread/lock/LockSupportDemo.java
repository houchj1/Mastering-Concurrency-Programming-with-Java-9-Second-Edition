package thread.lock;

import common.Print;

import java.util.concurrent.locks.LockSupport;

/**
 * JUC page 244, page 246.
 *
 *
 */
public class LockSupportDemo {

    static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Print.tco("About to become Endless Blocking status by LockSupport.park()");
            LockSupport.park();

            Print.tco("Thread is awakened! from LockSupport.park()");
            if (Thread.currentThread().isInterrupted()) {
                Print.tco("Thread is interrupted, but still can continue executing.");
            }
        }
    }

    static void testParkUnpark_InterruptPark() throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread("Thread1");
        ChangeObjectThread t2 = new ChangeObjectThread("Thread2");
        t1.start();
        Thread.sleep(1000);

        t2.start();
        Thread.sleep(1000);

        //TODO: NOTE: this also wake up t1 from LockSupport.park()
        t1.interrupt();

        LockSupport.unpark(t2);
    }

    static void testUnparkSleep() throws InterruptedException {
        Thread t1 = new Thread(() -> {
           try {
               Print.tco("about to sleep 1 seconds, cannot be wakeup by LockSupport.unpark()");
               Thread.sleep(1000);
           } catch(InterruptedException e) {
                e.printStackTrace();
           }

           Print.tco("即将进入无限时阻塞");

           LockSupport.park();

           Print.tco("Awaken up from LockSupport.park()");
        });

        t1.start();

        LockSupport.unpark(t1);
        Print.tco("第一次unpark()");

        LockSupport.unpark(t1);
        Print.tco("第2次unpark()");

        Thread.sleep(2000);

        Print.tco("第3次unpark()");
        LockSupport.unpark(t1);
    }
    public static void main(String[] args) throws InterruptedException {
        testParkUnpark_InterruptPark();

        testUnparkSleep();

    }


}
