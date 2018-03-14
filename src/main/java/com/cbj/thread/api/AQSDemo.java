package com.cbj.thread.api;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * AQS源码调试
 *
 * @author CBJ
 * @date 2018/3/12 16:35
 */
public class AQSDemo {
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        t1.start();
        t2.start();
        t3.start();

        TimeUnit.SECONDS.sleep(1000000);
        System.out.println("退出....");
        //t1.join();
        //t2.join();
    }


    private static class MyTask implements Runnable {
        private static final ReentrantLock lock = new ReentrantLock();
        int i = 0;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            lock.lock();
            i++;
            try {
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

}
