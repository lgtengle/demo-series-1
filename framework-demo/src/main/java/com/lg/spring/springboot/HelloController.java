package com.lg.spring.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/19 17:41
 *
 * @author leiguang
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object hello(){
        return "hello";
    }
}
