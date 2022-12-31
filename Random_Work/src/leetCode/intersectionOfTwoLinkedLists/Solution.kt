package leetCode.intersectionOfTwoLinkedLists

/*class Solution {
	/**
	 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
	 *
	 * Write a solution that runs in O(m + n) time and use only O(1) memory
	 */
	fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
		var a = headA
		var b = headB
		while (a != b) {
			a = if (a == null) headB else a.next
			b = if (b == null) headA else b.next
		}
		return a
	}
}*/
class Solution

fun Solution.getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
	val headASet = hashSetOf<ListNode>()
	var current = headA
	while (current != null) {
		headASet += current
		current = current.next
	}
	val result = null
	current = headB
	while (current != null) current = if (current in headASet) return current else current.next
	return result
}

data class ListNode(var value: Int, var next: ListNode? = null)
