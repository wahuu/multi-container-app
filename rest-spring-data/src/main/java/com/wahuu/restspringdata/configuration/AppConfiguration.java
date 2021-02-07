package com.wahuu.restspringdata.configuration;

import com.wahuu.restspringdata.model.RedisCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public RedisTemplate<String, RedisCounter> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, RedisCounter> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
