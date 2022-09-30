package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Light {
    static Map<int[], Integer> map = new HashMap();
    static Stack<int[]> stack = new Stack();

    public static int flipLights(int n, int presses) {
        int[] light = new int[n];
        for (int i = 0; i < n; i++) {
            light[i] = 1;
        }

        return map.size();
    }

    public static void trans(int num){
        if(stack.empty()){
            return;
        }
        int[] pop = stack.pop();

    }

    public static void main(String[] args) {
        int n = 5;
        int presses = 1;

    }
}
