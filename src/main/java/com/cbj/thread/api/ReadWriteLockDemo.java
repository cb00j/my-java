package com.cbj.thread.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 在Java中Lock接口比synchronized块的优势是什么？
 * Lock接口最大的优势是为读和写分别提供了锁。
 * <p>
 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它
 * 使用读写锁
 *
 * @author CBJ
 * @date 2018/3/13 17:14
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {

        final MyData myData = new MyData();

        for (int i = 0; i < 4; i++) {
            new Thread(() -> myData.get()).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> myData.put(new Random().nextInt(1000))).start();
        }

    }


    private static class MyData {

        private ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private int data;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        public void get() {
            rwLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "read lock is ready.." + sdf.format(new Date()));
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "read lock get data.." + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwLock.readLock().unlock();
            }

        }

        public void put(int data) {
            rwLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "write lock is ready.." + sdf.format(new Date()));
                TimeUnit.MILLISECONDS.sleep(1000);
                this.data = data;
                System.out.println(Thread.currentThread().getName() + "write lock data is " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwLock.writeLock().unlock();
            }
        }

    }

}
