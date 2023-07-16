package cn.minqi.consumer.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheInitializer {

    /**
     * redisTemplate 定义
     *
     * @param connectionFactory 缓存连接工厂，由容器根据配置提供
     * @return redisTemplate bean实例
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        System.out.print("RedisTemplate加载完成");
        return redisTemplate;
    }
}
