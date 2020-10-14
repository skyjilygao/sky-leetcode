package cn.skyjilygao.leetcode;

/**
 * 题目：统计所有小于非负整数 n 的质数的数量。
 * <p> 说明：
 * <br>
 * <p>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/count-primes"> 204. 计数质数 </a>
 *
 * @author skyjilygao
 * @date 20201014
 */
public class CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    /**
     * 思路1：暴力
     * <p> 说明：质数还有一个特点，就是它总是等于 6x-1 或者 6x+1，其中 x 是大于等于1的自然数
     * <br> 本题解完全参考：https://blog.csdn.net/afei__/article/details/80638460
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：231 ms, 在所有 Java 提交中击败了14.64%的用户
     * <br> 内存消耗：35.3 MB, 在所有 Java 提交中击败了99.23%的用户
     * @param n
     * @return
     */
    public static int countPrimes1(int n) {
        if(n <=1){
            return 0;
        }
        int aa = 0;
        for(int i = 2; i <n; i++){
            aa=isPrime(i) ? aa+1:aa;
        }
        return aa;
    }
    public static boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路2：埃拉托斯特尼筛法
     * <p> 说明：
     * <br> 本题解完全从这里复制的：<a href="https://leetcode-cn.com/problems/count-primes/solution/java8msai-la-tuo-si-te-ni-shai-fa-by-kevin_hwang/">Java，8ms，优化版埃拉托斯特尼筛法</a>
     * <br> 给出要筛数值的范围n，找出以内的素数。
     * 先用2去筛，即把2留下，把2的倍数剔除掉；再用下一个质数，也就是3筛，把3留下，把3的倍数剔除掉；
     * 接下去用下一个质数5筛，把5留下，把5的倍数剔除掉；不断重复下去....
     * 具体见<a href="https://baike.baidu.com/item/%E5%9F%83%E6%8B%89%E6%89%98%E6%96%AF%E7%89%B9%E5%B0%BC%E7%AD%9B%E6%B3%95">百度百科-埃拉托斯特尼筛法</a>
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：8 ms, 在所有 Java 提交中击败了97.46%的用户
     * <br> 内存消耗：37.2 MB, 在所有 Java 提交中击败了68.87%的用户
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int result = 0, sqrt_n = (int) Math.sqrt(n);
        // 初始化默认值都为 false，为质数标记
        // 用true false表示n以内n(i)数是否为质数
        boolean[] b = new boolean[n];
        // 如果大于 2 则一定拥有 2 这个质数
        if (2 < n) {
           result++;
        }
        // 从 3 开始遍历，且只遍历奇数
        for (int i = 3; i < n; i += 2) {
            // 是质数
            if (!b[i]) {
                //不大于根号n
                if (i <= sqrt_n){
                    // 将当前质数的奇数倍都设置成非质数标记 true
                    for (int j = i; i * j < n; j += 2){
                        b[i * j] = true;
                    }
                }
                // 质数个数 +1
                result++;
            }
        }
        return result;
    }
}
