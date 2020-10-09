package cn.skyjilygao.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p> 说明：
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/binary-tree-level-order-traversal"> 102. 二叉树的层序遍历 </a>
 *
 * @author skyjilygao
 * @date 20201009
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
//        Integer[] arr = {1,2,2,3,4,4,3};
//        Integer[] arr = {3,9,20,null,null,15,7};
        Integer[] arr = {3};
        TreeNode node = TreeNode.init(arr);
        System.out.println(levelOrder(node));
    }

    /**
     *
     * <p> 执行用时：1 ms, 在所有 Java 提交中击败了92.93%的用户
     * <p> 内存消耗：39.2 MB, 在所有 Java 提交中击败了23.14%的用户
     * 思路1：广度优先搜索
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> clist = new ArrayList<>();
            // 当size=0时，表示当前层遍历完成
            while (size > 0){
                TreeNode node = queue.poll();
                clist.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            list.add(clist);
        }
        return list;
    }

    /**
     * 树节点
     */
    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer x) {
            val = x;
        }

        public static TreeNode init(Integer[] arr) {
            //新建一个list集合，将数据变为各个节点
            List<TreeNode> list = new ArrayList<>();
            for (Integer tempdata : arr) {
//            if(tempdata != null){
                list.add(new TreeNode(tempdata));
//            }
            }
            TreeNode root = list.get(0);//将第一个元素设置为根节点
            /**
             * 利用构建完全二叉树的方式构建
             */
        /*for(int i=0;i<list.size()/2;i++){
            if((i*2+1)<list.size()){
                list.get(i).left =list.get(i*2+1);
            }
            if((i*2+2)<list.size()){

                list.get(i).right=list.get(i*2+2);
            }
        }*/

            /**
             * 从上到下，左到右
             */
            int rooti = 0;
            for (int i = 1; i <= list.size() - 1; i += 2) {

                if (list.get(i) != null && list.get(i).val != null) {
                    list.get(rooti).left = list.get(i);
                }
                if(i+1<list.size()){
                    if (list.get(i + 1) != null && list.get(i + 1).val != null) {
                        list.get(rooti).right = list.get(i + 1);
                    }
                }

                rooti++;

                while (list.get(rooti) == null || list.get(rooti).val == null) {
                    rooti++;
                }
            }
            return root;
        }

        public static String print(TreeNode root) {
            // 先序遍历
            /*if (root != null) {
                System.out.print(" " + root.val);
                print(root.left);
                print(root.right);
            }*/
            // 中序遍历
            if (root != null) {
                print(root.left);
                System.out.print(" " + root.val);
                print(root.right);
            }
            return "";
        }
    }
}
