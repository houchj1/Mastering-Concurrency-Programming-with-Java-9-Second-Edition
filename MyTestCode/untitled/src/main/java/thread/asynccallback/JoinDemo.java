package thread.asynccallback;

public class JoinDemo {

    public static final int SLEEP_GAP = 500;
    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {
        public HotWaterThread() {
            super("HotWater - Thread");
        }

        @Override
        public void run() {
            System.out.println("wash pot");
            System.out.println("filling water");
            System.out.println("put pot on fire");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("water is biolled");
        }
    }

    static class WashThread extends Thread {

        public WashThread() {
            super("$$ Wash-Thread");
        }
        @Override
        public void run() {
            System.out.println("Wash cup");
            System.out.println("get tea bag");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Wash Complete");
        }
    }

    public static void main(String[] args) {
        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThread();
        hThread.start();
        wThread.start();

        try {
            hThread.join();
            wThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main thread is done");



    }

}
