package testRedis;

import redis.clients.jedis.Jedis;

import java.io.IOException;

public class ObjectToRedisByJSON {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        User user = new User(12, "wd", "tent");
//        try {
//            String s = JacksonUtils.getInstance().writeValueAsString(user);
//            System.out.println("对象转化字符串："+s);
//            jedis.set("user",s);
//            String user2 = jedis.get("user");
//            User user3 = JacksonUtils.getInstance().readValue(user2, User.class);
//            System.out.println(user3);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
