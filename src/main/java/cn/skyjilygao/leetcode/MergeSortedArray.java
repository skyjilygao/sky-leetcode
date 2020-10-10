package cn.skyjilygao.leetcode;

import java.util.Arrays;

/**
 * 题目：给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p> 说明：1. 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * <br> 2. 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/merge-sorted-array"> 88. 合并两个有序数组 </a>
 *
 * @author skyjilygao
 * @date 20201010
 */
public class MergeSortedArray {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{0}; int len1 = 0; int[] nums2 = new int[]{1}; int len2 = 1;
        int[] nums1 = new int[]{1,2,0,0,0,0}; int len1 = 2; int[] nums2 = new int[]{1,3,5,6}; int len2 = 4;
//        int[] nums1 = new int[]{0,0}; int len1 = 0; int[] nums2 = new int[]{1,3}; int len2 = 2;
//        int[] nums1 = new int[]{2,0}; int len1 = 1; int[] nums2 = new int[]{1}; int len2 = 1;
//        int[] nums1 = new int[]{1,0}; int len1 = 1; int[] nums2 = new int[]{2}; int len2 = 1;
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}; int len1 = 3; int[] nums2 = new int[]{}; int len2 = 0;
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}; int len1 = 3; int[] nums2 = new int[]{2, 5, 6}; int len2 = 3;
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0}; int len1 = 3; int[] nums2 = new int[]{4, 5, 6}; int len2 = 3;
        merge(nums1, len1, nums2, len2);
        for (int n : nums1) {
            System.out.print(n+"\t");
            
        }
    }

    /**
     * 思路1：临时数组 + 双指针
     *
     * <p> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <p> 内存消耗：39 MB, 在所有 Java 提交中击败了44.55%的用户
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int len  = nums1.length;
        int[] numsTmp = new int[len];
        int i1,i2,it;
        i1=i2=it=0;
        while (i1<m && i2 <n) {
            numsTmp[it++] = nums1[i1] < nums2[i2] ? nums1[i1++] : nums2[i2++];
        }

        if(i1 < m){
            System.arraycopy(nums1, i1, numsTmp, it, m - i1);
        }
        if(i2 < n){
            System.arraycopy(nums2, i2, numsTmp, it, n - i2);
        }
        System.arraycopy(numsTmp, 0, nums1, 0 , len);
    }

    /**
     * 思路2：合并 + 排序
     * <p> 执行用时：1 ms, 在所有 Java 提交中击败了23.75%的用户
     * <p> 内存消耗：38.7 MB, 在所有 Java 提交中击败了90.99%的用户
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
    /**
     * 思路3：双指针 + 倒序遍历
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len  = nums1.length;
        int i1 = m - 1;
        int i2 = n - 1;
        int i = len - 1;
        while (i2 >=0 && i1 >= 0){
            nums1[i--] = nums1[i1] < nums2[i2] ? nums2[i2--] : nums1[i1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, i2 + 1);
    }
}
