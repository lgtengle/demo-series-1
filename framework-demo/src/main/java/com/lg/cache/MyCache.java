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
        return caches.get(o) == null ? null:new SimpleValueWrapper(caches.get(o));
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        System.out.println("读取缓存2");
        return (T)caches.get(o);
    }

    @Override
    public void put(Object o, Object o1) {
        System.out.println("设置缓存");
        caches.put(o, o1);
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
}
