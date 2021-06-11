package cn.sicnu.student.service;

import cn.sicnu.common.feign.IActivityFacade;
import cn.sicnu.common.feign.ISchoolFacade;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.entity.School;
import cn.sicnu.common.model.entity.Student;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentActivityService {

    private final IActivityFacade activityFacade;

    private final ISchoolFacade schoolFacade;

    public ResultInfo sign(long activityId, Student student) {
        val activity = (Activity) activityFacade.getActivity(activityId).getData();
        val school = (School) schoolFacade.getSchool(activity.getSchoolId()).getData();
        if (!school.getSchoolName().equals(student.getSchoolName())) {
            return ResultInfoUtil.buildError("学生所在学校与该项目的处理学校不匹配", getCurrentUrl());
        }

        return activityFacade.signStudentActivity(activityId, student.getId());
    }
}
