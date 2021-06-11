package cn.sicnu.school.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.LoginDTO;
import cn.sicnu.common.model.dto.SchoolDTO;
import cn.sicnu.common.utils.AssertUtil;
import cn.sicnu.school.service.SchoolLoginAndRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchoolLoginAndRegisterResource {
    private final SchoolLoginAndRegisterService loginAndRegisterService;

    @PostMapping("/login")
    public ResultInfo signIn(@RequestBody LoginDTO loginDTO) {
        // 参数校验
        AssertUtil.isNotEmpty(loginDTO.getAccount(), "请输入登录帐号");
        AssertUtil.isNotEmpty(loginDTO.getPassword(), "请输入登录密码");
        return loginAndRegisterService.signIn(loginDTO.getAccount(), loginDTO.getPassword());
    }

    @PostMapping("/register")
    public ResultInfo register(@RequestBody SchoolDTO schoolDTO) {
        //val beanMap = BeanUtil.beanToMap(schoolDTO);
        //beanMap.forEach((k, y) -> AssertUtil.isNotEmpty((String) y, k + "不能为空，请输入"));
        return loginAndRegisterService.schoolRegister(schoolDTO);
    }
}
