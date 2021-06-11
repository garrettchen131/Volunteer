package cn.sicnu.activity.rest;

import cn.sicnu.activity.service.ActivityService;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.vo.ActivityVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ActivitySchoolResource {

    private final ActivityService activityService;

    /**
     * 根据分页的方式，通过当前学校用户的ID获取其活动列表
     * @param schoolId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/common/school/id/{schoolId}/{page}/{size}")
    public ResultInfo<List<Activity>> getSchoolIdActivity(@PathVariable("schoolId") long schoolId, @PathVariable("page") int page, @PathVariable("size") int size) {
        val activities = activityService.getSchoolActivitiesOnPageBySchoolId(schoolId, page, size);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), activities);
    }

    /**
     * 根据分页的方式获取某一个学校的活动
     * @param schoolName
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/common/school/name/{schoolName}/{page}/{size}")
    public ResultInfo<List<ActivityVO>> getSchoolNameActivity(@PathVariable("schoolName") String schoolName, @PathVariable("page") int page, @PathVariable("size") int size) {
        val activities = activityService.getSchoolActivitiesOnPageBySchoolName(schoolName, page, size);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), activities);
    }


}
