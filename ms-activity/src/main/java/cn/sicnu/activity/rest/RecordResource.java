package cn.sicnu.activity.rest;

import cn.sicnu.activity.service.RecordService;
import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.common.exception.ParameterException;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.RecordDTO;
import cn.sicnu.common.model.entity.Record;
import cn.sicnu.common.model.enums.Status;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RecordResource {

    private final RecordService recordService;

    @PostMapping("/sign/{studentId}/{activityId}")
    public ResultInfo<Void> signStudentActivity(@PathVariable("activityId") long activityId, @PathVariable("studentId") long studentId) {
        return recordService.signStudentActivity(studentId, activityId);
    }

    @GetMapping("/ms/record/{activityId}")
    public ResultInfo<List<Record>> getStudentRecords(@PathVariable("activityId") long id) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), recordService.getRecordByActivityId(id));
    }

    @PostMapping("/ms/record/{activityId}")
    public ResultInfo<Void> postRecords(@PathVariable("activityId") long activityId, @RequestBody List<RecordDTO> records) {
        recordService.postRecords(records, activityId);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    @GetMapping("/ms/count/score/{studentId}")
    public ResultInfo<Long> countScore(@PathVariable("studentId") long studentId) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), recordService.countScore(studentId));
    }

    @GetMapping("/record/{status}/{studentId}")
    public ResultInfo<List<Record>> getStudentRecords(@PathVariable("status") String status, @PathVariable("studentId") long studentId) {
        val records = recordService.getRecords(Status.getCodeByName(status), studentId);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), records);
    }
}
