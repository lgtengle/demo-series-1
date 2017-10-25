package com.lg.javademo.other;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/28 16:06
 *
 * @author leiguang
 */
public class MyAppender<E>  extends ConsoleAppender<E> {

    //DaoUtils中日志的名字
    private String LoggerClassName = "com.richstonedt.commons.dao.DaoUtils";

    static {
        System.out.println("load....");
    }
    @Override
    protected void append(E e) {
        LoggingEvent le = (LoggingEvent) e;
        //如果是DaoUtils中的日志，采用自定义的方式处理，否则按默认方式处理
        if (LoggerClassName.equals(le.getLoggerName())){
            // TODO  le.getMessage()就是log.info()方法的参数
            System.out.println();
        }else
            super.append(e);
    }

    public static void test(){
        System.out.println("test...");
    }
}
