package testDesign;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseGeneric {
    public static String a;
    public static Map<Object, Object> b = new HashMap<>();
    public static List<Object> c = new ArrayList<>();

    public void setParams() {
        a = "#^%$@!&*";
        b.put("first", "diyige");
        b.put(2, "+_");
        c.add("43");
        c.add("21");
        c.add("52");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UseGeneric ug = new UseGeneric();
        ug.setParams();

        TransformFactory df = new TransformFactory();
        String parse = df.parse(a);
        System.out.println(parse);
        String parse1 = df.parse(b);
        System.out.println(parse1);
        String parse2 = df.parse(c);
        System.out.println(parse2);

        Class<UseGeneric> useGenericClass = UseGeneric.class;
        Method method = useGenericClass.getDeclaredMethod("setParams");
        method.invoke(ug);

    }
}
