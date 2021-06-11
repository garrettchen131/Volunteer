package cn.sicnu.activity.mapper;

import cn.sicnu.common.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IActivityMapper {
    int save(@Param("activity") Activity activity);

    List<Activity> selectSchoolActivitiesOnPageBySchoolId(Map<String, Object> params);

    List<Activity> selectSchoolActivitiesOnPageBySchoolName(Map<String, Object> params);

    Activity selectActivityById(@Param("id") Long id);

    List<Activity> selectActivityByIds(@Param("ids") List<Long> ids);

    int countActivityIfPresent(@Param("id") Long schoolId, @Param("title") String title, @Param("status") List<Integer> code);

    //int countStudentActivity(@Param("studentId") long studentId, @Param("activityId") long activityId);

    int updateActivityPersonNumber(@Param("id") long id);

    int updateActivityStatus(@Param("status") int status, @Param("id") long id);

    int incrementActivityStar(@Param("activityId") Long activityId);

    int decrementActivityStar(@Param("activityId") Long activityId);
}
