package cn.sicnu.activity.service;

import cn.sicnu.activity.mapper.IActivityMapper;
import cn.sicnu.activity.mapper.IActivityStarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityStarService {

    private final IActivityStarMapper activityStarMapper;

    private final IActivityMapper activityMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean changeStar(Long activityId, Long studentId) {
        val flag = new boolean[1];
        activityStarMapper.selectStar(activityId, studentId)
                .ifPresentOrElse(e -> {
                    activityMapper.decrementActivityStar(activityId);
                    activityStarMapper.deleteStar(e.getId());
                    flag[0] = false;
                }, () -> {
                    activityMapper.incrementActivityStar(activityId);
                    activityStarMapper.insertStar(activityId, studentId);
                    flag[0] = true;
                });
        return flag[0];
    }

    public boolean getStar(Long activityId, Long studentId) {
        return activityStarMapper
                .selectStar(activityId, studentId)
                .isPresent();
    }

}
