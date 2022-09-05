package testAnnotation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import testRedis.ObjectToRedis;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JsonInclude {

    public static void main(String[] args) throws IOException {
        Human human = new Human();

        human.setName("Jack");

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(human);
        System.out.println(s);

        ObjectToRedis otr = new ObjectToRedis();
        Object o = otr.deSerialize(otr.serialize(human));
        Human h = (Human) o;
        System.out.println(human.toString());//注解没生效？
    }
}
