package thread.waitnotify;

import common.Print;

/**
 * demo how Object.wait(), Object.notify() works
 * JUC page 154
 */
public class WaitNotifyDemo {

    static Object locko = new Object();
    static class WaitTarget implements Runnable {
        @Override
        public void run() {
            synchronized (locko) {
                try {
                    Print.tco("Start Waiting in WaitingTarget");
                    //Thread becomes WAITING status and gives up the monitor
                    locko.wait();
                    //got notify, thread will go to EntryList (Blocked if there is another thread got the monitor)
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Print.tco("收到通知,当前线程继续执行");
            }
        }
    }
    static class NotifyTarget implements Runnable {
        @Override
        public void run() {
            synchronized (locko) {
                Print.consoleInput();
                locko.notifyAll();
                Print.tco("发出通知了,但是线程还没有释放锁");
            }
        }
    }

    public static void main(String[] args) {
        Thread waitThread = new Thread(new WaitTarget(), "WaitingThread");
        waitThread.start();

        Thread notifyThread = new Thread(new NotifyTarget(), "NotifyThread");
        notifyThread.start();

        try {
            waitThread.join();
            notifyThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
