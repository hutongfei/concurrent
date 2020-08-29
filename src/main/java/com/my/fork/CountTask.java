package com.my.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 1 分割任务
 * 2 执行认证 并合并结果
 * <p>
 * fork类来把大任务分割成子任务 ->  分割到足够小  ->   并将任务放置到双端任务里  ->   启动线程并重双端队列中获取任务执行  -> 子任务执行完的结果统一放在一个队列里   ->   启动一个线程重队列中拿数据计算结果
 * fork 拆分任务， ForkJoinPool 执行任务
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;  // 阈值

    private int start = 0;

    private int end = 0;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;

    }

    @Override
    protected Integer compute() {
        boolean canCompute = (end - start) <= THRESHOLD;
        int sum = 0;
        if (canCompute) { //判断任务是否足够小
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {// 不够小 则拆分任务
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 计算任务
        CountTask countTask = new CountTask(1, 4);
        // 执行一个任务
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * ForkJoinTask：我们要使用ForkJoin框架，必须首先创建一个ForkJoin任务。它提供在任务
     * 中执行fork()和join()操作的机制。通常情况下，我们不需要直接继承ForkJoinTask类，只需要继
     * 承它的子类，Fork/Join框架提供了以下两个子类。
     *
     * ·RecursiveAction：用于没有返回结果的任务。
     * ·RecursiveTask：用于有返回结果的任务。
     *
     * ForkJoinPool：ForkJoinTask需要通过ForkJoinPool来执行。
     * 任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。当
     * 一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任
     * 务
     */

    /**
     * 通过这个例子，我们进一步了解ForkJoinTask，ForkJoinTask与一般任务的主要区别在于它
     * 需要实现compute方法，在这个方法里，首先需要判断任务是否足够小，如果足够小就直接执
     * 行任务。如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入
     * compute方法，看看当前子任务是否需要继续分割成子任务，如果不需要继续分割，则执行当
     * 前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。
     */
}
