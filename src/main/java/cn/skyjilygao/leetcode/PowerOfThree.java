package cn.skyjilygao.leetcode;


/**
 * 题目：给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * <p> 说明：
 * <br>
 * <p>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/power-of-three"> 326. 3的幂 </a>
 *
 * @author skyjilygao
 * @date 20201015
 */
public class PowerOfThree {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(isPowerOfThree(n));
    }

    /**
     * 思路1：暴力
     * <p> 说明：从0开始计算，直到不小于n，最后判断是否跟n相等即可。
     * <br>
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：24 ms, 在所有 Java 提交中击败了7.32%的用户
     * <br> 内存消耗：38.1 MB, 在所有 Java 提交中击败了99.61%的用户
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree1(int n) {
        int a = 0;
        double p = -1;
        while (p < n) {
            p = Math.pow(3, a++);
        }
        return p == n;
    }

    /**
     * 思路2：
     * <p> 说明：对于n的任何“高幂次方” 除以 “低幂次方” 的余数必然等于0
     * <br> 例如：3的10次方 除以 3的7次方 的 余数等于0.
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：19 ms, 在所有 Java 提交中击败了12.52%的用户
     * <br> 内存消耗：38.3 MB, 在所有 Java 提交中击败了94.99%的用户
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree2(int n) {
        // 写法1：
        /*if (n < 0) {
            return false;
        }
        double pow = Math.pow(3, 33);
        return pow % n == 0;*/

        // 写法2：写法1的优化版
        /*return n > 0 && Math.pow(3, 33) % n == 0;*/

        // 写法3：因为只要返回是否是3的幂即可，不需要计算Math.pow(3, 33)。所以，干脆取3的最大幂次方结果计算，这样省去了pow的计算时间
        // 因为int最大值2^32，在这范围3的最大幂次方=3^19=1162261467
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 思路3：基准转换
     * <p> 说明：完全是参考官解才直到还有这种方法，长见识了。
     * <br> 10的n次放结果都是1开头，后面全部都是0.那么其他数字是否也一样呢，答案是肯定的。
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：39 ms, 在所有 Java 提交中击败了5.17%的用户
     * <br> 内存消耗：38.7 MB, 在所有 Java 提交中击败了29.61%的用户
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree3(int n) {
        // 以3为基准后，判断是否为1开头，后面都是0结尾
        return Integer.toString(n, 3).matches("^10$");
    }

    /**
     * 思路4：求指数（幂指数）
     * <p> 说明：完全是参考官解才直到还有这种方法，学习
     * <br> 反向求解幂指数。如果n是3的幂次方，那么n=3^m，所以 m = log3(n)并且m肯定是整数。就可以得出结论，如果m小数部分不为0表示不是3的m次方；反之是3的m次方
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：16 ms, 在所有 Java 提交中击败了67.02%的用户
     * <br> 内存消耗：38.1 MB, 在所有 Java 提交中击败了99.61%的用户
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {
        // log3(n)=log10(n) / log10(3)
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

}
