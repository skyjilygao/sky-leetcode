package cn.skyjilygao.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * Leetcode题:  <a href="https://leetcode-cn.com/problems/contains-duplicate/"> 217. 存在重复元素 </a>
 * @author skyjilygao
 * @date 20200918
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,5,6,7};
        // 使用双枢快速排序
        System.out.println(containsDuplicate(nums));
        // 使用set集合
        System.out.println(containsDuplicate1(nums));
        // 原始方法
        System.out.println(containsDuplicate0(nums));
    }

    /**
     * 使用 ${@link Arrays#sort(int[])} 双枢快速排序。最坏时间复杂度为O(n * log^n)
     * 时间复杂度：O(n * log^n) 因为双枢快速排序
     * 空间复杂度: O(1) 因为没有用到额外空间
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1]==nums[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * 利用set集合
     * @param nums
     * @return
     */
    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 最原始方法
     * @param nums
     * @return
     */
    public static boolean containsDuplicate0(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
