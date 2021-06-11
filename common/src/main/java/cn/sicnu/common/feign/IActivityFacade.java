package cn.sicnu.common.feign;

import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.entity.Record;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.val;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;


@FeignClient(value = "ms-activity")
public interface IActivityFacade {
    @PostMapping("/ms/create/{schoolId}")
    ResultInfo<Void> createActivity(@RequestBody ActivityDTO activityDTO, @PathVariable("schoolId") long schoolId);

    @GetMapping("/common/school/id/{schoolId}/{page}/{size}")
    ResultInfo<List<Activity>> getSchoolIdActivity(@PathVariable("schoolId") long schoolId, @PathVariable("page") int page, @PathVariable("size") int size);

    @GetMapping("/common/school/name/{schoolName}/{page}/{size}")
    ResultInfo<List<Activity>> getSchoolNameActivity(@PathVariable("schoolName") String schoolName, @PathVariable("page") int page, @PathVariable("size") int size);

    @PostMapping("/sign/{studentId}/{activityId}")
    ResultInfo<Void> signStudentActivity(@PathVariable("activityId") long activityId, @PathVariable("studentId") long studentId);

//    @GetMapping("/common/student/{ids}")
//    ResultInfo<List<Activity>> getStudentActivity(@PathVariable("ids") List<Long> ids);

    @GetMapping("/common/id/{id}")
    ResultInfo<Activity> getActivity(@PathVariable("id") long id);

    @GetMapping("/ms/record/{activityId}")
    ResultInfo<List<Record>> getStudentRecords(@PathVariable("activityId") long id);

    @PostMapping("/ms/record/{activityId} ")
    ResultInfo<Void> postRecords(@PathVariable("activityId") long activityId, @RequestBody List<RecordDTO> records);

    @GetMapping("/ms/count/score/{studentId}")
    ResultInfo<Long> countScore(@PathVariable("studentId") long studentId);


}