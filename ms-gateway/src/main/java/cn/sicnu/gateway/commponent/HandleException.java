package cn.sicnu.gateway.commponent;

import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.common.utils.ResultInfoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class HandleException {

    private final ObjectMapper objectMapper;

    public Mono<Void> writeError(ServerWebExchange exchange, String error) {
        val response = exchange.getResponse();
        val request = exchange.getRequest();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        val resultInfo = ResultInfoUtil.buildError(ApiConstant.NO_LOGIN_CODE, error, request.getURI().getPath());
        DataBuffer buffer = null;
        try {
            val resultInfoJson = objectMapper.writeValueAsString(resultInfo);
            buffer =  response.bufferFactory().wrap(resultInfoJson.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return response.writeWith(Mono.just(buffer));
    }

}