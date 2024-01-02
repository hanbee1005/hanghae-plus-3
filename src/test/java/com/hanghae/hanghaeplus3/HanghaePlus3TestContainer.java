package com.hanghae.hanghaeplus3;

import com.redis.testcontainers.RedisContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class HanghaePlus3TestContainer {
    private static final String MYSQL_VERSION = "mysql:8";

    @Container
    public static final MySQLContainer MYSQL_CONTAINER = new MySQLContainer(MYSQL_VERSION);

    @Container
    public static final RedisContainer REDIS_CONTAINER = new RedisContainer(RedisContainer.DEFAULT_IMAGE_NAME.withTag(RedisContainer.DEFAULT_TAG));

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> ""+REDIS_CONTAINER.getMappedPort(6379));
    }
}
