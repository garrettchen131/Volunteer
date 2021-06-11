package cn.sicnu.activity.rest;

import cn.sicnu.activity.service.ActivityService;
import cn.sicnu.activity.service.UniversityService;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.domain.University;
import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.entity.Activity;
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
public class UniversityResource {

    private final UniversityService universityService;


    @GetMapping("/common/university")
    public ResultInfo<List<University>> get() {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), universityService.getUniversity());
    }

}
