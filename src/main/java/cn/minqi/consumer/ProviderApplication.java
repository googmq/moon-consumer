package cn.minqi.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@MapperScan("cn.minqi.consumer.mapper*")
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(
                ProviderApplication.class);
        application.run(args);
    }

}

