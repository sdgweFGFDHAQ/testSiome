package testJS;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class invokedJsFile {

    public static void testJSFile() throws ScriptException, NoSuchMethodException, IOException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("javascript");
        engine.eval(readJSFile());
        Invocable inv = (Invocable) engine;
        Object res = inv.invokeFunction("greet");
        System.out.println("=====" + res);
    }

    public static String readJSFile() throws IOException {
        StringBuffer script = new StringBuffer();
        File file = new File("F:\\ideaUprojects\\testSiome\\src\\main\\resources\\testJS\\one.js");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tempString = null;
        while ((tempString = bufferedReader.readLine()) != null) {
            script.append(tempString).append("\n");
        }
        bufferedReader.close();
        fileReader.close();
        return script.toString();
    }

    public static void main(String[] args) throws ScriptException, IOException, NoSuchMethodException {
        testJSFile();
    }
}
