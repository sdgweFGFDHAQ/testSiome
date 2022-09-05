package testRegular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

    public static void main(String[] args) {
        String str = "a";
        Pattern compile = Pattern.compile(str);
        Matcher matcher = compile.matcher("gyacdfsvbaasd");
        System.out.println(matcher);
        boolean b = matcher.find();
        int start = matcher.start();
        System.out.println(b);
        System.out.println(start);
    }
}
