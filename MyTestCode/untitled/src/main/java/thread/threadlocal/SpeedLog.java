package thread.threadlocal;

import common.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Java高并发核心编程2，JUC page 104
 */
public class SpeedLog {

    private static final ThreadLocal<Map<String, Long>> TIME_RECORD_LOCAL
            = ThreadLocal.withInitial(SpeedLog::initialStartTime);

    public static Map<String, Long> initialStartTime() {
        Map<String, Long> map = new HashMap<>();
        map.put("start", System.currentTimeMillis());
        map.put("last", System.currentTimeMillis());
        Print.tco("calling initialStartTime");
        return map;
    }

    public static final void beginSpeedLog() {
        Print.tco("Begin time cost record");
        TIME_RECORD_LOCAL.get();
    }

    public static final void endSpeedLog() {
        Print.tco("End time cost record, all records as: " + TIME_RECORD_LOCAL.get().toString());
        TIME_RECORD_LOCAL.remove();
    }

    public static final void logPoint(String point) {
        Long last = TIME_RECORD_LOCAL.get().get("last");
        Long cost = System.currentTimeMillis() - last;
        TIME_RECORD_LOCAL.get().put(point + " cost:", cost);
        TIME_RECORD_LOCAL.get().put("last", System.currentTimeMillis());
        Print.tco("calling logPoint...");
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            pool.submit(() -> {
                SpeedLog.beginSpeedLog();
                try {
                    Thread.sleep(new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SpeedLog.logPoint("after-sleep");
                SpeedLog.endSpeedLog();
            });
        }

        pool.shutdown();
        try {
            pool.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
