package cn.sicnu.third.config;

import cn.sicnu.third.contract.ScoreContract;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "contract")
@Slf4j
@Data
public class ContractConfig {

    private final String ACCOUNT_FILE_FORMAT = "pem";

    private Map<String, Object> cryptoMaterial;
    private Map<String, List<String>> network;
    private Map<String, Object> account;
    private String address;

    @Bean
    public BcosSDK bcosSDK(ConfigProperty configProperty) throws ConfigException {
        val configOption = new ConfigOption(configProperty, CryptoType.ECDSA_TYPE);
        return new BcosSDK(configOption);
    }

    @Bean
    public Client client(BcosSDK bcosSDK) {
        return bcosSDK.getClient(1);
    }

    @Bean
    public ScoreContract scoreContract(Client client) {
        val cryptoSuite = client.getCryptoSuite();
        val cryptoKeyPair = cryptoSuite.getCryptoKeyPair();
        cryptoSuite.setCryptoKeyPair(cryptoKeyPair);
        return ScoreContract.load(address, client, cryptoKeyPair);
    }

    @Bean
    public ConfigProperty configProperty() {
        val configProperty = new ConfigProperty();
        configProperty.setCryptoMaterial(cryptoMaterial);
        configProperty.setAccount(account);
        configProperty.setNetwork(new HashMap<>() {{
            put("peers", network.get("peers"));
        }} );
        configProperty.setAmop(Lists.newArrayList());
        configProperty.setThreadPool(Maps.newHashMap());
        return configProperty;
    }
}
