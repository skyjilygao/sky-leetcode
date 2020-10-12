package cn.skyjilygao.leetcode;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目：给定一个二叉树，找出其最大深度。
 * <br> 说明：二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。叶子节点是指没有子节点的节点。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree"> 104. 二叉树的最大深度 </a>
 *
 * @author skyjilygao
 * @date 20200929
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
//        Integer[] arr = {0};
//        Integer[] arr = {1,null,3};
//        Integer[] arr = {3,9,20,null,null,15,7};
//        Integer[] arr = {1,2,3,4,null,null,5};
        Integer[] arr = {0, 2, 4, 1, null, 3, -1, 5, 1, null, 6, null, 8};
//        Integer[] arr = {1,2,3,4,5};
        TreeNode node = TreeNode.init(arr);
        System.out.println(maxDepth(node));
        System.out.println(TreeNode.print(node));
    }

    /**
     * 思路1：深度优先搜索 + 递归。每个树深度=max（左树深度，右树深度） + 1
     *
     * @param root
     * @return
     */
    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    /**
     * 思路2：深度优先搜索 + stack。利用栈特性`后进先出`，每次push结点的同时都记录当前树的深度。在取出时比较跟上一个结点深度比较，取最大值
     *
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> dpStack = new Stack<>();
        // push root节点，深度=1
        stack.push(root);
        dpStack.push(1);
        int heigh = 0;
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            Integer pop1 = dpStack.pop();
            // 读取当前节点深度跟上一次深度比较，取最大
            heigh = Math.max(heigh, pop1);
            // 每push一次多记录当前节点深度=上一次深度 + 1
            if (pop.left != null) {
                stack.push(pop.left);
                dpStack.push(pop1 + 1);
            }
            if (pop.right != null) {
                stack.push(pop.right);
                dpStack.push(pop1 + 1);
            }
        }
        return heigh;
    }

    /**
     * 思路3：广度优先搜索，队列。遍历队列层数(树的层)就是树的深度
     * 利用队列性质(先进先出)。获取当前队列大小进行遍历，遍历是有子树addLast。再次遍历
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            // 当前队列大小：遍历时也就是相当取前 size 个元素
            int size = queue.size();
            while (size > 0) {
                // poll是first 取
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            // 树的当前层遍历完成，+1
            ans++;
        }
        return ans;
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
            for (int i = 1; i < list.size() - 1; i += 2) {

                if (list.get(i) != null && list.get(i).val != null) {
                    list.get(rooti).left = list.get(i);
                }
                if (list.get(i + 1) != null && list.get(i + 1).val != null) {
                    list.get(rooti).right = list.get(i + 1);
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