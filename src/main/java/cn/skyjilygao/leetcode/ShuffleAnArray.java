package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 题目：打乱一个没有重复元素的数组。
 * <p> 说明：
 * <br> 1. // 以数字集合 1, 2 和 3 初始化数组。
 * <br> int[] nums = {1,2,3};
 * <br> Solution solution = new Solution(nums);
 * <br> 2. // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * <br> solution.shuffle();
 * <br> 3. // 重设数组到它的初始状态[1,2,3]。
 * <br>  solution.reset();
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/shuffle-an-array"> 384. 打乱数组 </a>
 *
 * @author skyjilygao
 * @date 20201014
 */
public class ShuffleAnArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Solution solution = new Solution(nums);
        // 打乱
        int[] shuffle = solution.shuffle();
        System.out.println(printArr(shuffle));
        // 重置
        int[] reset = solution.reset();
        System.out.println(printArr(reset));
    }

    /**
     * 思路1：集合
     * <p> 说明：
     * <br> 从集合中随机取放入返回数组中，同时
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：111 ms, 在所有 Java 提交中击败了36.30%的用户
     * <br> 内存消耗：46.5 MB, 在所有 Java 提交中击败了99.76%的用户
     */
    static class Solution1{
        private int[] nums;
        private int[] rtnums;
        public Solution1(int[] nums) {
            this.nums = nums;
            this.rtnums = new int[nums.length];
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            rtnums = nums;
            nums = nums.clone();
            return rtnums;
        }


        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num : nums) {
                list.add(num);
            }
            for (int i = 0; i < nums.length; i++) {
                int r = new Random().nextInt(list.size());
                rtnums[i] = list.get(r);
                list.remove(r);
            }
            return rtnums;
        }
    }

    /**
     * 思路2：随机交换（也就是官方的洗牌算法）
     * <p> 说明：
     * <br> 随机位置跟当前遍历下标交换
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：97 ms, 在所有 Java 提交中击败了98.99%的用户
     * <br> 内存消耗：46.7 MB, 在所有 Java 提交中击败了97.76%的用户
     */
    static class Solution{
        private int[] nums;
        private int[] rtnums;
        Random random = new Random();
        public Solution(int[] nums) {
            this.nums = nums;
            this.rtnums = nums.clone();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            rtnums = nums;
            nums = nums.clone();
            return rtnums;
        }


        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            int len = rtnums.length;
            for (int i = 0; i < rtnums.length; i++) {
                int p = random.nextInt(len - i)+i;
                int t = rtnums[i];
                rtnums[i] = rtnums[p];
                rtnums[p] = t;
            }
            return rtnums;
        }

    }
    /**
     * 打印数组
     * @param nums
     * @return
     */
    private static String printArr(int[] nums){
        if(nums == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int num : nums) {
            if(sb.length() > 0){
                sb.append(", ");
            }
            sb.append(num);
        }
        return sb.toString();
    }

}
