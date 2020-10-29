package cn.skyjilygao.leetcode;

import cn.skyjilygao.leetcode.util.Printer;

/**
 * 题目：给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p> 说明：一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * <p> 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * <p> 你能想出一个常数空间的解决方案吗？
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/set-matrix-zeroes"> 73. 矩阵置零 </a>
 *
 * @author skyjilygao
 * @date 20201029
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        Printer.matrix(matrix);
    }

    /**
     * 思路1：常数标记法
     * <p> 说明：
     *
     * <p> 隐患：注意常数一定要再矩阵中数据范围之外
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户
     * <br> 内存消耗：40.1 MB, 在所有 Java 提交中击败了78.98%的用户
     *
     * @param matrix
     */
    public static void setZeroes1(int[][] matrix) {
        int consts = Integer.MIN_VALUE + 1;
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            int[] nums = matrix[i];
            for (int i1 = 0; i1 < nums.length; i1++) {
                if(nums[i1] == 0){
                    // 行
                    int a = 0;
//                    nums[a++] = 0;
                    while (a < nums.length){
                        if(a==i1 || nums[a] == 0){
                            a++;
                            continue;
                        }

                        nums[a++] = consts;
                    }
                    // 列
                    a = 0;
//                    matrix[a++][i1] = 0;
                    while (a < len){
                        if(a==i || matrix[a][i1] == 0){
                            a++;
                            continue;
                        }
                        matrix[a++][i1] = consts;
                    }
                }
            }

        }
        for (int i = 0; i < len; i++) {
            int[] nums = matrix[i];
            for (int i2 = 0; i2 < nums.length; i2++) {
                if(matrix[i][i2] == consts){
                    matrix[i][i2] = 0;
                }
            }
        }
    }


    /**
     * 思路2：列行首置0
     * <p> 说明：
     *
     * <p> 参考：官方题解
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户
     * <br> 内存消耗：40.2 MB, 在所有 Java 提交中击败了71.80%的用户
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        int len = matrix.length;
        int colLen = matrix[0].length;
        boolean col = false;

        for (int i = 0; i < len; i++) {
            if(matrix[i][0] == 0){
                col = true;
            }
            for (int i1 = 1; i1 < colLen; i1++) {
                // 将列首，行首置0
                if(matrix[i][i1] == 0){
                    matrix[i][0] = 0;
                    matrix[0][i1] = 0;
                }
            }
        }

        // 再次遍历，如果列首或行首为0，则将当前置0
        // 这里为什么要从第二行第二列开始遍历，因为在之前已经将第一列，第一行的第一个元素置0.如果在这里还从[0][0]开始遍历会导致第一列或第一行全部为0
        for (int i = 1; i < len; i++) {
            for (int i1 = 1; i1 < colLen; i1++) {
                if(matrix[i][0] == 0 || matrix[0][i1] == 0){
                    matrix[i][i1] = 0;
                }
            }
        }
        if(matrix[0][0] == 0){
            for (int i = 0; i < colLen; i++) {
                matrix[0][i] = 0;
            }
        }
        if(col){
            for (int i = 0; i < len; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
