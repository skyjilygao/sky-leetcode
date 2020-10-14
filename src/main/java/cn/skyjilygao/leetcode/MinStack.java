package cn.skyjilygao.leetcode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈
 * <p> 说明：
 * <br> 1. push(x) —— 将元素 x 推入栈中。
 * <br> 2. pop() —— 删除栈顶的元素。
 * <br> 3. top() —— 获取栈顶元素
 * <br> 4. getMin() —— 检索栈中的最小元素。
 * <p> 提示：pop、top 和 getMin 操作总是在 非空栈 上调用。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/min-stack"> 155. 最小栈 </a>
 *
 * @author skyjilygao
 * @date 20201014
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        int[] data = new int[]{-2, 0, -3};
        for (int datum : data) {
            obj.push(datum);
        }

        obj.push(3);
        obj.pop();
        System.out.println(obj.top());
        System.out.println("最小值：" + obj.getMin());

    }


    /**
     * 数据队列
     */
    Deque<Integer> dataq;
    /**
     * 对应 {@link MinStack#dataq} 的最小值队列
     */
    Deque<Integer> minq;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        dataq = new LinkedList<>();
        minq = new LinkedList<>();
    }

    public void push(int x) {
        // 同进
        dataq.push(x);
        // 对于minq，每次push最小值在上面
        if (minq.peek() == null) {
            minq.push(x);
        } else {
            minq.push(Math.min(minq.peek(), x));
        }
    }

    public void pop() {
        // 同出
        dataq.pop();
        minq.pop();
    }

    public int top() {
        return dataq.peek();
    }

    public int getMin() {
        return minq.peek();
    }

}
