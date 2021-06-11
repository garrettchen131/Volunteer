package cn.sicnu.common.feign;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-school")
public interface ISchoolFacade {
    @GetMapping("/ms/school/{schoolId}")
    ResultInfo<School> getSchool(@PathVariable("schoolId") long schoolId);
}
