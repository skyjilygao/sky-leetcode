package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组
 * <p> 说明：答案中不可以包含重复的三元组。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/3sum"> 15. 三数之和 </a>
 *
 * @author skyjilygao
 * @date 20201029
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
        System.out.println("]");
    }

    /**
     * 思路：排序 + 双指针
     * <p> 说明：对于有序数组：nums[i] + nums[i1] + nums[i2] = 0 那么nums[i2]必然大于0,换角度思考，i2可以从len - 1开始遍历，每次i2--。
     * 因为从i1+1开始遍历时元素有可能出现小于0.而从len -1遍历就肯定不会，如果出现说明整个数组没有符合条件的数据。所以可以减少遍历次数
     *
     * <p> 1. 避免出现重复，当nums[i] == nums[i-1]时continue
     * <p> 多次执行耗时：
     * <br> 执行用时：23 ms, 在所有 Java 提交中击败了84.39%的用户
     * <br> 内存消耗：42.8 MB, 在所有 Java 提交中击败了50.37%的用户
     *
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int i2 = nums.length - 1;
            for (int i1 = i + 1; i1 < nums.length - 1; i1++) {
                if (i1 > i + 1 && nums[i1] == nums[i1 - 1]) {
                    continue;
                }
                while (nums[i] + nums[i1] + nums[i2] > 0 && i2 > i1 && nums[i2] >= 0) {
                    i2--;
                }
                if (i1 == i2) {
                    break;
                }
                if (nums[i] + nums[i1] + nums[i2] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[i1]);
                    list.add(nums[i2]);
                    lists.add(list);
                    // 使用Stream效率太慢
//                    lists.add(Stream.of(nums[i], nums[i1], nums[i2]).collect(Collectors.toList()));
                }

            }
        }
        return lists;
    }
}
