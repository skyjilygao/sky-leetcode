package cn.skyjilygao.leetcode;

import java.util.Stack;

/**
 * 题目：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p> 说明：有效字符串需满足：
 * <br> 1. 左括号必须用相同类型的右括号闭合。
 * <br> 2. 左括号必须以正确的顺序闭合。
 * <br> 3. 注意空字符串可被认为是有效字符串
 * <br>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/valid-parentheses"> 20. 有效的括号 </a>
 *
 * @author skyjilygao
 * @date 20201023
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "([])";
        System.out.println(isValid(s));
    }

    /**
     *
     * 思路：栈
     * <p> 说明：利用栈的后进先出特性，当前i个字母与栈最后一个元素匹配时，则进行stack.pop()。当遍历完成后，栈为空则说明有效
     * <p>
     * <p> 多次执行耗时：
     * <br> 执行用时：2 ms, 在所有 Java 提交中击败了77.08%的用户
     * <br> 内存消耗：36.3 MB, 在所有 Java 提交中击败了98.76%的用户
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if(s == null || s.length() == 0 || s.length() % 2 == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        stack.push(s.charAt(0));
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if(stack.size() > 0 && stack.peek() == getL(c)){
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.size() == 0;
    }

    private static char getL(char left){
        switch (left){
            case ')': return '(';
            case ']': return '[';
            case '}': return '{';
            default: return 'a';
        }
    }
}
