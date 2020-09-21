package cn.skyjilygao.leetcode;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/rotate-image"> 48. 旋转图像 </a>
 * @author skyjilygao
 * @date 20200921
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printImage("原始数组:", matrix);
        rotate(matrix);
        printImage("旋转结果:", matrix);

    }

    /**
     * <p> 1. 原地以图形垂直中轴线为对称轴，进行翻转
     * <p> 2. 以右上-左下对角线为对称轴，进行翻转
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
//        printImage("原始图片：",matrix);
        int len = matrix.length;
//        System.out.println("开始水平翻转：");
        for (int i = 0; i < (len >> 1); ++i) {
            for (int j = 0; j < len; ++j) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[j][len - 1 - i];
                matrix[j][len - 1 - i] = tmp;
//                printImage("水平翻转 i="+i+", j="+j+"：", matrix);
            }
//            printImage("水平翻转 i="+i+"：", matrix);
        }

//        System.out.println("开始对角线翻转：");
        for (int i = 0; i < len-1; ++i) {
            // len - i -1 翻转到最后的前一个即可
            for (int j = 0; j < len - i -1; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][len - 1 - i];
                matrix[len - 1 - j][len - 1 - i] = tmp;
//                printImage("对角线翻转 i="+i+", j="+j+"：", matrix);
            }
//            printImage("对角线翻转 i="+i+"：", matrix);
        }
    }

    /**
     * <p> 1. 先以左下角-右上角对角线进行翻转
     * <p> 2. 再以上下中间位置水平线进行上下翻转
     * @param matrix
     */
    public static void rotate0(int[][] matrix) {
        printImage("原始图片：",matrix);
        int len = matrix.length;
        System.out.println("开始对角线翻转：");
        for (int i = 0; i < len-1; ++i) {
            // len - i -1 翻转到最后的前一个即可
            for (int j = 0; j < len - i -1; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][len - 1 - i];
                matrix[len - 1 - j][len - 1 - i] = tmp;
                printImage("对角线翻转 i="+i+", j="+j+"：", matrix);
            }
            printImage("对角线翻转 i="+i+"：", matrix);
        }

        System.out.println("开始水平翻转：");
        for (int i = 0; i < (len >> 1); ++i) {
            for (int j = 0; j < len; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] = tmp;
                printImage("水平翻转 i="+i+", j="+j+"：", matrix);
            }
            printImage("水平翻转 i="+i+"：", matrix);
        }
    }

    private static void printImage(String msg, int[][] matrix) {
        System.out.println(msg);
        for (int[] ints : matrix) {
            String r = "";
            for (int anInt : ints) {
                if("".equalsIgnoreCase(r)){
                    r += anInt;
                }else{
                    r += ", "+anInt;
                }
            }
            System.out.println(r);
        }
    }
}
