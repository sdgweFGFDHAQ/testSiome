package testNode;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NodeTest {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public int res = 0;

    public int deepestLeavesSum(TreeNode root) {

        if (root.left == null && root.right == null) {
            int len = 1;
            return len;
        }
        if(root.left != null){
            res = Math.max(res, deepestLeavesSum(root.left) + 1);
        }
        if(root.right != null){
            res = Math.max(res, deepestLeavesSum(root.right)) + 1;
        }

        return res;
    }


    public int deepestLeavesSum2(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }

}

