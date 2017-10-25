package com.lg.spring;

import com.lg.common.SpringContextInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * description:
 * </p>
 * Created on 2017/10/15 19:35
 *
 * @author leiguang
 */
public class SpringApp {
    private static Logger LOG = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args){
        System.out.println(LOG);
        TestBean testBean = SpringContextInstance.getBean("testBean", TestBean.class);
        //LOG.info("hahaha");
        /*LOG.info("hahaha");*/
        testBean.test();

    }

}
