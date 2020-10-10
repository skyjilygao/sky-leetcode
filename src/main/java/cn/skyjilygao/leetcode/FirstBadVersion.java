package cn.skyjilygao.leetcode;

/**
 * 题目：假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p> 说明：1. 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <br> 2. 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/first-bad-version"> 278. 第一个错误的版本 </a>
 *
 * @author skyjilygao
 * @date 20201010
 */
public class FirstBadVersion {
    // 第一个错误版本
    private static int badVersion;
    public static void main(String[] args) {
        int n = 5, version = 4;
        badVersion = version;
        System.out.println(firstBadVersion(n));
    }

    /**
     * 思路1：二分查找
     * <p> 多次执行耗时：
     * <br> 执行用时：16 ms, 在所有 Java 提交中击败了98.85%的用户
     * <br> 内存消耗：35.4 MB, 在所有 Java 提交中击败了86.52%的用户
     * @param n
     * @return
     */
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 思路2：二分查找 + 递归
     * <p> 多次执行耗时：
     * <br> 执行用时：16 ms, 在所有 Java 提交中击败了98.85%的用户
     * <br> 内存消耗：35.2 MB, 在所有 Java 提交中击败了97.41%的用户
     * @param n
     * @return
     */
    public static int firstBadVersion2(int n) {
        return helper(1, n);
    }
    private static int helper(int left, int right){
        if(left > right){
            return left;
        }
        int mid = left + (right - left) / 2;
        if(isBadVersion(mid)){
            right = mid - 1;
        }else{
            left = mid + 1;
        }
        return helper(left, right);
    }
    /**
     * 思路3：线性查找
     * <p> 多次执行耗时：
     * <br> 超时
     * @param n
     * @return
     */
    public static int firstBadVersion3(int n) {
        for (int i = 1; i <= n; i++) {
            if(isBadVersion(i)){
                return i;
            }
        }
        return n;
    }
    /**
     * 是否为错误版本
     * @param v
     * @return
     */
    private static boolean isBadVersion(int v){
        return badVersion == v;
    }
}
