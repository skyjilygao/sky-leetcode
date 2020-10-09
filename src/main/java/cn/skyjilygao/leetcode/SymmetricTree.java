package cn.skyjilygao.leetcode;

import java.util.*;

/**
 * 题目：给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/symmetric-tree"> 101. 对称二叉树 </a>
 *
 * @author skyjilygao
 * @date 20201009
 */
public class SymmetricTree {
    public static void main(String[] args) {
        Integer[] arr = {1,2,2,3,4,4,3};
//        Integer[] arr = {1,2,2,null,3,null,3};
//        Integer[] arr = {1};
//        Integer[] arr = {1,2,2,2,null,2};
//        Integer[] arr = {5,4,1,null,1,null,4,2,null,2,null};
        TreeNode node = TreeNode.init(arr);
        System.out.println(isSymmetric(node));
//        System.out.println(isSymmetric1(node));
    }

    /**
     * 思路1：递归
     * @param root
     * @return
     */
    public static boolean isSymmetric1(TreeNode root) {
        if(root == null){
            return true;
        }
        return helper(root,root);
    }
    private static boolean helper(TreeNode rootl,TreeNode rootr){
        if(rootl == null && rootr == null){
            return true;
        }
        if(rootl == null || rootr == null){
            return false;
        }
        return rootl.val.equals(rootr.val)  && helper(rootl.left, rootr.right) && helper(rootl.right, rootr.left);
    }

    /**
     * 思路2：迭代
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return helper2(root,root);
    }

    private static boolean helper2(TreeNode rootl,TreeNode rootr){
        // 由于rootl.left 或 rootl.right可能为空，所以使用 LinkedList，而不是 ArrayDeque
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootl);
        queue.offer(rootr);
        while (!queue.isEmpty()){
            rootl = queue.poll();
            rootr = queue.poll();
            if(rootl == null && rootr == null){
                continue;
            }
            if((rootl == null || rootr == null) || !rootl.val.equals(rootr.val)){
                return false;
            }
            queue.offer(rootl.left);
            queue.offer(rootr.right);

            queue.offer(rootl.right);
            queue.offer(rootr.left);
        }
        return true;
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
