package cn.sicnu.school.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.entity.School;
import cn.sicnu.common.model.vo.StudentVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.school.service.SchoolActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SchoolActivityResource {

    private final SchoolActivityService schoolActivityService;

    @PostMapping("/create")
    public ResultInfo<Void> createSchoolActivity(@RequestBody ActivityDTO activityDTO, @RequestAttribute("user") School school) {
        return schoolActivityService.createActivity(activityDTO, school.getId());
    }

    @GetMapping("/show/mine/{page}/{size}")
    public ResultInfo<List<Activity>> getActivity(@PathVariable("page") int page, @PathVariable("size") int size, @RequestAttribute("user") School school) {
        return schoolActivityService.getActivityOfMyId(school.getId(), page, size);
    }

    @GetMapping("/school/activity/{activityId}/record/students")
    public ResultInfo<List<StudentVO>> getStudentsOfActivity(@PathVariable("activityId") long activityId) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), schoolActivityService.getStudentsOfActivity(activityId));
    }

    @PostMapping("/activity/{activityId}")
    public ResultInfo<Void> postActivity(@RequestBody List<RecordDTO> recordDTOS, @PathVariable("activityId") long activityId) {
        return schoolActivityService.postRecordsOfStudents(recordDTOS, activityId);
    }



}
