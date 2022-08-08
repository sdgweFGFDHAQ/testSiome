package testLua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.ast.Str;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void onlyOne(String luaStr){
        Globals globals = JsePlatform.standardGlobals();
        LuaValue load = globals.load(luaStr);
        load.call();
    }

    public static void event(){
        String luaPath = "src/main/java/testLua/One.lua";
        Globals globals = JsePlatform.standardGlobals();

        globals.loadfile(luaPath).call();

        LuaValue start = globals.get(LuaValue.valueOf("start"));
        start.call();

        LuaValue ttk = globals.get(LuaValue.valueOf("ttk"));
        ttk.call();

        LuaValue attack = globals.get(LuaValue.valueOf("attack"));
        String res = attack.call(LuaValue.valueOf("quickly attack")).toString();
        System.out.println(res);
    }

    public static void cook(){
        String luaPath = "src/main/java/testLua/One.lua";
        Globals globals = JsePlatform.standardGlobals();

        globals.loadfile(luaPath).call();

        LuaValue skills = globals.get(LuaValue.valueOf("attack"));
        Map<String, Object> map = new HashMap<>();
        map.put("封尘绝念斩", "释放大招，伤害高的不谈");
        map.put("点燃",369);
        String end = skills.call(LuaValue.valueOf(String.valueOf(map))).toString();
        System.out.println(end);
    }

    public static void main(String[] args) {
        String luaStr = "print 'hello lua'";
        onlyOne(luaStr);

        event();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    event();
                    cook();
                }
            });
            thread.start();
        }

    }
}
