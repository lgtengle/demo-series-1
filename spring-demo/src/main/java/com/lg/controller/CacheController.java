package com.lg.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/28 17:40
 *
 * @author leiguang
 */
@RestController
public class CacheController {

    @Cacheable("Cx")
    @RequestMapping("/testCache")
    public Object testCache(@RequestParam("id") int id){
        System.out.println("id>>>>>>"+id);
        return "test";
    }
}
