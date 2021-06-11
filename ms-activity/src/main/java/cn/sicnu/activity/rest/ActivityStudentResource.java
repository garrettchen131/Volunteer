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
public class ActivityStudentResource {

    private final ActivityService activityService;

    /**
     * 提供给学生服务使用，获取学生用户参加的活动
     *
     * @param ids
     * @return
     */
    @GetMapping("/common/student/{ids}")
    public ResultInfo<List<Activity>> getStudentActivity(@PathVariable List<Long> ids) {
        val activities = activityService.getStudentActivityByActivityIds(ids);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), activities);
    }



}
