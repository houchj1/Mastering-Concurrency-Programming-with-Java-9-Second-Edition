package common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC page 69, like the SimpleThreadFactory
 * the thread factory to be used by Thread pool
 */
public class CustomThreadFactory implements ThreadFactory {
    static AtomicInteger threadNo = new AtomicInteger(1);

    String namePrefix;

    public CustomThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = namePrefix + "-" + threadNo.get();
        Print.tco("创建一条线程,名称为:" + threadName);
        threadNo.incrementAndGet();
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(false);// default is this value, just demo in case needs to set it the daemon
        return thread;
    }
}
