package cn.sicnu.student.mapper;

import cn.sicnu.common.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IStudentMapper {

   // Optional<Student>

    int save(@Param("student") Student student);

    int updateAccountIdById(@Param("id") Long id, @Param("accountId") Long accountId);

    Student selectStudentByAccountId(@Param("accountId") int accountId);

    List<Student> selectStudentsByIds(@Param("ids") List<Long> ids);
}
