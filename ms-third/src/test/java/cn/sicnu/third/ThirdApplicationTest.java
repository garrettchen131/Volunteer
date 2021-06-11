package cn.sicnu.third;

import cn.hutool.core.io.FileUtil;
import cn.sicnu.third.service.UploadFileService;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.FileInputStream;
import java.util.stream.IntStream;

@SpringBootTest
public class ThirdApplicationTest {
    @Autowired
    UploadFileService uploadFileService;


    @Test
    @SneakyThrows
    public void test() {
        val file = new File("/Users/chenxi/Downloads/IMG_1765.JPG");
        System.out.println(file.length());

        System.out.println((file.length() / 1024 / 1024));


        for (int i = 0; i < 3; i++) {
            val inputStream = new FileInputStream(file);
            val image = uploadFileService.uploadImage(inputStream, file.length(), MediaType.IMAGE_JPEG_VALUE);
            System.out.println(image);
        }

    }
}
