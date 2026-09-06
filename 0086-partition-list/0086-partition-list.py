# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        if not head:
            return head
        
        dummy = head
        ps = ListNode(0,dummy)
        ref = ps
        while dummy and dummy.val < x:
            dummy = dummy.next
            ps = ps.next

        while dummy and dummy.next:
            curr = dummy.next
            if dummy.next.val < x:
                nxt = curr.next
                dummy.next = nxt
                curr.next= ps.next
                ps.next = curr
                ps = ps.next
                
            else:
                dummy = dummy.next

        return ref.next