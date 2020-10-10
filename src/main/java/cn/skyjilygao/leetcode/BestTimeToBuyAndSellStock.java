package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p> 说明：
 * <br> 1. 注意：你不能在买入股票前卖出股票。
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock"> 121. 买卖股票的最佳时机 </a>
 *
 * @author skyjilygao
 * @date 20201010
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
//        int[] p = new int[]{7,1,5,3,6,4};
//        int[] p = new int[]{1,4,2};
        int[] p = new int[]{2, 1,4};
        System.out.println(maxProfit(p));
    }

    /**
     * 思路1：线性遍历
     * <p> 多次执行耗时：
     * <br> 执行用时：1 ms, 在所有 Java 提交中击败了99.00%的用户
     * <br> 内存消耗：38.7 MB, 在所有 Java 提交中击败了63.89%的用户
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        // 默认最佳
        int max = 0;
        // 初始最小值
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if(min > prices[i]){
                min = prices[i];
            }else if(prices[i] - min > max){
                max =prices[i] - min;
            }
        }
        return max;
    }
}
