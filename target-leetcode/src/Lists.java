import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Lists {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode fast = prev;
        ListNode slow = prev;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return prev.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null && headB != null) {
            if (!set.add(headA)) {
                return headA;
            }
            if (!set.add(headB)) {
                return headB;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        for (int i = 0; i < (left - 1); i++) {
            prev = prev.next;
        }
        ListNode rightNode = prev;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }
        ListNode leftNode = prev.next;
        ListNode curr = rightNode.next;

        prev.next = null;
        rightNode.next = null;

        reverseList(leftNode);
        prev.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }
}

