
public class Test {

    public static int lc1464(int[] nums){
       int a = -1, b = -2;
       int len = nums.length;
       for(int i = 0; i < len; i++){
           if(nums[i] > a){
               b = a;
               a = nums[i];
           }
           if(nums[i] > b){
               b = nums[i];
           }
       }
       return (a - 1) * (b - 1);
    }


    public static void main(String[] args) {

        String a = new String("1");
        //a.intern();
        String b = "1";
        System.out.println(a == b);

        String c = new String("1") + new String("1");
        c.intern();
        String d = "11";
        System.out.println(c.equals(d));

        int[] nums = {1, 5, 4, 5};
        System.out.println(lc1464(nums));
    }
}
