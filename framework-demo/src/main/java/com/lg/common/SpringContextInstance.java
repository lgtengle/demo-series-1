package com.lg.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuzhenxing on 2017-6-23.
 */
public class SpringContextInstance {
    private static ApplicationContext mInstance;

    protected SpringContextInstance() {
    }

    public static ApplicationContext getInstance() {
        if (null == mInstance)
            mInstance = new ClassPathXmlApplicationContext("applicationContext.xml");
        return mInstance;
    }

    public static <T> T getBean(String id, Class<T> requiredType) {
        ApplicationContext context = getInstance();
        if (context != null)
            return context.getBean(id, requiredType);
        return null;
    }


    public static Object getBean(String id){
        ApplicationContext context = getInstance();
        if (context != null)
            return context.getBean(id);
        return null;
    }
}
