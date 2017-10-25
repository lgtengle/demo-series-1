package com.example.springbootdemo;

import org.springframework.stereotype.Component;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/19 17:41
 *
 * @author leiguang
 */
@Component
public class HelloController {

    public HelloController(){
        System.out.println("HelloController");
        System.out.println(this.getClass().getClassLoader());
    }

    public void hello(){
        System.out.println("hello");
    }
}
