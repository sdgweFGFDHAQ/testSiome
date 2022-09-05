package testRedis;

import redis.clients.jedis.Jedis;

import java.io.*;

public class ObjectToRedis {
    public byte[] serialize(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public Object deSerialize(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
            bais.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        ObjectToRedis otr = new ObjectToRedis();
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());
        User user = new User(1, "sam", "s001");

        jedis.set("user".getBytes(), otr.serialize(user));
        Object o = otr.deSerialize(jedis.get("user".getBytes()));
        User user1 = (User) o;
        System.out.println(user1);
    }
}

