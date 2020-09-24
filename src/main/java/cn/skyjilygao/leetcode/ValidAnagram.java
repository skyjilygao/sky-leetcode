package cn.skyjilygao.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/valid-anagram"> 242. 有效的字母异位词 </a>
 *
 * @author skyjilygao
 * @date 20200923
 */
public class ValidAnagram {
    public static void main(String[] args) {
//        String s = "anagram", t = "nagaram";
        String s = "rat", t = "car";
        System.out.println(isAnagram(s, t));
    }

    /**
     * <p> 执行用时： 3 ms , 在所有 Java 提交中击败了 86.50% 的用户
     * <p> 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 22.80% 的用户
     *
     * <p> 思路：数组equals比较
     * <p> 说明: 字符串都是小写字母组成，使用临时数组存在每个字符出现次数。
     * Times: O(m + n)
     * Spaces: O(m + n)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        // 不能使用：sChars.equals(tChars)，因为这种比较只是比较引用地址，而非内容
        return Arrays.equals(sChars, tChars);
    }

    /**
     * <p> 执行用时： 2 ms , 在所有 Java 提交中击败了 99.90% 的用户
     * <p> 内存消耗： 38.7 MB , 在所有 Java 提交中击败了 92.14% 的用户
     *
     * <p> 思路: 暴力：使用数组
     * <p> 说明: 字符串都是小写字母组成，使用临时数组存在每个字符出现次数。
     * Times: O(m + n)
     * Spaces: O(m + n)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] tmpArr = new int[26];
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            tmpArr[sChars[i] - 'a']++;
        }
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            tmpArr[tChars[i] - 'a']--;
            // 因为长度相等，那么相同字母必然出现 < 0
            if(tmpArr[tChars[i] - 'a'] < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * <p> 执行用时： 2 ms , 在所有 Java 提交中击败了 99.90% 的用户
     * <p> 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 60.93% 的用户
     *
     * <p> 思路: 暴力：使用数组
     * <p> 说明: 字符串都是小写字母组成，使用临时数组存在每个字符出现次数。
     * Times: O(m + n)
     * Spaces: O(m + n)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] tmpArr = new int[26];
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            tmpArr[sChars[i] - 'a']++;
        }
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            tmpArr[tChars[i] - 'a']--;
        }
        // 再遍历：只要有一个不为0即返回false。
        boolean tt = true;
        for (int i : tmpArr) {
            if(i != 0){
                return false;
            }
        }
        return tt;
    }

    /**
     * <p> 执行用时： 2 ms , 在所有 Java 提交中击败了 99.90% 的用户
     * <p> 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 60.93% 的用户
     *
     * <p> 思路: 使用数组
     * <p> 说明: 字符串都是小写字母组成，使用临时数组存在每个字符出现次数。
     * Times: O(m + n)
     * Spaces: O(m + n)
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram11(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] tmpArr = new int[26];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            tmpArr[sChars[i] - 'a']++;
            tmpArr[tChars[i] - 'a']--;
        }

        // 再遍历：只要有一个不为0即返回false。
        boolean tt = true;
        for (int i : tmpArr) {
            if(i != 0){
                return false;
            }
        }
        return tt;
    }

    /**
     * <p> 执行用时： 16 ms , 在所有 Java 提交中击败了 20.34% 的用户
     * <p> 内存消耗： 40 MB , 在所有 Java 提交中击败了 5.01% 的用户
     *
     * <p> 思路: 原始暴力解法：使用Map
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram0(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character, Integer> map = new HashMap();
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            map.put(sChars[i], map.getOrDefault(sChars[i], 0) + 1);
        }
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            Integer integer = map.getOrDefault(tChars[i], 0);
            if(integer == 0){
                return false;
            }
            if(integer > 0){
                integer--;
                map.put(tChars[i], integer);
            }
            if(integer == 0){
                map.remove(tChars[i]);
            }
        }
        return map.size() == 0;
    }
}
