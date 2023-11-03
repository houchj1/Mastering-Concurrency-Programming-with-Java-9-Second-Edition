package thread.threadbasic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PhaserTest {

    static class MyTask implements Runnable {

        private String name;
        private static AtomicInteger runsTime = new AtomicInteger(0);
        private Phaser phaser;

        public MyTask(String name, Phaser phaser) {
            this.name = name;
            this.phaser = phaser;
            this.phaser.register();
        }

        @Override
        public void run() {
            this.phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ", runs " + runsTime.get());
            MyTask.runsTime.getAndIncrement();

            this.phaser.arriveAndDeregister();

        }
    }

    public static void main(String[] args) {

        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onAdvance, phase=" + phase + ", registeredParties=" + registeredParties);
                return super.onAdvance(phase, registeredParties);
            }
        };

        phaser.register();

        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(Thread.currentThread().getName() + ", initiate sub tasks ");
        System.out.println(Thread.currentThread().getName() + ", main thread wait for register of child");

//        phaser.arriveAndAwaitAdvance();

        Collection<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyTask("task" + i, phaser));
            thread.start();
        }

        System.out.println("main reached the barrier. phase 0 end.");
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + ", all children have completed phase 0," +
                " going to phase 1. ");

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyTask("task" + i, phaser));
            thread.start();
        }

        phaser.arriveAndDeregister();
        System.out.println(Thread.currentThread().getName() + ", all children have completed phase 1," +
                " going to phase 2. ");


    }
}
