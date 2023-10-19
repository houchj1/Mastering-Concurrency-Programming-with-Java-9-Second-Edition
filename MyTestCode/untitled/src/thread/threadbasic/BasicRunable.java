package thread.threadbasic;

public class BasicRunable {

    static class MyTask implements Runnable{
        private String name;
        public MyTask(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", task name " + name);
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyTask("thread1"));
        Thread thread2 = new Thread(new MyTask("thread2"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main thread exits");
    }

}
