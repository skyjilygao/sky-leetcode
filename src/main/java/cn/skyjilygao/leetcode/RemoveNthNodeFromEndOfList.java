package cn.skyjilygao.leetcode;

/**
 * 题目：给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <br> 说明：给定的 n 保证是有效的。
 * <p>
 * <p> Leetcode题: <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list"> 19. 删除链表的倒数第N个节点 </a>
 *
 * @author skyjilygao
 * @date 20200925
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
//        int[] arr = {4,5,1,9}; int n = 2;
        int[] arr = {4,5,6,7}; int n = 1;
        // 总的node
        ListNode node = ListNode.init(arr);
        node = removeNthFromEnd(node, n);
        System.out.println(node == null ? "[]" : node.toString());
    }

    /**
     *
     * <p> 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * <p> 内存消耗： 36.9 MB , 在所有 Java 提交中击败了 70.40% 的用户
     *
     * 思路1：1. 因为要删除倒数第n个，所以需要直到head长度len，才能计算除
     * 2. 再重新遍历，删除第（len - n + 1） 个就可以了
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd0(ListNode head, int n) {
        int len = 0;
        ListNode next1 = head;
        while (next1 != null){
            next1 = next1.next;
            len++;
        }
        int i = 0;
        next1 = head;
        while (next1 != null){
            if(i == len - n){
                // 删除倒数第len个。即删除第一个
                if(next1.next == null){
                    return null;
                }
                next1.val = next1.next.val;
                next1.next = next1.next.next;
                break;
            }else if(i == len - n-1){
                // 删除倒数第一，中间部分
                if(next1.next.next == null){
                    next1.next = null;
                }else{
                    next1.next.val = next1.next.next.val;
                    next1.next.next = next1.next.next.next;
                }
                break;
            }
            next1 = next1.next;
            i++;
        }
        return head;
    }

    /**
     *
     * <p> 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * <p> 内存消耗： 37 MB , 在所有 Java 提交中击败了 57.15% 的用户
     *
     * 思路2：由思路1看出，当删除第一个node时需要特殊考虑。那么在整个链表最前加一个前缀节点。就不用考虑那么多了
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode next1 = head;
        while (next1 != null){
            next1 = next1.next;
            len++;
        }
        ListNode preffix = new ListNode(-1);
        preffix.next = head;

        len = len - n;
        next1 = preffix;
        // 再次遍历，找到需要删除的上一个
        while (len > 0){
            len--;
            next1 = next1.next;
        }
        // 把next1.next.next 赋值给 next1.next，达到删除 next1.next 节点效果
        next1.next = next1.next.next;
        return preffix.next;
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
