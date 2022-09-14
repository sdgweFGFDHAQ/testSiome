package testDesign;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseGeneric {

    public static void main(String[] args) {
        String a = "#^%$@!&*";
        Map<Object, Object> b = new HashMap<>();
        b.put("first", "diyige");
        b.put(2, "+_");
        //b.put(null, "null");
        //b.put("", "blank");
        List<Object> c = new ArrayQueue<>(10);
        c.add("43");
        c.add("21");
        c.add("52");
        TransformFactory df = new TransformFactory();
        String parse = df.parse(a);
        String parse1 = df.parse(b);
        String parse2 = df.parse(c);
        System.out.println(parse);
        System.out.println(parse1);
        System.out.println(parse2);
    }
}
