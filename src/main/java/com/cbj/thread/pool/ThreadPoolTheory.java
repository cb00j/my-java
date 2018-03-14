package com.cbj.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author CBJ
 * @date 2018/3/12 9:46
 */
public class ThreadPoolTheory {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10), (runnable, poolExecutor) -> {
            System.out.println("拒接任务" + runnable);
            throw new RuntimeException("拒接！！！");
        });


        for (int i = 0; i < 21; i++) {
            executor.execute(new MyTask());
            System.out.println("poolSize:" + executor.getPoolSize() + "----activeCount:" + executor.getActiveCount() + "----queueSize:" + executor.getQueue().size() + "----taskCount:" + executor.getTaskCount());
        }

        try {
            TimeUnit.SECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();

    }

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i'm running!!!");
            System.out.println("hello," + Thread.currentThread().getName());
        }
    }
}
