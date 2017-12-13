package com.lg.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/12/13 18:13
 *
 * @author leiguang
 */
public class MyCache implements Cache{

    Map<Object, Object> caches = new HashMap<>();

    private long validTime;


    public void setValidTime(long validTime) {
        this.validTime = validTime  * 1000;
    }

    @Override
    public String getName() {
        return "myCache";
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(final  Object o) {
        System.out.println("读取缓存1");
        Object result = caches.get(o);
        if (result == null)
            return null;
        else{
            MyValueWrapper myValueWrapper = (MyValueWrapper) caches.get(o);
            if (System.currentTimeMillis() - myValueWrapper.getStartTime() > validTime)
                return null;
            else
                return myValueWrapper;
        }
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        System.out.println("读取缓存2");
        return (T)caches.get(o);
    }

    @Override
    public void put(Object o, Object o1) {
        System.out.println("设置缓存");
        caches.put(o, new MyValueWrapper(o1));
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        if (caches.get(o) == null)
            put(o, o1);
        return null;
    }

    @Override
    public void evict(Object o) {
        caches.remove(o);
    }

    @Override
    public void clear() {
        caches.clear();
    }

    static class MyValueWrapper extends SimpleValueWrapper{

        private long startTime;

        public MyValueWrapper(Object value) {
            this(value, System.currentTimeMillis());
        }

        public MyValueWrapper(Object value, long startTime) {
            super(value);
            this.startTime = startTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }
}
