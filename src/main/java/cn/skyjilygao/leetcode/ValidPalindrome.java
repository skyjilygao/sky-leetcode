package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <br> 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/valid-palindrome"> 125. 验证回文串 </a>
 *
 * @author skyjilygao
 * @date 20200923
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome12("A man, a plan, a canal: Panama"));
    }

    /**
     * 思路：双指针 + StringBuffer
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        StringBuffer sbuf = new StringBuffer();
        for (char aChar : chars) {
            if(Character.isLetterOrDigit(aChar)){
                sbuf.append(Character.toLowerCase(aChar));
            }
        }

        s = sbuf.toString();

        return s.equals(sbuf.reverse().toString());
    }

    /**
     * <p> 执行用时： 5 ms , 在所有 Java 提交中击败了 49.82% 的用户
     * <p> 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 55.19% 的用户
     *
     * <p> 思路：双指针 + StringBuffer
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        char[] chars = s.toCharArray();
        StringBuffer sbuf = new StringBuffer();
        for (char aChar : chars) {
            if(Character.isLetterOrDigit(aChar)){
                sbuf.append(Character.toLowerCase(aChar));
            }
        }

        s = sbuf.toString();
        int start = 0, end = s.length() - 1;
        while (start < end){
            if( s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * <p> 执行用时： 3 ms , 在所有 Java 提交中击败了 93.17% 的用户
     * <p> 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 97.27% 的用户
     *
     * <p> 思路：双指针。相对${@link #isPalindrome11(String)}之前解法来说，sChar，eChar不用每次多赋值。由于在while外面赋值，则需要考虑空串的情况(也就加个判断)
     * @param s
     * @return
     */
    public static boolean isPalindrome12(String s) {
        if(s.length() == 0){
            return true;
        }
        int start = 0, end = s.length() - 1;
        char sChar = s.charAt(start);
        char eChar = s.charAt(end);
        while (start < end){
            if(Character.isLetterOrDigit(sChar) && Character.isLetterOrDigit(eChar)){
                if(Character.toLowerCase(sChar) != Character.toLowerCase(eChar)){
                    return false;
                }
                start++;
                end--;
                sChar = s.charAt(start);
                eChar = s.charAt(end);
            }else if(!Character.isLetterOrDigit(sChar)){
                start++;
                sChar = s.charAt(start);
            }else if(!Character.isLetterOrDigit(eChar)){
                end--;
                eChar = s.charAt(end);
            }
        }
        return true;
    }

    /**
     * <p> 执行用时： 3 ms , 在所有 Java 提交中击败了 93.17% 的用户
     * <p> 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 97.55% 的用户
     *
     * <p> 思路：双指针。相对 ${@link #isPalindrome1(String)} 来说，转换小写放在需要的地方。因为将整个`s`转成小写，耗时
     * @param s
     * @return
     */
    public static boolean isPalindrome11(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end){
            char sChar = s.charAt(start);
            char eChar = s.charAt(end);
            if(Character.isLetterOrDigit(sChar) && Character.isLetterOrDigit(eChar)){
                if(Character.toLowerCase(sChar) != Character.toLowerCase(eChar)){
                    return false;
                }
                start++;
                end--;
            }else if(!Character.isLetterOrDigit(sChar)){
                start++;
            }else if(!Character.isLetterOrDigit(eChar)){
                end--;
            }
        }
        return true;
    }

    /**
     * 思路：双指针。相对 ${@link #isPalindrome0(String)}没有用到额外数组，所以消耗内存好低一些
     * @param s
     * @return
     */
    public static boolean isPalindrome1(String s) {
        s = s.toLowerCase();
        int start = 0, end = s.length() - 1;
        while (start < end){
            char sChar = s.charAt(start);
            char eChar = s.charAt(end);
            if(Character.isLetterOrDigit(sChar) && Character.isLetterOrDigit(eChar)){
                if(sChar != eChar){
                    return false;
                }
                start++;
                end--;
            }else if(!Character.isLetterOrDigit(sChar)){
                start++;
            }else if(!Character.isLetterOrDigit(eChar)){
                end--;
            }
        }
        return true;
    }

    /**
     * 思路：双指针 + 数组
     * @param s
     * @return
     */
    public static boolean isPalindrome0(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int start = 0, end = chars.length - 1;
        while (start < end){
            char sChar = chars[start];
            char eChar = chars[end];
            if(Character.isLetterOrDigit(sChar) && Character.isLetterOrDigit(eChar)){
                if(sChar != eChar){
                    return false;
                }
                start++;
                end--;
            }else if(!Character.isLetterOrDigit(sChar)){
                start++;
            }else if(!Character.isLetterOrDigit(eChar)){
                end--;
            }
        }
        return true;
    }
}
