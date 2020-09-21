package cn.skyjilygao.leetcode;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/move-zeroes"> 283. 移动零 </a>
 * @author skyjilygao
 * @date 20200921
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = new int[]{1,0,2,5};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }

    /**
     * 把不为0的往前移，实现把0的往后移动
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int tmp = nums[i];
                nums[i] = nums[a];
                nums[a] = tmp;
                a++;
            }
        }
    }

    /**
     * 把0往后移动
     * @param nums
     */
    public static void moveZeroes0(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        int tmpa = -1;
        for (int i = 0; i < nums.length; i++) {
            int a;
            if(tmpa > -1){
                a = tmpa;
            }else{
                a = i;
            }
            while (a < nums.length - 1 && nums[a] == 0){
                a++;
            }
            // 避免全为0时还交换值
            if(nums[a] != 0 && a !=i){
                tmpa = a;
                int tmp = nums[i];
                nums[i] = nums[a];
                nums[a] = tmp;
            }
        }
    }
}
