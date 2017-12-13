package com.lg.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/12/7 20:10
 *
 * @author leiguang
 */
@Service
public class CacheService {

    @Cacheable(value="myCache", key = "#msg")
    public String getData(String msg){
        System.out.println("getData---获取msg:"+msg);
        return msg;
    }
}
