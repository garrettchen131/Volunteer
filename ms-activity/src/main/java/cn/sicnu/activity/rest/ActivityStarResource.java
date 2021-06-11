package cn.sicnu.activity.rest;

import cn.sicnu.activity.service.ActivityStarService;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ActivityStarResource {

    private final ActivityStarService activityStarService;

    @GetMapping("/star/{activityId}/{studentId}")
    public ResultInfo<Boolean> getStar(
            @PathVariable("activityId") long activityId,
            @PathVariable("studentId") long studentId
    ) {

        return ResultInfoUtil.buildSuccess(
                getCurrentUrl(),
                activityStarService.getStar(activityId, studentId)
        );
    }

    @PostMapping("/star/{activityId}/{studentId}")
    public ResultInfo<Boolean> changeStar(
            @PathVariable("activityId") long activityId,
            @PathVariable("studentId") long studentId
    ) {

        return ResultInfoUtil.buildSuccess(
                getCurrentUrl(),
                activityStarService.changeStar(activityId, studentId)
        );
    }
}
