package cn.sicnu.third.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import lombok.Data;
import lombok.val;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloud.cos")
@Data
public class CloudCosConfig {
    private String secretId;
    private String secretKey;
    private String bucketName;
    private String regionName;
    private String url;

    @Bean
    public COSClient cosClient() {
        val cred = new BasicCOSCredentials(secretId, secretKey);
        val clientConfig = new ClientConfig(new Region(regionName));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }


}
