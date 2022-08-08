package testAnnotation;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UseAnno {
    @DefineAnnotation(nums = {1, 2, 3})
    public String name;

    public static void getAnno() throws NoSuchFieldException {
        Field name = UseAnno.class.getDeclaredField("name");
        DefineAnnotation annotation = name.getAnnotation(DefineAnnotation.class);
        System.out.println(annotation);
        System.out.println(Arrays.toString(annotation.nums()));

    }

    public static void main(String[] args) throws NoSuchFieldException {
        getAnno();
    }
}
