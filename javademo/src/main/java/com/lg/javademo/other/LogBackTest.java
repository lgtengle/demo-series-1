package com.lg.javademo.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/28 14:27
 *
 * @author leiguang
 */
public class LogBackTest {

    static final Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    public static void main(String[] args) throws IOException {
        System.out.println(logger.getClass().getClassLoader());
        logger.debug("000debug000");
        MyAppender.test();

    }
}
