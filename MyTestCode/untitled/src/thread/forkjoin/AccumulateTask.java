package thread.forkjoin;

import java.util.concurrent.RecursiveTask;

public class AccumulateTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public AccumulateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <=end; i++) {
                sum += i;
            }
            System.out.println("compute directly from " + start + " to " + end);
        } else {
            System.out.println("split task");
            int middle = start + (end - start)/2;
            AccumulateTask task1 = new AccumulateTask(start, middle);
            AccumulateTask task2 = new AccumulateTask(middle + 1, end);
            task1.fork();
            task2.fork();

            int ret1 = task1.join();
            int ret2 = task2.join();
            sum = ret1 + ret2;
        }
        return sum;
    }
}
