package cn.skyjilygao.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p> 说明：罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <br> I             1
 * <br> V             5
 * <br> X             10
 * <br> L             50
 * <br> C             100
 * <br> D             500
 * <br> M             1000
 * <br>  例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <br> 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <br> 1. I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * <br> 2. X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * <br> 3. C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <br>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/roman-to-integer/"> 13. 罗马数字转整数 </a>
 *
 * @author skyjilygao
 * @date 20201016
 */
public class RomanToInteger {
    public static void main(String[] args) {
        // 1994 MCMXCIV 1000 + (1000 - 100) + (100 - 10) + (5 - 1)
        // 1000 - 100 + 1000 -10 + 100 -1+5
        String s = "MCMXCIV";
//        String s = "IV";
//        String s = "III";
        System.out.println(romanToInt(s));
    }

    /**
     *
     * 思路1：挨个计算 + map映射
     * <p> 说明：当前R跟前一个L比较: R > L则将sum - L；当R < L则 sum + L. 计算后，将R = L. 遍历完成后sum += R。
     * <br> 例1：1994 MCMXCIV = 1000 + (1000 - 100) + (100 - 10) + (5 - 1) = 1000 - 100 + 1000 - 10 + 100 - 1 + 5 = 1994
     * <br> 例2：3 III = 1 + 1 + 1 = 3
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：5 ms, 在所有 Java 提交中击败了68.53%的用户
     * <br> 内存消耗：38.6 MB, 在所有 Java 提交中击败了96.99%的用户
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Integer preVal = getVal(s.charAt(0));
//        Integer preVal = RomanIntEnum.valueOf(String.valueOf(s.charAt(0))).getVal();
//        Integer preVal = map.get(s.charAt(0));
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            Integer curVal = getVal(s.charAt(i));
//            Integer curVal = RomanIntEnum.valueOf(String.valueOf(s.charAt(i))).getVal();
//            Integer curVal = map.get(s.charAt(i));
            if(curVal > preVal){
                sum -= preVal;
            }else{
                sum += preVal;
            }
            preVal = curVal;
        }
        sum += preVal;
        return sum;
    }

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了68.53%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了99.53%的用户
     * @param c
     * @return
     */
    private static int getVal(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    /**
     *
     * <br> 执行用时：5 ms, 在所有 Java 提交中击败了68.53%的用户
     * <br> 内存消耗：38.6 MB, 在所有 Java 提交中击败了96.99%的用户
     */
    static Map<Character, Integer> map = new HashMap<>();
    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了49.73%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了80.01%的用户
     */
    enum RomanIntEnum{
        I('I',1),
        V('V', 5),
        X('X', 10),
        L('L', 50),
        C('C', 100),
        D('D', 500),
        M('M', 1000),
        ;
        private char c;
        private int val;

        RomanIntEnum(char c, int v) {
            this.c = c;
            this.val = v;
        }

        public int getVal() {
            return val;
        }
    }

}
