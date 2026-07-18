class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find list length and tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k %= length;
        if (k == 0) {
            return head;
        }

        // Make the list circular
        tail.next = head;

        // Find the new tail: (length - k)th node
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}