package cn.skyjilygao.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/first-unique-character-in-a-string"> 387. 字符串中的第一个唯一字符 </a>
 *
 * @author skyjilygao
 * @date 20200923
 */
public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
//        String str = "leetcode";
        String str = "lovoveleetcodez";
//        String str = "dddccdbba";
        System.out.println(firstUniqChar1(str));
    }


    /**
     * 效率比使用数组要高.
     * 因为都是小写字母，所以数组大小最大26。最后获取索引，那只能遍历s了都是一样
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] arr = new int[26];
        for (int i = 0; i < chars.length; i++) {
            arr[chars[i] - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(arr[chars[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
    /**
     * 效率比数组，map的低。
     * <p> 虽然只使用一层for，但是indexOf, lastIndexOf 各使用了for。相当使用for多层嵌套</p>
     *
     * @param s
     * @return
     */
    public static int firstUniqChar3(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(chars[i]) == s.lastIndexOf(chars[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 超时
     *
     * @param s
     * @return
     */
    public static int firstUniqChar2(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            boolean f = false;
            for (int j = 0; j < len; j++) {
                if(i == j){
                    continue;
                }
                if(s.charAt(i) == s.charAt(j)){
                    f = true;
                    continue;
                }
            }
            if(!f){
                return i;
            }
        }
        return -1;
    }


    /**
     * 利用数组，效率比map要高
     * <p> Times: O(n)
     * <p> Spaces: O(n)
     *
     * @param s
     * @return
     */
    public static int firstUniqChar1(String s) {
        char[] cc = new char[123];
        int p = -1;
        for (int i = 0; i < s.length(); i++) {
            cc[s.charAt(i)] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
            if(cc[s.charAt(i)] == 1){
                return i;
            }
        }
        return p;
    }
    /**
     * 利用map
     * <p> Times: O(n)
     * <p> Spaces: O(n)
     *
     * @param s
     * @return
     */
    public static int firstUniqChar0(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int p = -1;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1){
                return s.indexOf(entry.getKey());
            }
        }
        return p;
    }
}
