package com.lg.cache;

import com.lg.common.SpringContextInstance;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/12/7 19:56
 *
 * @author leiguang
 */

public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        CacheService cacheService = SpringContextInstance.getBean("cacheService", CacheService.class);
        System.out.println(cacheService.getData("xiuxiuxiu"));
        System.out.println("---------");

        System.out.println(cacheService.getData("hello world"));
        System.out.println("-----------------");

        System.out.println(cacheService.getData("hello world"));
        System.out.println("-------------------------------");
        Thread.sleep(7000);

        //缓存过期
        System.out.println(cacheService.getData("hello world"));
        System.out.println("----------------------------------------------");


    }



}
