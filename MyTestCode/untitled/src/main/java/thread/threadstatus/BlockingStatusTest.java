package thread.threadstatus;

import common.Print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BlockingStatusTest {


    static synchronized void syncNeverEnd() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * t2 should be in BLOCKED status, see jstack output:
     * "t2-blocking-thread" #15 prio=5 os_prio=0 cpu=0.00ms elapsed=15.64s tid=0x000001af51639800 nid=0x4d48 waiting for monitor entry  [0x00000032af5ff000]
     *    java.lang.Thread.State: BLOCKED (on object monitor)
     *         at thread.threadstatus.BlockingStatusTest.sync1(BlockingStatusTest.java:7)
     *         - waiting to lock <0x00000007131eb348> (a java.lang.Class for thread.threadstatus.BlockingStatusTest)
     *         at thread.threadstatus.BlockingStatusTest.lambda$testBlockedBySynchronized$1(BlockingStatusTest.java:22)
     *         at thread.threadstatus.BlockingStatusTest$$Lambda$15/0x0000000800066c40.run(Unknown Source)
     *         at java.lang.Thread.run(java.base@11.0.19/Thread.java:829)
     */
    static void testBlockedBySynchronized() {
        Thread t1 = new Thread(() -> {
            syncNeverEnd();
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            syncNeverEnd();
        }, "t2-blocking-thread");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * TODO: this is not a correct case, as the Thread status is showing as RUNNABLE
     *
     * "thread-blocked-by-IO" #14 prio=5 os_prio=0 cpu=0.00ms elapsed=42.69s tid=0x0000028fff554800 nid=0x46f4 runnable  [0x000000a348efe000]
     *    java.lang.Thread.State: RUNNABLE
     *         at java.io.FileInputStream.readBytes(java.base@11.0.19/Native Method)
     *         at java.io.FileInputStream.read(java.base@11.0.19/FileInputStream.java:279)
     *         at java.io.BufferedInputStream.read1(java.base@11.0.19/BufferedInputStream.java:290)
     *         at java.io.BufferedInputStream.read(java.base@11.0.19/BufferedInputStream.java:351)
     *         - locked <0x00000007133435f0> (a java.io.BufferedInputStream)
     *         at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.19/StreamDecoder.java:284)
     *         at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.19/StreamDecoder.java:326)
     *         at sun.nio.cs.StreamDecoder.read(java.base@11.0.19/StreamDecoder.java:178)
     *         - locked <0x000000071301e4b8> (a java.io.InputStreamReader)
     *         at java.io.InputStreamReader.read(java.base@11.0.19/InputStreamReader.java:181)
     *         at java.io.Reader.read(java.base@11.0.19/Reader.java:189)
     *         at java.util.Scanner.readInput(java.base@11.0.19/Scanner.java:882)
     *         at java.util.Scanner.findWithinHorizon(java.base@11.0.19/Scanner.java:1796)
     *         at java.util.Scanner.nextLine(java.base@11.0.19/Scanner.java:1649)
     *         at common.Print.consoleInput(Print.java:17)
     */
    static void testBlockedByConsoleInput() {
        Thread t1 = new Thread(() -> {
            //should be blocked by console input
            Print.consoleInput();
        }, "thread-blocked-by-IO");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @throws InterruptedException
     */
    static void testBlockedByFileIO() throws InterruptedException {
        /**
         * first thread is TIMED_WAITING
         * "thread-blockedby_file-IO" #14 prio=5 os_prio=0 cpu=0.00ms elapsed=17.34s tid=0x00000220bc030800 nid=0x2f10 waiting on condition  [0x000000c6e02fe000]
         *    java.lang.Thread.State: TIMED_WAITING (sleeping)
         *         at java.lang.Thread.sleep(java.base@11.0.19/Native Method)
         *         at thread.threadstatus.BlockingStatusTest.lambda$testBlockedByFileIO$3(BlockingStatusTest.java:95)
         *         at thread.threadstatus.BlockingStatusTest$$Lambda$14/0x0000000800066840.run(Unknown Source)
         *         at java.lang.Thread.run(java.base@11.0.19/Thread.java:829)
         */
        Thread t1 = new Thread(() -> {
            File f = new File("D:\\test.txt");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(123);
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-blockedby_file-IO");
        t1.start();

        Thread t2 = new Thread(() -> {

            

        }, "");
        t2.start();
        t1.join();
    }
    public static void main(String[] args) throws InterruptedException {
//        testBlockedBySynchronized();

//        testBlockedByConsoleInput();

        testBlockedByFileIO();
    }

}
