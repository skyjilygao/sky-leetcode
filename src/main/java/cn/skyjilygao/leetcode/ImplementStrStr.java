package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <br> 说明：虽然indexOf效果相同，但是我们需要实现这样的方法，而不是直接使用
 *
 *  <p> Leetcode题: <a href="https://leetcode-cn.com/problems/implement-strstr"> 28. 实现 strStr() </a>
 *
 * @author skyjilygao
 * @date 20200923
 */
public class ImplementStrStr {
    public static void main(String[] args) {
        System.out.println(strStr("hello","oe"));
//        System.out.println(strStr("hello","sdfsdf"));
    }


    /**
     * <p> 执行用时： 1 ms , 在所有 Java 提交中击败了 78.49% 的用户
     * <p> 内存消耗： 37.3 MB , 在所有 Java 提交中击败了 78.24% 的用户
     *
     * <p> 思路2：先找到第一个相等的char，然后再依次比较之后的字符。
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle == null){
            return -1;
        }
        if(needle.length() == 0){
            return 0;
        }
        int lenHay = haystack.length();
        int lenNeed = needle.length();
        char[] hayChars = haystack.toCharArray();
        char[] needChars = needle.toCharArray();

        for (int i = 0; i < lenHay - lenNeed + 1; i++) {
            int j = 0;
            while (j < lenNeed && hayChars[i] == needChars[j]){
                j++;
                if(j == lenNeed){
                    return i - lenNeed + 1;
                }
                i++;
            }
            i = i - j;
        }
        return -1;
    }

    /**
     * 思路1：使用string的截取方法。但效率低，因为substring返回时new String对象
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr0(String haystack, String needle) {
        int lenHay = haystack.length();
        int lenNeed = needle.length();
        for (int i = 0; i < lenHay - lenNeed + 1; i++) {
            String substring = haystack.substring(i, i + lenNeed);
            if(needle.equals(substring)){
                return i;
            }
        }
        return -1;
    }
}
