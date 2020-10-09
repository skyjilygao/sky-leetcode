package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p> 说明：本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree"> 108. 将有序数组转换为二叉搜索树 </a>
 *
 * @author skyjilygao
 * @date 20201009
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Integer[] arr = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(arr));
    }

    /**
     * 思路：递归
     * <p> 说明：
     * <p> 1. 因为数组有序则说明一棵树中序遍历的结果。那么中间的位置即为根结点。中间位置左边就是左树，右边则是右树。
     * <p> 2. 同理，中间位置左边中间位置为左树的根结点；右边中间位置为右树的根结点
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(Integer[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private static TreeNode helper(Integer[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);
        return node;
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

        @Override
        public String toString() {
            return print(this);
        }
    }
}
