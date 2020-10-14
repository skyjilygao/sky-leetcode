package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p> 说明：
 * <br> 1. 如果 n 是3的倍数，输出“Fizz”；
 * <br> 2. 如果 n 是5的倍数，输出“Buzz”；
 * <br> 3. 如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * <p>
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/fizz-buzz"> 412. Fizz Buzz </a>
 *
 * @author skyjilygao
 * @date 20201014
 */
public class FizzBuzz {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(16));
    }

    /**
     * 思路1：暴力
     * <p> 说明：
     * <br> 直接遍历，遇到这添加到list
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：97 ms, 在所有 Java 提交中击败了98.99%的用户
     * <br> 内存消耗：46.7 MB, 在所有 Java 提交中击败了97.76%的用户
     * @param n
     * @return
     */
    public static List<String> fizzBuzz1(int n) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i<=n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                list.add("FizzBuzz");
            } else if(i % 3 == 0){
                list.add("Fizz");
            } else if(i % 5 == 0){
                list.add("Buzz");
            } else{
                list.add(""+i);
            }
        }
        return list;
    }

    /**
     * 思路2：拼接
     * <p> 说明：
     * <br>
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：9 ms, 在所有 Java 提交中击败了8.39%的用户
     * <br> 内存消耗：40 MB, 在所有 Java 提交中击败了55.01%的用户
     * @param n
     * @return
     */
    public static List<String> fizzBuzz2(int n) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i<=n; i++){
            StringBuffer sb = new StringBuffer();
            if(i % 3 == 0){
                sb.append("Fizz");
            }
            if(i % 5 == 0){
                sb.append("Buzz");
            }
            if(sb.length() == 0){
                sb.append(""+i);
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * 思路3：映射
     * <p> 说明：
     * <br>
     *
     * <p> 多次执行耗时：
     * <br> 执行用时：13 ms, 在所有 Java 提交中击败了5.58%的用户
     * <br> 内存消耗：40.1 MB, 在所有 Java 提交中击败了39.50%的用户
     * @param n
     * @return
     */
    public static List<String> fizzBuzz(int n) {
        Map<Integer, String> map = new HashMap();
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        List<String> list = new ArrayList<>();
        for(int i = 1; i<=n; i++){
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if(i % entry.getKey() == 0){
                    sb.append(entry.getValue());
                }
            }
            if(sb.length() == 0){
                sb.append(""+i);
            }
            list.add(sb.toString());
        }
        return list;
    }
}
