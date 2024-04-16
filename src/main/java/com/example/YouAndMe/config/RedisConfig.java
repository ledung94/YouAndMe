package com.example.YouAndMe.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.GenericToStringSerializer;
// import org.springframework.data.redis.serializer.RedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.timeout}")
    private int redisTimeout;

    // @Bean
    // LettuceConnectionFactory redisConnectionFactory() {
    //     // Tạo Standalone Connection tới Redis
    //     return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    // }

    // @Bean
    // @Primary
    // RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //     // tạo ra một RedisTemplate
    //     // Với Key là Object
    //     // Value là Object
    //     // RedisTemplate giúp chúng ta thao tác với Redis
    //     RedisTemplate<Object, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(redisConnectionFactory);
    //     // Thiết lập keySerializer và valueSerializer
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    //     template.afterPropertiesSet(); // Cần gọi để hoàn tất thiết lập

    //     RedisSerializer<?> keySerializer = template.getKeySerializer();
    //     RedisSerializer<?> valueSerializer = template.getValueSerializer();

    //     System.out.println("Key Serializer: " + keySerializer);
    //     System.out.println("Value Serializer: " + valueSerializer);
    //     return template;
    // }
    // @Bean
    // @Primary
    // Jedis jedisConnection() {
    //     Jedis jedis = new Jedis(redisHost, redisPort, redisTimeout);
    //     System.out.println("Connected to redis");
    //     // Xác thực (nếu cần)
    //     // jedis.auth("your_password");
    //     return jedis;
    // }
}
