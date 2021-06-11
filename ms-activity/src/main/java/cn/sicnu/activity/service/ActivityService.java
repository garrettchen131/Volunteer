package cn.sicnu.activity.service;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.activity.mapper.IActivityMapper;
import cn.sicnu.activity.mapper.IRecordMapper;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.ActivityDTO;
import cn.sicnu.common.model.entity.Activity;
import cn.sicnu.common.model.entity.Record;
import cn.sicnu.common.model.enums.Status;
import cn.sicnu.common.model.vo.ActivityVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityService {

    private final IActivityMapper activityMapper;

    private final IRecordMapper recordMapper;

    public ResultInfo<Void> createActivity(ActivityDTO activityDTO, long schoolId) {
        val count = activityMapper.countActivityIfPresent(
                schoolId, activityDTO.getTitle(),
                Lists.newArrayList(Status.PREPARE.getCode(), Status.EXECUTE.getCode())
        );
        if (count != 0) {
            return ResultInfoUtil.buildError(
                    "请勿重复创建目前正在进行中的同名项目",
                    getCurrentUrl()
            );
        }
        val activity = new Activity();
        BeanUtil.copyProperties(activityDTO, activity);
        activity.setSchoolId(schoolId);
        activity.setStatus(Status.PREPARE.getCode());
        activityMapper.save(activity);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    public List<Activity> getSchoolActivitiesOnPageBySchoolId(long schoolId, int page, int size) {
        val params = new HashMap<String, Object>();
        params.put("schoolId", schoolId);
        params.put("page", (page - 1) * size);
        params.put("size", size);
        return activityMapper.selectSchoolActivitiesOnPageBySchoolId(params);
    }

    public List<ActivityVO> getSchoolActivitiesOnPageBySchoolName(String schoolName, int page, int size) {
        val params = new HashMap<String, Object>();
        params.put("schoolName", schoolName);
        params.put("page", (page - 1) * size);
        params.put("size", size);
        val activities = activityMapper.selectSchoolActivitiesOnPageBySchoolName(params);
        return activities.stream()
                .map(e -> {
                    val activityVO = new ActivityVO();
                    BeanUtil.copyProperties(e, activityVO, false);
                    return activityVO;
                }).collect(Collectors.toList());
    }

    public Activity getActivityById(long id) {
        return activityMapper.selectActivityById(id);
    }

    public List<Activity> getStudentActivityByActivityIds(List<Long> ids) {
        return activityMapper.selectActivityByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeActivity(long activityId) {
        updateFun(activityId, Status.EXECUTE);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelActivity(long activityId) {
        updateFun(activityId, Status.INVALID);
    }

    @Transactional(rollbackFor = Exception.class)
    public void finishActivity(long activityId) {
        updateFun(activityId, Status.COMPLETED);
    }

    private void updateFun(long activityId, Status status) {
        activityMapper.updateActivityStatus(status.getCode(), activityId);
        recordMapper.updateRecordsStatus(
                Record.builder()
                        .status(status.getCode())
                        .activityId(activityId)
                        .build()
        );
    }
}
