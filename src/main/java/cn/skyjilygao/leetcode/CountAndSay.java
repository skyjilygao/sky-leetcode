package cn.skyjilygao.leetcode;


/**
 * Leetcode题: <a href="https://leetcode-cn.com/problems/count-and-say"> 38. 外观数列 </a>
 *
 * @author skyjilygao
 * @date 20200923
 */
public class CountAndSay {

    public static void main(String[] args) {
        System.out.println("结果：" + countAndSay(8));
    }


    /**
     * 递归，for嵌套 另一种方式
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String ans = countAndSay(n - 1);
        StringBuffer tmp = new StringBuffer();
        int len = ans.length();
        int s = 0;
        for (int i = 1; i <= len; i++) {
            if (i == len) {
                tmp.append(i - s).append(ans.charAt(s));
            }else if (ans.charAt(s) != ans.charAt(i)) {
                tmp.append(i - s).append(ans.charAt(s));
                s = i;
            }
        }
        return tmp.toString();
    }

    /**
     * 递归，for嵌套
     *
     * @param n
     * @return
     */
    public static String countAndSay1(int n) {
        if (n == 1) {
            return "1";
        }
        String ans = countAndSay1(n - 1);
        StringBuffer tmp = new StringBuffer();
        int len = ans.length();
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (ans.charAt(start) != ans.charAt(i)) {
                tmp.append(i - start).append(ans.charAt(start));
                start = i;
            }
            if (i + 1 == len) {
                tmp.append(i - start + 1).append(ans.charAt(i));
            }
        }
        return tmp.toString();
    }

    /**
     * 暴力，for嵌套
     *
     * @param n
     * @return
     */
    public static String countAndSay0(int n) {
        if (n == 1) {
            return "1";
        }
        String ans = "1";
        for (int i = 2; i <= n; i++) {
            String tmp = "";
            char[] chars = ans.toCharArray();
            int i2 = 0;
            for (int i1 = 0; i1 < chars.length && i2 < chars.length; i1++) {
                char c1 = chars[i1];
                while (i2 < chars.length) {
                    char c2 = chars[i2];
                    if (c1 == c2) {
                        i2++;
                    } else {
                        break;
                    }
                }
                tmp += (i2 - i1) + "" + c1;
                i1 = i2 - 1;
            }
            System.out.println("i=" + i + " : " + tmp);
            ans = tmp;
        }
        return ans;
    }
}
