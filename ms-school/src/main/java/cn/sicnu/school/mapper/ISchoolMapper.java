package cn.sicnu.school.mapper;

import cn.sicnu.common.model.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ISchoolMapper {

   // Optional<Student>

    int save(@Param("school") School school);

    School selectSchoolByAccountId(@Param("accountId") int accountId);

    School selectSchoolById(@Param("id") long id);
}
