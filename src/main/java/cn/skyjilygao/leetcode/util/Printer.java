package cn.skyjilygao.leetcode.util;

import java.util.StringJoiner;

/**
 * 打印工具类
 */
public class Printer {
    /**
     * 打印int 矩阵：二维数组
     * @param matrix
     */
    public static void matrix(int[][] matrix) {
        int len = matrix.length;
        System.out.println("[");
        for (int i = 0; i < len; i++) {
            int[] nums = matrix[i];
            StringJoiner joiner = new StringJoiner(",","[","]");
            for (int num : nums) {
                joiner.add(num+"");
            }
            System.out.println(joiner.toString());
        }
        System.out.println("]");
    }
}
