package cn.sicnu.school.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.School;
import cn.sicnu.common.model.vo.SchoolVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SchoolResource {

    private final SchoolService schoolService;

    @GetMapping("/ms/school/{schoolId}")
    public ResultInfo<School> getSchool(@PathVariable("schoolId") long schoolId) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), schoolService.getSchool(schoolId));
    }

    @GetMapping("/info")
    public ResultInfo<SchoolVO> getSchoolInfo(@RequestAttribute("user") School school) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), schoolService.getSchoolInfo(school));
    }

}
