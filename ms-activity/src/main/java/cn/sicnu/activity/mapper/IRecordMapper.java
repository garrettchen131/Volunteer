package cn.sicnu.activity.mapper;

import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IRecordMapper {

    int save(@Param("record") Record record);

    int countStudentActivity(@Param("studentId") long studentId, @Param("activityId") long activityId);

    List<Record> getRecordByActivityId(@Param("activityId") long activityId);

    Record selectRecordDetail(@Param("record") Record record);

    int updateRecordsStatus(@Param("record") Record record);

    long countScoreByStudentId(@Param("studentId") long studentId);

    List<Record> getRecordByStatusAndStudentId(@Param("status") int code, @Param("studentId") long studentId);

}
