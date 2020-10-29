package cn.skyjilygao.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p> 说明：在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/pascals-triangle"> 118. 杨辉三角 </a>
 *
 * @author skyjilygao
 * @date 20201029
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        int n = 2;
        List<List<Integer>> lists = generate(n);
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
        System.out.println("]");
    }

    /**
     * 思路1：遍历
     * <p> 说明：
     * <p> 1.
     * <p> 多次执行耗时：
     * <br> 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * <br> 内存消耗：36.5 MB, 在所有 Java 提交中击败了40.28%的用户
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        // 初始化第一个
        lists.add(new ArrayList<>());
        lists.get(0).add(1);
        // 也可以这样初始化：利用Stream
//        lists.add(Stream.of(1).collect(Collectors.toList()));

        for (int i = 1; i < numRows; i++) {
            // 上一行数据
            List<Integer> list = lists.get(i - 1);
            // 本行数据
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            for (int i1 = 1; i1 < i; i1++) {
                // 上一行的左上(i1 - 1) 与 上一行的右上(i1)之和
                newList.add(list.get(i1 - 1) + list.get(i1));
            }
            newList.add(1);
            lists.add(newList);
        }
        return lists;
    }

}
