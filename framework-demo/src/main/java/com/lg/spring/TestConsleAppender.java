package com.lg.spring;

import ch.qos.logback.core.ConsoleAppender;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/15 19:55
 *
 * @author leiguang
 */
public class TestConsleAppender<E> extends ConsoleAppender<E> {

    static {
        System.out.println("TestConsleAppender class load....");
    }

    @Override
    protected void append(E eventObject) {
        System.out.println(eventObject.toString());


    }

    public static void test(){
        System.out.println("test method...");
    }
}

