package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p> 说明：
 * <br> 1. 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/house-robber"> 198. 打家劫舍 </a>
 *
 * @author skyjilygao
 * @date 20201012
 */
public class HouseRobber {
    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3,1};
//        int[] nums = new int[]{2,7,9,3,1};
        int[] nums = new int[]{2,1,1,2};
        System.out.println(rob(nums));
    }

    /**
     * 思路1：动态规划
     * <p> 说明：
     * <br> 每偷一家金额 + 上上次总金额之和，再跟上次总金额比较取最大值。返回该最大值
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：36.5 MB, 在所有 Java 提交中击败了9.58%的用户
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }

        int a = nums[0], b=Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int t = b;
            b = Math.max(a + nums[i], b);
            a = t;
        }
        return b;
    }
}
