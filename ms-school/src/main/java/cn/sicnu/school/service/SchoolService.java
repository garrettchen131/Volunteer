package cn.sicnu.school.service;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.common.feign.IOAuthFacade;
import cn.sicnu.common.model.entity.School;
import cn.sicnu.common.model.vo.SchoolVO;
import cn.sicnu.school.mapper.ISchoolMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolService {

    private final ISchoolMapper schoolMapper;

    private final IOAuthFacade oAuthFacade;

    public School getSchool(long schoolId) {
        return schoolMapper.selectSchoolById(schoolId);
    }

    public SchoolVO getSchoolInfo(School school) {
        val accountVO = oAuthFacade.getAccountInfo(school.getAccountId()).getData();
        val schoolVO = new SchoolVO();
        BeanUtil.copyProperties(school, schoolVO, false);
        return schoolVO
                .setUsername(accountVO.getUsername())
                .setEmail(accountVO.getEmail())
                .setPhone(accountVO.getPhone());
    }

}
