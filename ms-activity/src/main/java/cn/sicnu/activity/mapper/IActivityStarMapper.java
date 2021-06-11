package cn.sicnu.activity.mapper;

import cn.sicnu.common.model.entity.ActivityStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface IActivityStarMapper {

    int countStar(@Param("activityId") Long activityId);

    Optional<ActivityStar> selectStar(@Param("activityId") Long activityId, @Param("studentId") Long studentId);

    int insertStar(@Param("activityId") Long activityId, @Param("studentId") Long studentId);

    int deleteStar(@Param("id") Long id);

}
