package cn.skyjilygao.leetcode;

/**
 * 题目：给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p> 说明：两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/hamming-distance"> 461. 汉明距离 </a>
 *
 * @author skyjilygao
 * @date 20201029
 */
public class HammingDistance {
    public static void main(String[] args) {
        int x = 5, y = 40;
        System.out.println(hammingDistance(x, y));
    }

    /**
     * 思路1：异或运算
     * <p> 说明：
     * <p> 1. 异或原理：相同为0，不同为1。那么x 和 y进行异或运算
     * <p> 2. 对异或结果和 数字1进行位与运算，结果等于1这说明当前位是1，然后结果右移1位再次和数字1进行位与运算，统计结果等于1的次数最后返回即可
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：35.3 MB, 在所有 Java 提交中击败了87.52%的用户
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance1(int x, int y) {
        x = x ^ y;
        int c = 0;
        while (x != 0) {
            if ((x & 1) == 1) {
                c++;
            }
            x = x >> 1;
        }
        return c;
    }

    /**
     * 思路2：布赖恩·克尼根算法
     * <p> 说明：当我们在 number 和 number-1 上做 AND 位运算时，原数字 number 的最右边等于 1 的比特会被移除。
     * <p>
     * <p>参考：官方题解：解法3
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：35.2 MB, 在所有 Java 提交中击败了89.74%的用户
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance3(int x, int y) {
        x = x ^ y;
        y = 0;
        while (x != 0) {
            y++;
            x = x & (x - 1);
        }
        return y;
    }
    /**
     * 思路3：java内置方法
     * <p> 说明：
     * <p>
     * <p>参考：官方题解：解法3
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：35.2 MB, 在所有 Java 提交中击败了86.74%的用户
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
