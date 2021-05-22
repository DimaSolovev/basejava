package com.urise.webapp;

public class DeadlockDemo {

    public final static Object one=new Object(), two=new Object();

    public static void main(String s[]) {

        Thread t1 = getThread(one,two);
        Thread t2 = getThread(two,one);

        t1.start();
        t2.start();
    }

    private static Thread getThread(Object obj1,Object obj2) {
        return new Thread(() -> {
            synchronized(obj1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println("Success!");
                }
            }
        });
    }
}
