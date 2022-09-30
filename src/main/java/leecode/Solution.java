package leecode;

import java.util.*;

public class Solution {
    public static int[] finalPrices(int[] prices) {
        int len = prices.length;
        int[] res = prices.clone();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (prices[i] >= prices[j]) {
                    res[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return res;
    }

    public static int findMinDifference(List<String> timePoints) {
        int minutes = 0;
        Collections.sort(timePoints);
        System.out.println(timePoints);

        for (int i = 0; i < timePoints.size(); i++) {
            timePoints.get(i).split(":");
        }
        return minutes;
    }

    public int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        int left1 = 0, right1 = 0;

        if (node.left != null && node.val == node.left.val) {
            left1 = left + 1;
        }
        if (node.right != null && node.val == node.right.val) {
            right1 = right + 1;
        }

        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }

    public static boolean isFlipedString(String s1, String s2) {
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.substring(len - i - 1).equals(s2.substring(0, i)) && s1.substring(0, i).equals(s2.substring(len - i - 1))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "standerpower";
        String b = "powerstander";
        boolean flipedString = isFlipedString(a, b);
        System.out.println(flipedString);
    }
}
