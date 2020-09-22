package cn.skyjilygao.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/intersection-of-two-arrays-ii"> 350. 两个数组的交集 II </a>
 * @author skyjilygao
 * @date 20200922
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums = new int[]{9};
        int[] nums2 = new int[]{1,9,90,10, 1};
        /*int[] nums = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};*/
        // 排序 + 双指针
        int[] ints = intersect(nums, nums2);
        // 利用map
//        int[] ints = intersect1(nums, nums2);
        // 利用排序
//        int[] ints = intersect0(nums, nums2);
        for (int num : ints) {
            System.out.print(num + "\t");
        }
    }

    /**
     *
     * 打败87%多
     * 排序 + 双指针
     * Times: O(max(nlogn, mlogm, n, m))
     * Spaces: O(1)
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] aa = new int[Math.min(nums1.length, nums2.length)];
        int n1i = 0, n2i = 0, aai = 0;
        while (n1i < nums1.length && n2i < nums2.length){
            if(nums1[n1i] < nums2[n2i]){
                n1i++;
            }else if(nums1[n1i] == nums2[n2i]){
                aa[aai++] = nums1[n1i];
                n1i++;
                n2i++;
            }else{
                n2i++;
            }
        }
        return Arrays.copyOfRange(aa, 0, aai);
    }

    /**
     * 打败60%多
     * 使用map
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // 把长度较短的数组放到map中
        Map<Integer, Integer> nums1Map = new HashMap<>();
        for (int i : nums1) {
            Integer ii = nums1Map.getOrDefault(i, 0);
            nums1Map.put(i, ++ii);
        }
        int len1 = nums1.length, len2 = nums2.length;
        int[] aa = new int[Math.min(len1, len2)];
        int aai = 0;
        for (int i : nums2) {
            Integer ii = nums1Map.getOrDefault(i, 0);
            // 没找到
            if(ii == 0){
                continue;
            }
            aa[aai++] = i;
            // map中找到的数量减1
            --ii;
            // 减1后是否还大于0。0表示num1数组中只出现1次，大于0表示出现多次
            if(ii > 0){
                nums1Map.put(i, ii);
                continue;
            }
            // 减1后等于0，0表示num1数组中只出现1次。需要移除
            nums1Map.remove(i);
            // map已经没有了，防止nums2很长，继续遍历。没必要
            if(nums1Map.size() == 0){
                break;
            }
        }
        return Arrays.copyOfRange(aa, 0, aai);
    }

    /**
     * 打败60%多
     * 先排序
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect0(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length, len2 = nums2.length;
        int[] aa = new int[Math.min(len1, len2)];
        // aa赋值下标
        int aai = 0;
        // nums2遍历起始下标
        int n2i = 0;
        for (int i = 0; i < nums1.length; i++) {
            int n1 = nums1[i];
            for (int i1 = n2i; i1 < nums2.length; i1++) {
                if(n1 == nums2[i1]){
                    n2i = i1+1;
                    // 找到后赋值，并推出内循环
                    aa[aai++] = n1;
                    break;
                }
            }
        }
        return Arrays.copyOfRange(aa, 0, aai);
    }
}
