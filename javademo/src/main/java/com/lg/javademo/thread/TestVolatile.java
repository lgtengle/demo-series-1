package com.lg.javademo.thread;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/26 15:50
 *
 * @author leiguang
 */
public class TestVolatile {

    public  int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

}
