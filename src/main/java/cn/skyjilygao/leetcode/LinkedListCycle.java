package cn.skyjilygao.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：给定一个链表，判断链表中是否有环。
 * <br> 说明：如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/linked-list-cycle"> 141. 环形链表 </a>
 *
 * @author skyjilygao
 * @date 20200928
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        int[] arr = {3,2,0,-4,2,0,-4,2,0,-4,2,0,-4};
        ListNode node = ListNode.init(arr);
        System.out.println(hasCycle1(node));
    }

    /**
     * <p> 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * <p> 内存消耗： 39 MB , 在所有 Java 提交中击败了 57.93% 的用户
     *
     * 思路2：双指针。first每次都1步，second每次走2步。那么，如果有环肯定存在second至少有一次等于first。（好比2个人在环形跑道上比赛，快的人肯定至少一次会追上慢的人）
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode first = head;
        ListNode second = head.next;
        while (first != second){
            if(first == null || second == null){
                return false;
            }
            first = first.next;
            second = second.next.next;
        }
        return true;
    }

    /**
     * <p> 执行用时： 5 ms , 在所有 Java 提交中击败了 16.80% 的用户
     * <p> 内存消耗： 39.6 MB , 在所有 Java 提交中击败了 5.79% 的用户
     *
     * 思路1：利用set集合保存每个node，当存在时则存在环，否则不存在环
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }

    /**
     * 单链表
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            ListNode node = this;
            while (node != null){
                if(sb.length() > 0){
                    sb.append(", ");
                }
                sb.append(node.val);
                node = node.next;
            }
            return sb.toString();
        }

        /**
         * 初始化链表
         * @param arr
         * @return
         */
        public static ListNode init(int[] arr){
            ListNode node = new ListNode(arr[0]);
            ListNode next;
            for (int i = 1; i < arr.length; i++) {
                next = node;
                while (next.next != null){
                    next = next.next;
                }
                next.next = new ListNode(arr[i]);
            }
            return node;
        }

    }
}
