package cn.sicnu.third.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.third.service.UploadFileService;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UploadFileResource {

    private final UploadFileService uploadFileService;


    @PostMapping("/upload/image")
    public ResultInfo<String> uploadImage(@RequestParam("/image") MultipartFile imageFile) throws IOException {
        val contentType = imageFile.getContentType();
        val url = uploadFileService.uploadImage(imageFile.getInputStream(), imageFile.getSize(), imageFile.getContentType());
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), url);
    }

    @PostMapping("/upload/avatar")
    public ResultInfo<String> uploadAvatar(@RequestParam("/image") MultipartFile imageFile) throws IOException {
        val contentType = imageFile.getContentType();
        val url = uploadFileService.uploadAvatar(imageFile.getInputStream(), imageFile.getSize(), imageFile.getContentType());
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), url);
    }
}
