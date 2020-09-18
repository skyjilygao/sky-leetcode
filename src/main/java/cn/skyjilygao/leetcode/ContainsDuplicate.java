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
        // 使用set集合，先统计所有数据再比较大小
        System.out.println(containsDuplicate2(nums));
        // 使用set集合，是否有重复
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
     * 直接统计不重复后count跟nums长度比较是否相等
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        // 法1：利用set不可重复属性
        /*Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;*/
        // 法2：stream的distinct就是统计不重复的数据集合，count就是集合的大小。
        return Arrays.stream(nums).distinct().count() != nums.length;
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
            // j = i + 1而不是j = 1，因为只需要跟后面毕竟就可以。不用每次都从头开始比较
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
