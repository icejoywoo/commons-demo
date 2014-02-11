package java17.newfeatures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by wujiabin on 14-2-11.
 */
public class ForkJoinDemoTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private int start;
    private int end;

    public ForkJoinDemoTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; ++i) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinDemoTask left = new ForkJoinDemoTask(start, middle);
            ForkJoinDemoTask right = new ForkJoinDemoTask(middle + 1, end);
            left.fork();
            right.fork();
            int leftResult = left.join();
            int rightResult = right.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemoTask task = new ForkJoinDemoTask(1, 38);
        Future<Integer> result = pool.submit(task);
        System.out.println(result.get());
    }
}
