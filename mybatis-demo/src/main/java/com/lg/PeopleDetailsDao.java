package com.lg;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/29 17:25
 *
 * @author leiguang
 */
public interface PeopleDetailsDao {

     List<PeopleDetails> getDetails(@Param("startTime") long startTime, @Param("endTime") long endTime);

}
