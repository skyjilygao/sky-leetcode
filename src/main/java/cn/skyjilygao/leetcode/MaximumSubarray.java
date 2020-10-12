package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p> 说明：
 * <br> 1. 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/maximum-subarray"> 53. 最大子序和 </a>
 *
 * @author skyjilygao
 * @date 20201012
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 思路1：贪心算法
     * <p> 说明：
     * <br> 如果上次sum 小于0，对于最终和没有意义；如果上次sum 大于0，对于最终和有意义，需要将当前num追加到上次sum中。
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了95.96%的用户
     * <br> 内存消耗：38.9 MB, 在所有 Java 提交中击败了44.67%的用户
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int lastSum = 0;
        for (int num : nums) {
            // if-else
            if(lastSum >= 0){
                lastSum += num;
            }else {
                lastSum = num;
            }

            // if-else替换成三目运算也是可以的
            /*lastSum = lastSum >= 0 ? lastSum + num : num;
            ans = Math.max(ans, lastSum);*/
        }
        return ans;
    }

    /**
     * 思路2：动态规划
     * <p> 说明：
     * <br> 如果上次sum 小于0，对于最终和没有意义；如果上次sum 大于0，对于最终和有意义，需要将当前num追加到上次sum中。
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int lastSum = 0;
        for (int num : nums) {
            lastSum = Math.max(lastSum + num, num);
            ans = Math.max(ans, lastSum);
        }
        return ans;
    }
    /**
     * 思路3：分治
     * <p> 说明：计算整个nums的连续子数组的最大和，就是将nums分为3个子数组：[left, mid],[mid + 1, right],[left, right]。最后取3者中最大和即可
     * <br>
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了95.96%的用户
     * <br> 内存消耗：38.3 MB, 在所有 Java 提交中击败了99.59%的用户
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private static int helper(int[] nums, int left, int right){
        if(left == right){
            return nums[left];
        }
        int mid = (left + right) / 2;
        // 左区间最大和
        int leftSumMax = helper(nums, left, mid);
        // 右区间最大和
        int rightSumMax = helper(nums, mid + 1, right);

        // 计算中间区间的最大和
        // 计算左区间至中间mid位置最大和
        int leftMidSumMax = Integer.MIN_VALUE;
        int leftMidSum = 0;
        for (int i = mid; i >= left; i--) {
            leftMidSum += nums[i];
            leftMidSumMax = Math.max(leftMidSum, leftMidSumMax);
        }

        // 计算中间mid位置+1至右区间最大值
        int rightMidSumMax = nums[mid + 1];
        int rightMidSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            rightMidSum += nums[i];
            rightMidSumMax = Math.max(rightMidSum, rightMidSumMax);
        }

        // 中间区间最大和
        int mcrosssum = leftMidSumMax + rightMidSumMax;

        // 3者中最大和就是整个nums的最大和
        return Math.max(mcrosssum, Math.max(rightSumMax, leftSumMax));
    }
}
