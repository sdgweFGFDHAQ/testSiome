package testLambda;

import java.util.ArrayList;
import java.util.List;


public class DeepCopy {
    //基础类型和引用类型的差距
    public static String getJsonStr(String content, List<String> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(tmp ->
        {
            sb.append(content).append(tmp);
        });
        if (sb.length() > 0) {
            // content = "";

            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }


    public static void main(String[] args) {
        String content = "content";

        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");

        String jsonStr = getJsonStr(content, list);
        System.out.println(jsonStr);

    }
}
