package testRedis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = RedisUtil.class)
public class RedisTest {
    @Resource
    private RedisUtil redisUtil;

    @Test
    public void set(){
        redisUtil.set("redis_key", "redis_value");
    }

    @Test
    public void get(){
        String value = redisUtil.get("redis_key");
        System.out.println(value);
    }


}
