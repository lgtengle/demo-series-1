package com.lg.javademo.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/25 15:45
 *
 * @author leiguang
 */
public class ExcutorDemo {

    public void testExcutorSource(){
        //ExecutorService
        //Executors
        //ThreadLocal
        //ThreadPool
    }

    public void testThreadLocal(){
        ThreadLocalModel tlm = new ThreadLocalModel();
        new Thread(tlm).start();
        new Thread(tlm).start();
        Thread.yield();
    }


    private static Model m = new Model();

    static ThreadLocal<Model> tl = new ThreadLocal<Model>(){
        @Override
        protected Model initialValue(){
            return m;
        }
    };

    /*public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadLocalModel()).start();
        new Thread(new ThreadLocalModel1()).start();

        Thread.sleep(6000);
        System.out.println(m.n);
    }*/

    static class Model{
        public int n = 0;

        public void inc(){
            n++;
        }

        public void des(){
            n--;
        }
    }

    static class ThreadLocalModel implements Runnable{
        @Override
        public void run() {
            try {
                tl.get().inc();
                Thread.sleep(3000);
                System.out.println("ThreadLocalModel0----" + tl.get());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadLocalModel1 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                tl.get().inc();
                System.out.println("ThreadLocalModel1----" + tl.get());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
    public void testThreadName(){
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}
