package testAnnotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@Configuration
public class UseAnno {
    @DefineAnnotation(nums = {1, 2, 3})
    public String name;

    public static void getAnno() throws NoSuchFieldException {
        Field name = UseAnno.class.getDeclaredField("name");
        DefineAnnotation annotation = name.getAnnotation(DefineAnnotation.class);
        System.out.println(annotation);
        System.out.println(Arrays.toString(annotation.nums()));

    }


    @SetValue(12)
    public int getValue(int v) {
        System.out.println("这是怎么反射的呢:" + v);
        return v;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        getAnno();

        SpringApplication app = new SpringApplication(UseAnno.class);
        app.run(args);
    }
}
