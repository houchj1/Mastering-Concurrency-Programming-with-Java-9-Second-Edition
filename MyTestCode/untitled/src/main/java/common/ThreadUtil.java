package common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * JUC page 87, use self defined util method to create ThreadPool to avoid jdk included ThreadPool problem.
 *
 * JDK thread pools Problems like:
 *  the internal blocking queue size is unlimited, if continuously adding task the internal blocking queue will be full
 *  until it reaches the maxPoolSize, the jvm heap will be out of memory.
 *
 */
public class ThreadUtil {
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int QUEUE_SIZE = 128;
    public static final int MIXED_MAX = 128;  //其他条件没有设置好的情况的最大值,保证不会无限制
    public static final String MIXED_THREAD_AMOUNT = "mixed.thread.amount";
    private static class MixedTargetThreadPoolLazyHolder {
        private static final int max = (null != System.getProperty(MIXED_THREAD_AMOUNT)) ?
                Integer.parseInt(System.getProperty(MIXED_THREAD_AMOUNT)) : MIXED_MAX;
        private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
                max,
                max,
                KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(QUEUE_SIZE),
                new CustomThreadFactory("mixed"));

        static {
            EXECUTOR.allowCoreThreadTimeOut(true);
            // add jvm shutdown hook to gracefully shut down the thread pool
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                shutdownThreadPoolGracefully(EXECUTOR);
            }, "混合型任务线程池回调线程"));
        }


    }

    public static ExecutorService getMixedTargetThreadPool() {
        return MixedTargetThreadPoolLazyHolder.EXECUTOR;
    }

    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        if (!(threadPool instanceof ExecutorService) || threadPool.isTerminated()) {
            return;
        }

        threadPool.shutdown();
        try {
            if (threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务为正常执行结束");
                }
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            throw new RuntimeException(e);
        }
    }
}
