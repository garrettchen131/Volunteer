package cn.sicnu.activity.rest;

import cn.sicnu.activity.service.ActivityService;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ActivityResource {

    private final ActivityService activityService;

    /**
     * 通过学校ID创建一个活动项目
     */
    @PostMapping("/ms/create/{schoolId}")
    public ResultInfo<Void> createActivity(@RequestBody ActivityDTO activityDTO, @PathVariable long schoolId) {
        return activityService.createActivity(activityDTO, schoolId);
    }

    /**
     * 根据活动ID获取活动详情
     * @param id
     * @return
     */
    @GetMapping("/common/id/{id}")
    public ResultInfo<Activity> getActivity(@PathVariable("id") long id) {
        val activity = activityService.getActivityById(id);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), activity);
    }

    @PostMapping("/execute/{activityId}")
    public ResultInfo<Void> executeActivity(@PathVariable("activityId") long activityId) {
        activityService.executeActivity(activityId);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }


    @PostMapping("/cancel/{activityId}")
    public ResultInfo<Void> cancelActivity(@PathVariable("activityId") long activityId) {
        activityService.cancelActivity(activityId);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

}
