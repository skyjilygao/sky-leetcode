package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p> 说明：
 * <br>
 * <p> 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * <br>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/missing-number"> 268. 丢失的数字 </a>
 *
 * @author skyjilygao
 * @date 20201026
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 0, 1};
        System.out.println(missingNumber(nums));
    }

    /**
     * 思路1：异或运算
     * <p> 说明：异或原理：相同为0，不同为1.
     * <p> 1. 将每个元素与对应下标进行异或。
     * 如果元素与下标相同则为0；
     * 如果元素与下标不同异或结果为1，但同时必然会出现元素与下标颠倒情况（下标值为元素，元素值为下标）再次异或是也为1。那么再与前一次异或结果再次异或则为0
     * <p> 2. 而缺失的元素始终只出现一次，则整个遍历完成后异或最终结果必然是缺少的元素值
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：38.8 MB, 在所有 Java 提交中击败了93.04%的用户
     *
     * @param nums
     * @return
     */
    public static int missingNumber1(int[] nums) {
        int n = nums.length;
        int m = n;
        for (int i = 0; i < n; i++) {
            m = m ^ (i ^ nums[i]);
        }
        return m;
    }

    /**
     * 思路2：求和
     * <p> 说明：因为{0...n}的和=n*(n+1)/2；而nums数又是0到n之间的数值，那么sum(n) - sum(nums)就等于nums缺失的数值
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：38.5 MB, 在所有 Java 提交中击败了98.05%的用户
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;

        // 数组求和方法1：
        int m = 0;
        for (int i = 0; i < n; i++) {
            m += nums[i];
        }
        // 数组求和方法2：原本以为通过stream求和可能会快，但时间挺慢的的。xswl
//        return sum - Arrays.stream(nums).sum();
        return sum - m;
    }

}
