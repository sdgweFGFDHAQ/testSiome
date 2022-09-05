package testRedis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

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

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost", 6379);
//        jedis.auth("123456");
        System.out.println(jedis.ping());
    }

}
