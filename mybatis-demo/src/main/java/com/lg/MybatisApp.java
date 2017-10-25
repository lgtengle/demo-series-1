package com.lg;

import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/10 10:20
 *
 * @author leiguang
 */
public class MybatisApp {

    public static void main(String[] args){
        PeopleDetailsDao peopleDetailsDao = SpringContextInstance.getBean("peopleDetailsDao", PeopleDetailsDao.class);
        long startTime = CommonUtils.getStartTime(35);
        long endTime = CommonUtils.getStartTime(30);
        System.out.println("startTime:" +startTime + ", endTime:" + endTime);
        List<PeopleDetails> details = peopleDetailsDao.getDetails(startTime, endTime);
        System.out.println("details length----------------"+details.size());
        System.out.println(new String(details.get(0).getRealtimePeopleDetailsForBytes()));
    }
}
