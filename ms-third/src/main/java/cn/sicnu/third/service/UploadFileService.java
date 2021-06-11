package cn.sicnu.third.service;

import cn.hutool.core.bean.BeanUtil;
import cn.sicnu.third.config.CloudCosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadFileService {

    private final CloudCosConfig cloudCosConfig;

    private final COSClient cosClient;

    public String uploadImage(InputStream inputStream, long size, String contentType) {
        val key = "images/" + UUID.randomUUID();
        uploadFile(inputStream, size, contentType, key);
        return cloudCosConfig.getUrl() + key;
    }

    public String uploadAvatar(InputStream inputStream, long size, String contentType) {
        val key = "avatar/" + UUID.randomUUID();
        uploadFile(inputStream, size, contentType, key);
        return cloudCosConfig.getUrl() + key;
    }

    private void uploadFile(
            InputStream inputStream,
            long size,
            String contentType,
            String key
    ) {
        val metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        metadata.setContentType(contentType);
        val request = new PutObjectRequest(
                cloudCosConfig.getBucketName(),
                key, inputStream, metadata
        );
        cosClient.putObject(request);
    }

    public String uploadFile(File file){
        val name = file.getName();
        System.out.println(name);
        val key = "images/" + UUID.randomUUID() + name.substring(name.lastIndexOf("."));
        val start = System.currentTimeMillis();
        System.out.println("\n\n开始上传");
        val result = cosClient.putObject(cloudCosConfig.getBucketName(), key, file);
        System.out.println("上传结束，耗时：" + (System.currentTimeMillis() - start));
        val metadata = result.getMetadata();
        val map = BeanUtil.beanToMap(metadata);
        map.forEach((k, v) -> System.out.println(k + " : " + v));
        return cloudCosConfig.getUrl() + key;
    }

}
