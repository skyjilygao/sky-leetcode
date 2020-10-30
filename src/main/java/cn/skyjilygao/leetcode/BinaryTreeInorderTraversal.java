package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：给定一个二叉树，返回它的中序 遍历。
 * <p> 说明：
 * <p>
 * <p> 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal"> 94. 二叉树的中序遍历 </a>
 *
 * @author skyjilygao
 * @date 20201030
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Integer[] arr = {1, null, 2, 3};
//        Integer[] arr = {3,9,20,null,null,15,7};
//        Integer[] arr = {3};
        TreeNode node = TreeNode.init(arr);
        System.out.println(inorderTraversal(node));
    }

    /**
     * 思路1：中序遍历：队列
     * <p> 说明：
     * <p> 1.
     * <p>
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了45.48%的用户
     * <br> 内存消耗：36.8 MB, 在所有 Java 提交中击败了85.14%的用户
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> q = new LinkedList<>();
        while (root != null || !q.isEmpty()) {
            while (root != null) {
                q.push(root);
                root = root.left;
            }
            root = q.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    /**
     * 思路2：中序遍历：Morris莫里斯算法迭代
     * <p> 说明：
     * <p> 1.
     * <p>
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：36.9 MB, 在所有 Java 提交中击败了73.66%的用户
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode pre = null;
        while (root != null) {
            if (root.left == null) {
                // 左边为空，则将当前结点值加到list中，root=右结点
                list.add(root.val);
                root = root.right;
            } else {
                // 左结点不为空
                pre = root.left;
                // 遍历找到左结点的最右结点
                while (pre.right != null){
                    pre = pre.right;
                }
                // 将当前结点加到左结点的最右结点的右结点上。此时，树结构发生变化
                pre.right = root;

                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            }
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
                if (i + 1 < list.size()) {
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

        /**
         * 中序遍历：递归
         *
         * @param root
         * @return
         */
        public static String inorderTraversalByRecursive(TreeNode root) {
            // 中序遍历
            if (root != null) {
                System.out.print(" " + root.val);
                inorderTraversalByRecursive(root.left);
                inorderTraversalByRecursive(root.right);
            }
            return "";
        }

        /**
         * 中序遍历：迭代器
         *
         * @param root
         * @return
         */
        public static String inorderTraversalByIterator(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list.toString();
            }
            Deque<TreeNode> q = new LinkedList<>();
            while (root != null || !q.isEmpty()) {
                while (root != null) {
                    q.push(root);
                    root = root.left;
                }
                root = q.pop();
                list.add(root.val);
                root = root.right;
            }
            return list.toString();
        }
    }


    /**
     * 中序遍历：Morris莫里斯算法迭代
     * @param root
     * @return
     */
    public static String inorderTraversalByIteratorByMorris(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode pre = null;
        while (root != null) {
            if (root.left == null) {
                // 左边为空，则将当前结点值加到list中，root=右结点
                list.add(root.val);
                root = root.right;
            } else {
                // 左结点不为空
                pre = root.left;
                // 遍历找到左结点的最右结点
                while (pre.right != null){
                    pre = pre.right;
                }
                // 将当前结点加到左结点的最右结点的右结点上。此时，树结构发生变化
                pre.right = root;

                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            }
        }
        return list.toString();
    }
}
