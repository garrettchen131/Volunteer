package cn.sicnu.activity.service;

import cn.sicnu.activity.mapper.IActivityMapper;
import cn.sicnu.activity.mapper.IRecordMapper;
import cn.sicnu.common.feign.IOAuthFacade;
import cn.sicnu.common.feign.IStudentFacade;
import cn.sicnu.common.feign.IThirdFacade;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Record;
import cn.sicnu.common.model.enums.Status;
import cn.sicnu.common.model.vo.AccountVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static cn.sicnu.common.model.enums.Status.*;
import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RequiredArgsConstructor
@Service
@Slf4j
public class RecordService {

    private final IRecordMapper recordMapper;

    private final IActivityMapper activityMapper;

    private final IThirdFacade thirdFacade;

    private final IStudentFacade studentFacade;

    private final IOAuthFacade oAuthFacade;

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<Void> signStudentActivity(long studentId, long activityId) {
        val count = recordMapper.countStudentActivity(studentId, activityId);
        if (count != 0) {
            return ResultInfoUtil.buildError("请勿重复加入正在进行中的项目", getCurrentUrl());
        }
        val activity = activityMapper.selectActivityById(activityId);
        if (!validStatus(activity.getStatus())) {
            return ResultInfoUtil.buildError("此项目已经结束报名", getCurrentUrl());
        }
        val update = activityMapper.updateActivityPersonNumber(activityId);
        if (update != 1) {
            return ResultInfoUtil.buildError("此项目报名人数已满", getCurrentUrl());
        }
        val record = Record.builder()
                .activityId(activityId)
                .title(activity.getTitle())
                .studentId(studentId)
                .status(Status.PREPARE.getCode())
                .score(activity.getScore())
                .build();
        recordMapper.save(record);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    public List<Record> getRecordByActivityId(long activityId) {
        return recordMapper.getRecordByActivityId(activityId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void postRecords(List<RecordDTO> recordDTOS, long activityId) {
        recordDTOS.stream()
                .map(e -> Record.builder()
                        .activityId(activityId)
                        .studentId(e.getId())
                        .status(e.getValid() ? COMPLETED.getCode() : INVALID.getCode())
                        .build())
                .peek(recordMapper::updateRecordsStatus)
                .filter(e -> e.getStatus().equals(COMPLETED.getCode()))
                .map(recordMapper::selectRecordDetail)
                .forEach(e -> {
                    val accountId = studentFacade.getStudentAccountId(e.getStudentId()).getData();
                    val idCard = oAuthFacade.getAccountInfo(accountId).getData().getIdCard();
                    thirdFacade.addScore(idCard, e.getScore());
                });
        activityMapper.updateActivityStatus(COMPLETED.getCode(), activityId);
    }

    public long countScore(long studentId) {
        return recordMapper.countScoreByStudentId(studentId);
    }

    public List<Record> getRecords(int status, long studentId) {
        return recordMapper.getRecordByStatusAndStudentId(status, studentId);
    }

}
