package testWChatRobot.test;

public class Test {
    @Deprecated
    public String name = "as";

    public int num;

    public void zhujiedeshiyong() {
        String s = name;
        System.out.println(s);
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int len = startTime.length;
        int count = 0;
        for(int i = 0; i < len; i++){
            if(startTime[i] <= queryTime && queryTime <= endTime[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        t.zhujiedeshiyong();
    }
}
