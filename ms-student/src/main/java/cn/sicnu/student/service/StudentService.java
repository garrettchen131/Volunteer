package cn.sicnu.student.service;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.common.feign.IActivityFacade;
import cn.sicnu.common.feign.IOAuthFacade;
import cn.sicnu.common.model.entity.Student;
import cn.sicnu.common.model.vo.StudentInfoVO;
import cn.sicnu.student.mapper.IStudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentService {

    private final IStudentMapper studentMapper;

    private final IActivityFacade activityFacade;

    private final IOAuthFacade oAuthFacade;

    public List<Student> getStudents(List<Long> ids) {
        return studentMapper.selectStudentsByIds(ids);
    }

    public StudentInfoVO getInfo(Student student) {
        val studentInfo = new StudentInfoVO();
        BeanUtil.copyProperties(student, studentInfo);
        val score = activityFacade.countScore(student.getId()).getData();
        val accountVO = oAuthFacade.getAccountInfo(student.getAccountId()).getData();
        return studentInfo
                .setScore(score)
                .setUsername(accountVO.getUsername())
                .setEmail(accountVO.getEmail())
                .setPhone(accountVO.getPhone());
    }

}
