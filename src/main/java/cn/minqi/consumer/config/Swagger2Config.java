package cn.minqi.consumer.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 API文档配置
 * 默认未开启，建议开发环境启用，生产环境关闭
 *
 * 文档地址为 /doc.html
 *
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(name = {"swagger.enabled"}, havingValue = "true")
public class Swagger2Config {
    /**
     * 扫描路径
     */
    @Value("${swagger.base-package}")
    private String basePackage;
    /**
     * 文档标题
     */
    @Value("${swagger.title}")
    private String title;
    /**
     * 文档描述
     */
    @Value("${swagger.description:}")
    private String description;
    /**
     * 文档版本号
     */
    @Value("${swagger.version:1.0.0}")
    private String version;

    /**
     * Swagger2 配置
     *
     * @return Swagger2 Docket实例
     */
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)
                .version(version).build();
    }
}
