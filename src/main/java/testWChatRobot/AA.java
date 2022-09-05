package testWChatRobot;

import java.util.HashMap;
import java.util.Map;

public class AA {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("add", 23);
        String bb = String.valueOf(map.get("ewdf"));
        System.out.println(bb);
    }
}
