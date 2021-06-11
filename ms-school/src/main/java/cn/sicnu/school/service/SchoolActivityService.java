package cn.sicnu.school.service;

import cn.sicnu.common.feign.IActivityFacade;
import cn.sicnu.common.feign.IStudentFacade;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.entity.Record;
import cn.sicnu.common.model.vo.StudentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolActivityService {

    private final IActivityFacade activityFacade;

    private final IStudentFacade studentFacade;

    public ResultInfo<Void> createActivity(ActivityDTO activityDTO, Long id) {
        return activityFacade.createActivity(activityDTO, id).withPath(getCurrentUrl());
    }

    public ResultInfo<List<Activity>> getActivityOfMyId(long id, int page, int size) {
        return activityFacade.getSchoolIdActivity(id, page, size);
    }

    public List<StudentVO> getStudentsOfActivity(long activityId) {
        val records = activityFacade.getStudentRecords(activityId).getData();
        val ids = records.stream().map(Record::getStudentId).collect(Collectors.toList());
        val students = studentFacade.getStudents(ids).getData();
        return students.stream().map(e -> new StudentVO(e.getId(), e.getRealName())).collect(Collectors.toList());
    }

    public ResultInfo<Void> postRecordsOfStudents(List<RecordDTO> recordDTOS, long activityId) {
        return activityFacade.postRecords(activityId, recordDTOS);
    }

//    public ResultInfo<List<Activity>> getSchoolNameActivity(String schoolName, int size, int page) {
//        return activityFacade.getSchoolNameActivity(schoolName, page, size);
//    }

}
