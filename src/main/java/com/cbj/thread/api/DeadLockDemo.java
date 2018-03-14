package com.cbj.thread.api;

/**
 * @author CBJ
 * @date 2018/3/14 9:22
 */
public class DeadLockDemo {


    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> deadLockDemo.methodA()).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> deadLockDemo.methodB()).start();
        }
    }

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void methodA(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("methodA");
            }
        }
    }

    public void methodB(){
        synchronized (lock2){
            synchronized (lock1){
                System.out.println("methodB");
            }
        }
    }
    /**
         Found one Java-level deadlock:
         =============================
         "Thread-7":
         waiting to lock monitor 0x0000000002ba8c88 (object 0x000000076b309308, a java.lang.Object),
         which is held by "Thread-6"
         "Thread-6":
         waiting to lock monitor 0x0000000002ba77e8 (object 0x000000076b3092f8, a java.lang.Object),
         which is held by "Thread-3"
         "Thread-3":
         waiting to lock monitor 0x0000000002ba8c88 (object 0x000000076b309308, a java.lang.Object),
         which is held by "Thread-6"

     */
}
