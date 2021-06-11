package cn.sicnu.student.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.Student;
import cn.sicnu.common.model.vo.StudentInfoVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.student.service.StudentService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentResource {

    private final StudentService studentService;

    @GetMapping("/students/{ids}")
    public ResultInfo<List<Student>> getStudents(@PathVariable("ids") List<Long> ids) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), studentService.getStudents(ids));
    }

    @GetMapping("/info")
    public ResultInfo<StudentInfoVO> getStudentInfo(@RequestAttribute("user") Student student) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), studentService.getInfo(student));
    }

    @GetMapping("/ms/accountId/{studentId}")
    public ResultInfo<Long> getStudentAccountId(@PathVariable("studentId") long studentId) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), studentService.getStudents(Lists.newArrayList(studentId)).get(0).getAccountId());
    }

}
