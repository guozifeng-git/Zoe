import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author admin
 */
public class MergeKLists {
    //输入：lists = [[1,4,5],[1,3,4],[2,6]]
    //输出：[1,1,2,3,4,4,5,6]
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for (ListNode head : lists) {
            if (head != null) {
                queue.add(head);
            }
        }

        while (!queue.isEmpty()){
            ListNode curr = queue.poll();
            prev.next = curr;
            if (curr.next != null){
                queue.add(curr.next);
            }
            prev = prev.next;
        }
        return preHead.next;
    }

}
