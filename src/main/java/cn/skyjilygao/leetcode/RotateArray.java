package cn.skyjilygao.leetcode;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 189. 旋转数组 题：https://leetcode-cn.com/problems/rotate-array/
 *
 * @author skyjilygao
 * @date 20200918
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7}; int k = 31;
//        int[] nums = new int[]{-1,-100,3,99}; int k = 2;
//        int[] nums = new int[]{-1}; int k = 20;
//        int[] nums = new int[]{1,2}; int k = 3;
//        int[] nums = new int[]{1,2,3,4,5}; int k = 3;
        // v1.3 反转 最优
        rotate(nums, k);
        // v1.2 再优化
        rotate2(nums, k);
        // v1.1 优化v1.0
        rotate1(nums, k);
        // v1.0 最原始，耗时长
        rotate0(nums, k);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 反转：
     * 观察最后的结果，会发现原数组中最后k个元素在最前面k个位置。既然这样就可以经过多次反转得到最后结果。
     * （我这是参考题解写的，哈哈。我相信这种发现肯定是前辈们多次研究发现出来的规律或是捷径）
     * 1. 先把整个数组反转。让最后k个元素先到最前面再说
     * 2. 跟最后的结果比较发现：再把反转后的前k个元素反转，即可得到最后结果的前k个元素的样子；而把length - k个最后元素反转即可得到最后结果的length - k个元素的样子
     * @param nums
     * @param k
     * @version v1.3
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        reserve(nums, 0, len - 1);
        reserve(nums, 0, k -1);
        reserve(nums, k, len - 1);
    }

    /**
     * 反转数据
     * @param nums
     * @param start
     * @param end
     */
    private static void reserve(int[] nums, int start, int end){
        while (start < end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }

    }

    /**
     * 移动一次就整个数组所有元素全部一次。移动k次，那整个数组所有元素都移动k次
     * @param nums
     * @param k
     * @version v1.2
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        int tmp,pre;
        for (int i = 0; i < k; i++) {
            pre = nums[len - 1];
            for (int j = 0; j < len; j++) {
                // 将当前值跟上一个值交换
                tmp = nums[j];
                nums[j] = pre;
                pre = tmp;
            }
        }
    }

    /**
     * 手动一步一步推演，会发现新的下标正好是（原下标 + k）除以数组长度的余数（% nums.length）
     * @param nums
     * @param k
     * @version v1.1
     */
    public static void rotate1(int[] nums, int k) {
        int len = nums.length;
        int[] narr = new int[len];
        for (int i = 0; i < len; i++) {
            int np = ((i + k) % len);
            narr[np] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = narr[i];
        }
    }

    /**
     * 原始解法，耗时长
     * @param nums
     * @param k
     * @version v1.0
     */
    public static void rotate0(int[] nums, int k) {
        int len = nums.length;
        int[] narr = new int[len];
        for (int i = 0; i < len; i++) {
            int np = i + k;
            while (np > len -1){
                int npp = np - (len - 1);
                np = npp <= 0 ? np : npp -1;
            }
            narr[np] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = narr[i];
        }
    }
}
