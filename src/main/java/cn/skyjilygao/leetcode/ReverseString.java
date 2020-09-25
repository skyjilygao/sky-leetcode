package cn.skyjilygao.leetcode;


/**
 * 题目：编写一个函数，其作用是将输入的字符串反转过来。
 * <br> 说明：不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/reverse-string"> 344. 反转字符串 </a>
 *
 * @author skyjilygao
 * @date 20200925
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
        StringBuffer sb = new StringBuffer();
        for (char c : s) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(c);
        }
        System.out.println(sb.toString());
    }


    /**
     * 思路1：双指针 + 临时变量
     * <p> 执行用时： 1 ms , 在所有 Java 提交中击败了 99.97% 的用户
     * <p> 内存消耗： 45.3 MB , 在所有 Java 提交中击败了 81.52% 的用户
     * @param s
     */
    public static void reverseString0(char[] s) {
        if (null == s || s.length == 0) {
            return;
        }
        int l = 0;
        int r = s.length - 1;
        char tmp;
        while (l < r) {
            tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * 思路1（优化版）：双指针 + 异或
     * <p> 执行用时： 1 ms , 在所有 Java 提交中击败了 99.97% 的用户
     * <p> 内存消耗： 45.5 MB , 在所有 Java 提交中击败了 47.77% 的用户
     * @param s
     */
    public static void reverseString01(char[] s) {
        // 右边下标
        int r = null == s || s.length == 0 ? 0 : s.length - 1;
        // 左边下标
        int l = 0;
        while (l < r) {
            s[l] = (char)(s[l] ^ s[r]);
            s[r] = (char)(s[l] ^ s[r]);
            s[l] = (char)(s[l] ^ s[r]);
            l++;
            r--;
        }
    }
    /**
     * 思路2：双指针 + 递归
     * <p> 执行用时： 2 ms , 在所有 Java 提交中击败了 15.17% 的用户
     * <p> 内存消耗： 47 MB , 在所有 Java 提交中击败了 5.07% 的用户
     * @param s
     */
    public static void reverseString(char[] s) {
        if (null == s || s.length == 0) {
            return;
        }
        recursive(s, 0, s.length - 1);
    }

    /**
     * 递归
     * @param s 数组
     * @param l 左下标
     * @param r 右下标
     */
    private static void recursive(char[] s, int l, int r){
        if(l >= r){
            return;
        }
        s[l] = (char)(s[l] ^ s[r]);
        s[r] = (char)(s[l] ^ s[r]);
        s[l] = (char)(s[l] ^ s[r]);
        recursive(s, ++l, --r);
    }
}
