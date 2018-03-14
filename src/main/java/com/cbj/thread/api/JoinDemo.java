package com.cbj.thread.api;


/**
 *
 * 现在有T1、T2、T3三个线程，怎样保证T2在T1执行完后执行，T3在T2执行完后执行?
 *
 * @author CBJ
 * @date 2018/3/13 16:53
 */
public class JoinDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(),"t1");
        Thread t2 = new Thread(new MyTask(),"t2");
        Thread t3 = new Thread(new MyTask(),"t3");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        Thread t4 = new Thread(new MyTask2(null),"t4");
        Thread t5 = new Thread(new MyTask2(t4),"t5");
        Thread t6 = new Thread(new MyTask2(t5),"t6");

        t6.start();
        t5.start();
        t4.start();
    }

    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class MyTask2 implements Runnable{

        private Thread prev;

        public MyTask2(Thread prev) {
            this.prev = prev;
        }

        @Override
        public void run() {
            if(prev != null){
                try {
                    prev.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
