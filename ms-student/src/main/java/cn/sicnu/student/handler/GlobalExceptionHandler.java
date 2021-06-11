package cn.sicnu.student.handler;

import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.common.exception.ParameterException;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUri;

@Slf4j
@RestControllerAdvice // 将输出的内容写入 ResponseBody 中
public class GlobalExceptionHandler {

    @ExceptionHandler(ParameterException.class)
    public ResultInfo<Map<String, String>> handlerParameterException(ParameterException ex) {
        return ResultInfoUtil.buildError(ex.getErrorCode(), ex.getMessage(), getCurrentUri());
    }

    @ExceptionHandler(Exception.class)
    public ResultInfo<Map<String, String>> handlerException(Exception ex) {
        log.error("未知异常：{}", ex);
        return ResultInfoUtil.buildError(ApiConstant.OTHER_ERROR_CODE, "server error", getCurrentUri());
    }

}