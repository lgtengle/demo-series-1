package com.lg.javademo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/14 18:09
 *
 * @author leiguang
 */
public class TestThread1 {

    public static void main(String[] args) {
        Thread.currentThread().setDaemon(false);
        TestThread1 testThread1 = new TestThread1();
        testThread1.a();
    }
    public void a() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //service.shutdown();
    }
}
