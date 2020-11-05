package cn.minqi.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author minqi
 */
@Configuration
@ConfigurationProperties(prefix = "config")
@Data
public class ServiceConfig {

    private String user;

    private String baseEncodeStr;

    private String splitStr;
}