package com.cbj.thread.api;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier和CountDownLath区别
 *
 * CyclicBarrier可重复使用，并可以传入一个Runnable用作达到等待阈值的触发方法
 *
 * CountDownLath适用于一组线程和一个主线程，一个主线程等待一组线程完成，主线程再执行的场景
 * CyclicBarrier适用于一组线程同时进入准备状态，再同时开始工作的场景
 *
 *
 * @author CBJ
 * @date 2018/3/14 10:48
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(5, () -> System.out.println("已到达5个等待，执行并重置state。。。"));
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "进入执行");
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "走你。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "进入执行");
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "走你。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);

        System.out.println("执行成功...");

    }

}
