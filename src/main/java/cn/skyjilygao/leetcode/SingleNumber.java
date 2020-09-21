package cn.skyjilygao.leetcode;

import java.util.Arrays;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/single-number/">136. 只出现一次的数字</a>
 * 知识点：异或运算（运算符：⊕）
 * <p> 异或运算有以下三个性质</p>
 * <p> 1. 任何数和 0 做异或运算，结果仍然是原来的数，即 a ⊕ 0 = a
 * <p> 2. 任何数和其自身做异或运算，结果是 00，即 a ⊕ a = 0
 * <p> 3. 异或运算满足交换律和结合律，即 a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ (a ⊕ a) = b ⊕ 0 = b
 *
 * @author skyjilygao
 * @date 20200921
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 5, 5, 4};
        // 异或
        System.out.println(singleNumber(nums));
        // 暴力
        System.out.println(singleNumber0(nums));
    }

    /**
     * 利用异或运算：Times:O(n) Space: O(1)
     * <p> 例如：nums = new int[]{1,4,5,5,4};
     * <p> 异或运算步骤如下：
     * <p> 第一个或上一个运算结果 | 下一个元素 | 异或结果 | 10进制结果
     * <p> 0001 | 0100 | 0101 | 5
     * <p> 0101 | 0101 | 0000 | 0
     * <p> 0000 | 0101 | 0101 | 5
     * <p> 0101 | 0100 | 0001 | 1
     * <p> 最后异或结果：为1就是正确答案
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int a = nums[0];
        for (int i = 1; i < nums.length; i++) {
            a = a ^ nums[i];
        }
        return a;
    }

    /**
     * 原始版 击败 30.60% 的用户
     *
     * @param nums
     * @return
     */
    public static int singleNumber0(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
            i++;
        }
        return nums[nums.length - 1];
    }
}
