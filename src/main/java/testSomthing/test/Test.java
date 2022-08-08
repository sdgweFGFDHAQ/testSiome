package testSomthing.test;

public class Test {
    @Deprecated
    public String name = "as";

    public int num;

    public void zhujiedeshiyong() {
        String s = name;
        System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        t.zhujiedeshiyong();
    }
}
