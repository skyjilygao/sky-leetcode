package cn.skyjilygao.leetcode;

import java.util.*;

/**
 * 题目：给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <br> 说明：假设一个二叉搜索树具有如下特征：
 * <p> 1. 节点的左子树只包含小于当前节点的数。
 * <p> 2. 节点的右子树只包含大于当前节点的数。
 * <p> 3. 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/validate-binary-search-tree"> 98. 验证二叉搜索树 </a>
 *
 * @author skyjilygao
 * @date 20200930
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
//        Integer[] arr = {2,1,3};
//        Integer[] arr = {2,1,3,0,null,null,2};
//        Integer[] arr = {2,2};
//        Integer[] arr = {10,11,15,null,null,21,20};
        Integer[] arr = {0,-1};
        TreeNode node = TreeNode.init(arr);
        System.out.println(isValidBST(node));
    }

    /**
     * 思路1：递归。左树所有节点值 < 根节点值；右树所有节点值 > 根节点值
     * @param root
     * @return
     */
    public static boolean isValidBST1(TreeNode root) {
        return recursion(root, null, null);
    }

    /**
     *
     * @param root 当前结点
     * @param minVal 最小值
     * @param maxVal 最大值
     * @return
     */
    public static boolean recursion(TreeNode root, Integer minVal, Integer maxVal) {
        if (root == null) {
            return true;
        }
        int val = root.val;

        if (minVal != null && val <= minVal) {
            return false;
        }
        if (maxVal != null && val >= maxVal) {
            return false;
        }

        if(!recursion(root.right, val, maxVal)){
            return false;
        }
        if(!recursion(root.left, minVal, val)){
            return false;
        }
        return true;
    }

    /**
     * 思路2：中序遍历。形成升序序列，只要当前小于等于前一个值返回false
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        Deque<TreeNode> deque  = new ArrayDeque<>();
        double aa = -Double.MAX_VALUE;
        while (!deque.isEmpty() || root != null){
            // 先push左树
            while (root!= null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            if(root.val <=aa){
                return false;
            }
            aa = root.val;
            root = root.right;
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
            // 中序遍历
            if (root != null) {
                System.out.print(" " + root.val);
                print(root.left);
                print(root.right);
            }
            return "";
        }
    }
}