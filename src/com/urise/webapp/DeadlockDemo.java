package com.urise.webapp;

public class DeadlockDemo {

    public static void main(String[] args) {

        Object one = new Object();
        Object two = new Object();
        getThread(one, two).start();
        getThread(two, one).start();
    }

    private static Thread getThread(Object obj1, Object obj2) {
        return new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " пытается захватить " + obj1);
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + " захватил " + obj1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " пытается захватить " + obj2);
                synchronized (obj2) {
                    System.out.println("Success!");
                }
            }
        });
    }
}
