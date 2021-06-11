package cn.sicnu.common.feign;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.entity.Student;
import cn.sicnu.common.utils.ResultInfoUtil;
import com.google.common.collect.Lists;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@FeignClient(value = "ms-student")
public interface IStudentFacade {

    @GetMapping("/students/{ids}")
    ResultInfo<List<Student>> getStudents(@PathVariable("ids") List<Long> ids);

    @GetMapping("/ms/accountId/{studentId}")
    ResultInfo<Long> getStudentAccountId(@PathVariable("studentId") long studentId);
}
