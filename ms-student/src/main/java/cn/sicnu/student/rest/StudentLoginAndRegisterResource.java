package cn.sicnu.student.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.LoginDTO;
import cn.sicnu.common.model.dto.StudentDTO;
import cn.sicnu.common.utils.AssertUtil;
import cn.sicnu.student.service.LoginAndRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentLoginAndRegisterResource {
    private final LoginAndRegisterService loginAndRegisterService;

    @PostMapping("/login")
    public ResultInfo signIn(@RequestBody LoginDTO loginDTO) {
        // 参数校验
        AssertUtil.isNotEmpty(loginDTO.getAccount(), "请输入登录帐号");
        AssertUtil.isNotEmpty(loginDTO.getPassword(), "请输入登录密码");
        return loginAndRegisterService.signIn(loginDTO.getAccount(), loginDTO.getPassword());
    }

    @PostMapping("/register")
    public ResultInfo register(@RequestBody StudentDTO studentDTO) {
        //val beanMap = BeanUtil.beanToMap(studentDTO);
        //beanMap.forEach((k, y) -> AssertUtil.isNotEmpty((String) y, k + "不能为空，请输入"));
        return loginAndRegisterService.studentRegister(studentDTO);
    }
}
