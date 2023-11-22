package cn.minqi.consumer;

//import org.mybatis.spring.annotation.MapperScan;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author minqi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
//@MapperScan("cn.minqi.consumer.persistence.mapper")
public class Application {

    /**
     * main方法
     *
     * @param args 运行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

