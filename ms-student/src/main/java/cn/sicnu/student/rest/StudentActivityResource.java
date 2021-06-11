package cn.sicnu.student.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.Student;
import cn.sicnu.student.service.StudentActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StudentActivityResource {

    private final StudentActivityService studentActivityService;

    @PostMapping("/sign/{activityId}")
    public ResultInfo sign(@PathVariable("activityId") long activityId, @RequestAttribute("user") Student student) {
        return studentActivityService.sign(activityId, student);
    }
}
