package cn.skyjilygao.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 题目：请判断一个链表是否为回文链表。
 * <br> 说明：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/palindrome-linked-list"> 234. 回文链表 </a>
 *
 * @author skyjilygao
 * @date 20200928
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
//        int[] arr = {};
        int[] arr = {1,2,2,1,1};
//        int[] arr = {-129};
        ListNode node = ListNode.init(arr);
        System.out.println(isPalindrome(node));
        System.out.println(ListNode.print(node));

    }

    /**
     * <p> 执行用时： 4 ms , 在所有 Java 提交中击败了 27.73% 的用户
     * <p> 内存消耗： 42.8 MB , 在所有 Java 提交中击败了 12.93% 的用户
     *
     * 思路3：集合 + 双指针。简单粗暴，遍历放到集合中，再双指针遍历逐个判断
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // 因为无法得知head长度，不能使用数组
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            if(!list.get(i).equals(list.get(j))){
                return false;
            }
        }
        return true;
    }

    private static ListNode pNode;
    /**
     * <p> 执行用时： 2 ms , 在所有 Java 提交中击败了 53.80% 的用户
     * <p> 内存消耗： 44.5 MB , 在所有 Java 提交中击败了 5.01% 的用户
     *
     *
     * 思路2：看了官解，启发的。递归的性质从内到外打印，所以可以从内开始依次跟head比较
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        pNode = head;
        return recursively(head);
    }

    private static boolean recursively(ListNode node){
        if(node == null){
            return true;
        }
        if(!recursively(node.next)){
            return false;
        }
        // 第一次比较：第一个节点值pNode.val 跟 最后的节点值比较。
        if(pNode.val != node.val){
            return false;
        }
        pNode = pNode.next;
        return true;
    }

    /**
     * <p> 执行用时： 1 ms , 在所有 Java 提交中击败了 99.84% 的用户
     * <p> 内存消耗： 41.6 MB , 在所有 Java 提交中击败了 52.05% 的用户
     *
     * 思路1：将后半部分翻转，同时翻转后的后半部分和整个链表，逐个比较。
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode first = head;
        ListNode second = head.next;
        while (second != null && second.next != null){
            second = second.next.next;
            first = first.next;
        }
        second = first.next;
        first = head;
        // 翻转second
        ListNode secondRevs = reverse(second);
        boolean ans = true;
        while (secondRevs != null){
            if(first.val != secondRevs.val){
                ans = false;
                break;
            }
            first = first.next;
            secondRevs = secondRevs.next;
        }
        // 再翻转回来.保证head里恢复原来的顺序。这里很奇怪，翻转前head少，翻转后没有赋head，head居然恢复了
        reverse(secondRevs);

        return ans;
    }

    private static ListNode reverse(ListNode node){
        ListNode secondRevs = null;
        ListNode ll2;
        while (node != null){
            ll2 = node;
            node = node.next;
            ll2.next = secondRevs;
            secondRevs = ll2;
        }
        return secondRevs;
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
         * 打印链表
         * @param node
         * @return
         */
        public static String print(ListNode node){
            if(node == null){
                return "[]";
            }
            StringBuffer sb = new StringBuffer();
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
            if(arr == null || arr.length == 0){
                return null;
            }
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
