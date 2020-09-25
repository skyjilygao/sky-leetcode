package cn.skyjilygao.leetcode;

/**
 * 题目：请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 * <br> 说明：链表至少包含两个节点。<br> 链表中所有节点的值都是唯一的。<br> 给定的节点为非末尾节点并且一定是链表中的一个有效节点。 <br> 不要从你的函数中返回任何结果。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/delete-node-in-a-linked-list"> 237. 删除链表中的节点 </a>
 *
 * @author skyjilygao
 * @date 20200925
 */
public class DeleteNodeInALinkedList {
    public static void main(String[] args) {
        int[] arr = {4,5,1,9};
        // 总的node
        ListNode node = initListNode(arr);
        // 待删除的node
        ListNode delNode = node.next.next;
        deleteNode(delNode);

        printListNode(node);
    }

    /**
     * 思路：将当前的val改成下一个node的val，当前的next改成下一个的next
     * @param node 需要删除的节点
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 初始化链表
     * @param arr
     * @return
     */
    private static ListNode initListNode(int[] arr){
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

    /**
     * 打印链表
     * @param node
     */
    private static void printListNode(ListNode node){
        StringBuffer sb = new StringBuffer();
        while (node != null){
            if(sb.length() > 0){
                sb.append(", ");
            }
            sb.append(node.val);
            node = node.next;
        }
        System.out.println(sb.toString());
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
    }
}
